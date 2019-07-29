package com.github.thedwellers.fyreplugin.configuration;

import java.io.File;

public class PlayerOperations {

	//TODO: Seperated file existence check and file creation into two methods, not sure if needed.
	public static void configurePlayerData(String uuid, File dataFolder) {
		File playerFile = new File(dataFolder + File.separator + "player_data" + File.separator + uuid + ".yml");
		PlayerConfiguration.createPlayerFile(playerFile);
	}

	public static boolean playerFileExists(String uuid, File dataFolder) {
		File playerFile = new File(dataFolder + File.separator + "player_data" + File.separator + uuid + ".yml");
		if(playerFile.exists()) {
			return true;
		}
		return false;
	}
}
