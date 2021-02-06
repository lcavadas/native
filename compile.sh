#!/usr/bin/env bash

ARTIFACT=${PWD##*/}
VERSION=0.0.1-SNAPSHOT

GREEN='\033[0;32m'
RED='\033[0;31m'
NC='\033[0m'

echo "Packaging $ARTIFACT with Gradle"
./gradlew clean assemble
MAINCLASS=`cat build/bootJarMainClassName`

JAR="$ARTIFACT-$VERSION.jar"
rm -f $ARTIFACT
echo "Unpacking $JAR"
mkdir -p build/native-image
cd build/native-image || exit 1
jar -xvf ../libs/$JAR >/dev/null 2>&1
cp -R META-INF BOOT-INF/classes

LIBPATH=`find BOOT-INF/lib | tr '\n' ':'`
CP=BOOT-INF/classes:$LIBPATH

GRAALVM_VERSION=`native-image --version`
echo "Compiling $ARTIFACT with $GRAALVM_VERSION"
{ time native-image \
  --verbose \
  -Dspring.spel.ignore=true \
  -Dspring.native.remove-yaml-support=true \
  -H:Name=$ARTIFACT \
  -cp $CP $MAINCLASS >> output.txt ; } 2>> output.txt

if [[ -f $ARTIFACT ]]
then
  printf "${GREEN}SUCCESS${NC} - $ARTIFACT\n"
  mv ./$ARTIFACT ..
  cd ..
  ./$ARTIFACT
else
  cat output.txt
  printf "${RED}FAILURE${NC}: an error occurred when compiling the native-image.\n"
  exit 1
fi
