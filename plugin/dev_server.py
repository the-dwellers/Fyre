import os
import subprocess
import platform
import requests
import hashlib
import shutil

### Functions ###

def clear_console():
	if platform.system().lower() == "windows":
		subprocess.call("cls")
	else:
		subprocess.call("clear")

### Main Script ###

clear_console()

os.chdir(os.path.dirname(os.path.realpath(__file__)))

if not os.path.exists("server"):
	os.mkdir("server")

if not os.path.exists("server/plugins"):
	os.mkdir("server/plugins")

if platform.system().lower() == "windows":
	subprocess.call(['gradlew.bat', 'clean', 'build'])
else:
	subprocess.call(['./gradlew', 'clean', 'build'])

if os.path.exists("server/plugins/FyrePlugin.jar"):
	os.remove("server/plugins/FyrePlugin.jar")

shutil.copy("build/libs/FyrePlugin-1.0-SNAPSHOT.jar", "server/plugins/FyrePlugin.jar")

os.chdir("server")

if not os.path.exists("eula.txt"):
	print("")
	print("By continuing you accept the Minecraft EULA (https://account.mojang.com/documents/minecraft_eula)")
	input("Press any key to accept or Ctrl+C to decline")
	print("")

	f = open("eula.txt", "w+")
	f.write("eula=true")
	f.close()

	print("Minecraft EULA accepted")

if not os.path.exists("world"):
	os.mkdir("world")

if os.path.exists("world/datapacks"):
	shutil.rmtree("world/datapacks")

shutil.copytree("../../datapack/", "world/datapacks")

headers = {"user-agent": "fyre-devserver-script/1.0.0"}

if not os.path.exists("server.jar"):
	print("Downloading PaperMC...")

	r = requests.get("https://papermc.io/api/v1/paper/1.14.4/latest/download", headers=headers)
	f = open("server.jar", "wb")
	f.write(r.content)
	f.close()

	print("PaperMC downloaded")
else:
	print("Checking for PaperMC updates...")

	r = requests.get("https://papermc.io/api/v1/paper/1.14.4/latest/download", headers=headers)

	h1 = hashlib.md5()
	h2 = hashlib.md5()

	f = open("server.jar", "rb")
	h1.update(f.read())
	f.close()
	h2.update(r.content)

	if str(h1.hexdigest()) != str(h2.hexdigest()):
		print("Update found, installing the latest version now")

		if os.path.exists("server.jar.old"):
			os.remove("server.jar.old")

		os.rename("server.jar", "server.jar.old")

		f = open("server.jar", "wb")
		f.write(r.content)
		f.close()

	else:
		print("No updates found, continuing...")

# TODO: Download other plugins

print("Starting the server now")

clear_console()

subprocess.call(["java", "-Xms256M", "-Xmx1024M", "-jar", "server.jar", "nogui", "-h", "127.0.0.1"])
