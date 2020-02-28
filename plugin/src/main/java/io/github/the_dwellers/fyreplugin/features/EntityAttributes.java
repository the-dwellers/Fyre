package io.github.the_dwellers.fyreplugin.features;

import org.bukkit.Material;
import org.bukkit.attribute.Attributable;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.attribute.AttributeModifier.Operation;
import org.bukkit.block.Block;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Spider;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import io.github.the_dwellers.fyreplugin.FyrePlugin;
import io.github.the_dwellers.fyreplugin.configuration.SupportedVersions;
import io.github.the_dwellers.fyreplugin.util.MinecraftVersion;

/**
 * Attribute changes to entities.
 */
public class EntityAttributes implements AbstractFeature, Listener {

	public static MinecraftVersion minVersion = SupportedVersions.MIN;

	protected boolean enabled = false;
	protected static String name = "Entity Attributes";
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


	/**
	 * Update player attributes.
	 *
	 * @param player Player to update attributes of
	 */
	public static void applyPlayer(Player player) {
		// 5 hearts of health
		player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(10);

		// player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.13);
		// Default walkspeed is 0.2 with setWalkSpeed()
		player.setWalkSpeed(0.226f);
	}

	public static void applyMob(Entity entity) {
		if (entity instanceof Attributable) {
			switch (entity.getType()) {
				case ZOMBIE:
				case SKELETON:
				case SPIDER:
				case ENDERMAN:
					applyOverworldHostile((Attributable) entity);
					break;
				case BLAZE:
				case GHAST:
				case WITHER_SKELETON:
					applyNetherHostile((Attributable) entity);
				default:
					break;
			}
		}
	}

	public static void applyOverworldHostile(Attributable entity) {
		entity.getAttribute(Attribute.GENERIC_MAX_HEALTH)
			.addModifier(new AttributeModifier("Fyre Spawn Health 1.5", 0.5, Operation.MULTIPLY_SCALAR_1));
	}

	public static void applyNetherHostile(Attributable entity) {
		entity.getAttribute(Attribute.GENERIC_MAX_HEALTH)
			.addModifier(new AttributeModifier("Fyre Spawn Health 2", 1, Operation.MULTIPLY_SCALAR_1));

	}

	@EventHandler()
	private void onEntitySpawn(EntitySpawnEvent event) {
		if (!(event.getEntity() instanceof Player)) {
			applyMob(event.getEntity());
		}
	}

	@EventHandler()
	public void onDamage(EntityDamageByEntityEvent event) {
		if (event.getEntity() instanceof Creeper) {
			// Creepers explode when hit
			((Creeper) event.getEntity()).ignite();
		}

		if (event.getEntity() instanceof Spider) {
			Block block = event.getEntity().getLocation().getBlock();
			if (block.getType() == Material.AIR) {
				block.setType(Material.COBWEB);
			}
			return;
		}

		if (event.getEntity() instanceof Player) {
			Player player = (Player) event.getEntity();

			switch (event.getDamager().getType()) {
				case ZOMBIE:
					player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 5 * 20, 1));
					break;
				case PHANTOM:
					player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 5 * 20, 0));
					break;
				case ENDERMAN:
					player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 10 * 20, 0));
					break;
				default:
					break;
			}

			// TODO: Add one-shot health threshold to config
			if (player.getHealth() < 3 && event.getFinalDamage() >= player.getHealth()) {
				// One-shot protection
				event.setDamage(0);
				player.setHealth(1);
			}
		}
	}

	@EventHandler()
	public void onPlayerRespawnEvent(PlayerRespawnEvent event) {
		// Apply spawn attributes
		applyPlayer(event.getPlayer());
	}

}
