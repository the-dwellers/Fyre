package io.github.the_dwellers.fyreplugin.features;

import io.github.the_dwellers.fyreplugin.core.AbstractFeature;
import io.github.the_dwellers.fyreplugin.configuration.ItemCollections;
import io.github.the_dwellers.fyreplugin.configuration.ItemCollections.ToolMaterial;
import io.github.the_dwellers.fyreplugin.configuration.Items;
import io.github.the_dwellers.fyreplugin.configuration.SupportedVersions;
import io.github.the_dwellers.fyreplugin.core.MinecraftVersion;
import io.github.the_dwellers.fyreplugin.util.RandomUtil;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.attribute.AttributeModifier.Operation;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Monster;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * Adds different equipment rules for Mobs, including difficulty based on depth.
 * Also provides methods for setting up bosses and events. See
 * {@link EntityAttributes} for default on-hit effects and general attribute
 * changes.
 *
 * @see EntityAttributes
 */
public class Mobs extends AbstractFeature implements Listener {
	@Override
	public MinecraftVersion getMinecraftVersion() {
		return SupportedVersions.MIN;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	@Override
	public boolean setup() {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		enabled = true;
		return enabled;
	}

	@Override
	public String getName() {
		return "Mob Tweaks";
	}

	@EventHandler()
	public void onCreatureSpawned(CreatureSpawnEvent event) {
		// event.getEntity().addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 100000, 1));
		if (event.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL) {
			if (event.getEntity() instanceof Monster) {
				Entity entity = event.getEntity();
				if (event.getEntityType() == EntityType.ZOMBIE || event.getEntityType() == EntityType.SKELETON) {
					// 1% chance to spawn a boss for skeleton and zombies
					if (RandomUtil.integer(100) == 1) {
						setupBoss((Monster) entity, BossType.Random);
						((Monster) entity)
								.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 999999, 1, false, false));
						return;
					}
				}
				setupMobEquipment((Monster) entity, getDifficulty(entity.getLocation().getBlockY()));
			}
		}
	}

	/**
	 * Generates a difficulty based on the current yPosition. Does not include boss chances.
	 *
	 * @param yPos depth in blocks, with 0 at the bottom.
	 * @return Difficulty of a mob spawned at the provided depth.
	 */
	private MobDifficulty getDifficulty(int yPos) {
		if (yPos < 21) {
			return MobDifficulty.VeryStrong;
		} else if (yPos < 40) {
			return MobDifficulty.Strong;
		} else if (yPos < 60) {
			return MobDifficulty.Medium;
		} else {
			return MobDifficulty.Weak;
		}
	}

	/**
	 * Sets up the provided entity with the equipment for the given difficulty. <p>
	 * Entities which do not scale with difficulty will be unaffected.
	 *
	 * @param entity     Entity to setup.
	 * @param difficulty Difficulty to equip mob for.
	 */
	private void setupMobEquipment(Monster entity, MobDifficulty difficulty) {
		// Java is a good language and has no bugs whatsoever
		int rand;
		switch (entity.getType()) {
			case SKELETON:
			case ZOMBIE:
				switch (difficulty) {
					case Weak:
						entity.getEquipment().setItemInMainHand(ItemCollections.randomlyBreak(
								ItemCollections.getTool(ToolMaterial.Wood, ItemCollections.getRandomTool(false))));
						break;
					case Medium:
						entity.getEquipment().setItemInMainHand(ItemCollections.randomlyBreak(
								ItemCollections.getTool(ToolMaterial.Stone, ItemCollections.getRandomTool(false))));
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
					case Strong:
						entity.getEquipment().setItemInMainHand(ItemCollections.randomlyBreak(
								ItemCollections.getTool(ToolMaterial.Stone, ItemCollections.getRandomTool(true))));
						rand = RandomUtil.integer(2);
						if (rand == 1) {
							entity.getEquipment()
									.setChestplate(ItemCollections.randomlyBreak(Items.getLeatherChestplate()));
						} else {
							entity.getEquipment()
									.setChestplate(ItemCollections.randomlyBreak(Items.getChainmailChestplate()));
						}
						rand = RandomUtil.integer(2);
						if (rand == 1) {
							entity.getEquipment()
									.setLeggings(ItemCollections.randomlyBreak(Items.getLeatherLeggings()));
						} else {
							entity.getEquipment()
									.setLeggings(ItemCollections.randomlyBreak(Items.getChainmailLeggings()));
						}
						rand = RandomUtil.integer(2);
						if (rand == 1) {
							entity.getEquipment().setBoots(ItemCollections.randomlyBreak(Items.getLeatherBoots()));
						} else {
							entity.getEquipment().setBoots(ItemCollections.randomlyBreak(Items.getChainmailBoots()));
						}
						break;
					case VeryStrong:
						entity.getEquipment().setItemInMainHand(ItemCollections.randomlyBreak(
								ItemCollections.getTool(ToolMaterial.Stone, ItemCollections.getRandomTool(true))));
						rand = RandomUtil.integer(2);
						if (rand == 1) {
							entity.getEquipment()
									.setChestplate(ItemCollections.randomlyBreak(Items.getIronChestplate()));
						} else {
							entity.getEquipment()
									.setChestplate(ItemCollections.randomlyBreak(Items.getChainmailChestplate()));
						}
						rand = RandomUtil.integer(2);
						if (rand == 1) {
							entity.getEquipment().setLeggings(ItemCollections.randomlyBreak(Items.getIronLeggings()));
						} else {
							entity.getEquipment()
									.setLeggings(ItemCollections.randomlyBreak(Items.getChainmailLeggings()));
						}
						rand = RandomUtil.integer(2);
						if (rand == 1) {
							entity.getEquipment().setBoots(ItemCollections.randomlyBreak(Items.getIronBoots()));
						} else {
							entity.getEquipment().setBoots(ItemCollections.randomlyBreak(Items.getChainmailBoots()));
						}
						entity.getEquipment().setItemInOffHand(Items.getShield());
						break;
					default:
						break;
				}
				break;
			case CREEPER:
				switch (difficulty) {
					case VeryStrong:
						Creeper creeper = (Creeper) entity;
						creeper.setPowered(true);
						break;
					default:
						break;
				}

			default:
				break;
		}
	}

	/**
	 * Spawns a boss at the provided location.
	 *
	 * @param bossType Type of boss to spawn.
	 * @param location Location to spawn boss at.
	 */
	public void spawnBoss(BossType bossType, Location location) {
		Entity boss = location.getWorld().spawnEntity(location, EntityType.ZOMBIE);
		setupBoss((Monster) boss, bossType);
	}

	/**
	 * Setup a boss entity with correct equipment and attribute changes. <p>
	 * Some boss types may be incompatible with the provided entity, ensure
	 * passed entity types are supported! <p> Supports {@link BossType}.Random
	 *
	 * @param entity Entity to setup as a boss
	 * @param type   Type of boss to setup entity as.
	 */
	private void setupBoss(Monster entity, BossType type) {
		if (type == BossType.Random) {
			// Todo: Multiple boss types
			type = BossType.GuardCaptain;
		}

		switch (type) {
			default:
			case GuardCaptain:
				// Name
				entity.setCustomName("Guard Captain");
				entity.setCustomNameVisible(true);

				// Attributes
				entity.getAttribute(Attribute.GENERIC_MAX_HEALTH)
						.addModifier(new AttributeModifier("Fyre Boss Health", 1, Operation.MULTIPLY_SCALAR_1));
				entity.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).addModifier(
						new AttributeModifier("Fyre Boss Knockback Resistance", 0.6, Operation.ADD_NUMBER));
				entity.getAttribute(Attribute.GENERIC_FOLLOW_RANGE)
						.addModifier(new AttributeModifier("Fyre Boss Follow Range", 40, Operation.ADD_NUMBER));
				entity.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)
						.addModifier(new AttributeModifier("Fyre Boss Speed", 1, Operation.MULTIPLY_SCALAR_1));

				// Equipment
				entity.getEquipment().setItemInMainHand(Items.getWoodSword());
				entity.getEquipment().setItemInMainHandDropChance(1);

				entity.getEquipment().setItemInOffHand(Items.getShield());
				entity.getEquipment().setItemInOffHandDropChance(1);

				entity.getEquipment().setHelmet(Items.getIronHelmet());
				entity.getEquipment().setHelmetDropChance(1);

				entity.getEquipment().setChestplate(Items.getIronChestplate());
				entity.getEquipment().setChestplateDropChance(1);

				entity.getEquipment().setLeggings(Items.getIronLeggings());
				entity.getEquipment().setLeggingsDropChance(1);

				entity.getEquipment().setBoots(Items.getIronBoots());
				entity.getEquipment().setBootsDropChance(1);
				break;
		}
	}

	/**
	 * Fyre Mob difficulty.
	 */
	public enum MobDifficulty {
		Weak, Medium, Strong, VeryStrong, Boss, RaidBoss
	}

	/**
	 * Type of bosses available in Fyre. These are replacements for common
	 * enemies and are not specific events.
	 */
	public enum BossType {
		Random, GuardCaptain
	}
}
