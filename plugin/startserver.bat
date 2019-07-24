@echo off
del .\server\plugins\FyrePlugin.jar
call .\gradlew.bat build

REM Copy datapack
CALL copydatapack.bat

REM Yes, this is hardcoded
mkdir .\server\plugins
move ".\build\libs\FyrePlugin-1.0-SNAPSHOT.jar" ".\server\plugins\FyrePlugin.jar"

cd .\server\
java -jar -Xmx1024M server.jar
cd ..
