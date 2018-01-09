#!/bin/bash
script_full_path=$(dirname "$0")

if [ "$#" -ne 2 ]; then
    echo "missing keypass and/or storepass"
    exit 1
fi

keytool -genkeypair -alias config-server-key \
       -keyalg RSA -keysize 4096 -sigalg SHA512withRSA \
       -dname 'CN=Config Server,OU=net.ins,O=Insomnium' \
       -keypass "$1" -keystore "$script_full_path/config-server.jks" \
       -storepass "$2"
