#!/bin/bash

CONTAINER_AWAITING_DELAY_SEC=10
TMP_CONTAINER_NAME=tmp-config-server-instance

if [ "$#" -lt 2 ]; then
    echo "<<< missing keypass and/or storepass"
    exit 1
fi

function status {
    if [ "$?" -ne 0 ]; then
        exit -1
    fi
}

echo ">>> Building temporary config-server image to encrypt repo password..."
cd ..
./gradlew :infra:config-server:dockerBuildImage
status

echo ">>> Running temporary container..."
docker run --name "$TMP_CONTAINER_NAME" -d --net=host -p 8890:8890 -e SPRING_PROFILES_ACTIVE=keystore -e KEY_PWD="$1" -e KEYSTORE_PWD="$2" net.ins.hw.netflix/config-server:0.0.1
status


echo ">>> Now let's wait for $CONTAINER_AWAITING_DELAY_SEC seconds to let config-server run"
sleep $CONTAINER_AWAITING_DELAY_SEC


if [ -z "$3" ]; then
    read -p "Enter repo password to be encrypted: " REPO_PASS
else
    REPO_PASS=$3
fi

echo ">>> Here is your encrypted password:"
curl -XPOST localhost:8890/encrypt -d "$REPO_PASS"

echo ""
echo ">>> Cleaning up..."
docker rm -f "$TMP_CONTAINER_NAME"