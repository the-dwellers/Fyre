package io.github.the_dwellers.fyreplugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Simple command for testing
 */
public class DebugCommand extends AbstractCommand {
	@Override
	public String getPermission() {
		return "fyre.debug.use";
	}

	@Override
	public boolean execute(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			player.setFlySpeed(1);
		}
		return true;
	}
}
