package com.github.thedwellers.fyreplugin;

import com.github.thedwellers.fyreplugin.commands.*;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.thedwellers.fyreplugin.configuration.ServerOperations;
import com.github.thedwellers.fyreplugin.events.BlockBreak;
import com.github.thedwellers.fyreplugin.events.MerchantClick;
import com.github.thedwellers.fyreplugin.events.PlayerJoin;
import com.github.thedwellers.fyreplugin.events.PlayerPreProcessorCommand;

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
		this.getCommand("bank").setExecutor(new BankCommand(this));

		// Remove Bukkit plugin command
		getServer().getCommandMap().getCommand("plugins");
		this.getCommand("plugins").setExecutor(new PluginsCommand(this));
	}

	private void registerListeners() {
		getServer().getPluginManager().registerEvents(new PlayerJoin(this), this);
		getServer().getPluginManager().registerEvents(new PlayerPreProcessorCommand(this), this);
		getServer().getPluginManager().registerEvents(new BlockBreak(this), this);
		getServer().getPluginManager().registerEvents(new MerchantClick(this), this);

	}

	private void serverSetUp(){
		ServerOperations.createPlayerFolder(this.getDataFolder());
	}
}
