package com.github.thedwellers.fyreplugin;

import org.bukkit.plugin.java.JavaPlugin;
import com.github.thedwellers.fyreplugin.commands.MerchantCommand;

public final class FyrePlugin extends JavaPlugin {

	@Override
	public void onEnable() {
		this.getCommand("merchant").setExecutor(new MerchantCommand());
	}

	@Override
	public void onDisable() {
	}

}
