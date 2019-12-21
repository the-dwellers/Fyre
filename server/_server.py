import threading


class ServerThread(threading.Thread):
	def __init__(self):
		super().__init__()

	def start(self):
		super().start()

	def run(self):
		pass
