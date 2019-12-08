package io.github.the_dwellers.fyreplugin.events;

import io.github.the_dwellers.fyreplugin.entity.TagInventory;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;

public class BoatClick implements Listener {

	@EventHandler
	public void onPlayerInteractEntityEvent(PlayerInteractEntityEvent event) {
		if (!(event.getPlayer().isSneaking() && event.getHand() == EquipmentSlot.HAND)) {
			return;
		}

		Entity entity = event.getRightClicked();

		if (entity.getType() != EntityType.BOAT) {
			return;
		}

		TagInventory bInventory = new TagInventory(entity);
		Inventory inventory = bInventory.getInventory();
		if (inventory == null) {
			// * Remember: Open TagInventorys need to be closed with TagInventory#closeInventory()
			// * before they can be opened again.
			return;
		} else {
			event.getPlayer().openInventory(inventory);
		}
	}
}
