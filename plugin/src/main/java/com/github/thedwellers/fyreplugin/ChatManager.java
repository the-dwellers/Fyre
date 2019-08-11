package com.github.thedwellers.fyreplugin;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import net.milkbowl.vault.chat.Chat;

/**
 * Functions dedicated to chat manipulation and formatting
 */
public abstract class ChatManager {

	public static void sendPlayerMessage(Player player, TextComponent message){
		// * This is also called from commands and logic that require impersonating the user!
		// * Any edits should keep this in mind!

		TextComponent text = new TextComponent();

		Chat vaultChat = FyrePlugin.getInstance().getVaultChat();
		String prefix = "" + ChatColor.DARK_GREEN;
		String suffix = "";

		if (vaultChat != null) {
			prefix = vaultChat.getPlayerPrefix(player);
			suffix = vaultChat.getPlayerSuffix(player);
		}

		text.addExtra(prefix);
		text.addExtra(player.getDisplayName());
		text.addExtra(suffix);
		text.addExtra(ChatColor.RESET + ": ");
		text.addExtra(message);

		Bukkit.broadcast(text);
		Bukkit.getConsoleSender().sendMessage(text);
	}

	public static void sendPlayerMessage(Player player, String message){
		sendPlayerMessage(player, new TextComponent(message));
	}

}
