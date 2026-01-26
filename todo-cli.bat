@ECHO OFF
CLS
PUSHD %~dp0
java --enable-native-access=ALL-UNNAMED -jar todo-cli.jar %1 %2 %3