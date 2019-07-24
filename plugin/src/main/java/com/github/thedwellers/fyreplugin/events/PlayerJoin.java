package com.github.thedwellers.fyreplugin.events;

import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;

public class PlayerJoin implements Listener{
	@EventHandler()
	public void onPlayerJoin(PlayerJoinEvent event){
		Player player = event.getPlayer();

	}
}
