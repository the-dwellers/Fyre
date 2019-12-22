import curses

# noinspection PyPep8Naming
import _colors as Colors
# noinspection PyPep8Naming
import _paths as Paths
# noinspection PyPep8Naming
import _options as Options

import _curses

width = 51

status = "Stopped"
attached = False


def banner(screen: curses.window):
	# region Line 1
	for i in range(0, width):
		if i == 0:
			screen.addstr(0, i, u"\u2554")
		elif i == width - 1:
			screen.addstr(0, i, u"\u2557")
		else:
			screen.addstr(0, i, u"\u2550")
	# endregion
	# region Line 2 - Title
	screen.addstr(1, 0, u"\u2551")

	title = "Fyre Development Server"
	x = int(width / 2 - len(title) / 2)
	screen.addstr(1, x, title, Colors.CYAN)

	screen.addstr(1, width - 1, u"\u2551")
	# endregion
	# region Line 3 - Status
	screen.addstr(2, 0, u"\u2551")

	if status == "Running":
		screen.addstr(2, 2, status, Colors.GREEN)
	elif status == "Stopping" or status == "Starting":
		screen.addstr(2, 2, status, Colors.YELLOW)
	elif status == "Stopped":
		screen.addstr(2, 2, status, Colors.RED)
	else:
		screen.addstr(2, 2, status)

	screen.addstr(2, width - 1, u"\u2551")
	# endregion
	# region Line 4
	for i in range(0, width):
		if i == 0:
			screen.addstr(3, i, u"\u255A")
		elif i == width - 1:
			screen.addstr(3, i, u"\u255D")
		else:
			screen.addstr(3, i, u"\u2550")
	# endregion
	pass


def init():
	curses.curs_set(False)
	curses.halfdelay(1)

	Colors.init()
	Paths.init()


def main(screen: curses.window):
	init()
	while True:
		screen.clear()
		if attached:
			pass
		else:
			banner(screen)

			y = 5
			for x in Options.get():
				screen.addstr(y, 0, "[{}] {}".format(x["key"], x["name"]))
				y += 1

			try:
				key = screen.getkey()
				for x in Options.get():
					if key == x["key"]:
						x["func"]()
						break
			except _curses.error:
				pass


if __name__ == '__main__':
	curses.wrapper(main)
