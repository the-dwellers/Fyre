import os
import sys
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


def download(url, filename):
	with open(filename, 'wb') as f:
		response = requests.get(url, stream=True)
		total = response.headers.get('content-length')

		if total is None:
			f.write(response.content)
		else:
			downloaded = 0
			total = int(total)
			for data in response.iter_content(chunk_size=max(int(total/1000), 1024*1024)):
				downloaded += len(data)
				f.write(data)
				done = int(50*downloaded/total)
				sys.stdout.write('\r[{}{}]'.format('█' * done, '.' * (50-done)))
				sys.stdout.flush()
	sys.stdout.write('\n')


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
api_url = "https://papermc.io/api/v1/paper/1.14.4/latest/download"

if not os.path.exists("server.jar"):
	print("Downloading PaperMC...")

	download(api_url, "server.jar")

	print("PaperMC downloaded")
else:
	print("Checking for PaperMC updates...")

	r = requests.get(url=api_url, headers=headers)

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

		download(api_url, "server.jar")

	else:
		print("No updates found, continuing...")

# TODO: Download other plugins

print("Starting the server now")

clear_console()

subprocess.call(["java", "-Xms256M", "-Xmx1024M", "-jar", "server.jar", "nogui", "-h", "127.0.0.1"])
