package io.github.the_dwellers.fyreplugin.features;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.AbstractHorse;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

import io.github.the_dwellers.fyreplugin.FyrePlugin;
import io.github.the_dwellers.fyreplugin.configuration.SupportedVersions;
import io.github.the_dwellers.fyreplugin.util.MinecraftVersion;
import io.github.the_dwellers.fyreplugin.util.RandomUtil;

/**
 * LandTrampling
 */
public class LandTrampling implements AbstractFeature, Listener {

	public static MinecraftVersion minVersion = SupportedVersions.MIN;

	protected boolean enabled = false;
	protected static String name = "LandTrampling";
	private static ClientBreakItem featureInstance;

	public static ClientBreakItem getInstance() {
		if (featureInstance == null) {
			featureInstance = new ClientBreakItem();
		}
		return featureInstance;
	}

	@Override
	public MinecraftVersion getMinecraftVersion() {
		return minVersion;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public boolean setup(FyrePlugin plugin) {
		return false;
	}

	@EventHandler()
	public static void onEntityCropTrample(EntityInteractEvent event) {
		// ? Why does EntityInteractEvent not fire for players?
		Block block = event.getBlock();
		Entity entity = event.getEntity();
		if (block != null && event.getEntity() instanceof LivingEntity) {
			if (block.getType() == Material.FARMLAND) {
				preventCropTrample(event, (LivingEntity) entity);
			}
		}
	}

	@EventHandler()
	public static void onPlayerCropTrample(PlayerInteractEvent event) {
		Block block = event.getClickedBlock();
		Player player = event.getPlayer();
		if (block != null && block.getType() == Material.FARMLAND && event.getAction() == Action.PHYSICAL) {
			preventCropTrample(event, (LivingEntity) player);
		}
	}

	public static void preventCropTrample(Cancellable event, LivingEntity entity) {
		ItemStack boots = entity.getEquipment().getBoots();
		if (boots == null) {
			return;
		} else if (boots.getType() == Material.LEATHER_BOOTS) {
			event.setCancelled(true);
		}
	}

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

				if (r <= 5) {
					// 5% Chance
					block.setType(Material.COARSE_DIRT);
				}
			}
		} else if (player.getVehicle() instanceof AbstractHorse) {
			// Player is in a horse
			// instanceof returns false if player.getVehicle() is null or not a horse
			Entity vehicle = player.getVehicle();

			if (vehicle.isOnGround()) {

				Block block = vehicle.getLocation().getBlock().getRelative(BlockFace.DOWN);

				if (block.getType() == Material.GRASS_BLOCK) {
					int r = RandomUtil.integer(100);

					if (r <= 10) {
						// 10% Chance
						block.setType(Material.COARSE_DIRT);
					}
				}
			}
		}
	}
}
