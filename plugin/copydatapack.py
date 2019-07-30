import os
import platform
import shutil
import subprocess


# Functions #


def clear_console():
	if platform.system().lower() == "windows":
		subprocess.call("cls", shell=True)
	else:
		subprocess.call("clear", shell=True)


# Main Script #


clear_console()

os.chdir(os.path.dirname(os.path.realpath(__file__)))
os.chdir("server")

if os.path.exists("world/datapacks"):
	shutil.rmtree("world/datapacks")

shutil.copytree("../../datapack/", "world/datapacks")

clear_console()
