#!/bin/bash

set -e
clear
JAVA_FILES=$(find src/main/java/com/todocli -name "*.java")
javac -d out $JAVA_FILES
jar cvfm to-do-cli.jar MANIFEST.MF -C out .
clear
echo Successfully Compiled