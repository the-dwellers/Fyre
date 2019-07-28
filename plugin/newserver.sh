#!/usr/bin/env sh

# Paper does not need to be built unlike Spigot.
# We can just download the server jar and launch it.

# Make server directory
mkdir server
cd server/

# Download and execute buildtools
curl https://papermc.io/api/v1/paper/1.14.4/146/download --output server.jar

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
