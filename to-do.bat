@ECHO OFF
PUSHD %~dp0
java --enable-native-access=ALL-UNNAMED -jar to-do-cli.jar %1 %2 %3