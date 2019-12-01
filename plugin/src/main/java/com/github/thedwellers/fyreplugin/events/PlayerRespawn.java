package com.github.thedwellers.fyreplugin.events;

import com.github.thedwellers.fyreplugin.Attributes;
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
