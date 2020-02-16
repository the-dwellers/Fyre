package io.github.the_dwellers.fyreplugin.events;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

/**
 * CropTrample
 */
public class CropTrample implements Listener {

	@EventHandler()
	public static void onEntityCropTrample(EntityInteractEvent event) {
		// ? Why does EntityInteractEvent not fire for players?
		Block block = event.getBlock();
		Entity entity = event.getEntity();
		if (block != null && event.getEntity() instanceof LivingEntity) {
			if (block.getType() == Material.FARMLAND) {
				preventCropTrample(event, (LivingEntity) entity);
			}
		}
	}

	@EventHandler()
	public static void onPlayerCropTrample(PlayerInteractEvent event) {
		Block block = event.getClickedBlock();
		Player player = event.getPlayer();
		if (block != null && block.getType() == Material.FARMLAND && event.getAction() == Action.PHYSICAL) {
			preventCropTrample(event, (LivingEntity) player);
		}
	}

	public static void preventCropTrample(Cancellable event, LivingEntity entity) {
		ItemStack boots = entity.getEquipment().getBoots();
		if (boots == null) {
			return;
		} else if (boots.getType() == Material.LEATHER_BOOTS) {
			event.setCancelled(true);
		}
	}
}
