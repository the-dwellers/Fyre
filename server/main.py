import _curses
import curses

# region Colors


class Colors:
	BLACK = None,
	BLUE = None,
	CYAN = None,
	GREEN = None,
	MAGENTA = None,
	RED = None,
	WHITE = None,
	YELLOW = None,


def setup_colors():
	curses.init_pair(1, curses.COLOR_BLACK, curses.COLOR_BLACK)
	curses.init_pair(2, curses.COLOR_BLUE, curses.COLOR_BLACK)
	curses.init_pair(3, curses.COLOR_CYAN, curses.COLOR_BLACK)
	curses.init_pair(4, curses.COLOR_GREEN, curses.COLOR_BLACK)
	curses.init_pair(5, curses.COLOR_MAGENTA, curses.COLOR_BLACK)
	curses.init_pair(6, curses.COLOR_RED, curses.COLOR_BLACK)
	curses.init_pair(7, curses.COLOR_WHITE, curses.COLOR_BLACK)
	curses.init_pair(8, curses.COLOR_YELLOW, curses.COLOR_BLACK)

	Colors.BLACK = curses.color_pair(1)
	Colors.BLUE = curses.color_pair(2)
	Colors.CYAN = curses.color_pair(3)
	Colors.GREEN = curses.color_pair(4)
	Colors.MAGENTA = curses.color_pair(5)
	Colors.RED = curses.color_pair(6)
	Colors.WHITE = curses.color_pair(7)
	Colors.YELLOW = curses.color_pair(8)
# endregion


def print_status(screen: curses.window):
	pass


def main(screen: curses.window):
	# region Init
	curses.curs_set(False)
	curses.halfdelay(5)

	setup_colors()
	# endregion
	while True:
		print_status(screen)

		try:
			key = screen.getkey()
			if key == 'KEY_END':
				break
		except _curses.error:
			pass


if __name__ == '__main__':
	curses.wrapper(main)
