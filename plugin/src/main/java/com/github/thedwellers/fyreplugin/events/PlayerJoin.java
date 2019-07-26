package com.github.thedwellers.fyreplugin.events;

import com.github.thedwellers.fyreplugin.configuration.PlayerOperations;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.java.JavaPlugin;
import com.github.thedwellers.fyreplugin.commands.*;

public class PlayerJoin extends AbstractEvent {

	public PlayerJoin(JavaPlugin plugin){
		super(plugin);
	}

	@EventHandler()
	public void onPlayerJoin(PlayerJoinEvent event){
		Player player = event.getPlayer();
		String uuid = player.getUniqueId().toString();
		
		PlayerOperations.configureDataFile(uuid);
	}
}
