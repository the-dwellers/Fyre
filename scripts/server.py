import curses

import _colorpairs as colorpairs


class Application:
	screen: curses.window

	def __init__(self, stdscr):
		self.screen = stdscr

		curses.curs_set(False)

		curses.init_pair(colorpairs.BLACK, curses.COLOR_BLACK, curses.COLOR_BLACK)
		curses.init_pair(colorpairs.BLUE, curses.COLOR_BLUE, curses.COLOR_BLACK)
		curses.init_pair(colorpairs.CYAN, curses.COLOR_CYAN, curses.COLOR_BLACK)
		curses.init_pair(colorpairs.GREEN, curses.COLOR_GREEN, curses.COLOR_BLACK)
		curses.init_pair(colorpairs.MAGENTA, curses.COLOR_MAGENTA, curses.COLOR_BLACK)
		curses.init_pair(colorpairs.RED, curses.COLOR_RED, curses.COLOR_BLACK)
		curses.init_pair(colorpairs.WHITE, curses.COLOR_WHITE, curses.COLOR_BLACK)
		curses.init_pair(colorpairs.YELLOW, curses.COLOR_YELLOW, curses.COLOR_BLACK)

		self.execute()

	def print_banner(self):
		# Line 1
		self.screen.addstr(0, 0, u"\u2554")
		for i in range(1, 26):
			self.screen.addstr(0, i, u"\u2550")
		self.screen.addstr(0, 26, u"\u2557")

		# Line 2
		self.screen.addstr(1, 0, u"\u2551")
		self.screen.addstr(1, 2, "Fyre Development Server", curses.color_pair(colorpairs.CYAN))
		self.screen.addstr(1, 26, u"\u2551")

		# Line 3
		self.screen.addstr(2, 0, u"\u2551")

		base = "Status: "
		status = "Stopped"

		x = int(27 / 2 - (len(base) + len(status)) / 2)
		self.screen.addstr(2, x, base)

		if status.lower() == "stopped":
			self.screen.addstr(2, x + len(base), status, curses.color_pair(colorpairs.RED))
		elif status.lower() == "running":
			self.screen.addstr(2, x + len(base), status, curses.color_pair(colorpairs.GREEN))
		else:
			self.screen.addstr(2, x + len(base), status)

		self.screen.addstr(2, 26, u"\u2551")

		# Line 4
		self.screen.addstr(3, 0, u"\u255A")
		for i in range(1, 26):
			self.screen.addstr(3, i, u"\u2550")
		self.screen.addstr(3, 26, u"\u255D")

	def print_menu(self):
		options = [
			{
				"key": "0",
				"name": "Quit",
				"action": self.screen.refresh()
			}
		]
		for option in options:
			pass

	def execute(self):
		while True:
			# Banner
			self.print_banner()
			# Menu
			self.print_menu()
			# Input

			self.screen.refresh()
			self.screen.getch()
			break


if __name__ == '__main__':
	curses.wrapper(Application)
