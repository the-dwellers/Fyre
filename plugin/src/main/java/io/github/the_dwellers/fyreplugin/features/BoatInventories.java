package io.github.the_dwellers.fyreplugin.features;

import io.github.the_dwellers.fyreplugin.FyrePlugin;
import io.github.the_dwellers.fyreplugin.features.tagdata.TagInventory;
import io.github.the_dwellers.fyreplugin.Feature;
import io.github.the_dwellers.fyreplugin.features.tagdata.TagInventoryFeature;
import io.github.the_dwellers.fyreplugin.util.MinecraftVersion;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Vehicle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.vehicle.VehicleDestroyEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * Allow boats to open as chests on sneak right-click, ensures only one player
 * can open at once and data is saved correctly.
 */
public class BoatInventories extends Feature implements Listener {

	public static MinecraftVersion minVersion = TagInventoryFeature.minVersion;

	protected boolean enabled = false;
	protected static String name = "Chest Boats";
	private static BoatInventories featureInstance;

	public static BoatInventories getInstance() {
		if (featureInstance == null) {
			featureInstance = new BoatInventories();
		}
		return featureInstance;
	}

	@Override
	public MinecraftVersion getMinecraftVersion() {
		return minVersion;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public boolean setup(FyrePlugin plugin) {
		if (TagInventoryFeature.getInstance().isEnabled()) {
			plugin.getServer().getPluginManager().registerEvents(this, plugin);
			enabled = true;
		}
		return enabled;
	}

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
			// * Remember: Open TagInventorys need to be closed with
			// TagInventory#closeInventory()
			// * before they can be opened again.
			return;
		} else {
			event.getPlayer().openInventory(inventory);
		}
	}

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
