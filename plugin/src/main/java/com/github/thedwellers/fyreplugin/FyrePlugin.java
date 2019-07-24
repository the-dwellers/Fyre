package com.github.thedwellers.fyreplugin;

import org.bukkit.plugin.java.JavaPlugin;
import com.github.thedwellers.fyreplugin.commands.MerchantCommand;
<<<<<<< HEAD
import com.github.thedwellers.fyreplugin.events.PlayerJoin;
=======
import com.github.thedwellers.fyreplugin.commands.StatusCommand;
>>>>>>> 3ee63cda1b7d43a2d2f9b0ecf9ca3c175ce541c4

public final class FyrePlugin extends JavaPlugin {

	@Override
	public void onEnable() {
<<<<<<< HEAD
		registerCommands();
		registerListeners();
=======
		this.getCommand("merchant").setExecutor(new MerchantCommand());
		this.getCommand("status").setExecutor(new StatusCommand(this));
>>>>>>> 3ee63cda1b7d43a2d2f9b0ecf9ca3c175ce541c4
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
