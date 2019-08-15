package com.github.thedwellers.fyreplugin.events;

import com.github.thedwellers.fyreplugin.configuration.Strings;
import com.github.thedwellers.fyreplugin.exceptions.ReflectionFailedException;
import com.github.thedwellers.fyreplugin.inventory.BoatInventoryHolder;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

/**
 * InventoryClose
 */
public class InventoryClose implements Listener {
	@EventHandler()
	public void onPlayerInteractEntityEvent(InventoryCloseEvent event) {
		if (event.getInventory().getHolder() instanceof BoatInventoryHolder) {
			BoatInventoryHolder boat = (BoatInventoryHolder) event.getInventory().getHolder();
			try {
				boat.saveInventory();
			} catch (ReflectionFailedException e) {
				event.getPlayer().sendMessage(
						Strings.OUT_PREFIX + Strings.C_ERROR + "Failed to save boat inventory: " + e.getMessage());
				e.printStackTrace();
			}
		}
	}
}
