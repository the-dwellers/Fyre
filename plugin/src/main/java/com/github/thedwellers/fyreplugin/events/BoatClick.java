package com.github.thedwellers.fyreplugin.events;

import com.github.thedwellers.fyreplugin.configuration.Strings;
import com.github.thedwellers.fyreplugin.inventory.BoatInventoryHolder;

import org.bukkit.entity.Boat;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;

/**
 * BoatClick
 */
public class BoatClick implements Listener {
	@EventHandler()
	public void onPlayerInteractEntityEvent(PlayerInteractEntityEvent event) {
		if (event.getHand() != EquipmentSlot.HAND) {
			// Only process for main hand (run once)
			return;
		}
		if (event.getRightClicked().getType() != EntityType.BOAT || !event.getPlayer().isSneaking()) {
			return;
		}

		Player player = event.getPlayer();
		Boat boat = (Boat) event.getRightClicked();

		try {
			BoatInventoryHolder boatHolder = new BoatInventoryHolder(boat);
			boatHolder.readInventory();
			player.openInventory(boatHolder.getInventory());

		} catch (Exception e) {
			player.sendMessage(Strings.OUT_PREFIX + Strings.C_ERROR + "Reflection failed: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
