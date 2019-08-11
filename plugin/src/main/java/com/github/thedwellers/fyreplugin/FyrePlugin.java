package com.github.thedwellers.fyreplugin;

import com.github.thedwellers.fyreplugin.commands.*;
import com.github.thedwellers.fyreplugin.events.*;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.thedwellers.fyreplugin.configuration.ServerOperations;

/**
 * The Fyre Plugin is a helper plugin to implement features such as server
 * administration tools, merchant functionality, and a few other features.
 *
 * For more information about the Fyre project please view the documentation
 * @see https://github.com/the-dwellers/Fyre
 * @author WYVERN, Brandagot, Charged Byte
 * @version 0.1
 */
public final class FyrePlugin extends JavaPlugin {

	private static FyrePlugin instance;

	public FyrePlugin() {
		instance = this;
	}

	@Override
	public void onEnable() {
		serverSetUp();
		registerCommands();
		registerListeners();
	}

	@Override
	public void onDisable() {
	}

	/**
	 * Registers all commands provided by the Fyre plugin.
	 * <p>
	 * Also attempts to replace {@code bukkit:plugins} with {@code fyre:plugins}
	 */
	private void registerCommands() {
		this.getCommand("merchant").setExecutor(new MerchantCommand());
		this.getCommand("status").setExecutor(new StatusCommand());
		this.getCommand("list").setExecutor(new ListCommand());
		this.getCommand("bank").setExecutor(new BankCommand());
		this.getCommand("money").setExecutor(new MoneyCommand());
		this.getCommand("item").setExecutor(new ItemCommand());

		// Remove Bukkit plugin command
		getServer().getCommandMap().getCommand("plugins");
		this.getCommand("plugins").setExecutor(new PluginsCommand());
	}

	/**
	 * Register all event listeners provided by the Fyre plugin.
	 */
	private void registerListeners() {
		getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
		getServer().getPluginManager().registerEvents(new PlayerPreProcessorCommand(), this);
		getServer().getPluginManager().registerEvents(new BlockBreak(), this);
		getServer().getPluginManager().registerEvents(new MerchantClick(), this);
		getServer().getPluginManager().registerEvents(new BankerClick(), this);

	}

	private void serverSetUp(){
		ServerOperations.createPlayerFolder(this.getDataFolder());
	}

	public static FyrePlugin getInstance() {
		return instance;
	}
}
