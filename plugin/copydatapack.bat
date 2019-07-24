@echo off
REM Copies the datapacks from Fyre/Datapack to server/world/datapacks
REM Remember to execute minecraft:reload in the server console after copying. The server does not need to be restarted.

xcopy "..\datapack" ".\server\world\datapacks" /S /E /D /Q
