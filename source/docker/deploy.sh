#!/bin/sh

PROJECT_ROOT=../../
GRADLE_VERSION=7.4
JDK_VERSION=11
OUTPUT_FILE=psi-mockup.tar

DOCKER_COMMAND=docker
if command -v podman &> /dev/null
then
    DOCKER_COMMAND=podman
fi

echo "Building project, please wait..."
NATIVE_GRADLE=0
if command -v gradle &> /dev/null
then
    installed_version=$(gradle --version)
    if [[ $installed_version == *"Gradle $GRADLE_VERSION"* ]]; then
        echo "Using native Gradle installation"
        NATIVE_GRADLE=1
    else
        echo "Wrong Gradle version, using $DOCKER_COMMAND"
    fi
else
    echo "Gradle not found, using $DOCKER_COMMAND"
fi

if [ $NATIVE_GRADLE = 0 ]
then
    $DOCKER_COMMAND run --rm -v $PROJECT_ROOT:/home/gradle gradle:$GRADLE_VERSION-jdk$JDK_VERSION gradle assemble
else
    pushd $PROJECT_ROOT
    gradle assemble
    popd
fi

if [ -f $OUTPUT_FILE ]
then
  rm $OUTPUT_FILE
fi

$DOCKER_COMMAND build -f Dockerfile -t psi:latest $PROJECT_ROOT
$DOCKER_COMMAND save -o $OUTPUT_FILE psi:latest
