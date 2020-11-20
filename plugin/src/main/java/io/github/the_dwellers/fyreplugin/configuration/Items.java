package io.github.the_dwellers.fyreplugin.configuration;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.attribute.AttributeModifier.Operation;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.KnowledgeBookMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

/**
 * Static class of specific items that may may be referenced from other places
 * inside the application. This includes items such as Currency or other unique
 * materials. This class uses lazy instantiation.
 * <p>
 * * Note: Remember to {@code .clone()} any returned static
 */
public abstract class Items {

	/**
	 * Recipe prefix text
	 */
	public static String PREFIX_RECIPE = ChatColor.LIGHT_PURPLE + "Recipe: ";

	/**
	 * Special item text
	 */
	public static String PREFIX_SPECIAL = "" + ChatColor.GOLD;

	/**
	 * Common item text
	 */
	public static String PREFIX_COMMON = "" + ChatColor.WHITE;

	/**
	 * Common lore text
	 */
	public static String PREFIX_LORE_COMMON = PREFIX_COMMON + ChatColor.GRAY;

	// ~~~~~~~~ Item Instance Caching ~~~~~~~~ //
	private static ItemStack SILVER_COIN;
	private static ItemStack ERROR_ITEM;

	private static ItemStack WOOD_AXE;
	private static ItemStack WOOD_SHOVEL;
	private static ItemStack WOOD_SWORD;
	private static ItemStack WOOD_PICKAXE;
	private static ItemStack WOOD_HOE;

	private static ItemStack STONE_AXE;
	private static ItemStack STONE_SHOVEL;
	private static ItemStack STONE_SWORD;
	private static ItemStack STONE_PICKAXE;
	private static ItemStack STONE_HOE;

	private static ItemStack IRON_AXE;
	private static ItemStack IRON_SHOVEL;
	private static ItemStack IRON_SWORD;
	private static ItemStack IRON_PICKAXE;
	private static ItemStack IRON_HOE;

	private static ItemStack DIAMOND_AXE;
	private static ItemStack DIAMOND_SHOVEL;
	private static ItemStack DIAMOND_SWORD;
	private static ItemStack DIAMOND_PICKAXE;
	private static ItemStack DIAMOND_HOE;

	private static ItemStack SPLINTERS;

	private static ItemStack TOME_CRAFTING_TABLE;
	private static ItemStack TOME_WOODEN_TOOLS;
	private static ItemStack TOME_STONE_TOOLS;
	private static ItemStack TOME_IRON_TOOLS;
	private static ItemStack TOME_WOODEN_SWORD;
	private static ItemStack TOME_STONE_SWORD;
	private static ItemStack TOME_IRON_SWORD;

	private static ItemStack ARMOR_LEATHER_HELMET;
	private static ItemStack ARMOR_LEATHER_CHESTPLATE;
	private static ItemStack ARMOR_LEATHER_LEGGINGS;
	private static ItemStack ARMOR_LEATHER_BOOTS;

	private static ItemStack ARMOR_CHAINMAIL_HELMET;
	private static ItemStack ARMOR_CHAINMAIL_CHESTPLATE;
	private static ItemStack ARMOR_CHAINMAIL_LEGGINGS;
	private static ItemStack ARMOR_CHAINMAIL_BOOTS;

	private static ItemStack ARMOR_IRON_HELMET;
	private static ItemStack ARMOR_IRON_CHESTPLATE;
	private static ItemStack ARMOR_IRON_LEGGINGS;
	private static ItemStack ARMOR_IRON_BOOTS;

	private static ItemStack ARMOR_DIAMOND_HELMET;
	private static ItemStack ARMOR_DIAMOND_CHESTPLATE;
	private static ItemStack ARMOR_DIAMOND_LEGGINGS;
	private static ItemStack ARMOR_DIAMOND_BOOTS;

	private static ItemStack SHIELD;

	// ~~~~~~~~~~~~~ Item Methods ~~~~~~~~~~~~ //

