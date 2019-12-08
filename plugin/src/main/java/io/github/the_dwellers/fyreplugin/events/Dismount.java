package io.github.the_dwellers.fyreplugin.events;

import org.bukkit.entity.AbstractHorse;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.spigotmc.event.entity.EntityDismountEvent;

/**
 * Dismount
 */
public class Dismount implements Listener {

	@EventHandler()
	public void onDismount(EntityDismountEvent event) {
		if (!(event.getDismounted() instanceof AbstractHorse) || event.getEntity().getType() != EntityType.PLAYER) {
			return;
		}

		// Player has dismounted a horse
		AbstractHorse horse = (AbstractHorse) event.getDismounted();

		if (horse.getInventory().getSaddle() != null) {
			// Horse is wearing a saddle, disable the ai
			horse.setAI(false);
		} else {
			// horse is not wearing a saddle, enable the ai
			horse.setAI(true);
		}
	}
}
