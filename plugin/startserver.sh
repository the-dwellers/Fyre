#!/usr/bin/env sh

rm ./server/plugins/FyrePlugin.jar
./gradlew build

# Copy datapack
./copydatapack.sh

# Yes, this is hardcoded
mkdir ./server/plugins
mv "./build/libs/FyrePlugin-1.0-SNAPSHOT.jar" "./server/plugins/FyrePlugin.jar"

cd ./server/
java -jar -Xmx1024M server.jar
cd ..
