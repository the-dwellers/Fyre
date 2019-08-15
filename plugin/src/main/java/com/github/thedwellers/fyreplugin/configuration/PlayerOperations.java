package com.github.thedwellers.fyreplugin.configuration;

import java.io.File;

import com.github.thedwellers.fyreplugin.FyrePlugin;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Villager;

public class PlayerOperations {;
	private static File dataFolder = FyrePlugin.getInstance().getDataFolder();
	
	//TODO: Seperated file existence check and file creation into two methods, not sure if needed.
	public static void configurePlayerData(String uuid) {
		File playerFile = new File(dataFolder + File.separator + "player_data" + File.separator + uuid + ".yml");
		PlayerConfiguration.createPlayerFile(playerFile);
	}

	public static boolean playerFileExists(String uuid) {
		File playerFile = new File(dataFolder + File.separator + "player_data" + File.separator + uuid + ".yml");
		if(playerFile.exists()) {
			return true;
		}
		return false;
	}

	public static String getMerchantTier(String merchantType, String uuid){
		File playerFile = new File(dataFolder + File.separator + "player_data" + File.separator + uuid + ".yml");
		FileConfiguration playerTiers = YamlConfiguration.loadConfiguration(playerFile);

		return playerTiers.get(merchantType).toString();
	}
}
