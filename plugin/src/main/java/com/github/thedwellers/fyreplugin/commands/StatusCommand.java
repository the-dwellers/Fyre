package com.github.thedwellers.fyreplugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.chat.TextComponent;

/**
 * StatusCommand
 */
public class StatusCommand extends AbstractCommand {

	public StatusCommand(JavaPlugin plugin){
		super(plugin);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		// Player[] players = plugin.getServer().getOnlinePlayers().toArray(new Player[plugin.getServer().getOnlinePlayers().size()]);
		// boolean isPlayer = sender instanceof Player;
		TextComponent responseText = new TextComponent();

		// Players
		responseText.addExtra(ListCommand.getPlayers(sender));

		// Player Ping
		// TODO: List ping and average player ping

		// Loaded Chunks
		// TODO: Show Number of Loaded Chunks

		// Plugins
		// TODO: Show Enabled and Disabled Plugins

		// TPS
		// TODO: Show Server Ticks Per Second

		// RAM
		// TODO: Show used Memory

		sender.spigot().sendMessage(responseText);
		return true;
	}

}
