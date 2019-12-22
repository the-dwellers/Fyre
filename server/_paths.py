from os import path, mkdir

ROOT = path.dirname(path.realpath(__file__))
SERVER = path.join(ROOT, "server")
PLUGINS = path.join(SERVER, "plugins")
WORLD = path.join(SERVER, "world")
DATAPACK = path.join(WORLD, "datapacks")


def init():
    if not path.exists(SERVER):
        mkdir(SERVER)
        pass
    if not path.exists(PLUGINS):
        mkdir(PLUGINS)
        pass
    if not path.exists(WORLD):
        mkdir(WORLD)
        pass
    if not path.exists(DATAPACK):
        mkdir(DATAPACK)
        pass
