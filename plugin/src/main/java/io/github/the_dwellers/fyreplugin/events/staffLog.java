package io.github.the_dwellers.fyreplugin.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import io.github.the_dwellers.fyreplugin.ChatManager;

/**
 * Events related to heuristics and logs to assist administration.
 * <p>
 * This includes:
 * <ul>
 * <li>Printing of IP address of connecting users</li>
 * </ul>
 */
public class staffLog implements Listener {

	@EventHandler()
	public static void onConnect(PlayerLoginEvent event) {
		ChatManager.sendStaffMessage("'" + event.getPlayer().getName() + "' is connecting from " + event.getAddress().toString());
	}
}
