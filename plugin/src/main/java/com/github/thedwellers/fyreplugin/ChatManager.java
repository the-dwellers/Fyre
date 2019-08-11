package com.github.thedwellers.fyreplugin;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;

/**
 * Functions dedicated to chat manipulation and formatting
 */
public abstract class ChatManager {

	public static void sendPlayerMessage(Player player, TextComponent message){
		// * This is also called from commands and logic that require impersonating the user!
		// * Any edits should keep this in mind!
		// TODO: Vault intergration + Correct chat handling (however that may be)

		TextComponent prefix = new TextComponent(""+ChatColor.DARK_GREEN);
		TextComponent suffix = new TextComponent(ChatColor.RESET+": ");
		TextComponent text = new TextComponent();

		text.addExtra(prefix);
		text.addExtra(player.getDisplayName());
		text.addExtra(suffix);
		text.addExtra(message);

		Bukkit.getServer().broadcast(text);
		Bukkit.getConsoleSender().sendMessage(text);
	}

	public static void sendPlayerMessage(Player player, String message){
		sendPlayerMessage(player, new TextComponent(message));
	}
}
