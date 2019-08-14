package com.github.thedwellers.fyreplugin.inventory;

import com.github.thedwellers.fyreplugin.Reflected;
import com.github.thedwellers.fyreplugin.exceptions.ReflectionFailedException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Boat;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

/**
 * BoatInventoryHolder
 */
public class BoatInventoryHolder implements InventoryHolder {

	Inventory inventory;
	Boat boat;

	public BoatInventoryHolder(Boat boat) {
		this.boat = boat;
		this.inventory = Bukkit.createInventory(this, InventoryType.CHEST);
	}

	public void saveInventory() throws ReflectionFailedException {
		Reflected.writeNBT(Reflected.inventoryTo64(inventory.getContents()), "Tags", boat);
	}

	public void readInventory() throws ReflectionFailedException {
		System.out.println("read");
		ItemStack[] items = Reflected.inventoryFrom64(Reflected.getTag(Reflected.getNBTOfEntity(boat), "Tags"));
		if (items == null) {
			// No items in boat
			inventory.clear();
			return;
		}
		inventory.setContents(items);
	}

	@Override
	public Inventory getInventory() {
		return inventory;
	}

}
