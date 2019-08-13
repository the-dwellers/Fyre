package com.github.thedwellers.fyreplugin.events;

import com.github.thedwellers.fyreplugin.Reflected;
import com.github.thedwellers.fyreplugin.configuration.Strings;

import org.bukkit.Bukkit;
import org.bukkit.entity.Boat;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

/**
 * BoatClick
 */
public class BoatClick implements Listener {
	@EventHandler()
	public void onPlayerInteractEntityEvent(PlayerInteractEntityEvent event) {
		if (event.getRightClicked().getType() != EntityType.BOAT || !event.getPlayer().isSneaking()) {
			return;
		}

		Player player = event.getPlayer();
		Boat boat = (Boat) event.getRightClicked();

		try {
			String data = Reflected.getNBTOfEntity(boat);
			Reflected.saveNBTToEntity(data, boat);
		} catch (Exception e) {
			player.sendMessage(Strings.OUT_PREFIX+Strings.C_ERROR + "Reflection failed: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
