#!/usr/bin/env sh

clear

cd "$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"

if [ ! -d "server" ]; then
	mkdir "server"
fi

if [ ! -d "server/plugins" ]; then
	mkdir "server/plugins"
fi

./gradlew clean build

if [ -e "server/plugins/FyrePlugin.jar" ]; then
	rm -rf "server/plugins/FyrePlugin.jar"
fi

cp "build/libs/FyrePlugin-1.0-SNAPSHOT.jar" "server/plugins/FyrePlugin.jar"

cd "server"

if [ ! -e "eula.txt" ]; then
	echo ""
	echo "By continuing you accept the Minecraft EULA (https://account.mojang.com\documents\minecraft_eula)"
	echo "Press any key to accept or Ctrl+C to decline"
	echo ""
	read

	echo "eula=true" > "eula.txt"

	echo "Minecraft EULA accepted"
fi

if [ ! -d "world" ]; then
	mkdir "world"
fi

if [ -d "world/datapacks" ]; then
	rm -rf "world/datapacks"
fi

mkdir "world/datapacks"
cp -r "../../datapack/." "world/datapacks"

if [ ! -e "server.jar" ]; then
	echo "Downloading PaperMC..."

	curl -o "server.jar" "https://papermc.io/api/v1/paper/1.14.4/latest/download"

	echo "PaperMC downloaded"
else
	echo "Checking for PaperMC updates..."

	remote="$(curl -s "https://papermc.io/api/v1/paper/1.14.4/latest/download" | md5sum)"
	loc="$(cat server.jar | md5sum)"

	if [ $loc != $remote ]; then
		echo "Update found, installing the latest version now"

		if [ -e "server.jar.old" ]; then
			rm -f "server.jar.old"
		fi

		mv "server.jar" "server.jar.old"

		curl -o "server.jar" "https://papermc.io/api/v1/paper/1.14.4/latest/download"
	else
		echo "No updates found, continuing..."
	fi
fi

# TODO: Download other plugins

echo "Starting the server now"

clear

java -Xms256M -Xmx1024M -jar server.jar nogui -h 127.0.0.1

cd "$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"
