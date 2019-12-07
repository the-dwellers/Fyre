@echo off

cls

cd %~dp0
cd "server"

IF EXIST "world\datapacks" (
	echo Y|rmdir /s "world\datapacks"
)

mkdir "world\datapacks"
xcopy """..\..\datapack" "world\datapacks" /S /E /D /Q

cd %~dp0
