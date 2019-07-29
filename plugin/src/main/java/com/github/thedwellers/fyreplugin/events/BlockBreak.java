package com.github.thedwellers.fyreplugin.events;

import com.github.thedwellers.fyreplugin.Advancements;

import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * BlockBreak
 */
public class BlockBreak extends AbstractEvent {

	@EventHandler()
	public void onBlockBreak(BlockBreakEvent event) {
		Advancements.updateAdvancements(event.getPlayer());
	}
}
