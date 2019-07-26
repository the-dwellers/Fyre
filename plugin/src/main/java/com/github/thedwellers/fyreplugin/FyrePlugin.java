package com.github.thedwellers.fyreplugin;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

import com.github.thedwellers.fyreplugin.configuration.ServerOperations;
import com.github.thedwellers.fyreplugin.commands.ListCommand;
import com.github.thedwellers.fyreplugin.commands.MerchantCommand;
import com.github.thedwellers.fyreplugin.commands.PluginCommand;
import com.github.thedwellers.fyreplugin.events.PlayerJoin;
import com.github.thedwellers.fyreplugin.commands.StatusCommand;

public final class FyrePlugin extends JavaPlugin {

	@Override
	public void onEnable() {
		serverSetUp();
		registerCommands();
		registerListeners();
	}

	@Override
	public void onDisable() {
	}

	private void registerCommands() {
		this.getCommand("merchant").setExecutor(new MerchantCommand(this));
		this.getCommand("status").setExecutor(new StatusCommand(this));
		this.getCommand("list").setExecutor(new ListCommand(this));

		// Remove Bukkit plugin command
		getServer().getCommandMap().getCommand("plugins");
		this.getCommand("plugins").setExecutor(new PluginCommand(this));
	}

	private void registerListeners() {
		getServer().getPluginManager().registerEvents(new PlayerJoin(this), this);
	}

	private void serverSetUp(){
		ServerOperations.createPlayerFolder(this.getDataFolder());
	}
}
