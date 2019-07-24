package com.github.thedwellers.fyreplugin;

import org.bukkit.plugin.java.JavaPlugin;
import com.github.thedwellers.fyreplugin.commands.MerchantCommand;
import com.github.thedwellers.fyreplugin.events.PlayerJoin;

public final class FyrePlugin extends JavaPlugin {

	@Override
	public void onEnable() {
		registerCommands();
		registerListeners();
	}

	@Override
	public void onDisable() {
	}

	private void registerCommands() {
		this.getCommand("merchant").setExecutor(new MerchantCommand());
	}

	private void registerListeners() {
		getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
	}

}
