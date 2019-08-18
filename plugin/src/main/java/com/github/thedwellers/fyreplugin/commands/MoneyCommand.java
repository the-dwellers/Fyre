package com.github.thedwellers.fyreplugin.commands;

import com.github.thedwellers.fyreplugin.configuration.Items;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * Gives the player 1 Silver Coin
 */
public class MoneyCommand extends AbstractCommand {
	@Override
	public String getPermission() {
		return "fyre.money.use";
	}

	@Override
	public boolean execute(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			player.getInventory().addItem(Items.getSilverCoin());
		}
		return true;
	}
}
