package com.github.thedwellers.fyreplugin.commands;

import com.github.thedwellers.fyreplugin.ChatManager;
import com.github.thedwellers.fyreplugin.configuration.Strings;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Displays the currently held item to main chat
 */
public class ItemCommand extends AbstractCommand {
	@Override
	public boolean execute(CommandSender sender, Command command, String label, String[] args) {
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
