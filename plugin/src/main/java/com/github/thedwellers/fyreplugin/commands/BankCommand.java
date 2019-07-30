package com.github.thedwellers.fyreplugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Summons an npc that acts as a bank to any interacting
 * player. Banks are shared inventory's specific to each player that is opened
 * from interacting with any bank trader created with this command.
 */
public class BankCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			sender.sendMessage("Hello there");
			return true;
		}
		return false;
	}
}
