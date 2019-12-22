def _action_quit():
	# TODO: Clean exit
	exit(0)


def get() -> []:
	return [{
		"key": "0",
		"name": "Quit",
		"func": _action_quit
	}]
