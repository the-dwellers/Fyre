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
		playerConfig.set("ARMORER", 0);
		playerConfig.set("BUTCHER", 0);
		playerConfig.set("CARTOGRAPHER", 0);
		playerConfig.set("CLERIC", 0);
		playerConfig.set("FARMER", 0);
		playerConfig.set("FISHERMAN", 0);
		playerConfig.set("FLETCHER", 0);
		playerConfig.set("LEATHERWORKER", 0);
		playerConfig.set("LIBRARIAN", 0);
		playerConfig.set("MASON", 0);
		playerConfig.set("SHEPERD", 0);
		playerConfig.set("TOOLSMITH", 0);
		playerConfig.set("WEAPONSMITH", 0);
		try {
			playerConfig.save(playerFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
