package com.github.thedwellers.fyreplugin.commands;

import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class MerchantCommand extends AbstractCommand {

	public MerchantCommand(JavaPlugin plugin){
		super(plugin);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			sender.sendMessage("Hello there");
			return true;
		}
		return false;
	}
}
