package com.github.thedwellers.fyreplugin.configuration;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class PlayerConfiguration {
	public static void createPlayerFile(File playerFile) {
		try {
			if(!playerFile.exists()) {
				playerFile.createNewFile();
				defaultTiers(playerFile);
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	private static void defaultTiers(File playerFile) {
		FileConfiguration playerConfig = YamlConfiguration.loadConfiguration(playerFile);
		playerConfig.set("Armorer", 0);
		playerConfig.set("Butcher", 0);
		playerConfig.set("Cartographer", 0);
		playerConfig.set("Cleric", 0);
		playerConfig.set("Farmer", 0);
		playerConfig.set("Fisherman", 0);
		playerConfig.set("Fletcher", 0);
		playerConfig.set("Leatherworker", 0);
		playerConfig.set("Librarian", 0);
		playerConfig.set("Mason", 0);
		playerConfig.set("Sheperd", 0);
		playerConfig.set("Toolsmith", 0);
		playerConfig.set("Weaponsmith", 0);
		try {
			playerConfig.save(playerFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
