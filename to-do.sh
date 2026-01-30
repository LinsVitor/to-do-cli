#!/bin/bash

SCRIPT_DIR="$(cd "$(dirname "$0")" && pwd)"
java --enable-native-access=ALL-UNNAMED -jar $SCRIPT_DIR/to-do-cli.jar "$@"