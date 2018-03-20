#!/bin/bash
script_full_path=$(dirname "$0")

function usage() {
	echo "Usage:"
	echo "$(basename $0)" keypass storepass
}


if [ "$#" -ne 2 ]; then
	usage
fi

echo ">>> Removing previous keystore..."
rm -rf config-server.jks

echo ">>> Creating new one..."
keytool -genkeypair -alias config-server-key \
       -keyalg RSA -keysize 4096 -sigalg SHA512withRSA \
       -dname 'CN=Config Server,OU=net.ins,O=Insomnium' \
       -keypass "$1" -keystore "$script_full_path/config-server.jks" \
       -storepass "$2"
