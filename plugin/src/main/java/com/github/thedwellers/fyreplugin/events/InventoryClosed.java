package com.github.thedwellers.fyreplugin.events;

import com.github.thedwellers.fyreplugin.entity.TagInventory;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

/**
 * InventoryClosed
 */
public class InventoryClosed implements Listener {

	@EventHandler()
	public void onInventoryClosed(InventoryCloseEvent event) {
		if (event.getInventory().getHolder() != null && event.getInventory().getHolder().getClass() != TagInventory.class) {
			// Only handle the event if the inventory holder is a TagInventory
			return;
		}
		TagInventory inv = (TagInventory) event.getInventory().getHolder();
		if (inv != null) {
			inv.closeInventory();
		}
	}
}
