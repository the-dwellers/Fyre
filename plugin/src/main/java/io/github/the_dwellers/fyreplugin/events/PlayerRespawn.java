package io.github.the_dwellers.fyreplugin.events;

import io.github.the_dwellers.fyreplugin.Attributes;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerRespawn implements Listener {

	@EventHandler()
	public void onPlayerRespawnEvent(PlayerRespawnEvent event) {
		// Apply spawn attributes
		Attributes.applyPlayer(event.getPlayer());
	}
}
