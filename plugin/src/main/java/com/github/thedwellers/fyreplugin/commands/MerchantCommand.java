package com.github.thedwellers.fyreplugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MerchantCommand extends AbstractCommand {

	@Override
	public String getPermission() {
		return "fyre.merchant.use";
	}

	@Override
	public boolean execute(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			sender.sendMessage("Hello there");
			return true;
		}
		return false;
	}
}
