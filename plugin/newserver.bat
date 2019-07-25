@echo off

REM Paper does not need to be built unlike Spigot.
REM We can just download the server jar and launch it.

REM Make server directory
mkdir server
cd server/

curl https://papermc.io/api/v1/paper/1.14.4/146/download --output server.jar

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
