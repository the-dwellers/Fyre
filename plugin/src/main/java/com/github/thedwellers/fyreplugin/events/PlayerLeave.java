package com.github.thedwellers.fyreplugin.events;

import com.github.thedwellers.fyreplugin.ChatManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * PlayerLeave
 */
public class PlayerLeave implements Listener {

	@EventHandler()
	public void onPlayerJoin(PlayerQuitEvent event) {
		event.setQuitMessage("");
		ChatManager.sendPlayerLeave(event.getPlayer());
	}
}
