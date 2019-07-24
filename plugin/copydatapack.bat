@echo off
REM Copies the datapacks from Fyre/Datapack to server/world/datapacks
REM Remember to execute minecraft:reload in the server console after copying. The server does not need to be restarted.
REM Note: if newserver.bat is ran, the datapack will need to be copied again

xcopy "..\datapack" ".\server\world\datapacks" /S /E /D /Q
