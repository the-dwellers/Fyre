package com.github.thedwellers.fyreplugin.events;

import com.github.thedwellers.fyreplugin.ChatManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * Sends chat events into Fyre's {@link ChatManager}<p>
 * This is almost guaranteed to be incompatible with any other chat systems!
 */
public class PlayerChat implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerJoin(AsyncPlayerChatEvent event) {
		// Cancel the chat event and dispatch the message to the ChatManager
		event.setCancelled(true);
		ChatManager.sendPlayerMessage(event.getPlayer(), event.getMessage());
	}
}
