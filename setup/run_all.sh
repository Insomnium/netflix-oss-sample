#!/bin/bash

script_full_path=$(dirname "$0")

KEYPASS=
STOREPASS=
REPO_PASS=
while getopts "k:s:" opt; do
    case $opt in
        k) KEYPASS=$OPTARG;;
        s) STOREPASS=$OPTARG;;
        p) REPO_PASS=$OPTARG;;
        :) echo "<<< Missing option argument for -$OPTARG" >&2; exit 1;;
    esac
done

function status {
    if [ "$?" -ne 0 ]; then
        exit -1
    fi
}

"$script_full_path/0_create_keystore.sh" "$KEYPASS" "$STOREPASS"
status
"$script_full_path/1_build_java-jce_container.sh"
status
"$script_full_path/2_build_temp_config_server_container.sh" "$KEYPASS" "$STOREPASS" "$REPO_PASS"
status