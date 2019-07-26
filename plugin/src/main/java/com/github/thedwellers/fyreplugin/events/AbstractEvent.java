package com.github.thedwellers.fyreplugin.events;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * AbstractEvent
 */
public abstract class AbstractEvent implements Listener {

	protected JavaPlugin plugin;

	public AbstractEvent(JavaPlugin plugin){
		this.plugin = plugin;
	}
}
