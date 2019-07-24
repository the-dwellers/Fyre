@echo off

REM Spigot works by compiling the server.jar on the local machine
REM To do this, BuildTools is downloaded and executed, the outputted server.jar
REM is then copied to the server directory, and extra artifacts deleted.
REM The process of building the server can take quite long.

echo Building the server can take quite a while, Up to about 10 minutes. During this the ./server/ directory will be deleted.
echo Press any key to continue, or Ctrl+C to cancel...
pause>NUL

REM Make server directory
mkdir server
cd server/

REM Download and execute buildtools
curl https://hub.spigotmc.org/jenkins/job/BuildTools/lastSuccessfulBuild/artifact/target/BuildTools.jar --output buildTools.jar
java -jar buildTools.jar --rev 1.14.4

REM Copy spigot jar to server directory

MOVE spigot-1.14.4.jar ../server.jar
cd ..

REM Delete everything within the server directory
REM RD /S /Q server
MKDIR server
MOVE server.jar server/server.jar

REM Copy Datapack
CALL copydatapack.bat

REM Execute server
cd server/
java -jar server.jar

REM Ask user to accept EULA
echo Press Ctrl+C to decline the EULA (https://account.mojang.com/documents/minecraft_eula)
echo Or any other key to accept
PAUSE>NUL

REM Accept Eula
FINDSTR /R /I /V "^$ eula=false" eula.txt>>eula2.txt
ECHO eula=TRUE>> eula2.txt
DEL eula.txt
MOVE eula2.txt eula.txt

ECHO Server created! Use startserver.bat to start the server
