@echo off

cls

cd %~dp0

IF NOT EXIST "server" (
	mkdir "server"
)

IF NOT EXIST "server\plugins" (
	mkdir "server\plugins"
)

call .\gradlew.bat jar --warning-mode all

IF EXIST "server\plugins\FyrePlugin.jar" (
	del "server\plugins\FyrePlugin.jar"
)

echo F|xcopy "build\libs\FyrePlugin-1.0-SNAPSHOT.jar" "server\plugins\FyrePlugin.jar" /Q

cd "server"

IF NOT EXIST "eula.txt" (
	echo By continuing you accept the Minecraft EULA "https://account.mojang.com\documents\minecraft_eula"
	echo Press any key to accept or Ctrl+C to decline
	PAUSE>NUL

	echo eula=true> "eula.txt"

	echo Minecraft EULA accepted
)

IF NOT EXIST "world" (
	mkdir "world"
)

IF EXIST "world\datapacks" (
	echo Y|rmdir /s "world\datapacks"
)

mkdir "world\datapacks"
xcopy """..\..\datapack" "world\datapacks" /S /E /D /Q

IF NOT EXIST "server.jar" (
	echo Downloading PaperMC...

	curl -o "server.jar" "https://papermc.io/api/v1/paper/1.15.2/latest/download"
	REM curl -o "server.jar" "https://papermc.io/api/v1/paper/1.13.2/latest/download"

	echo PaperMC downloaded
)

REM TODO: Download other plugins

REM IF NOT EXIST "plugins\Vault.jar" (
REM     echo Downloading Vault...

REM     curl -o "plugins\Vault.jar" "https://dev.bukkit.org/projects/vault/files/2704903/download"

REM     echo Vault downloaded
REM )

echo Starting the server now

cls

java -Xms256M -Xmx1024M -jar server.jar nogui -h 127.0.0.1

cd %~dp0
