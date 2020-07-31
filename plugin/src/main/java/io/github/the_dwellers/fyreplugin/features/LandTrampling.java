package io.github.the_dwellers.fyreplugin.features;

import io.github.the_dwellers.fyreplugin.core.AbstractFeature;
import io.github.the_dwellers.fyreplugin.configuration.SupportedVersions;
import io.github.the_dwellers.fyreplugin.core.MinecraftVersion;
import io.github.the_dwellers.fyreplugin.util.RandomUtil;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.AbstractHorse;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

/**
 * Trample grass into dirt.
 */
public class LandTrampling extends AbstractFeature implements Listener {

	public MinecraftVersion getMinecraftVersion() {
		return SupportedVersions.MIN;
	}

	public String getName() {
		return "Land Trampling";
	}

	public boolean setup() {
		if (isEnabled()) {
			// Enable-gate
			return isEnabled();
		}

		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		enabled = true;
		return isEnabled();
	}

	/**
	 * Player tramples grass into course dirt
	 * @param event {@link PlayerMoveEvent}
	 */
	// * isOnGround() is marked as deprecated, but has no replacements.
	@SuppressWarnings("deprecation")
	@EventHandler()
	public void onPlayerMove(PlayerMoveEvent event) {

		if (event.getFrom().getBlock().equals(event.getTo().getBlock())) {
			// Early exit if blocks were not changed.
			return;
		}

		Player player = event.getPlayer();

		if (player.isOnGround()) {
			// Player is walking
			Block block = player.getLocation().getBlock().getRelative(BlockFace.DOWN);

			if (block.getType() == Material.GRASS_BLOCK) {
				int r = RandomUtil.integer(100);

				if (r <= plugin.config.getDirtTrampleWalkChance()) {
					block.setType(Material.COARSE_DIRT);
				}
			}
		} else if (player.getVehicle() instanceof AbstractHorse) {
			// Player is on a horse
			// instanceof returns false if player.getVehicle() is null or not a horse
			Entity vehicle = player.getVehicle();

			if (vehicle.isOnGround()) {

				Block block = vehicle.getLocation().getBlock().getRelative(BlockFace.DOWN);

				if (block.getType() == Material.GRASS_BLOCK) {
					int r = RandomUtil.integer(100);

					if (r <= plugin.config.getDirtTrampleRideChance()) {
						// Todo: Populate with configuration
						// 10% Chance
						block.setType(Material.COARSE_DIRT);
					}
				}
			}
		}
	}
}
