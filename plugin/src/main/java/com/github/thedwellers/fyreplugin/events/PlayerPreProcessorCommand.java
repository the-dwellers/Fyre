package com.github.thedwellers.fyreplugin.events;

import com.github.thedwellers.fyreplugin.FyrePlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

/**
 * Event to be executed when a command is attempted to be ran by a player.
 * <p>
 * This is currently present to catch all calls to {@code bukkit:plugins} and convert
 * them to {@code fyre:plugins}
 */
public class PlayerPreProcessorCommand implements Listener {

	private FyrePlugin plugin = FyrePlugin.getInstance();

	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerCommand(PlayerCommandPreprocessEvent event) {
		String command = event.getMessage().split(" ")[0].toLowerCase();

		// Capture plugin command calls, and run fyre:plugins
		if (command.equals("/pl") || command.equals("/plugins")) {
			event.setCancelled(true);
			plugin.getServer().dispatchCommand(event.getPlayer(), "fyre:plugins");
		}
	}
}
