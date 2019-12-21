import threading
import json
import os
import requests
import urllib.request
from _global import server_dir, plugins_dir

server_file = os.path.join(server_dir, "server.jar")
info_file = os.path.join(server_dir, ".info")
vault_file = os.path.join(plugins_dir, "Vault.jar")
protocolLib_file = os.path.join(plugins_dir, "ProtocolLib.jar")


class ServerThread(threading.Thread):
	def __init__(self):
		super().__init__()

	def start(self):
		local_build = -1
		local_version = ""
		if os.path.exists(info_file):
			with open(info_file) as f:
				data = json.load(f)
				local_build = int(data["build"])
				local_version = data["version"]

		remote_build = -1
		with urllib.request.urlopen("https://papermc.io/api/v1/paper/1.15.1/latest") as url:
			data = json.loads(url.read().decode())
			remote_build = int(data["build"])
			remote_version = data["version"]

			if local_version != remote_version:
				local_build = -1

			if remote_build > local_build:
				with open(info_file, "w") as f:
					json.dump(data, f)

		if remote_build > local_build:
			r = requests.get("https://papermc.io/api/v1/paper/1.15.1/latest/download")
			with open(server_file, "wb") as f:
				f.write(r.content)

		# TODO: Update check
		if not os.path.exists(vault_file):
			r = requests.get("https://dev.bukkit.org/projects/vault/files/latest")
			with open(vault_file, "wb") as f:
				f.write(r.content)

		# TODO: Update check
		# TODO: Find SSL fix
		if not os.path.exists(protocolLib_file):
			r = requests.get("https://ci.dmulloy2.net/job/ProtocolLib/lastStableBuild/artifact/target/ProtocolLib.jar", verify = False)
			with open(protocolLib_file, "wb") as f:
				f.write(r.content)

		super().start()

	def run(self):
		pass
