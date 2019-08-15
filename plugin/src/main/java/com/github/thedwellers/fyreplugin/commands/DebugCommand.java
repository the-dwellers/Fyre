package com.github.thedwellers.fyreplugin.commands;

import com.github.thedwellers.fyreplugin.Reflected;
import com.github.thedwellers.fyreplugin.configuration.Strings;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * Simple command for testing
 */
public class DebugCommand extends AbstractCommand {
	@Override
	public boolean execute(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			try {
				ItemStack[] items = Reflected.nbtToInventory("{[{id:\"minecraft:diamond_chestplate\",Count:1b}, {id:\"minecraft:diamond_axe\",Count:1b}, {id:\"minecraft:dirt\",Count:32b}]}");
				Inventory inv = Bukkit.createInventory(null, InventoryType.CHEST);
				inv.setContents(items);
				player.openInventory(inv);

			} catch (Exception e) {
				player.sendMessage(Strings.OUT_PREFIX+Strings.C_ERROR+"Reflection failed: "+e.getMessage());
			}
		}
		return true;
	}
}
