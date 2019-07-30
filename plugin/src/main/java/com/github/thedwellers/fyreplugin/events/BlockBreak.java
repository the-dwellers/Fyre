package com.github.thedwellers.fyreplugin.events;

import com.github.thedwellers.fyreplugin.Advancements;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

/**
 * BlockBreak
 */
public class BlockBreak implements Listener {

	@EventHandler()
	public void onBlockBreak(BlockBreakEvent event) {
		Advancements.updateBlockBreakAdvancement(event.getPlayer(),event.getBlock().getType());
	}
}
