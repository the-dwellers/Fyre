package io.github.the_dwellers.fyreplugin.commands;

import io.github.the_dwellers.fyreplugin.Reflected;
import io.github.the_dwellers.fyreplugin.configuration.Items;
import io.github.the_dwellers.fyreplugin.exceptions.ReflectionFailedException;
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
			player.getInventory().addItem(Items.getSplinters());
			try {
				System.out.println(Reflected.itemStackToNBT(Items.getSplinters()));
			} catch (ReflectionFailedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return true;
	}
}
