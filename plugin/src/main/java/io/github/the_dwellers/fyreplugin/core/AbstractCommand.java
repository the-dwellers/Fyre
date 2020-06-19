package io.github.the_dwellers.fyreplugin.core;

import io.github.the_dwellers.fyreplugin.configuration.Strings;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Provides common functionality of Fyre's commands.
 */
public abstract class AbstractCommand implements CommandExecutor {
	public abstract String getPermission();

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!sender.hasPermission(getPermission())) {
			sender.sendMessage(Strings.NO_PERMISSION_COMMAND);
			return true;
		}

		return execute(sender, command, label, args);
	}

	/**
	 * Executes the given command, returning its success. If false is returned, then the "usage" plugin.yml entry for this command (if defined) will be sent to the player.
	 * @param sender Source of the command
	 * @param command Command which was executed
	 * @param label Alias of the command which was used
	 * @param args Passed command arguments
	 * @return true if a valid command, otherwise false
	 */
	public abstract boolean execute(CommandSender sender, Command command, String label, String[] args);
}
