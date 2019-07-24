package com.github.thedwellers.fyreplugin;

import org.bukkit.plugin.java.JavaPlugin;
import com.github.thedwellers.fyreplugin.commands.MerchantCommand;
import com.github.thedwellers.fyreplugin.commands.StatusCommand;

public final class FyrePlugin extends JavaPlugin {

	@Override
	public void onEnable() {
		this.getCommand("merchant").setExecutor(new MerchantCommand());
		this.getCommand("status").setExecutor(new StatusCommand(this));
	}

	@Override
	public void onDisable() {
	}

}
