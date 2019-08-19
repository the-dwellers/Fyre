package com.github.thedwellers.fyreplugin.events;

import com.github.thedwellers.fyreplugin.util.RandomUtil;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.AbstractHorse;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Vehicle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleMoveEvent;

public class VehicleMove implements Listener {
	@EventHandler()
	public void onVehicleMove(VehicleMoveEvent event) {
		Vehicle vehicle = event.getVehicle();
		
		if (vehicle.getClass().isAssignableFrom(AbstractHorse.class)) {
			if (vehicle.isOnGround()) {
				Entity entity = vehicle.getPassenger();

				if (entity instanceof Player) {
					Block block = vehicle.getLocation().getBlock().getRelative(BlockFace.DOWN);

					if (block.getType() == Material.GRASS_BLOCK) {
						int r = RandomUtil.integer(50);

						if (r >= 0 && r <= 10) {
							block.setType(Material.GRASS_PATH);
						}
					}
				}
			}
		}
	}
}
