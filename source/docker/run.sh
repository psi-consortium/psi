#!/bin/sh

echo "Building project, please wait..."
pushd ..
gradle assemble
popd

echo "Building image, please wait..."
IMAGE_ID=$(podman build -q -f Dockerfile ..)

podman run\
	--rm\
	--name=psi\
	-p 8001:8001\
	-p 8081:8081\
  $IMAGE_ID
