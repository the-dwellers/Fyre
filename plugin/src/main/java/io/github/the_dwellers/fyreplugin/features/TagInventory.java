package io.github.the_dwellers.fyreplugin.features;

import io.github.the_dwellers.fyreplugin.FyrePlugin;
import io.github.the_dwellers.fyreplugin.configuration.Items;
import io.github.the_dwellers.fyreplugin.exceptions.ReflectionFailedException;
import io.github.the_dwellers.fyreplugin.features.AbstractFeature;
import io.github.the_dwellers.fyreplugin.features.NBTAdapter;
import io.github.the_dwellers.fyreplugin.util.MinecraftVersion;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/**
 * Represents an entity that contains an inventory defined within their 'Tags'
 * nbt Tag.
 */
public class TagInventory extends TagDataHolder implements InventoryHolder, AbstractFeature {
	public static MinecraftVersion minVersion = NBTAdapter.minVersion;

	protected boolean enabled = false;
	protected static String name = "NBT TagInventory";


	@Override
	public MinecraftVersion getMinecraftVersion() {
		return minVersion;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	@Override
	public boolean setup(FyrePlugin plugin) {
		return NBTAdapter.getInstance().isEnabled();
	}

	@Override
	public String getName() {
		return name;
	}

	private static HashSet<String> instances = new HashSet<String>();
	private Inventory inventory;

	public TagInventory(Entity entity) {
		super(entity);
		if (instances == null) {
			instances = new HashSet<String>();
		}

		this.inventory = Bukkit.createInventory(this, 18);
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
					itemStr = NBTAdapter.itemStackToNBT(item);
				}
			} catch (ReflectionFailedException e) {
				e.printStackTrace();
			}

			invStr += "|" + itemStr;
		}
		try {
			writeToEntity(invStr);
		} catch (ReflectionFailedException e) {
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
					if (sections[i].length() < 2) {
						itemList.add(new ItemStack(Material.AIR));
					} else {
						itemList.add(NBTAdapter.nbtToItem(sections[i]));
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
		if (instances.contains(entity.getUniqueId().toString())) {
			return null;
		}

		instances.add(entity.getUniqueId().toString());

		readInventory();
		return inventory;
	}

	/**
	 * Update inventory stored from the entity.
	 */
	private void readInventory() {
		deserialize();
	}

	/**
	 * Close and save the current inventory to the entity.
	 */
	public void closeInventory() {
		instances.remove(entity.getUniqueId().toString());
		serialize();
	}

	public void forceCloseInventory() {
		List<HumanEntity> viewers = inventory.getViewers();

		Iterator<HumanEntity> it = viewers.iterator();
		while (it.hasNext()) {
			it.next().closeInventory();
		}
	}
}
