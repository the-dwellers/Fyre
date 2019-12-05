package com.github.thedwellers.fyreplugin.events;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 * BrickHit
 */
public class BrickHit implements Listener {

	@EventHandler(priority = EventPriority.HIGH)
	public void onBrickHit(EntityDamageByEntityEvent event) {
		if (event.getDamager().getType() != EntityType.SNOWBALL) {
			return;
		}
		if (event.getDamager().getPassengers().size() != 1) {
			return;
		}
		if (event.getDamager().getPassengers().get(0).getType() == EntityType.DROPPED_ITEM) {
			Item item = (Item) event.getDamager().getPassengers().get(0);
			if (item.getItemStack().getType() == Material.BRICK) {
				event.setDamage(4.0);
			}
		}
	}
}
