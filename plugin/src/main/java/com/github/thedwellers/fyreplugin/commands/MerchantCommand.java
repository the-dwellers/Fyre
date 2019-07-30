package com.github.thedwellers.fyreplugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Spawn a merchant that trade with players, depending on their merchant levels.
 * Each player will view a screen specific to that player.
 */
public class MerchantCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			sender.sendMessage("Hello there");
			return true;
		}
		return false;
	}
}
