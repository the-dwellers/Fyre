package com.github.thedwellers.fyreplugin.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * PlayerPreProcessorCommand
 */
public class PlayerPreProcessorCommand extends AbstractEvent {

	public PlayerPreProcessorCommand(JavaPlugin plugin) {
		super(plugin);
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerCommand(PlayerCommandPreprocessEvent event) {
		String command = event.getMessage().split(" ")[0].toLowerCase();

		// Capture plugin command calls, and run Fyre /plugins
		if (command.equals("/pl") || command.equals("/plugins")) {
			event.setCancelled(true);
			plugin.getServer().dispatchCommand(event.getPlayer(), "fyre:plugins");
		}
	}
}
