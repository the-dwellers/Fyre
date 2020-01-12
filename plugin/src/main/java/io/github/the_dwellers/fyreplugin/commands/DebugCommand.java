package io.github.the_dwellers.fyreplugin.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

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
			ItemStack hoe = new ItemStack(Material.DIAMOND_HOE, 1);
			Damageable hoeMeta = (Damageable) hoe.getItemMeta();
			hoeMeta.setDamage(Material.DIAMOND_HOE.getMaxDurability() - 3);
			hoe.setItemMeta((ItemMeta) hoeMeta);
			player.getInventory().addItem(hoe);
		}
		return true;
	}
}
