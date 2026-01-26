@ECHO OFF
CLS
javac -d out src/main/java/com/todocli/*.java src/main/java/com/todocli/cli/*.java src/main/java/com/todocli/db/*.java src/main/java/com/todocli/model/*.java src/main/java/com/todocli/repository/*.java src/main/java/com/todocli/service/*.java src/main/java/com/todocli/util/*.java
jar cvfm todo-cli.jar MANIFEST.MF -C out .
CLS
ECHO Successfully Compiled