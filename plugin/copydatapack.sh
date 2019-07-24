#!/usr/bin/env sh

# Copies the datapacks from Fyre/Datapack to server/world/datapacks
# Remember to execute minecraft:reload in the server console after copying. The server does not need to be restarted.

cp -r "../datapack/." "./server/world/datapacks/."
