#!/usr/bin/env bash

clear

cd "$(dirname "$0")"

if [[ ! -d "server" ]]; then
	mkdir "server"
fi

if [[ ! -d "server/plugins" ]]; then
	mkdir "server/plugins"
fi

./gradlew jar --warning-mode all
if [[ $? -ne 0 ]]; then
	echo ERROR: Gradle build finished with non-zero exit code
	exit 1
fi

if [[ -a "server/plugins/FyrePlugin.jar" ]]; then
	rm -f "server/plugins/FyrePlugin.jar"
fi

cp "build/libs/FyrePlugin-0-1.0-SNAPSHOT.jar" "server/plugins/FyrePlugin.jar"

cd "server"

if [[ ! -a "eula.txt" ]]; then
	echo By continuing you accept the Minecraft EULA "https://account.mojang.com/documents/minecraft_eula"
	echo Press any key to accept or Ctrl+C to decline
	read -n 1

	echo eula=true > "eula.txt"

	echo Minecraft EULA accepted
fi

if [[ ! -d "world" ]]; then
	mkdir "world"
fi

if [[ -d "world/datapacks" ]]; then
	rm -rf "world/datapacks"
fi

mkdir "world/datapacks"
cp -r "../../datapack/." "world/datapacks"

if [[ ! -a "server.jar" ]]; then
	echo Downloading PaperMC...

	curl -o "server.jar" "https://papermc.io/api/v1/paper/1.15.2/latest/download"

	echo PaperMC downloaded
fi

echo Starting the server now

clear

java -Xms256M -Xmx1024M -jar server.jar nogui -h 127.0.0.1

cd "$(dirname "$0")"
