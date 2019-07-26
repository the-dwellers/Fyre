package com.github.thedwellers.fyreplugin.configuration;

import java.io.File;

import com.github.thedwellers.fyreplugin.commands.AbstractEvent;

import org.bukkit.plugin.java.JavaPlugin;

public class PlayerOperations extends AbstractEvent {

	public PlayerOperations(JavaPlugin plugin) {
		super(plugin);
	}

	public static void configureDataFile(String uuid){
		//String test = plugin.getDataFolder();
	}
}
