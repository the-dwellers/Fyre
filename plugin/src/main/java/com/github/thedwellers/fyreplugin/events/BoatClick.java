package com.github.thedwellers.fyreplugin.events;

import com.github.thedwellers.fyreplugin.entity.TagInventory;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;

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

		// TODO: Find a solution that fixes multiple players using the same inventory
		// ! Currently the last closed inventory will overwrite any changes made by other players
		// ? Perhaps look into preventing players opening the inventory while it's already open

		TagInventory bInventory = new TagInventory(entity);
		event.getPlayer().openInventory(bInventory.getInventory());
	}
}
