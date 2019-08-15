package com.github.thedwellers.fyreplugin.configuration;

import com.github.thedwellers.fyreplugin.FyrePlugin;

import java.io.File;

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
		}
	}

	public static String getMerchantTier(String merchantType, String uuid){
		File playerFile = new File(dataFolder + File.separator + "player_data" + File.separator + uuid + ".yml");
		FileConfiguration playerTiers = YamlConfiguration.loadConfiguration(playerFile);

		return playerTiers.get(merchantType).toString();
	}
}
