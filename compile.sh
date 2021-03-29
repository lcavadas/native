#!/usr/bin/env bash

if [[ $# -eq 0 && ! -d "$1" ]]; then
  echo "Please specify the implementation to compile"
  exit 1
fi

if [[ "$1" == "groovy" ]]; then
  EXTRA_ARGS="--initialize-at-build-time=sun.instrument.InstrumentationImpl"
fi

if [[ "$1" == "java-jpa" ]]; then
#  EXTRA_ARGS="--enable-all-security-services -H:+AddAllCharsets"
  EXTRA_ARGS="-H:IncludeResources=.*/db/.*"
fi

if [[ "$2" == "--debug" ]]; then
  EXTRA_ARGS="$EXTRA_ARGS --verbose -H:Log=registerResource -H:+PrintAnalysisCallTree -H:+TraceServiceLoaderFeature"
fi

ARTIFACT=$1

GREEN='\033[0;32m'
RED='\033[0;31m'
NC='\033[0m'

./gradlew $ARTIFACT:clean $ARTIFACT:assemble
MAINCLASS=$(cat $ARTIFACT/build/bootJarMainClassName)

JAR="$ARTIFACT.jar"
echo "Unpacking $JAR"
mkdir -p $ARTIFACT/build/native-image
cd $ARTIFACT/build/native-image || exit 1
jar -xvf ../libs/$JAR >/dev/null 2>&1
cp -R META-INF BOOT-INF/classes

LIBPATH=$(find BOOT-INF/lib | tr '\n' ':')
CP=BOOT-INF/classes:$LIBPATH

GRAALVM_VERSION=$(native-image --version)
echo "Compiling $ARTIFACT with $GRAALVM_VERSION"
{ time native-image \
  -Dspring.spel.ignore=false \
  -Dspring.native.remove-yaml-support=true \
  $EXTRA_ARGS \
  -H:Name=$ARTIFACT \
  -cp $CP $MAINCLASS >>output.txt; } 2>>output.txt

if [[ -f $ARTIFACT ]]; then
  printf "${GREEN}SUCCESS${NC} - $ARTIFACT\n"
  mv ./$ARTIFACT ../native-$ARTIFACT
  cd ..
  ./native-$ARTIFACT -Dspring.profiles.active=native
else
  cat output.txt
  printf "${RED}FAILURE${NC}: an error occurred when compiling the native-image.\n"
  exit 1
fi
