import curses

BLACK = None
BLUE = None
CYAN = None
GREEN = None
MAGENTA = None
RED = None
WHITE = None
YELLOW = None


def init():
	global BLACK
	global BLUE
	global CYAN
	global GREEN
	global MAGENTA
	global RED
	global WHITE
	global YELLOW

	curses.init_pair(1, curses.COLOR_BLACK, curses.COLOR_BLACK)
	curses.init_pair(2, curses.COLOR_BLUE, curses.COLOR_BLACK)
	curses.init_pair(3, curses.COLOR_CYAN, curses.COLOR_BLACK)
	curses.init_pair(4, curses.COLOR_GREEN, curses.COLOR_BLACK)
	curses.init_pair(5, curses.COLOR_MAGENTA, curses.COLOR_BLACK)
	curses.init_pair(6, curses.COLOR_RED, curses.COLOR_BLACK)
	curses.init_pair(7, curses.COLOR_WHITE, curses.COLOR_BLACK)
	curses.init_pair(8, curses.COLOR_YELLOW, curses.COLOR_BLACK)

	BLACK = curses.color_pair(1)
	BLUE = curses.color_pair(2)
	CYAN = curses.color_pair(3)
	GREEN = curses.color_pair(4)
	MAGENTA = curses.color_pair(5)
	RED = curses.color_pair(6)
	WHITE = curses.color_pair(7)
	YELLOW = curses.color_pair(8)
