package com.github.thedwellers.fyreplugin.commands;

import com.github.thedwellers.fyreplugin.ChatManager;
import com.github.thedwellers.fyreplugin.Reflected;
import com.github.thedwellers.fyreplugin.exceptions.ReflectionFailedException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.bukkit.Bukkit;
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

				if (item.getAmount() == 1) {
					text = new TextComponent(ChatColor.GREEN+""+ChatColor.BOLD+"["+item.getI18NDisplayName()+"]");
				} else {
					text = new TextComponent(ChatColor.GREEN+""+ChatColor.BOLD+"["+item.getAmount()+"x "+item.getI18NDisplayName()+"]");
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
