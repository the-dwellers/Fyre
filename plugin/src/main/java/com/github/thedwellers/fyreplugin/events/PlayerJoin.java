package com.github.thedwellers.fyreplugin.events;

import com.github.thedwellers.fyreplugin.FyrePlugin;
import com.github.thedwellers.fyreplugin.configuration.PlayerOperations;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

	private FyrePlugin plugin = FyrePlugin.getInstance();

	@EventHandler()
	public void onPlayerJoin(PlayerJoinEvent event){
		Player player = event.getPlayer();
		String uuid = player.getUniqueId().toString();

		if(!PlayerOperations.playerFileExists(uuid, plugin.getDataFolder())){
			PlayerOperations.configurePlayerData(uuid, plugin.getDataFolder());
		}
	}
}
