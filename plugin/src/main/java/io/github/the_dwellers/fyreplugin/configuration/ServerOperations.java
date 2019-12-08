package io.github.the_dwellers.fyreplugin.configuration;


import io.github.the_dwellers.fyreplugin.FyrePlugin;

import java.io.File;

public class ServerOperations {
	private static FyrePlugin plugin = FyrePlugin.getInstance();

	public static void createPlayerDataDirectory() {
		File base = plugin.getDataFolder();
		File playerData = new File(base, "player_data");

		if (!playerData.exists()) {
			playerData.mkdirs();
		}
	}
}
