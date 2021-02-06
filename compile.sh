#!/usr/bin/env bash

if [[ $# -eq 0 && ! -d "$1" ]]; then
  echo "Please specify the implementation to compile"
  exit 1
fi

if [[ "$1" -eq "groovy" ]]; then
  EXTRA_ARGS="--initialize-at-build-time=sun.instrument.InstrumentationImpl"
fi

ARTIFACT=$1

GREEN='\033[0;32m'
RED='\033[0;31m'
NC='\033[0m'

echo "Packaging $ARTIFACT with Gradle"
./gradlew clean $ARTIFACT:assemble
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
  --verbose \
  -Dspring.spel.ignore=true \
  -Dspring.native.remove-yaml-support=true \
  $EXTRA_ARGS \
  -H:Name=$ARTIFACT \
  -cp $CP $MAINCLASS >>output.txt; } 2>>output.txt

if [[ -f $ARTIFACT ]]; then
  printf "${GREEN}SUCCESS${NC} - $ARTIFACT\n"
  mv ./$ARTIFACT ../nattive-$ARTIFACT
  cd ..
  ./nattive-$ARTIFACT
else
  cat output.txt
  printf "${RED}FAILURE${NC}: an error occurred when compiling the native-image.\n"
  exit 1
fi
