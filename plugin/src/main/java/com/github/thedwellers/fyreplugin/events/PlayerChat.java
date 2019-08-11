package com.github.thedwellers.fyreplugin.events;

import com.github.thedwellers.fyreplugin.ChatManager;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

/**
 * PlayerChat
 */
public class PlayerChat implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerJoin(PlayerChatEvent event) {
		// ! This is a horrendously dirty way of doing this, but is needed to imitate player chat events
		// TODO: Re-create, perhaps using AsyncPlayerChatEvent and chat format to obtain the desired results?
		event.setCancelled(true);
		ChatManager.sendPlayerMessage(event.getPlayer(), event.getMessage());
	}
}
