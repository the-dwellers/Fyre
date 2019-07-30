package com.github.thedwellers.fyreplugin.commands;

import com.github.thedwellers.fyreplugin.configuration.Items;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * MoneyCommand
 */
public class MoneyCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			player.getInventory().addItem(Items.getSilverCoin());
		}
		return true;
	}
}
