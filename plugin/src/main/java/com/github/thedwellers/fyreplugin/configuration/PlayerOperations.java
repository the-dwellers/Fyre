package com.github.thedwellers.fyreplugin.configuration;

import com.github.thedwellers.fyreplugin.FyrePlugin;

import java.io.File;

<<<<<<< HEAD
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
=======
public class PlayerOperations {
	private static FyrePlugin plugin = FyrePlugin.getInstance();
	private static File playerData = new File(plugin.getDataFolder(), "player_data");
	
	public static void initializePlayerConfiguration(String uuid) {
		File folder = new File(playerData, uuid);
		if (!folder.exists()) {
			folder.mkdirs();
		}
		
		File bank = new File(folder, "bank.json");
		if (!bank.exists()) {
			PlayerConfiguration.createPlayerBankConfiguration(uuid);
		}
		
		File merchant = new File(folder, "merchant.json");
		if (!merchant.exists()) {
			PlayerConfiguration.createPlayerMerchantConfiguration(uuid);
>>>>>>> 4a0c3a57af020de911603851cab42fab7d2c2cf1
		}
	}

	public static String getMerchantTier(String merchantType, String uuid){
		File playerFile = new File(dataFolder + File.separator + "player_data" + File.separator + uuid + ".yml");
		FileConfiguration playerTiers = YamlConfiguration.loadConfiguration(playerFile);

		return playerTiers.get(merchantType).toString();
	}
}
