package com.github.thedwellers.fyreplugin.commands;

import com.github.thedwellers.fyreplugin.configuration.Strings;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

/**
 * ListCommand
 */
public class ListCommand extends AbstractCommand {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		sender.spigot().sendMessage(getPlayers(sender, !(sender instanceof Player)));
		return true;
	}

	public static TextComponent getPlayers(CommandSender src) {
		return getPlayers(src, false);
	}

	public static TextComponent getPlayers(CommandSender src, boolean longForm) {
		boolean isPlayer = src instanceof Player;

		Player[] players = src.getServer().getOnlinePlayers()
				.toArray(new Player[src.getServer().getOnlinePlayers().size()]);
		if (players.length == 0) {
			// No players online
			return new TextComponent(Strings.OUT_PREFIX + "There are no players online");
		}

		if (longForm) {
			// Return longform output
			return buildLongForm(players);
		}

		TextComponent text = new TextComponent();
		if (players.length == 1) {
			// English single
			text.addExtra(Strings.OUT_PREFIX + "There is " + Strings.C_ACCENT +
				players.length + Strings.C_DEFAULT + " player online");
		} else {
			text.addExtra(Strings.OUT_PREFIX + "There is " + Strings.C_ACCENT +
				players.length + Strings.C_DEFAULT + " players online");

		}

		if (!isPlayer) {
			return text;
		}

		text.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
			new TextComponent[] { buildLongForm(players) }));
		return text;
	}

	private static TextComponent buildLongForm(Player[] players) {

		if (players.length == 0) {
			return new TextComponent();
		}

		TextComponent longFormText = new TextComponent(Strings.C_DEFAULT);

		// Construct list of player names
		for (int i = 0; i < players.length; i++) {
			Player player = players[i];
			longFormText.addExtra(player.getDisplayName());

			if (i + 1 != players.length) {
				longFormText.addExtra(Strings.C_DEFAULT);
			}
		}
		return longFormText;
	}
}
