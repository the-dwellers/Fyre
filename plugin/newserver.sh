#!/usr/bin/env sh

# Spigot works by compiling the server.jar on the local machine
# To do this, BuildTools is downloaded and executed, the outputted server.jar
# is then copied to the server directory, and extra artifacts deleted.
# The process of building the server can take quite long.

echo 'Building the server can take quite a while, Up to about 10 minutes. During this the ./server/ directory will be deleted.'
echo 'Press any key to continue, or Ctrl+C to cancel...'
read

# Make server directory
mkdir server
cd server/

# Download and execute buildtools
curl https://hub.spigotmc.org/jenkins/job/BuildTools/lastSuccessfulBuild/artifact/target/BuildTools.jar --output buildTools.jar
java -jar buildTools.jar --rev 1.14.4

# Copy spigot jar to server directory

mv spigot-1.14.4.jar ../server.jar
cd ..

# Delete everything within the server directory
rm -rf server/
mkdir server
mv server.jar server/server.jar

# Copy datapack
./copydatapack.sh

# Execute server
cd server/
java -jar server.jar

# Ask user to accept EULA
echo 'Press Ctrl+C to decline the EULA (https://account.mojang.com/documents/minecraft_eula)'
echo 'Or any other key to accept'
read

# Accept Eula
head -2 eula.txt>>eula2.txt
echo eula=TRUE>>eula2.txt
rm eula.txt
mv eula2.txt eula.txt

echo 'Server created! Use startserver.sh to start the server'
