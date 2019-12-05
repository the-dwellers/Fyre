package com.github.thedwellers.fyreplugin.configuration;

import com.github.thedwellers.fyreplugin.FyrePlugin;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;

public class PlayerConfiguration {
	private static FyrePlugin plugin = FyrePlugin.getInstance();
	private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
	private static File base = new File(plugin.getDataFolder(), "player_data");
}
