package com.github.thedwellers.fyreplugin.commands;

import com.github.thedwellers.fyreplugin.FyrePlugin;
import com.github.thedwellers.fyreplugin.configuration.Strings;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Provides common functionality to Fyre's commands
 */
public abstract class AbstractCommand implements CommandExecutor {
	protected FyrePlugin plugin = FyrePlugin.getInstance();

	public abstract String getPermission();

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!sender.hasPermission(getPermission())) {
			sender.sendMessage(Strings.NO_PERMISSION_COMMAND);
			return true;
		}

		return execute(sender, command, label, args);
	}

	public abstract boolean execute(CommandSender sender, Command command, String label, String[] args);
}
