# Fyre Plugin

The Fyre Plugin is a helper plugin to implement features such as server
administration tools, merchant functionality, and a few other features.

---

- [Fyre Plugin](#fyre-plugin)
	- [Technical](#technical)
		- [Setting up the development server](#setting-up-the-development-server)

---

## Technical

The Plugin is built using Paper-api `1.14.4-R0.1-SNAPSHOT`
With gradle for compilation and building.

### Setting up the development server

> Depending on your operating system, use *.bat or *.sh scripts.
>
> For *nix users, ensure *.sh are executable

1. Firstly, ensure you are in the `plugin/` directory of the github (same as this file)
2. Run the `newserver` script, which will automatically download paper from https://papermc.io/
	> Please note that running `newserver` expects an empty `server/` folder. (or none at all)
	1. The `newserver` script will use `curl` to download the file, and then `java` to start the server
	2. You should see the started server fails to start due to an unaccepted EULA
	3. If you agree to [Minecraft's Server EULA](https://account.mojang.com/documents/minecraft_eula) Press Enter, which will automatically accept the eula
3. After the server is created, run the `startserver` script to compile the plugin and start the server
	1. The `startserver` scripts runs `gradlew build` and moves the output to the `plugins` directory of the server
	2. The script also moves the datapack from `/datapack` to the server's main world datapack folder
4. The server is started!

If you change the plugin, just stop the server and use `startserver` again.

To compile the plugin without starting the server, use `gradlew build`
