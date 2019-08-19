package com.github.thedwellers.fyreplugin.events;

import com.github.thedwellers.fyreplugin.util.RandomUtil;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMove implements Listener {
	@EventHandler()
	public void onPlayerMove(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		
		if (player.isOnGround()) {
			Block block = player.getLocation().getBlock().getRelative(BlockFace.DOWN);
			
			if (event.getFrom().getBlock() == event.getTo().getBlock())
				return;
			
			if (block.getType() == Material.GRASS_BLOCK) {
				int r = RandomUtil.integer(500);
				
				if (r >= 0 && r <= 10) {
					block.setType(Material.GRASS_PATH);
				}
			}
		}
	}
}
