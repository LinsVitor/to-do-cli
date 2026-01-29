@ECHO OFF
SETLOCAL enabledelayedexpansion
CLS
SET JAVA_FILES=
FOR /f "delims=" %%i in ('dir /a-d /s /b src\main\java\com\todocli\*.java') DO (
    SET "JAVA_FILES=!JAVA_FILES! "%%i""
)
javac -d out %JAVA_FILES%
jar cvfm to-do-cli.jar MANIFEST.MF -C out .
CLS
ECHO Successfully Compiled
ENDLOCAL