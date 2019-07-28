@echo off

cls

cd %~dp0

IF NOT EXIST "server" (
	mkdir "server"
)

IF NOT EXIST "server\plugins" (
	mkdir "server\plugins"
)

call .\gradlew.bat clean build

IF EXIST "server\plugins\FyrePlugin.jar" (
	del "server\plugins\FyrePlugin.jar"
)

xcopy "build\libs\FyrePlugin-1.0-SNAPSHOT.jar" "server\plugins\FyrePlugin.jar" \Q

cd "server"

IF NOT EXIST "eula.txt" (
	echo ""
	echo "By continuing you accept the Minecraft EULA (https://account.mojang.com\documents\minecraft_eula)"
	echo "Press any key to accept or Ctrl+C to decline"
	echo ""
	PAUSE>NUL

	echo "eula=true" > "eula.txt"

	echo "Minecraft EULA accepted"
)

IF NOT EXIST "world" (
	mkdir "world"
)

IF EXIST "world\datapacks" (
	rmdir /s "world\datapacks"
)

mkdir "world\datapacks"
xcopy "..\..\datapack" "world\datapacks" /S /E /D /Q

IF NOT EXIST "server.jar" (
	echo "Downloading PaperMC..."

	curl -o "server.jar" "https://papermc.io/api/v1/paper/1.14.4/latest/download"

	echo "PaperMC downloaded"
) ELSE (
	echo "Checking for PaperMC updates..."

	set "ORIGINAL=" & for /F "skip=1 delims=" %%H in ('
		2^> nul CertUtil -hashfile "server.jar" MD5
	') do if not defined ORIGINAL set "ORIGINAL=%%H"
	set "RECEIVED=" & for /F "skip=1 delims=" %%H in ('
		2^> nul CertUtil -hashfile "https://papermc.io/api/v1/paper/1.14.4/latest/download" MD5
	') do if not defined RECEIVED set "RECEIVED=%%H"
	if "%ORIGINAL%%RECEIVED%"=="" (
		>&2 echo ERROR: no hashes available!
	) else (
		if "%ORIGINAL%"=="%RECEIVED%" (
			echo "No updates found, continuing..."
		) else (
			echo "Update found, installing the latest version now"

			IF EXIST "server.jar.old" (
				del "server.jar.old"
			)

			move "server.jar" "server.jar.old"

			curl -o "server.jar" "https://papermc.io/api/v1/paper/1.14.4/latest/download"
		)
	)
)

REM TODO: Download other plugins

echo "Starting the server now"

cls

java -Xms256M -Xmx1024M -jar server.jar nogui -h 127.0.0.1

cd %~dp0
