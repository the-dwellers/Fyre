package com.github.thedwellers.fyreplugin.commands;

import com.github.thedwellers.fyreplugin.ChatManager;
import com.github.thedwellers.fyreplugin.configuration.Strings;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.chat.TextComponent;

/**
 * Displays the currently held item to main chat
 */
public class ItemCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			TextComponent itemText = ChatManager.getItemText(ChatManager.getDisplayStackMainHand(player));
			if (itemText == null) {
				player.sendMessage(Strings.NO_ITEM_HELD);
			} else {
				ChatManager.sendPlayerMessage(player, itemText);
			}
		}
		return true;
	}
}
