import curses
import os

import _curses

from _colors import Colors, setup_colors
from _server import ServerThread

options = []

root_dir = os.path.dirname(os.path.realpath(__file__))
server_dir = os.path.join(root_dir, "server")
plugins_dir = os.path.join(server_dir, "plugins")
world_dir = os.path.join(server_dir, "world")
data_pack_dir = os.path.join(world_dir, "datapacks")


def setup_options():
	# region Actions
	def action_start():
		pass

	def action_stop():
		pass

	def action_restart():
		pass

	def action_rebuild_all():
		pass

	def action_rebuild_data_pack():
		pass

	def action_rebuild_plugin():
		pass

	def action_attach():
		pass

	def action_quit():
		# TODO: CLean exit
		exit(0)

	# endregion
	options.append({
		"key": "1",
		"name": "Start",
		"func": action_start
	})
	options.append({
		"key": "2",
		"name": "Stop",
		"func": action_stop
	})
	options.append({
		"key": "3",
		"name": "Restart",
		"func": action_restart
	})
	options.append({
		"key": "4",
		"name": "Rebuild all",
		"func": action_rebuild_all
	})
	options.append({
		"key": "5",
		"name": "Rebuild data pack",
		"func": action_rebuild_data_pack
	})
	options.append({
		"key": "6",
		"name": "Rebuild plugin",
		"func": action_rebuild_plugin
	})
	options.append({
		"key": "9",
		"name": "Attach",
		"func": action_attach
	})
	options.append({
		"key": "0",
		"name": "Quit",
		"func": action_quit
	})


def setup_dirs():
	if not os.path.exists(server_dir):
		os.mkdir(server_dir)

	if not os.path.exists(plugins_dir):
		os.mkdir(plugins_dir)

	if not os.path.exists(world_dir):
		os.mkdir(world_dir)

	if not os.path.exists(data_pack_dir):
		os.mkdir(data_pack_dir)


def print_status(screen: curses.window):
	title = "Fyre Development Server"

	status = "Running"
	uptime = "00:00:00"

	width = 51

	y = 0
	# region Line 1
	for i in range(0, width):
		if i == 0:
			screen.addstr(y, i, u"\u2554")
		elif i == width - 1:
			screen.addstr(y, i, u"\u2557")
		else:
			screen.addstr(y, i, u"\u2550")
	y += 1
	# endregion
	# region Line 2 - Title
	screen.addstr(y, 0, u"\u2551")

	x = int(width / 2 - len(title) / 2)
	screen.addstr(y, x, title, Colors.Cyan)

	screen.addstr(y, width - 1, u"\u2551")
	y += 1
	# endregion
	# region Line 3 - Status & Uptime
	screen.addstr(y, 0, u"\u2551")

	if status == "Running":
		screen.addstr(y, 2, status, Colors.Green)
		x = width - 2 - len(uptime)
		screen.addstr(y, x, uptime)
	else:
		screen.addstr(y, 2, status, Colors.Red)

	screen.addstr(y, width - 1, u"\u2551")
	y += 1
	# endregion
	# region Line 4
	for i in range(0, width):
		if i == 0:
			screen.addstr(y, i, u"\u255A")
		elif i == width - 1:
			screen.addstr(y, i, u"\u255D")
		else:
			screen.addstr(y, i, u"\u2550")
	# endregion
	pass


def main(screen: curses.window):
	# region Init
	curses.curs_set(False)
	curses.halfdelay(1)

	setup_colors()
	setup_options()
	setup_dirs()
	# endregion
	# region EULA
	eula_file = os.path.join(server_dir, "eula.txt")
	eula = False

	while not eula:
		screen.clear()
		if not os.path.exists(eula_file):
			eula = False
		else:
			f = open(eula_file, "r")
			for line in f.readlines():
				if "eula=" in line:
					if line[5:].lower() == 'true':
						eula = True
			f.close()

		if eula:
			break

		screen.addstr(1, 0, "By continuing you accept the Minecraft EULA (https://account.mojang.com/documents/minecraft_eula)")
		screen.addstr(2, 0, "Press 'y' to Accept, 'n' to decline")

		try:
			key = screen.getkey()
			if key == 'n':
				exit(0)
			elif key == 'y':
				if os.path.exists(eula_file):
					os.remove(eula_file)

				f = open(eula_file, "w+")
				f.write("eula=true")
				f.close()

				eula = True
		except _curses.error:
			pass
	# endregion
	while True:
		screen.clear()
		print_status(screen)

		y = 5
		for option in options:
			screen.addstr(y, 0, "[{}] {}".format(option["key"], option["name"]))
			y += 1

		try:
			key = screen.getkey()
			for option in options:
				if key == option["key"]:
					option["func"]()
		except _curses.error:
			pass


if __name__ == '__main__':
	curses.wrapper(main)
