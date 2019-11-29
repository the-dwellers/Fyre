package com.github.thedwellers.fyreplugin.events;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.inventory.ItemStack;

/**
 * FallDamage
 */
public class FallDamage implements Listener{

	@EventHandler()
	public void onFallDamage(EntityDamageEvent event) {
		if (!(event.getEntity() instanceof LivingEntity)) {
			return;
		}
		LivingEntity entity = (LivingEntity) event.getEntity();
		if (event.getCause() != DamageCause.FALL) {
			return;
		}

		Block impactBlock = event.getEntity().getLocation().getBlock().getRelative(BlockFace.DOWN);
		if (impactBlock.getType() == Material.GLASS) {
			impactBlock.breakNaturally();

			event.setCancelled(true);
			entity.damage(0.5);

			for (BlockFace face : BlockFace.values()) {
				Block block = impactBlock.getRelative(face);
				if (block.getType() == Material.GLASS) {
					block.breakNaturally(new ItemStack(Material.IRON_PICKAXE));
					block.getWorld().playSound(block.getLocation(), Sound.BLOCK_GLASS_BREAK, 1, 1);
				}
			}
		}
	}
}