	public static ItemStack getLeatherHelmet() {
		if (ARMOR_LEATHER_HELMET == null) {
			ARMOR_LEATHER_HELMET = new ItemStack(Material.LEATHER_HELMET, 1);
			ItemMeta meta = ARMOR_LEATHER_HELMET.getItemMeta();

			Multimap<Attribute, AttributeModifier> modifiers = HashMultimap.create();

			modifiers.put(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(),
					"generic.max_health", 2, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
			modifiers.put(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(),
					"generic.generic_armor", 1, Operation.ADD_NUMBER, EquipmentSlot.HEAD));

			meta.setAttributeModifiers(modifiers);
			ARMOR_LEATHER_HELMET.setItemMeta(meta);
		}
		return ARMOR_LEATHER_HELMET.clone();
	}

	public static ItemStack getLeatherChestplate() {
		if (ARMOR_LEATHER_CHESTPLATE == null) {
			ARMOR_LEATHER_CHESTPLATE = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
			ItemMeta meta = ARMOR_LEATHER_CHESTPLATE.getItemMeta();

			Multimap<Attribute, AttributeModifier> modifiers = HashMultimap.create();

			modifiers.put(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(),
					"generic.max_health", 4, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
			modifiers.put(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(),
					"generic.generic_armor", 3, Operation.ADD_NUMBER, EquipmentSlot.CHEST));

			meta.setAttributeModifiers(modifiers);
			ARMOR_LEATHER_CHESTPLATE.setItemMeta(meta);
		}
		return ARMOR_LEATHER_CHESTPLATE.clone();
	}

	public static ItemStack getLeatherLeggings() {
		if (ARMOR_LEATHER_LEGGINGS == null) {
			ARMOR_LEATHER_LEGGINGS = new ItemStack(Material.LEATHER_LEGGINGS, 1);
			ItemMeta meta = ARMOR_LEATHER_LEGGINGS.getItemMeta();

			Multimap<Attribute, AttributeModifier> modifiers = HashMultimap.create();

			modifiers.put(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(),
					"generic.max_health", 2, Operation.ADD_NUMBER, EquipmentSlot.LEGS));
			modifiers.put(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(),
					"generic.generic_armor", 2, Operation.ADD_NUMBER, EquipmentSlot.LEGS));

