package com.github.thedwellers.fyreplugin.configuration;

import com.github.thedwellers.fyreplugin.FyrePlugin;

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
