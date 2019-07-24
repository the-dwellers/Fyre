package com.github.thedwellers.fyreplugin;

import org.bukkit.plugin.java.JavaPlugin;
import com.github.thedwellers.fyreplugin.commands.MerchantCommand;
import com.github.thedwellers.fyreplugin.events.PlayerJoin;
import com.github.thedwellers.fyreplugin.commands.StatusCommand;

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
		this.getCommand("merchant").setExecutor(new MerchantCommand(this));
		this.getCommand("status").setExecutor(new StatusCommand(this));
	}

	private void registerListeners() {
		getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
	}

}
