import curses


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
