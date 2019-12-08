package io.github.the_dwellers.fyreplugin.configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.github.the_dwellers.fyreplugin.FyrePlugin;

import java.io.File;

public class PlayerOperations {
	private static FyrePlugin plugin = FyrePlugin.getInstance();
	private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
	private static File playerData = new File(plugin.getDataFolder(), "player_data");

	public static void initializePlayerConfiguration(String uuid) {
		File folder = new File(playerData, uuid);
		if (!folder.exists()) {
			folder.mkdirs();
		}
	}
}