			meta.setAttributeModifiers(modifiers);
			ARMOR_LEATHER_LEGGINGS.setItemMeta(meta);
		}
		return ARMOR_LEATHER_LEGGINGS.clone();
	}

	public static ItemStack getLeatherBoots() {
		if (ARMOR_LEATHER_BOOTS == null) {
			ARMOR_LEATHER_BOOTS = new ItemStack(Material.LEATHER_BOOTS, 1);
			ItemMeta meta = ARMOR_LEATHER_BOOTS.getItemMeta();
			meta.setLore(Arrays.asList(PREFIX_LORE_COMMON + "Cannot Trample Crops"));

			Multimap<Attribute, AttributeModifier> modifiers = HashMultimap.create();

			modifiers.put(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(),
					"generic.max_health", 2, Operation.ADD_NUMBER, EquipmentSlot.FEET));
			modifiers.put(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(),
					"generic.generic_armor", 1, Operation.ADD_NUMBER, EquipmentSlot.FEET));

			meta.setAttributeModifiers(modifiers);
			ARMOR_LEATHER_BOOTS.setItemMeta(meta);
		}
		return ARMOR_LEATHER_BOOTS.clone();
	}

	public static ItemStack getChainmailHelmet() {
		if (ARMOR_CHAINMAIL_HELMET == null) {
			ARMOR_CHAINMAIL_HELMET = new ItemStack(Material.CHAINMAIL_HELMET, 1);
			ItemMeta meta = ARMOR_CHAINMAIL_HELMET.getItemMeta();

			Multimap<Attribute, AttributeModifier> modifiers = HashMultimap.create();

			modifiers.put(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(),
					"generic.max_health", 4, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
			modifiers.put(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(),
					"generic.generic_armor", 3, Operation.ADD_NUMBER, EquipmentSlot.HEAD));

			meta.setAttributeModifiers(modifiers);
			ARMOR_CHAINMAIL_HELMET.setItemMeta(meta);
		}
		return ARMOR_CHAINMAIL_HELMET.clone();
	}

	public static ItemStack getChainmailChestplate() {
		if (ARMOR_CHAINMAIL_CHESTPLATE == null) {
			ARMOR_CHAINMAIL_CHESTPLATE = new ItemStack(Material.CHAINMAIL_CHESTPLATE, 1);
			ItemMeta meta = ARMOR_CHAINMAIL_CHESTPLATE.getItemMeta();

			Multimap<Attribute, AttributeModifier> modifiers = HashMultimap.create();

			modifiers.put(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(),
					"generic.max_health", 4, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
			modifiers.put(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(),
					"generic.generic_armor", 5, Operation.ADD_NUMBER, EquipmentSlot.CHEST));

			meta.setAttributeModifiers(modifiers);
			ARMOR_CHAINMAIL_CHESTPLATE.setItemMeta(meta);
		}
		return ARMOR_CHAINMAIL_CHESTPLATE.clone();
	}

	public static ItemStack getChainmailLeggings() {
		if (ARMOR_CHAINMAIL_LEGGINGS == null) {
			ARMOR_CHAINMAIL_LEGGINGS = new ItemStack(Material.CHAINMAIL_LEGGINGS, 1);
			ItemMeta meta = ARMOR_CHAINMAIL_LEGGINGS.getItemMeta();

			Multimap<Attribute, AttributeModifier> modifiers = HashMultimap.create();

			modifiers.put(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(),
					"generic.max_health", 4, Operation.ADD_NUMBER, EquipmentSlot.LEGS));
			modifiers.put(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(),
					"generic.generic_armor", 4, Operation.ADD_NUMBER, EquipmentSlot.LEGS));

			meta.setAttributeModifiers(modifiers);
			ARMOR_CHAINMAIL_LEGGINGS.setItemMeta(meta);
		}
		return ARMOR_CHAINMAIL_LEGGINGS.clone();
	}

	public static ItemStack getChainmailBoots() {
		if (ARMOR_CHAINMAIL_BOOTS == null) {
			ARMOR_CHAINMAIL_BOOTS = new ItemStack(Material.CHAINMAIL_BOOTS, 1);
			ItemMeta meta = ARMOR_CHAINMAIL_BOOTS.getItemMeta();

			Multimap<Attribute, AttributeModifier> modifiers = HashMultimap.create();

			modifiers.put(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(), "generic.max_health",
					2, Operation.ADD_NUMBER, EquipmentSlot.FEET));
			modifiers.put(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "generic.generic_armor", 2,
					Operation.ADD_NUMBER, EquipmentSlot.FEET));

			meta.setAttributeModifiers(modifiers);
			ARMOR_CHAINMAIL_BOOTS.setItemMeta(meta);
		}
		return ARMOR_CHAINMAIL_BOOTS.clone();
	}

	public static ItemStack getIronHelmet() {
		if (ARMOR_IRON_HELMET == null) {
			ARMOR_IRON_HELMET = new ItemStack(Material.IRON_HELMET, 1);
			ItemMeta meta = ARMOR_IRON_HELMET.getItemMeta();

			Multimap<Attribute, AttributeModifier> modifiers = HashMultimap.create();

			modifiers.put(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(), "generic.max_health",
					4, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
			modifiers.put(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "generic.generic_armor", 3,
					Operation.ADD_NUMBER, EquipmentSlot.HEAD));
			modifiers.put(Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(),
					"generic.movement_speed", -0.01, Operation.MULTIPLY_SCALAR_1, EquipmentSlot.HEAD));

			meta.setAttributeModifiers(modifiers);
			ARMOR_IRON_HELMET.setItemMeta(meta);
		}
		return ARMOR_IRON_HELMET.clone();
	}

	public static ItemStack getIronChestplate() {
		if (ARMOR_IRON_CHESTPLATE == null) {
			ARMOR_IRON_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE, 1);
			ItemMeta meta = ARMOR_IRON_CHESTPLATE.getItemMeta();

			Multimap<Attribute, AttributeModifier> modifiers = HashMultimap.create();

			modifiers.put(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(), "generic.max_health",
					6, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
			modifiers.put(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "generic.generic_armor", 6,
					Operation.ADD_NUMBER, EquipmentSlot.CHEST));
			modifiers.put(Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(), "generic.movement_speed", -0.03,
					Operation.MULTIPLY_SCALAR_1, EquipmentSlot.CHEST));

			meta.setAttributeModifiers(modifiers);
			ARMOR_IRON_CHESTPLATE.setItemMeta(meta);
		}
		return ARMOR_IRON_CHESTPLATE.clone();
	}

	public static ItemStack getIronLeggings() {
		if (ARMOR_IRON_LEGGINGS == null) {
			ARMOR_IRON_LEGGINGS = new ItemStack(Material.IRON_LEGGINGS, 1);
			ItemMeta meta = ARMOR_IRON_LEGGINGS.getItemMeta();

			Multimap<Attribute, AttributeModifier> modifiers = HashMultimap.create();

			modifiers.put(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(),
					"generic.max_health", 6, Operation.ADD_NUMBER, EquipmentSlot.LEGS));
			modifiers.put(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(),
					"generic.generic_armor", 4, Operation.ADD_NUMBER, EquipmentSlot.LEGS));
			modifiers.put(Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(),
					"generic.movement_speed", -0.03, Operation.MULTIPLY_SCALAR_1, EquipmentSlot.LEGS));

			meta.setAttributeModifiers(modifiers);
			ARMOR_IRON_LEGGINGS.setItemMeta(meta);
		}
		return ARMOR_IRON_LEGGINGS.clone();
	}

	public static ItemStack getIronBoots() {
		if (ARMOR_IRON_BOOTS == null) {
			ARMOR_IRON_BOOTS = new ItemStack(Material.IRON_BOOTS, 1);
			ItemMeta meta = ARMOR_IRON_BOOTS.getItemMeta();

			Multimap<Attribute, AttributeModifier> modifiers = HashMultimap.create();

			modifiers.put(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(), "generic.max_health",
					4, Operation.ADD_NUMBER, EquipmentSlot.FEET));
			modifiers.put(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "generic.generic_armor", 3,
					Operation.ADD_NUMBER, EquipmentSlot.FEET));
			modifiers.put(Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(),
					"generic.movement_speed", -0.01, Operation.MULTIPLY_SCALAR_1, EquipmentSlot.FEET));

			meta.setAttributeModifiers(modifiers);
			ARMOR_IRON_BOOTS.setItemMeta(meta);
		}
		return ARMOR_IRON_BOOTS.clone();
	}

	public static ItemStack getDiamondHelmet() {
		if (ARMOR_DIAMOND_HELMET == null) {
			ARMOR_DIAMOND_HELMET = new ItemStack(Material.DIAMOND_HELMET, 1);
			ItemMeta meta = ARMOR_DIAMOND_HELMET.getItemMeta();

			Multimap<Attribute, AttributeModifier> modifiers = HashMultimap.create();

			modifiers.put(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(), "generic.max_health",
					6, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
			modifiers.put(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "generic.generic_armor", 4,
					Operation.ADD_NUMBER, EquipmentSlot.HEAD));
			modifiers.put(Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(),
					"generic.movement_speed", -0.02, Operation.MULTIPLY_SCALAR_1, EquipmentSlot.HEAD));

			meta.setAttributeModifiers(modifiers);
			ARMOR_DIAMOND_HELMET.setItemMeta(meta);
		}
		return ARMOR_DIAMOND_HELMET.clone();
	}

	public static ItemStack getDiamondChestplate() {
		if (ARMOR_DIAMOND_CHESTPLATE == null) {
			ARMOR_DIAMOND_CHESTPLATE = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
			ItemMeta meta = ARMOR_DIAMOND_CHESTPLATE.getItemMeta();

			Multimap<Attribute, AttributeModifier> modifiers = HashMultimap.create();

			modifiers.put(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(), "generic.max_health",
					10, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
			modifiers.put(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "generic.generic_armor", 7,
					Operation.ADD_NUMBER, EquipmentSlot.CHEST));
			modifiers.put(Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(),
					"generic.movement_speed", -0.06, Operation.MULTIPLY_SCALAR_1, EquipmentSlot.CHEST));

			meta.setAttributeModifiers(modifiers);
			ARMOR_DIAMOND_CHESTPLATE.setItemMeta(meta);
		}
		return ARMOR_DIAMOND_CHESTPLATE.clone();
	}

	public static ItemStack getDiamondLeggings() {
		if (ARMOR_DIAMOND_LEGGINGS == null) {
			ARMOR_DIAMOND_LEGGINGS = new ItemStack(Material.DIAMOND_LEGGINGS, 1);
			ItemMeta meta = ARMOR_DIAMOND_LEGGINGS.getItemMeta();

			Multimap<Attribute, AttributeModifier> modifiers = HashMultimap.create();

			modifiers.put(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(),
					"generic.max_health", 8, Operation.ADD_NUMBER, EquipmentSlot.LEGS));
			modifiers.put(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(),
					"generic.generic_armor", 5, Operation.ADD_NUMBER, EquipmentSlot.LEGS));
			modifiers.put(Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(),
					"generic.movement_speed", -0.06, Operation.MULTIPLY_SCALAR_1, EquipmentSlot.LEGS));

			meta.setAttributeModifiers(modifiers);
			ARMOR_DIAMOND_LEGGINGS.setItemMeta(meta);
		}
		return ARMOR_DIAMOND_LEGGINGS.clone();
	}

	public static ItemStack getDiamondBoots() {
		if (ARMOR_DIAMOND_BOOTS == null) {
			ARMOR_DIAMOND_BOOTS = new ItemStack(Material.DIAMOND_BOOTS, 1);
			ItemMeta meta = ARMOR_DIAMOND_BOOTS.getItemMeta();

			Multimap<Attribute, AttributeModifier> modifiers = HashMultimap.create();

			modifiers.put(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(), "generic.max_health",
					6, Operation.ADD_NUMBER, EquipmentSlot.FEET));
			modifiers.put(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "generic.generic_armor", 4,
					Operation.ADD_NUMBER, EquipmentSlot.FEET));
			modifiers.put(Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(),
					"generic.movement_speed", -0.02, Operation.MULTIPLY_SCALAR_1, EquipmentSlot.FEET));

			meta.setAttributeModifiers(modifiers);
			ARMOR_DIAMOND_BOOTS.setItemMeta(meta);
		}
		return ARMOR_DIAMOND_BOOTS.clone();
	}

	public static ItemStack getSilverCoin() {
		if (SILVER_COIN == null) {
			SILVER_COIN = new ItemStack(Material.IRON_NUGGET, 1);
			ItemMeta meta = SILVER_COIN.getItemMeta();
			meta.setLore(Arrays.asList(PREFIX_LORE_COMMON + "Simple Currency"));
			meta.setDisplayName(PREFIX_SPECIAL + "Silver Coin");
			SILVER_COIN.setItemMeta(meta);
		}
		return SILVER_COIN.clone();
	}

	public static ItemStack getErrorItem() {
		if (ERROR_ITEM == null) {
			ERROR_ITEM = new ItemStack(Material.GLASS, 1);
			ItemMeta meta = ERROR_ITEM.getItemMeta();
			meta.setDisplayName(ChatColor.RED + "Error with inventory");
			meta.setLore(Arrays.asList(ChatColor.GOLD + "Please contact a server admin"));
			ERROR_ITEM.setItemMeta(meta);
		}
		return ERROR_ITEM.clone();
	}

	public static ItemStack getShield() {
		if (SHIELD == null) {
			SHIELD = new ItemStack(Material.SHIELD, 1);
		}
		return SHIELD.clone();
	}

	public static ItemStack getWoodSword() {
		if (WOOD_SWORD == null) {
			WOOD_SWORD = new ItemStack(Material.WOODEN_SWORD, 1);
		}
		return WOOD_SWORD.clone();
	}

	public static ItemStack getWoodAxe() {
		if (WOOD_AXE == null) {
			WOOD_AXE = new ItemStack(Material.WOODEN_AXE, 1);
		}
		return WOOD_AXE.clone();
	}

	public static ItemStack getWoodShovel() {
		if (WOOD_SHOVEL == null) {
			WOOD_SHOVEL = new ItemStack(Material.WOODEN_SHOVEL, 1);
		}
		return WOOD_SHOVEL.clone();
	}

	public static ItemStack getWoodPickaxe() {
		if (WOOD_PICKAXE == null) {
			WOOD_PICKAXE = new ItemStack(Material.WOODEN_PICKAXE, 1);
		}
		return WOOD_PICKAXE.clone();
	}

	public static ItemStack getWoodHoe() {
		if (WOOD_HOE == null) {
			WOOD_HOE = new ItemStack(Material.WOODEN_HOE, 1);
		}
		return WOOD_HOE.clone();
	}

	public static ItemStack getStoneSword() {
		if (STONE_SWORD == null) {
			STONE_SWORD = new ItemStack(Material.STONE_SWORD, 1);
		}
		return STONE_SWORD.clone();
	}

	public static ItemStack getStoneShovel() {
		if (STONE_AXE == null) {
			STONE_AXE = new ItemStack(Material.STONE_SHOVEL, 1);
		}
		return STONE_AXE.clone();
	}

	public static ItemStack getStonePickaxe() {
		if (STONE_SHOVEL == null) {
			STONE_SHOVEL = new ItemStack(Material.STONE_PICKAXE, 1);
		}
		return STONE_SHOVEL.clone();
	}

	public static ItemStack getStoneAxe() {
		if (STONE_PICKAXE == null) {
			STONE_PICKAXE = new ItemStack(Material.STONE_AXE, 1);
		}
		return STONE_PICKAXE.clone();
	}

	public static ItemStack getStoneHoe() {
		if (STONE_HOE == null) {
			STONE_HOE = new ItemStack(Material.STONE_HOE, 1);
		}
		return STONE_HOE.clone();
	}


	public static ItemStack getIronSword() {
		if (IRON_SWORD == null) {
			IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);
		}
		return IRON_SWORD.clone();
	}

	public static ItemStack getDiamondSword() {
		if (DIAMOND_SWORD == null) {
			DIAMOND_SWORD = new ItemStack(Material.DIAMOND_SWORD, 1);
		}
		return DIAMOND_SWORD.clone();
	}

	public static ItemStack getIronShovel() {
		if (IRON_AXE == null) {
			IRON_AXE = new ItemStack(Material.IRON_SHOVEL, 1);
		}
		return IRON_AXE.clone();
	}

	public static ItemStack getIronPickaxe() {
		if (IRON_SHOVEL == null) {
			IRON_SHOVEL = new ItemStack(Material.IRON_PICKAXE, 1);
		}
		return IRON_SHOVEL.clone();
	}

	public static ItemStack getIronAxe() {
		if (IRON_PICKAXE == null) {
			IRON_PICKAXE = new ItemStack(Material.IRON_AXE, 1);
		}
		return IRON_PICKAXE.clone();
	}

	public static ItemStack getIronHoe() {
		if (IRON_HOE == null) {
			IRON_HOE = new ItemStack(Material.IRON_HOE, 1);
		}
		return IRON_HOE.clone();
	}

	public static ItemStack getDiamondShovel() {
		if (DIAMOND_AXE == null) {
			DIAMOND_AXE = new ItemStack(Material.DIAMOND_SHOVEL, 1);
		}
		return DIAMOND_AXE.clone();
	}

	public static ItemStack getDiamondPickaxe() {
		if (DIAMOND_SHOVEL == null) {
			DIAMOND_SHOVEL = new ItemStack(Material.DIAMOND_PICKAXE, 1);
		}
		return DIAMOND_SHOVEL.clone();
	}

	public static ItemStack getDiamondAxe() {
		if (DIAMOND_PICKAXE == null) {
			DIAMOND_PICKAXE = new ItemStack(Material.DIAMOND_AXE, 1);
		}
		return DIAMOND_PICKAXE.clone();
	}

	public static ItemStack getDiamondHoe() {
		if (DIAMOND_HOE == null) {
			DIAMOND_HOE = new ItemStack(Material.DIAMOND_HOE, 1);
		}
		return DIAMOND_HOE.clone();
	}


	public static ItemStack getSplinters() {
		// ? Potential issue with UUID set by attributes preventing proper usage with datapacks
		// TODO: Need to look more into this, unsure which specific component prevents usage with nbt-string defined items
		// * Item UUID attributes will change in MC 1.16, will wait until then.
		if (SPLINTERS == null) {
			SPLINTERS = new ItemStack(Material.STICK, 1);
			ItemMeta meta = SPLINTERS.getItemMeta();
			meta.setDisplayName(PREFIX_COMMON + "Sharp Splinters");

			ArrayList<String> lore = new ArrayList<String>();
			lore.add(ChatColor.GRAY + "A Bundle of rough sharp sticks");
			meta.setLore(lore);

			// Multimap<Attribute, AttributeModifier> modifiers = HashMultimap.create();
			// modifiers.put(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(),
			//      "generic.attackDamage", 2, Operation.ADD_NUMBER, EquipmentSlot.HAND));
			// meta.setAttributeModifiers(modifiers);
			SPLINTERS.setItemMeta(meta);
		}

		return SPLINTERS.clone();
	}


	/**
	 * Crafting books that unlock crafting recipes:
	 * <ul>
	 * <li> Crafting Table</li>
	 * <li> Wooden Tools <ul><li>Wooden Axe</li><li>Wooden Shovel</li><li>Wooden Pickaxe</li></ul></li>
	 * <li> Wooden Sword</li>
	 * </ul>
	 */
	public enum CraftingBook {
		CraftingTable, WoodenTools, WoodenSword, StoneTools, StoneSword, IronTools, IronSword,
	}

	/**
	 * Get a crafting recipe tome of the specified type.
	 * @param book {@link CraftingBook} book to get.
	 * @return ItemStack of the requested recipe
	 */
	public static ItemStack craftingBook(CraftingBook book) {
		switch (book) {
			case CraftingTable:
				if (TOME_CRAFTING_TABLE == null) {
					TOME_CRAFTING_TABLE = generateCraftingBook(CraftingBook.CraftingTable);
				}
				return TOME_CRAFTING_TABLE.clone();
			case WoodenTools:
				if (TOME_WOODEN_TOOLS == null) {
					TOME_WOODEN_TOOLS = generateCraftingBook(CraftingBook.WoodenTools);
				}
				return TOME_WOODEN_TOOLS.clone();
			case StoneTools:
				if (TOME_STONE_TOOLS == null) {
					TOME_STONE_TOOLS = generateCraftingBook(CraftingBook.StoneTools);
				}
				return TOME_STONE_TOOLS.clone();
			case IronTools:
				if (TOME_IRON_TOOLS == null) {
					TOME_IRON_TOOLS = generateCraftingBook(CraftingBook.IronTools);
				}
				return TOME_IRON_TOOLS.clone();
			case WoodenSword:
				if (TOME_WOODEN_SWORD == null) {
					TOME_WOODEN_SWORD = generateCraftingBook(CraftingBook.WoodenSword);
				}
				return TOME_WOODEN_SWORD.clone();
			case StoneSword:
				if (TOME_STONE_SWORD == null) {
					TOME_STONE_SWORD = generateCraftingBook(CraftingBook.StoneSword);
				}
			return TOME_STONE_SWORD.clone();
			case IronSword:
				if (TOME_IRON_SWORD == null) {
					TOME_IRON_SWORD = generateCraftingBook(CraftingBook.IronSword);
				}
				return TOME_IRON_SWORD.clone();
			default:
				return generateCraftingBook(book);
		}
	}

	/**
	 * Generate a crafting book of the specified type. Does not cache requests.
	 * @param book {@link CraftingBook} to generate.
	 * @return Generated crafting book
	 */
	@SuppressWarnings("deprecation")
	private static ItemStack generateCraftingBook(CraftingBook book) {
		ItemStack knowledgeBook = new ItemStack(Material.KNOWLEDGE_BOOK);
		KnowledgeBookMeta itemMeta = (KnowledgeBookMeta) knowledgeBook.getItemMeta();

		itemMeta.addEnchant(Enchantment.DURABILITY, 1, true);
		itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

		switch (book) {
			case CraftingTable:
				itemMeta.setDisplayName(PREFIX_RECIPE + "Crafting Table");

				ArrayList<String> loreCraftingTable = new ArrayList<String>();

				loreCraftingTable.add(ChatColor.RESET + "" + ChatColor.AQUA + "Use to unlock Crafting Tables");
				loreCraftingTable.add(PREFIX_LORE_COMMON + "Crafting tables are used to craft");
				loreCraftingTable.add(PREFIX_LORE_COMMON + "complex items, such as tools.");
				loreCraftingTable.add("");
				loreCraftingTable.add(ChatColor.GREEN + "Unlocks:");
				loreCraftingTable.add(PREFIX_LORE_COMMON + "- Crafting Table");
				loreCraftingTable.add("");
				loreCraftingTable.add(ChatColor.MAGIC + "Replacement for the B key");

				itemMeta.setLore(loreCraftingTable);

				// ? No alternative to NamespacedKey(), even though it's depreciated?
				itemMeta.addRecipe(new NamespacedKey("minecraft", "crafting_table"));
				break;
			case WoodenTools:
				itemMeta.setDisplayName(PREFIX_RECIPE + "Wooden Tools");

				ArrayList<String> loreWoodenTools = new ArrayList<String>();

				loreWoodenTools.add(ChatColor.RESET + "" + ChatColor.AQUA + "Use to unlock Wooden Tools");
				loreWoodenTools.add(PREFIX_LORE_COMMON + "Wooden tools are the most basic,");
				loreWoodenTools.add(PREFIX_LORE_COMMON + "but still better than punching.");
				loreWoodenTools.add("");
				loreWoodenTools.add(ChatColor.GREEN + "Unlocks:");
				loreWoodenTools.add(PREFIX_LORE_COMMON + "- Wooden Axe");
				loreWoodenTools.add(PREFIX_LORE_COMMON + "- Wooden Shovel");
				loreWoodenTools.add(PREFIX_LORE_COMMON + "- Wooden Pickaxe");
				loreWoodenTools.add("");
				loreWoodenTools.add(ChatColor.MAGIC + "Only used once");

				itemMeta.setLore(loreWoodenTools);

				itemMeta.addRecipe(new NamespacedKey("minecraft", "wooden_axe"));
				itemMeta.addRecipe(new NamespacedKey("minecraft", "wooden_pickaxe"));
				itemMeta.addRecipe(new NamespacedKey("minecraft", "wooden_shovel"));
				break;
			case WoodenSword:
				itemMeta.setDisplayName(PREFIX_RECIPE + "Wooden Sword");

				ArrayList<String> loreWoodenSword = new ArrayList<String>();

				loreWoodenSword.add(ChatColor.RESET + "" + ChatColor.AQUA + "Use to unlock Wooden Swords");
				loreWoodenSword.add(PREFIX_LORE_COMMON + "A wooden sword isn't the best,");
				loreWoodenSword.add(PREFIX_LORE_COMMON + "but it's sharper than some sticks.");
				loreWoodenSword.add("");
				loreWoodenSword.add(ChatColor.GREEN + "Unlocks:");
				loreWoodenSword.add(PREFIX_LORE_COMMON + "- Wooden Sword");
				loreWoodenSword.add("");
				loreWoodenSword.add(ChatColor.MAGIC + "Sharp Pointy Stick");

				itemMeta.setLore(loreWoodenSword);

				itemMeta.addRecipe(new NamespacedKey("minecraft", "wooden_sword"));
				break;
			// TODO: construct learning tomes
			case IronSword:
			case IronTools:
			case StoneSword:
			case StoneTools:
			default:
				itemMeta.setDisplayName(PREFIX_COMMON + "Green Broken Book");
				ArrayList<String> loreDefault = new ArrayList<String>();
				loreDefault.add(PREFIX_LORE_COMMON + "This strange green book");
				loreDefault.add(PREFIX_LORE_COMMON + "dissolves when you use it...");
				itemMeta.setLore(loreDefault);
				itemMeta.addRecipe(new NamespacedKey("minecraft", "campfire"));
				break;
		}

		knowledgeBook.setItemMeta(itemMeta);
		return knowledgeBook;
	}

}
