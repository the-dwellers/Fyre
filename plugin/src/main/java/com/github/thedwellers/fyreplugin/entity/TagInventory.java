package com.github.thedwellers.fyreplugin.entity;

import java.util.ArrayList;

import com.github.thedwellers.fyreplugin.Reflected;
import com.github.thedwellers.fyreplugin.configuration.Items;
import com.github.thedwellers.fyreplugin.exceptions.ReflectionFailedException;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

/**
 * TagInventory
 */
public class TagInventory extends TagDataHolder implements InventoryHolder {

	private Inventory inventory;
	private boolean invRead;


	public TagInventory(Entity entity) {
		super(entity);
		this.inventory = Bukkit.createInventory(this, InventoryType.CHEST);
		deserialize();
	}

	@Override
	protected void serialize() {
		ItemStack[] items = inventory.getContents();
		String invStr = "I";
		for (ItemStack item : items) {
			String itemStr = "";
			try {
				if (item != null && item.getType() != Material.AIR) {
					itemStr = Reflected.itemStackToNBT(item);
				}
			} catch (ReflectionFailedException e) {
				e.printStackTrace();
			}

			invStr += "|" + itemStr;
		}
		try {
			writeToEntity(invStr);
		} catch (ReflectionFailedException e){
			e.printStackTrace();
		}
	}

	@Override
	protected void deserialize() {
		inventory.clear();
		try {
			String nbt = readFromEntity();
			if (nbt == null) {
				// No inventory on entity
				return;
			}

			String[] sections = nbt.split("\\|");
			if (sections.length < 2) {
				// Invalid inventory, nothing to deserialize
				return;
			}

			if (!sections[0].equals("I")) {
				// Invalid inventory, nothing to deserialize
				return;
			}

			ArrayList<ItemStack> itemList = new ArrayList<ItemStack>();

			for (int i = 1; i < sections.length; i++) {
				try {
					if (sections[i].length()< 2) {
						itemList.add(new ItemStack(Material.AIR));
					} else {
						itemList.add(Reflected.nbtToItem(sections[i]));
					}
				} catch (ReflectionFailedException | IllegalArgumentException e) {
					itemList.add(Items.getErrorItem());
				}
			}
			inventory.setContents(itemList.toArray(new ItemStack[itemList.size()]));
		} catch (ReflectionFailedException e) {
			// something broke, print stacktrace and display error block
			inventory.setItem(0, Items.getErrorItem());
			e.printStackTrace();
			return;
		}
	}

	@Override
	public Inventory getInventory() {
		if (!invRead) {
			readInventory();
			invRead = true;
		}
		return inventory;
	}

	public void readInventory() {
		deserialize();
	}

	public void writeInventory() {
		serialize();
	}

}
