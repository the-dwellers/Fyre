package io.github.the_dwellers.fyreplugin.events;

import io.github.the_dwellers.fyreplugin.Attributes;
import io.github.the_dwellers.fyreplugin.ChatManager;
import io.github.the_dwellers.fyreplugin.configuration.PlayerOperations;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {
	@EventHandler()
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		String uuid = player.getUniqueId().toString();

		PlayerOperations.initializePlayerConfiguration(uuid);
		Attributes.applyPlayer(player);

		// Custom join message
		event.setJoinMessage("");
		ChatManager.sendPlayerJoin(player);
	}
}
