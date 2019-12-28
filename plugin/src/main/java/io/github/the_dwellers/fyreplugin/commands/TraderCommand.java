package io.github.the_dwellers.fyreplugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Villager.Profession;

/**
 * Simple command for testing
 */
public class TraderCommand extends AbstractCommand {
	@Override
	public String getPermission() {
		return "fyre.trader.use";
	}

	@Override
	public boolean execute(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (args.length != 1) {
				player.sendMessage("No arg 1");
				return true;
			}
			Villager villager = (Villager) player.getLocation().getWorld().spawnEntity(player.getEyeLocation(), EntityType.VILLAGER);
			villager.setProfession(Profession.valueOf(args[0]));
		}
		return true;
	}
}
