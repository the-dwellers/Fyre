import curses

import _curses


# region Colors
class Colors:
	Black = None,
	Blue = None,
	Cyan = None,
	Green = None,
	Magenta = None,
	Red = None,
	White = None,
	Yellow = None,


def setup_colors():
	curses.init_pair(1, curses.COLOR_BLACK, curses.COLOR_BLACK)
	curses.init_pair(2, curses.COLOR_BLUE, curses.COLOR_BLACK)
	curses.init_pair(3, curses.COLOR_CYAN, curses.COLOR_BLACK)
	curses.init_pair(4, curses.COLOR_GREEN, curses.COLOR_BLACK)
	curses.init_pair(5, curses.COLOR_MAGENTA, curses.COLOR_BLACK)
	curses.init_pair(6, curses.COLOR_RED, curses.COLOR_BLACK)
	curses.init_pair(7, curses.COLOR_WHITE, curses.COLOR_BLACK)
	curses.init_pair(8, curses.COLOR_YELLOW, curses.COLOR_BLACK)

	Colors.Black = curses.color_pair(1)
	Colors.Blue = curses.color_pair(2)
	Colors.Cyan = curses.color_pair(3)
	Colors.Green = curses.color_pair(4)
	Colors.Magenta = curses.color_pair(5)
	Colors.Red = curses.color_pair(6)
	Colors.White = curses.color_pair(7)
	Colors.Yellow = curses.color_pair(8)
# endregion


options = []


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
		pass
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
