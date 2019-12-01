package com.github.thedwellers.fyreplugin.configuration;

import com.github.thedwellers.fyreplugin.FyrePlugin;
import com.github.thedwellers.fyreplugin.model.MerchantModel;
import com.github.thedwellers.fyreplugin.util.FileUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bukkit.entity.Villager;

import java.io.File;
import java.io.IOException;

public class PlayerOperations {
	private static FyrePlugin plugin = FyrePlugin.getInstance();
	private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
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

	public static int getMerchantTier(Villager.Profession merchantType, String uuid) {

		MerchantModel merchant = null;

		try {
			String json = FileUtil.readAllText(new File(playerData, uuid + File.separator + "merchant.json"));
			merchant = gson.fromJson(json, MerchantModel.class);

			switch (merchantType) {
				case ARMORER:
					return merchant.getArmorer();
				case BUTCHER:
					return merchant.getButcher();
				case CARTOGRAPHER:
					return merchant.getCartographer();
				case CLERIC:
					return merchant.getCleric();
				case FARMER:
					return merchant.getFarmer();
				case FISHERMAN:
					return merchant.getFisherman();
				case FLETCHER:
					return merchant.getFletcher();
				case LEATHERWORKER:
					return merchant.getLeatherworker();
				case LIBRARIAN:
					return merchant.getLibrarian();
				case MASON:
					return merchant.getMason();
				case SHEPHERD:
					return merchant.getShepherd();
				case TOOLSMITH:
					return merchant.getToolsmith();
				case WEAPONSMITH:
					return merchant.getWeaponsmith();
				case NONE:
					return 0;
				default:
					return 0;
			}

		} catch (IOException e) {
			e.printStackTrace();
			return 0;
		}
	}
}
