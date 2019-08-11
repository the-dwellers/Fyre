package com.github.thedwellers.fyreplugin.commands;

import com.github.thedwellers.fyreplugin.ChatManager;
import com.github.thedwellers.fyreplugin.Reflected;
import com.github.thedwellers.fyreplugin.exceptions.ReflectionFailedException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.HoverEvent.Action;

/**
 * ItemCommand
 */
public class ItemCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			ItemStack item = ((Player) sender).getInventory().getItemInMainHand();

			try {
				String nbt = Reflected.stackToJson(item);
				TextComponent text;
				int amount = 0;

				// Oh look, multiple methods to get player inventory contents...
				for (ItemStack stack : player.getInventory().getContents()) {
					if (stack!=null && stack.isSimilar(item)) {
						amount += stack.getAmount();
					}
				}

				String itemName = item.getItemMeta().hasDisplayName() ? item.getItemMeta().getDisplayName() : item.getI18NDisplayName();

				// Check to see if the item has a custom colored name. this includes enchanted and renamed items
				// * Due to java optimization, we can bundle in the display name check using logical and for null checks
				boolean customItem = itemName.toCharArray()[0] == '\u00a7' && itemName.toCharArray()[1] != 'o';

				// Default to a light green color
				String color = "\u00a7a";

				if (customItem) {
					// Item has a custom name / color
					color = String.valueOf(new char[]{'\u00a7',itemName.toCharArray()[1]});
				} else {
					// If the item is not a custom item, then display the default name to chat, rather than the rename
					itemName = item.getI18NDisplayName();
				}

				if (amount == 1) {
					text = new TextComponent(
							color + ChatColor.BOLD + "[" + itemName + "]");
				} else {
					text = new TextComponent(color + ChatColor.BOLD + "[" + amount + "x "
							+ itemName + "]");
				}

				text.setHoverEvent(new HoverEvent(Action.SHOW_ITEM, new TextComponent[] { new TextComponent(nbt) }));
				ChatManager.sendPlayerMessage(player, text);
			} catch (ReflectionFailedException e) {
				player.sendMessage(e.getMessage());
				e.printStackTrace();
			}
		}
		return true;
	}
}
