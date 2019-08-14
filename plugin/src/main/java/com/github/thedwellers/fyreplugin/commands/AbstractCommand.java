package com.github.thedwellers.fyreplugin.commands;

import com.github.thedwellers.fyreplugin.FyrePlugin;
import com.github.thedwellers.fyreplugin.configuration.Strings;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Provides common functionality to Fyre's commands
 */
public abstract class AbstractCommand implements CommandExecutor {
	private FyrePlugin plugin = FyrePlugin.getInstance();

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Permission perms = plugin.getVaultPerms();
		
		if (!perms.has(sender, getPermission())) {
			sender.sendMessage(Strings.NO_PERMISSION_COMMAND);
			return true;
		}
		
		return execute(sender, command, label, args);
	}

	public abstract String getPermission();
	
	public abstract boolean execute(CommandSender sender, Command command, String label, String[] args);
}
