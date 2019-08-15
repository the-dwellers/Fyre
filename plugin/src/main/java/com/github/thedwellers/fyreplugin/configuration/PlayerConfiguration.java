package com.github.thedwellers.fyreplugin.configuration;

import com.github.thedwellers.fyreplugin.FyrePlugin;
import com.github.thedwellers.fyreplugin.model.BankModel;
import com.github.thedwellers.fyreplugin.model.MerchantModel;
import com.github.thedwellers.fyreplugin.util.FileUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.IOException;

public class PlayerConfiguration {
	private static FyrePlugin plugin = FyrePlugin.getInstance();
	private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
	private static File base = new File(plugin.getDataFolder(), "player_data");
	
	public static void createPlayerBankConfiguration(String uuid) {
		File folder = new File(base, uuid);
		File bank = new File(folder, "bank.json");
		
		BankModel bankConfig = new BankModel();
		String json = gson.toJson(bankConfig);
		
		try {
			FileUtil.writeAllText(bank, json);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public static void createPlayerMerchantConfiguration(String uuid) {
		File folder = new File(base, uuid);
		File merchant = new File(folder, "merchant.json");
		
		MerchantModel merchantConfig = new MerchantModel();
		String json = gson.toJson(merchantConfig);
		
		try {
			FileUtil.writeAllText(merchant, json);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
