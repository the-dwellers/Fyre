# Fyre Plugin

The Fyre Plugin is a helper plugin to implement features such as server
administration tools, merchant functionality, and a few other features.

---

- [Fyre Plugin](#fyre-plugin)
	- [Technical](#technical)
		- [Setting up the development server](#setting-up-the-development-server)
			- [Setup with Python (Recommended)](#setup-with-python-recommended)
			- [Setup with Shell script](#setup-with-shell-script)
			- [Setup with Batch Script](#setup-with-batch-script)
		- [Building](#building)

---

## Technical

The Plugin is built using Paper-api `1.14.4-R0.1-SNAPSHOT`
With gradle for compilation and building.

### Setting up the development server

> NOTICE: If you were using the old `.sh` or `.bat` files it is recommended that you delete the `server` directory created by those scripts
>
> This is not mandatory but it helps to make sure nothing conflicts when running the new scripts

With these scripts it doesn't matter from which directory the scripts are called from.

Before running any of the scripts make sure your Java is available in the PATH variable
(If running `java -version` in your terminal or cmd works you should be good to go)

All the scripts work essentially in the same way

1. The `server` directory will be created
2. Then the plugin will be compiled and copied to the server's `plugins` directory
3. After this the script will ask you to accept the [Minecraft EULA](https://account.mojang.com/documents/minecraft_eula)
	1. You must accept the EULA if you wish to continue
	2. Declining will cause the script to stop
4. Now the script will copy the datapack(s) from the folder in to the server
5. After this the script will update the `server.jar` ([PaperMC](https://papermc.io)) if needed
	> An exception to this is the `dev_server.bat` script that will always download the latest version, even if an update is not required
	> This is due to the lack of knowledge I have of Batch Script
6. Finally the server gets started
7. The server should now be running

#### Setup with Python (Recommended)

> Script: `dev_server.py`
> Requirements: Python 3 (Tested with 3.7.3)

Simply run the script with Python
For example:

```$ python3 dev_server.py```

or

```$ python3 plugin/dev_server.py```

#### Setup with Shell script

> Script: `dev_server.sh`
> Requirements: `curl` and `md5sum`

1. This script relies on few tools that need to be available on the system: `curl` and `md5sum`
2. Make sure the script is executable by running `chmod +x dev_server.sh`
3. Run the script to start the server
   ```$ ./dev_server.sh```
   or
   ```$ bash ./dev_server.sh```

#### Setup with Batch Script

> Script: `dev_server.bat`
> Requirements: `curl`

Simply run the script:
```> dev_server.bat```

### Building

To build the plugin simply run `> gradlew.bat clean build` on Windows
and `$ ./gradlew clean build` on *nix
