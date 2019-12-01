package com.github.thedwellers.fyreplugin.events;

import com.github.thedwellers.fyreplugin.entity.TagInventory;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Vehicle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleDestroyEvent;
import org.bukkit.inventory.ItemStack;

/**
 * VehicleDestroy
 */
public class VehicleDestroy implements Listener {

	@EventHandler()
	public void onVehicleDestroy(VehicleDestroyEvent event) {
		Vehicle vehicle = event.getVehicle();
		if (vehicle.getType() != EntityType.BOAT) {
			// Entity is not a boat
			return;
		}

		// Convert boat into tag inventory
		TagInventory taginv = new TagInventory(vehicle);

		// Force-close boat inventory
		taginv.forceCloseInventory();

		// Drop items at boat
		ItemStack[] items = taginv.getInventory().getContents();
		for (ItemStack item : items) {
			if (item != null && item.getType() != Material.AIR) {
				vehicle.getWorld().dropItemNaturally(vehicle.getLocation(), item);
			}
		}
	}
}
