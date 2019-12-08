package io.github.the_dwellers.fyreplugin.configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.github.the_dwellers.fyreplugin.FyrePlugin;

import java.io.File;

public class PlayerConfiguration {
	private static FyrePlugin plugin = FyrePlugin.getInstance();
	private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
	private static File base = new File(plugin.getDataFolder(), "player_data");
}
