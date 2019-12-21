import os

root_dir = os.path.dirname(os.path.realpath(__file__))
server_dir = os.path.join(root_dir, "server")
plugins_dir = os.path.join(server_dir, "plugins")
world_dir = os.path.join(server_dir, "world")
data_pack_dir = os.path.join(world_dir, "datapacks")
