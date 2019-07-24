package com.github.thedwellers.fyreplugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * StatusCommand
 */
public class StatusCommand extends AbstractCommand {

	public StatusCommand(JavaPlugin plugin){
		super(plugin);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		// Players
		// Player Ping
		// Loaded Chunks
		// Plugins
		// TPS
		// RAM
		return false;
	}

}
