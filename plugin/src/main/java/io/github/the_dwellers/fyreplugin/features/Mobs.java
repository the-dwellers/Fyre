package io.github.the_dwellers.fyreplugin.features;

import io.github.the_dwellers.fyreplugin.FyrePlugin;
import io.github.the_dwellers.fyreplugin.configuration.ItemCollections;
import io.github.the_dwellers.fyreplugin.configuration.Items;
import io.github.the_dwellers.fyreplugin.configuration.SupportedVersions;
import io.github.the_dwellers.fyreplugin.configuration.ItemCollections.ToolMaterial;

import org.bukkit.entity.Creeper;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

import io.github.the_dwellers.fyreplugin.Feature;
import io.github.the_dwellers.fyreplugin.util.MinecraftVersion;
import io.github.the_dwellers.fyreplugin.util.RandomUtil;

/**
 * Feature wrapper for {@link TagDataHolder}
 * <hr>
 * Represents any {@link Entity} that may have external data serialized to their
 * 'Tag' nbt value.
 */
public class Mobs extends Feature implements Listener {

	public static MinecraftVersion minVersion = SupportedVersions.MIN;

	protected boolean enabled = false;
	protected static String name = "Mob Tweaks";
	private static Mobs instance;

	public static Mobs getInstance() {
		if (instance == null) {
			instance = new Mobs();
		}
		return instance;
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
	public boolean setup(FyrePlugin plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		enabled = true;
		return enabled;
	}

	@Override
	public String getName() {
		return name;
	}

	@EventHandler()
	public void onCreatureSpawned(CreatureSpawnEvent event) {
		if (event.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL) {
			if (event.getEntity() instanceof Monster) {
				Entity entity = event.getEntity();
				setupMobEquipment((Monster) entity, getDifficulty(entity.getLocation().getBlockY()));
			}
		}
	}

	public enum MobDifficulty {
		Weak, Medium, Strong, VeryStrong, Boss, RaidBoss
	}

	private MobDifficulty getDifficulty(int yPos) {
		if (yPos < 60) {
			return MobDifficulty.VeryStrong;
		}
		return MobDifficulty.Weak;
	}

	private void setupMobEquipment(Monster entity, MobDifficulty difficulty) {
		// Java is a good language and has no bugs whatsoever
		int rand;
		switch (entity.getType()) {
		case ZOMBIE:
			switch (difficulty) {
			case Weak:
				entity.getEquipment().setItemInMainHand(ItemCollections.randomlyBreak(
						ItemCollections.getTool(ToolMaterial.Wood, ItemCollections.getRandomTool(false))));
				break;
			default:
			case Medium:
				entity.getEquipment().setItemInMainHand(ItemCollections.randomlyBreak(
						ItemCollections.getTool(ToolMaterial.Wood, ItemCollections.getRandomTool(false))));
				rand = RandomUtil.integer(2);
				if (rand == 1) {
					entity.getEquipment()
							.setChestplate(ItemCollections.randomlyBreak(Items.getLeatherChestplate()));
				}
				rand = RandomUtil.integer(2);
				if (rand == 1) {
					entity.getEquipment()
							.setLeggings(ItemCollections.randomlyBreak(Items.getLeatherLeggings()));
				}
				rand = RandomUtil.integer(2);
				if (rand == 1) {
					entity.getEquipment().setBoots(ItemCollections.randomlyBreak(Items.getLeatherBoots()));
				}
				break;
			}

		case SKELETON:
			break;
		case CREEPER:
			switch (difficulty) {
				default:
				case VeryStrong:
				Creeper creeper = (Creeper) entity;
				creeper.setPowered(true);
					break;
			}

		default:
			break;
		}
	}
}
