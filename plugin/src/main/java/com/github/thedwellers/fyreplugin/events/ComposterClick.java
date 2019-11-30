package com.github.thedwellers.fyreplugin.events;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Levelled;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

/**
 * ComposterClick
 */
public class ComposterClick implements Listener {
	@EventHandler(priority = EventPriority.HIGH)
	public void onComposterClick(PlayerInteractEvent event) {
		Block block = event.getClickedBlock();

		if (block.getType() == Material.COMPOSTER) {
			if (event.getPlayer().getInventory().getItemInMainHand().getType() == Material.ROTTEN_FLESH) {
				event.getPlayer().getInventory().removeItem(new ItemStack(Material.ROTTEN_FLESH, 1));
				BlockData blockData = block.getBlockData();
				Levelled blockLevel = (Levelled) blockData;

				blockLevel.setLevel(blockLevel.getLevel() == blockLevel.getMaximumLevel() ? 0 : blockLevel.getLevel() + 1);
				block.setBlockData(blockLevel);
			}
		}
	}
}
