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
 * * Note: Remember to {@code .clone()} any static before returning.
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
	 * Uncommon item text
	 */
	public static String PREFIX_UNCOMMON = "" + ChatColor.AQUA;

	/**
	 * Common lore text
	 */
	public static String PREFIX_LORE_COMMON = PREFIX_COMMON + ChatColor.GRAY;

	// ~~~~~~~~ Item Instance Caching ~~~~~~~~ //

	// --------- Tools -------- //
	private static ItemStack AXE_WOOD;
	private static ItemStack AXE_STONE;
	private static ItemStack AXE_IRON;
	private static ItemStack AXE_DIAMOND;

	private static ItemStack SHOVEL_WOOD;
	private static ItemStack SHOVEL_STONE;
	private static ItemStack SHOVEL_IRON;
	private static ItemStack SHOVEL_DIAMOND;

	private static ItemStack SWORD_WOOD;
	private static ItemStack SWORD_STONE;
	private static ItemStack SWORD_IRON;
	private static ItemStack SWORD_DIAMOND;

	private static ItemStack PICKAXE_WOOD;
	private static ItemStack PICKAXE_STONE;
	private static ItemStack PICKAXE_IRON;
	private static ItemStack PICKAXE_DIAMOND;

	private static ItemStack HOE_WOOD;
	private static ItemStack HOE_STONE;
	private static ItemStack HOE_IRON;
	private static ItemStack HOE_DIAMOND;

	// --------- Armor -------- //
	private static ItemStack ARMOR_HELMET_LEATHER;
	private static ItemStack ARMOR_HELMET_CHAINMAIL;
	private static ItemStack ARMOR_HELMET_IRON;
	private static ItemStack ARMOR_HELMET_DIAMOND;

	private static ItemStack ARMOR_CHESTPLATE_LEATHER;
	private static ItemStack ARMOR_CHESTPLATE_CHAINMAIL;
	private static ItemStack ARMOR_CHESTPLATE_IRON;
	private static ItemStack ARMOR_CHESTPLATE_DIAMOND;

	private static ItemStack ARMOR_LEGGINGS_LEATHER;
	private static ItemStack ARMOR_LEGGINGS_CHAINMAIL;
	private static ItemStack ARMOR_LEGGINGS_IRON;
	private static ItemStack ARMOR_LEGGINGS_DIAMOND;

	private static ItemStack ARMOR_BOOTS_LEATHER;
	private static ItemStack ARMOR_BOOTS_CHAINMAIL;
	private static ItemStack ARMOR_BOOTS_IRON;
	private static ItemStack ARMOR_BOOTS_DIAMOND;

	// --------- Tomes -------- //
	private static ItemStack TOME_CRAFTING_TABLE;

	private static ItemStack TOME_PICKAXE_WOODEN;
	private static ItemStack TOME_PICKAXE_STONE;
	private static ItemStack TOME_PICKAXE_IRON;
	// private static ItemStack TOME_PICKAXE_DIAMOND;

	private static ItemStack TOME_TOOLS_WOODEN;
	private static ItemStack TOME_TOOLS_STONE;
	private static ItemStack TOME_TOOLS_IRON;
	// private static ItemStack TOME_TOOLS_DIAMOND;

	private static ItemStack TOME_SWORD_WOODEN;
	private static ItemStack TOME_SWORD_STONE;
	private static ItemStack TOME_SWORD_IRON;
	// private static ItemStack TOME_SWORD_DIAMOND;

	private static ItemStack TOME_ARMOR_HAT_BOOTS_LEATHER;
	private static ItemStack TOME_ARMOR_HAT_BOOTS_CHAINMAIL;
	private static ItemStack TOME_ARMOR_HAT_BOOTS_IRON;
	// private static ItemStack TOME_ARMOR_HAT_BOOTS_DIAMOND;

	private static ItemStack TOME_ARMOR_CHEST_LEGS_LEATHER;
	private static ItemStack TOME_ARMOR_CHEST_LEGS_CHAINMAIL;
	private static ItemStack TOME_ARMOR_CHEST_LEGS_IRON;
	// private static ItemStack TOME_ARMOR_CHEST_LEGS_DIAMOND;

	// ---- Misc equipment ---- //
	private static ItemStack SHIELD;

	// -------- Arrows -------- //
	private static ItemStack ARROW_ENDER;
	private static ItemStack ARROW_PIONEER;

	// --------- Misc --------- //
	private static ItemStack ERROR_ITEM;
	private static ItemStack SPLINTERS;
	private static ItemStack SILVER_COIN;
	private static ItemStack GOLD_COIN;

	// -------- Food -------- //
	private static ItemStack STEAK_TIER_1;
	private static ItemStack STEAK_TIER_2;
	private static ItemStack STEAK_TIER_3;
	private static ItemStack STEAK_TIER_4;
	private static ItemStack STEAK_TIER_5;

	private static ItemStack MUTTON_TIER_1;
	private static ItemStack MUTTON_TIER_2;
	private static ItemStack MUTTON_TIER_3;
	private static ItemStack MUTTON_TIER_4;
	private static ItemStack MUTTON_TIER_5;

	private static ItemStack PORK_TIER_1;
	private static ItemStack PORK_TIER_2;
	private static ItemStack PORK_TIER_3;
	private static ItemStack PORK_TIER_4;
	private static ItemStack PORK_TIER_5;

	private static ItemStack CHICKEN_TIER_1;
	private static ItemStack CHICKEN_TIER_2;
	private static ItemStack CHICKEN_TIER_3;
	private static ItemStack CHICKEN_TIER_4;
	private static ItemStack CHICKEN_TIER_5;

	private static ItemStack RABBIT_STEW_TIER_1;
	private static ItemStack RABBIT_STEW_TIER_2;
	private static ItemStack RABBIT_STEW_TIER_3;
	private static ItemStack RABBIT_STEW_TIER_4;
	private static ItemStack RABBIT_STEW_TIER_5;

	private static ItemStack COOKED_COD_TIER_1;
	private static ItemStack COOKED_COD_TIER_2;
	private static ItemStack COOKED_COD_TIER_3;
	private static ItemStack COOKED_COD_TIER_4;
	private static ItemStack COOKED_COD_TIER_5;

	// VEGGIES //
	private static ItemStack POTATO_TIER_1;
	private static ItemStack POTATO_TIER_2;
	private static ItemStack POTATO_TIER_3;
	private static ItemStack POTATO_TIER_4;

	private static ItemStack BREAD_TIER_1;
	private static ItemStack BREAD_TIER_2;
	private static ItemStack BREAD_TIER_3;
	private static ItemStack BREAD_TIER_4;

	private static ItemStack MUSHROOM_SOUP_TIER_1;
	private static ItemStack MUSHROOM_SOUP_TIER_2;
	private static ItemStack MUSHROOM_SOUP_TIER_3;
	private static ItemStack MUSHROOM_SOUP_TIER_4;
	private static ItemStack MUSHROOM_SOUP_TIER_5;

	private static ItemStack BEETROOT_SOUP_TIER_1;
	private static ItemStack BEETROOT_SOUP_TIER_2;
	private static ItemStack BEETROOT_SOUP_TIER_3;
	private static ItemStack BEETROOT_SOUP_TIER_4;
	private static ItemStack BEETROOT_SOUP_TIER_5;

	// ~~~~~~~~~~~~~ Item Methods ~~~~~~~~~~~~ //
	public static ItemStack getLeatherHelmet() {
		if (ARMOR_HELMET_LEATHER == null) {
			ARMOR_HELMET_LEATHER = new ItemStack(Material.LEATHER_HELMET, 1);
			ItemMeta meta = ARMOR_HELMET_LEATHER.getItemMeta();

			Multimap<Attribute, AttributeModifier> modifiers = HashMultimap.create();

			modifiers.put(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(), "generic.max_health",
					2, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
			modifiers.put(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "generic.generic_armor", 1,
					Operation.ADD_NUMBER, EquipmentSlot.HEAD));

			meta.setAttributeModifiers(modifiers);
			ARMOR_HELMET_LEATHER.setItemMeta(meta);
		}
		return ARMOR_HELMET_LEATHER.clone();
	}

	public static ItemStack getLeatherChestplate() {
		if (ARMOR_CHESTPLATE_LEATHER == null) {
			ARMOR_CHESTPLATE_LEATHER = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
			ItemMeta meta = ARMOR_CHESTPLATE_LEATHER.getItemMeta();

			Multimap<Attribute, AttributeModifier> modifiers = HashMultimap.create();

			modifiers.put(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(), "generic.max_health",
					4, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
			modifiers.put(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "generic.generic_armor", 3,
					Operation.ADD_NUMBER, EquipmentSlot.CHEST));

			meta.setAttributeModifiers(modifiers);
			ARMOR_CHESTPLATE_LEATHER.setItemMeta(meta);
		}
		return ARMOR_CHESTPLATE_LEATHER.clone();
	}

	public static ItemStack getLeatherLeggings() {
		if (ARMOR_LEGGINGS_LEATHER == null) {
			ARMOR_LEGGINGS_LEATHER = new ItemStack(Material.LEATHER_LEGGINGS, 1);
			ItemMeta meta = ARMOR_LEGGINGS_LEATHER.getItemMeta();

			Multimap<Attribute, AttributeModifier> modifiers = HashMultimap.create();

			modifiers.put(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(), "generic.max_health",
					2, Operation.ADD_NUMBER, EquipmentSlot.LEGS));
			modifiers.put(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "generic.generic_armor", 2,
					Operation.ADD_NUMBER, EquipmentSlot.LEGS));

			meta.setAttributeModifiers(modifiers);
			ARMOR_LEGGINGS_LEATHER.setItemMeta(meta);
		}
		return ARMOR_LEGGINGS_LEATHER.clone();
	}

	public static ItemStack getLeatherBoots() {
		if (ARMOR_BOOTS_LEATHER == null) {
			ARMOR_BOOTS_LEATHER = new ItemStack(Material.LEATHER_BOOTS, 1);
			ItemMeta meta = ARMOR_BOOTS_LEATHER.getItemMeta();
			meta.setLore(Arrays.asList(PREFIX_LORE_COMMON + "Cannot Trample Crops"));

			Multimap<Attribute, AttributeModifier> modifiers = HashMultimap.create();

			modifiers.put(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(), "generic.max_health",
					2, Operation.ADD_NUMBER, EquipmentSlot.FEET));
			modifiers.put(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "generic.generic_armor", 1,
					Operation.ADD_NUMBER, EquipmentSlot.FEET));

			meta.setAttributeModifiers(modifiers);
			ARMOR_BOOTS_LEATHER.setItemMeta(meta);
		}
		return ARMOR_BOOTS_LEATHER.clone();
	}

	public static ItemStack getChainmailHelmet() {
		if (ARMOR_HELMET_CHAINMAIL == null) {
			ARMOR_HELMET_CHAINMAIL = new ItemStack(Material.CHAINMAIL_HELMET, 1);
			ItemMeta meta = ARMOR_HELMET_CHAINMAIL.getItemMeta();

			Multimap<Attribute, AttributeModifier> modifiers = HashMultimap.create();

			modifiers.put(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(), "generic.max_health",
					4, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
			modifiers.put(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "generic.generic_armor", 3,
					Operation.ADD_NUMBER, EquipmentSlot.HEAD));

			meta.setAttributeModifiers(modifiers);
			ARMOR_HELMET_CHAINMAIL.setItemMeta(meta);
		}
		return ARMOR_HELMET_CHAINMAIL.clone();
	}

	public static ItemStack getChainmailChestplate() {
		if (ARMOR_CHESTPLATE_CHAINMAIL == null) {
			ARMOR_CHESTPLATE_CHAINMAIL = new ItemStack(Material.CHAINMAIL_CHESTPLATE, 1);
			ItemMeta meta = ARMOR_CHESTPLATE_CHAINMAIL.getItemMeta();

			Multimap<Attribute, AttributeModifier> modifiers = HashMultimap.create();

			modifiers.put(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(), "generic.max_health",
					4, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
			modifiers.put(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "generic.generic_armor", 5,
					Operation.ADD_NUMBER, EquipmentSlot.CHEST));

			meta.setAttributeModifiers(modifiers);
			ARMOR_CHESTPLATE_CHAINMAIL.setItemMeta(meta);
		}
		return ARMOR_CHESTPLATE_CHAINMAIL.clone();
	}

	public static ItemStack getChainmailLeggings() {
		if (ARMOR_LEGGINGS_CHAINMAIL == null) {
			ARMOR_LEGGINGS_CHAINMAIL = new ItemStack(Material.CHAINMAIL_LEGGINGS, 1);
			ItemMeta meta = ARMOR_LEGGINGS_CHAINMAIL.getItemMeta();

			Multimap<Attribute, AttributeModifier> modifiers = HashMultimap.create();

			modifiers.put(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(), "generic.max_health",
					4, Operation.ADD_NUMBER, EquipmentSlot.LEGS));
			modifiers.put(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "generic.generic_armor", 4,
					Operation.ADD_NUMBER, EquipmentSlot.LEGS));

			meta.setAttributeModifiers(modifiers);
			ARMOR_LEGGINGS_CHAINMAIL.setItemMeta(meta);
		}
		return ARMOR_LEGGINGS_CHAINMAIL.clone();
	}

	public static ItemStack getChainmailBoots() {
		if (ARMOR_BOOTS_CHAINMAIL == null) {
			ARMOR_BOOTS_CHAINMAIL = new ItemStack(Material.CHAINMAIL_BOOTS, 1);
			ItemMeta meta = ARMOR_BOOTS_CHAINMAIL.getItemMeta();

			Multimap<Attribute, AttributeModifier> modifiers = HashMultimap.create();

			modifiers.put(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(), "generic.max_health",
					2, Operation.ADD_NUMBER, EquipmentSlot.FEET));
			modifiers.put(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "generic.generic_armor", 2,
					Operation.ADD_NUMBER, EquipmentSlot.FEET));

			meta.setAttributeModifiers(modifiers);
			ARMOR_BOOTS_CHAINMAIL.setItemMeta(meta);
		}
		return ARMOR_BOOTS_CHAINMAIL.clone();
	}

	public static ItemStack getIronHelmet() {
		if (ARMOR_HELMET_IRON == null) {
			ARMOR_HELMET_IRON = new ItemStack(Material.IRON_HELMET, 1);
			ItemMeta meta = ARMOR_HELMET_IRON.getItemMeta();

			Multimap<Attribute, AttributeModifier> modifiers = HashMultimap.create();

			modifiers.put(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(), "generic.max_health",
					4, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
			modifiers.put(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "generic.generic_armor", 3,
					Operation.ADD_NUMBER, EquipmentSlot.HEAD));
			modifiers.put(Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(),
					"generic.movement_speed", -0.01, Operation.MULTIPLY_SCALAR_1, EquipmentSlot.HEAD));

			meta.setAttributeModifiers(modifiers);
			ARMOR_HELMET_IRON.setItemMeta(meta);
		}
		return ARMOR_HELMET_IRON.clone();
	}

	public static ItemStack getIronChestplate() {
		if (ARMOR_CHESTPLATE_IRON == null) {
			ARMOR_CHESTPLATE_IRON = new ItemStack(Material.IRON_CHESTPLATE, 1);
			ItemMeta meta = ARMOR_CHESTPLATE_IRON.getItemMeta();

			Multimap<Attribute, AttributeModifier> modifiers = HashMultimap.create();

			modifiers.put(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(), "generic.max_health",
					6, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
			modifiers.put(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "generic.generic_armor", 6,
					Operation.ADD_NUMBER, EquipmentSlot.CHEST));
			modifiers.put(Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(),
					"generic.movement_speed", -0.03, Operation.MULTIPLY_SCALAR_1, EquipmentSlot.CHEST));

			meta.setAttributeModifiers(modifiers);
			ARMOR_CHESTPLATE_IRON.setItemMeta(meta);
		}
		return ARMOR_CHESTPLATE_IRON.clone();
	}

	public static ItemStack getIronLeggings() {
		if (ARMOR_LEGGINGS_IRON == null) {
			ARMOR_LEGGINGS_IRON = new ItemStack(Material.IRON_LEGGINGS, 1);
			ItemMeta meta = ARMOR_LEGGINGS_IRON.getItemMeta();

			Multimap<Attribute, AttributeModifier> modifiers = HashMultimap.create();

			modifiers.put(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(), "generic.max_health",
					6, Operation.ADD_NUMBER, EquipmentSlot.LEGS));
			modifiers.put(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "generic.generic_armor", 4,
					Operation.ADD_NUMBER, EquipmentSlot.LEGS));
			modifiers.put(Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(),
					"generic.movement_speed", -0.03, Operation.MULTIPLY_SCALAR_1, EquipmentSlot.LEGS));

			meta.setAttributeModifiers(modifiers);
			ARMOR_LEGGINGS_IRON.setItemMeta(meta);
		}
		return ARMOR_LEGGINGS_IRON.clone();
	}

	public static ItemStack getIronBoots() {
		if (ARMOR_BOOTS_IRON == null) {
			ARMOR_BOOTS_IRON = new ItemStack(Material.IRON_BOOTS, 1);
			ItemMeta meta = ARMOR_BOOTS_IRON.getItemMeta();

			Multimap<Attribute, AttributeModifier> modifiers = HashMultimap.create();

			modifiers.put(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(), "generic.max_health",
					4, Operation.ADD_NUMBER, EquipmentSlot.FEET));
			modifiers.put(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "generic.generic_armor", 3,
					Operation.ADD_NUMBER, EquipmentSlot.FEET));
			modifiers.put(Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(),
					"generic.movement_speed", -0.01, Operation.MULTIPLY_SCALAR_1, EquipmentSlot.FEET));

			meta.setAttributeModifiers(modifiers);
			ARMOR_BOOTS_IRON.setItemMeta(meta);
		}
		return ARMOR_BOOTS_IRON.clone();
	}

	public static ItemStack getDiamondHelmet() {
		if (ARMOR_HELMET_DIAMOND == null) {
			ARMOR_HELMET_DIAMOND = new ItemStack(Material.DIAMOND_HELMET, 1);
			ItemMeta meta = ARMOR_HELMET_DIAMOND.getItemMeta();

			Multimap<Attribute, AttributeModifier> modifiers = HashMultimap.create();

			modifiers.put(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(), "generic.max_health",
					6, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
			modifiers.put(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "generic.generic_armor", 4,
					Operation.ADD_NUMBER, EquipmentSlot.HEAD));
			modifiers.put(Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(),
					"generic.movement_speed", -0.02, Operation.MULTIPLY_SCALAR_1, EquipmentSlot.HEAD));

			meta.setAttributeModifiers(modifiers);
			ARMOR_HELMET_DIAMOND.setItemMeta(meta);
		}
		return ARMOR_HELMET_DIAMOND.clone();
	}

	public static ItemStack getDiamondChestplate() {
		if (ARMOR_CHESTPLATE_DIAMOND == null) {
			ARMOR_CHESTPLATE_DIAMOND = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
			ItemMeta meta = ARMOR_CHESTPLATE_DIAMOND.getItemMeta();

			Multimap<Attribute, AttributeModifier> modifiers = HashMultimap.create();

			modifiers.put(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(), "generic.max_health",
					10, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
			modifiers.put(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "generic.generic_armor", 7,
					Operation.ADD_NUMBER, EquipmentSlot.CHEST));
			modifiers.put(Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(),
					"generic.movement_speed", -0.06, Operation.MULTIPLY_SCALAR_1, EquipmentSlot.CHEST));

			meta.setAttributeModifiers(modifiers);
			ARMOR_CHESTPLATE_DIAMOND.setItemMeta(meta);
		}
		return ARMOR_CHESTPLATE_DIAMOND.clone();
	}

	public static ItemStack getDiamondLeggings() {
		if (ARMOR_LEGGINGS_DIAMOND == null) {
			ARMOR_LEGGINGS_DIAMOND = new ItemStack(Material.DIAMOND_LEGGINGS, 1);
			ItemMeta meta = ARMOR_LEGGINGS_DIAMOND.getItemMeta();

			Multimap<Attribute, AttributeModifier> modifiers = HashMultimap.create();

			modifiers.put(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(), "generic.max_health",
					8, Operation.ADD_NUMBER, EquipmentSlot.LEGS));
			modifiers.put(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "generic.generic_armor", 5,
					Operation.ADD_NUMBER, EquipmentSlot.LEGS));
			modifiers.put(Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(),
					"generic.movement_speed", -0.06, Operation.MULTIPLY_SCALAR_1, EquipmentSlot.LEGS));

			meta.setAttributeModifiers(modifiers);
			ARMOR_LEGGINGS_DIAMOND.setItemMeta(meta);
		}
		return ARMOR_LEGGINGS_DIAMOND.clone();
	}

	public static ItemStack getDiamondBoots() {
		if (ARMOR_BOOTS_DIAMOND == null) {
			ARMOR_BOOTS_DIAMOND = new ItemStack(Material.DIAMOND_BOOTS, 1);
			ItemMeta meta = ARMOR_BOOTS_DIAMOND.getItemMeta();

			Multimap<Attribute, AttributeModifier> modifiers = HashMultimap.create();

			modifiers.put(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(), "generic.max_health",
					6, Operation.ADD_NUMBER, EquipmentSlot.FEET));
			modifiers.put(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "generic.generic_armor", 4,
					Operation.ADD_NUMBER, EquipmentSlot.FEET));
			modifiers.put(Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(),
					"generic.movement_speed", -0.02, Operation.MULTIPLY_SCALAR_1, EquipmentSlot.FEET));

			meta.setAttributeModifiers(modifiers);
			ARMOR_BOOTS_DIAMOND.setItemMeta(meta);
		}
		return ARMOR_BOOTS_DIAMOND.clone();
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

	public static ItemStack getGoldCoin() {
		if (GOLD_COIN == null) {
			GOLD_COIN = new ItemStack(Material.GOLD_NUGGET, 1);
			ItemMeta meta = GOLD_COIN.getItemMeta();
			meta.setLore(Arrays.asList(PREFIX_LORE_COMMON + "Worth 64 Silver Coins."));
			meta.setDisplayName(PREFIX_SPECIAL + "Gold Coin");
			GOLD_COIN.setItemMeta(meta);
		}
		return GOLD_COIN.clone();
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
		if (SWORD_WOOD == null) {
			SWORD_WOOD = new ItemStack(Material.WOODEN_SWORD, 1);
		}
		return SWORD_WOOD.clone();
	}

	public static ItemStack getWoodAxe() {
		if (AXE_WOOD == null) {
			AXE_WOOD = new ItemStack(Material.WOODEN_AXE, 1);
		}
		return AXE_WOOD.clone();
	}

	public static ItemStack getWoodShovel() {
		if (SHOVEL_WOOD == null) {
			SHOVEL_WOOD = new ItemStack(Material.WOODEN_SHOVEL, 1);
		}
		return SHOVEL_WOOD.clone();
	}

	public static ItemStack getWoodPickaxe() {
		if (PICKAXE_WOOD == null) {
			PICKAXE_WOOD = new ItemStack(Material.WOODEN_PICKAXE, 1);
		}
		return PICKAXE_WOOD.clone();
	}

	public static ItemStack getWoodHoe() {
		if (HOE_WOOD == null) {
			HOE_WOOD = new ItemStack(Material.WOODEN_HOE, 1);
		}
		return HOE_WOOD.clone();
	}

	public static ItemStack getStoneSword() {
		if (SWORD_STONE == null) {
			SWORD_STONE = new ItemStack(Material.STONE_SWORD, 1);
		}
		return SWORD_STONE.clone();
	}

	public static ItemStack getStoneShovel() {
		if (AXE_STONE == null) {
			AXE_STONE = new ItemStack(Material.STONE_SHOVEL, 1);
		}
		return AXE_STONE.clone();
	}

	public static ItemStack getStonePickaxe() {
		if (SHOVEL_STONE == null) {
			SHOVEL_STONE = new ItemStack(Material.STONE_PICKAXE, 1);
		}
		return SHOVEL_STONE.clone();
	}

	public static ItemStack getStoneAxe() {
		if (PICKAXE_STONE == null) {
			PICKAXE_STONE = new ItemStack(Material.STONE_AXE, 1);
		}
		return PICKAXE_STONE.clone();
	}

	public static ItemStack getStoneHoe() {
		if (HOE_STONE == null) {
			HOE_STONE = new ItemStack(Material.STONE_HOE, 1);
		}
		return HOE_STONE.clone();
	}

	public static ItemStack getIronSword() {
		if (SWORD_IRON == null) {
			SWORD_IRON = new ItemStack(Material.IRON_SWORD, 1);
		}
		return SWORD_IRON.clone();
	}

	public static ItemStack getDiamondSword() {
		if (SWORD_DIAMOND == null) {
			SWORD_DIAMOND = new ItemStack(Material.DIAMOND_SWORD, 1);
		}
		return SWORD_DIAMOND.clone();
	}

	public static ItemStack getIronShovel() {
		if (AXE_IRON == null) {
			AXE_IRON = new ItemStack(Material.IRON_SHOVEL, 1);
		}
		return AXE_IRON.clone();
	}

	public static ItemStack getIronPickaxe() {
		if (SHOVEL_IRON == null) {
			SHOVEL_IRON = new ItemStack(Material.IRON_PICKAXE, 1);
		}
		return SHOVEL_IRON.clone();
	}

	public static ItemStack getIronAxe() {
		if (PICKAXE_IRON == null) {
			PICKAXE_IRON = new ItemStack(Material.IRON_AXE, 1);
		}
		return PICKAXE_IRON.clone();
	}

	public static ItemStack getIronHoe() {
		if (HOE_IRON == null) {
			HOE_IRON = new ItemStack(Material.IRON_HOE, 1);
		}
		return HOE_IRON.clone();
	}

	public static ItemStack getDiamondShovel() {
		if (AXE_DIAMOND == null) {
			AXE_DIAMOND = new ItemStack(Material.DIAMOND_SHOVEL, 1);
		}
		return AXE_DIAMOND.clone();
	}

	public static ItemStack getDiamondPickaxe() {
		if (SHOVEL_DIAMOND == null) {
			SHOVEL_DIAMOND = new ItemStack(Material.DIAMOND_PICKAXE, 1);
		}
		return SHOVEL_DIAMOND.clone();
	}

	public static ItemStack getDiamondAxe() {
		if (PICKAXE_DIAMOND == null) {
			PICKAXE_DIAMOND = new ItemStack(Material.DIAMOND_AXE, 1);
		}
		return PICKAXE_DIAMOND.clone();
	}

	public static ItemStack getDiamondHoe() {
		if (HOE_DIAMOND == null) {
			HOE_DIAMOND = new ItemStack(Material.DIAMOND_HOE, 1);
		}
		return HOE_DIAMOND.clone();
	}

	public static ItemStack getSplinters() {
		// ? Potential issue with UUID set by attributes preventing proper usage with
		// datapacks
		// TODO: Need to look more into this, unsure which specific component prevents
		// usage with nbt-string defined items
		// * Item UUID attributes will change in MC 1.16, will wait until then.
		if (SPLINTERS == null) {
			SPLINTERS = new ItemStack(Material.STICK, 1);
			ItemMeta meta = SPLINTERS.getItemMeta();
			meta.setDisplayName(PREFIX_COMMON + "Sharp Splinters");

			ArrayList<String> lore = new ArrayList<String>();
			lore.add(ChatColor.GRAY + "A Bundle of rough sharp sticks");
			meta.setLore(lore);

			// Multimap<Attribute, AttributeModifier> modifiers = HashMultimap.create();
			// modifiers.put(Attribute.GENERIC_ATTACK_DAMAGE, new
			// AttributeModifier(UUID.randomUUID(),
			// "generic.attackDamage", 2, Operation.ADD_NUMBER, EquipmentSlot.HAND));
			// meta.setAttributeModifiers(modifiers);
			SPLINTERS.setItemMeta(meta);
		}

		return SPLINTERS.clone();
	}

	public static ItemStack getEnderArrow() {
		if (ARROW_ENDER == null) {
			ARROW_ENDER = new ItemStack(Material.ARROW, 1);
			ItemMeta meta = ARROW_ENDER.getItemMeta();
			meta.setDisplayName(PREFIX_UNCOMMON + "Ender Arrow");

			ArrayList<String> lore = new ArrayList<String>();
			lore.add(ChatColor.GRAY + "An arrow infused with an Ender Perl");
			meta.setLore(lore);

			ARROW_ENDER.setItemMeta(meta);
			ARROW_ENDER.ensureServerConversions();
		}

		return ARROW_ENDER.clone();
	}

	public static ItemStack getSaddledArrow() {
		if (ARROW_PIONEER == null) {
			ARROW_PIONEER = new ItemStack(Material.ARROW, 1);
			ItemMeta meta = ARROW_PIONEER.getItemMeta();
			meta.setDisplayName(PREFIX_UNCOMMON + "Pioneer Arrow");

			ArrayList<String> lore = new ArrayList<String>();
			lore.add(ChatColor.GRAY + "The pioneers used to ride these babies for miles");
			meta.setLore(lore);

			ARROW_PIONEER.setItemMeta(meta);
			ARROW_PIONEER.ensureServerConversions();
		}

		return ARROW_PIONEER.clone();
	}

	public static ItemStack getSteakTier1() {
		if (STEAK_TIER_1 == null) {
			STEAK_TIER_1 = new ItemStack(Material.COOKED_BEEF, 1);
			ItemMeta meta = STEAK_TIER_1.getItemMeta();
			meta.setDisplayName(PREFIX_UNCOMMON + "Steak (OK)");

			ArrayList<String> lore = new ArrayList<String>();
			lore.add(ChatColor.GRAY + "Overdone steak, gets the job done");
			lore.add(ChatColor.GOLD + "Hunger + 1");
			meta.setLore(lore);

			STEAK_TIER_1.setItemMeta(meta);
			STEAK_TIER_1.ensureServerConversions();
		}

		return STEAK_TIER_1.clone();
	}
	public static ItemStack getSteakTier2() {
		if (STEAK_TIER_2 == null) {
			STEAK_TIER_2 = new ItemStack(Material.COOKED_BEEF, 1);
			ItemMeta meta = STEAK_TIER_2.getItemMeta();
			meta.setDisplayName(PREFIX_UNCOMMON + "Steak (Good)");

			ArrayList<String> lore = new ArrayList<String>();
			lore.add(ChatColor.GRAY + "Medium Steak, slightly burnt");
			lore.add(ChatColor.GOLD + "Hunger: + 2");
			lore.add(ChatColor.YELLOW + "Saturation: + 2");

			meta.setLore(lore);

			STEAK_TIER_2.setItemMeta(meta);
			STEAK_TIER_2.ensureServerConversions();
		}

		return STEAK_TIER_2.clone();
	}
	public static ItemStack getSteakTier3() {
		if (STEAK_TIER_3 == null) {
			STEAK_TIER_3 = new ItemStack(Material.COOKED_BEEF, 1);
			ItemMeta meta = STEAK_TIER_3.getItemMeta();
			meta.setDisplayName(PREFIX_UNCOMMON + "Steak (Great)");

			ArrayList<String> lore = new ArrayList<String>();
			lore.add(ChatColor.GRAY + "Medium rare, flavourful");
			lore.add(ChatColor.GOLD + "Hunger: + 3");
			lore.add(ChatColor.YELLOW + "Saturation: + 2");
			meta.setLore(lore);

			STEAK_TIER_3.setItemMeta(meta);
			STEAK_TIER_3.ensureServerConversions();
		}

		return STEAK_TIER_3.clone();
	}
	public static ItemStack getSteakTier4() {
		if (STEAK_TIER_4 == null) {
			STEAK_TIER_4 = new ItemStack(Material.COOKED_BEEF, 1);
			ItemMeta meta = STEAK_TIER_4.getItemMeta();
			meta.setDisplayName(PREFIX_UNCOMMON + "Steak (Super)");

			ArrayList<String> lore = new ArrayList<String>();
			lore.add(ChatColor.GRAY + "This steak appears more than it seems?");
			lore.add(ChatColor.GOLD + "Hunger: + 3");
			lore.add(ChatColor.YELLOW + "Saturation: + 2");
			lore.add(ChatColor.DARK_PURPLE + "Effect: Health Boost 1 - 1 min");
			meta.setLore(lore);

			STEAK_TIER_4.setItemMeta(meta);
			STEAK_TIER_4.ensureServerConversions();
		}

		return STEAK_TIER_4.clone();
	}

	public static ItemStack getSteakTier5() {
		if (STEAK_TIER_5 == null) {
			STEAK_TIER_5 = new ItemStack(Material.COOKED_BEEF, 1);
			ItemMeta meta = STEAK_TIER_5.getItemMeta();
			meta.setDisplayName(PREFIX_UNCOMMON + "Steak (Divine)");

			ArrayList<String> lore = new ArrayList<String>();
			lore.add(ChatColor.GRAY + "A steak that has broken its limits and gone beyond");
			lore.add(ChatColor.GOLD + "Hunger: + 3");
			lore.add(ChatColor.YELLOW + "Saturation: + 2");
			lore.add(ChatColor.DARK_PURPLE + "Effect: Health Boost 2 - 1:30 min");
			meta.setLore(lore);

			STEAK_TIER_5.setItemMeta(meta);
			STEAK_TIER_5.ensureServerConversions();
		}

		return STEAK_TIER_5.clone();
	}


	public static ItemStack getMuttonTier1() {
		if (MUTTON_TIER_1 == null) {
			MUTTON_TIER_1 = new ItemStack(Material.COOKED_MUTTON, 1);
			ItemMeta meta = MUTTON_TIER_1.getItemMeta();
			meta.setDisplayName(PREFIX_UNCOMMON + "Mutton (OK)");

			ArrayList<String> lore = new ArrayList<String>();
			lore.add(ChatColor.GRAY + "Very chewy, almost like a piece of gum");
			lore.add(ChatColor.GOLD + "Hunger + 1");
			meta.setLore(lore);

			MUTTON_TIER_1.setItemMeta(meta);
			MUTTON_TIER_1.ensureServerConversions();
		}

		return MUTTON_TIER_1.clone();
	}
	public static ItemStack getMuttonTier2() {
		if (MUTTON_TIER_2 == null) {
			MUTTON_TIER_2 = new ItemStack(Material.COOKED_MUTTON, 1);
			ItemMeta meta = MUTTON_TIER_2.getItemMeta();
			meta.setDisplayName(PREFIX_UNCOMMON + "Mutton (Good)");

			ArrayList<String> lore = new ArrayList<String>();
			lore.add(ChatColor.GRAY + "Quality mutton, just like mamas");
			lore.add(ChatColor.GOLD + "Hunger: + 2");
			lore.add(ChatColor.YELLOW + "Saturation: + 2");
			meta.setLore(lore);

			MUTTON_TIER_2.setItemMeta(meta);
			MUTTON_TIER_2.ensureServerConversions();
		}

		return MUTTON_TIER_2.clone();
	}
	public static ItemStack getMuttonTier3() {
		if (MUTTON_TIER_3 == null) {
			MUTTON_TIER_3 = new ItemStack(Material.COOKED_MUTTON, 1);
			ItemMeta meta = MUTTON_TIER_3.getItemMeta();
			meta.setDisplayName(PREFIX_UNCOMMON + "Mutton (Great)");

			ArrayList<String> lore = new ArrayList<String>();
			lore.add(ChatColor.GRAY + "Meat from the best sheep in the herd");
			lore.add(ChatColor.GOLD + "Hunger: + 3");
			lore.add(ChatColor.YELLOW + "Saturation: + 2");

			meta.setLore(lore);

			MUTTON_TIER_3.setItemMeta(meta);
			MUTTON_TIER_3.ensureServerConversions();
		}

		return MUTTON_TIER_3.clone();
	}
	public static ItemStack getMuttonTier4() {
		if (MUTTON_TIER_4 == null) {
			MUTTON_TIER_4 = new ItemStack(Material.COOKED_MUTTON, 1);
			ItemMeta meta = MUTTON_TIER_4.getItemMeta();
			meta.setDisplayName(PREFIX_UNCOMMON + "Mutton (Super)");

			ArrayList<String> lore = new ArrayList<String>();
			lore.add(ChatColor.GRAY + "Fat and Muscle interwined perfectly");
			lore.add(ChatColor.GOLD + "Hunger: + 3");
			lore.add(ChatColor.YELLOW + "Saturation: + 2");
			lore.add(ChatColor.DARK_PURPLE + "Effect: Regen 1 - 1 min");
			meta.setLore(lore);

			MUTTON_TIER_4.setItemMeta(meta);
			MUTTON_TIER_4.ensureServerConversions();
		}

		return MUTTON_TIER_4.clone();
	}

	public static ItemStack getMuttonTier5() {
		if (MUTTON_TIER_5 == null) {
			MUTTON_TIER_5 = new ItemStack(Material.COOKED_MUTTON, 1);
			ItemMeta meta = MUTTON_TIER_5.getItemMeta();
			meta.setDisplayName(PREFIX_UNCOMMON + "Mutton (Divine)");

			ArrayList<String> lore = new ArrayList<String>();
			lore.add(ChatColor.GRAY + "Meat from a sheep chosen by god");
			lore.add(ChatColor.GOLD + "Hunger: + 3");
			lore.add(ChatColor.YELLOW + "Saturation: + 2");
			lore.add(ChatColor.DARK_PURPLE + "Effect: Regen 2 - 1:30 min");
			meta.setLore(lore);

			MUTTON_TIER_5.setItemMeta(meta);
			MUTTON_TIER_5.ensureServerConversions();
		}

		return MUTTON_TIER_5.clone();
	}


	/**
	 * Crafting books that unlock crafting recipes:
	 * <ul>
	 * <li>Crafting Table</li>
	 * <li>Wooden Tools
	 * <ul>
	 * <li>Wooden Axe</li>
	 * <li>Wooden Shovel</li>
	 * <li>Wooden Pickaxe</li>
	 * </ul>
	 * </li>
	 * <li>Wooden Sword</li>
	 * </ul>
	 */
	public enum CraftingBook {
		CraftingTable, SwordWooden, SwordStone, SwordIron, // SwordDiamond,
		ToolsWooden, ToolsStone, ToolsIron, // ToolsDiamond,
		PickaxeWooden, PickaxeStone, PickaxeIron, // PickaxeDiamond,
		ArmorHatBootsLeather, ArmorHatBootsChainmail, ArmorHatBootsIron, // ArmorHatBootsDiamond,
		ArmorChestLegsLeather, ArmorChestLegsChainmail, ArmorChestLegsIron, // ArmorChestLegsDiamond,
	}

	/**
	 * Get a crafting recipe tome of the specified type.
	 *
	 * @param book {@link CraftingBook} book to get.
	 * @return ItemStack of the requested recipe
	 */
	public static ItemStack craftingBook(CraftingBook book) {
		switch (book) {
			// -- Armor: Chest & Legs - //
			case ArmorChestLegsChainmail:
				if (TOME_ARMOR_CHEST_LEGS_CHAINMAIL == null) {
					TOME_ARMOR_CHEST_LEGS_CHAINMAIL = generateCraftingBook(CraftingBook.ArmorChestLegsChainmail);
				}
				return TOME_ARMOR_CHEST_LEGS_CHAINMAIL.clone();
			case ArmorChestLegsIron:
				if (TOME_ARMOR_CHEST_LEGS_IRON == null) {
					TOME_ARMOR_CHEST_LEGS_IRON = generateCraftingBook(CraftingBook.ArmorChestLegsIron);
				}
				return TOME_ARMOR_CHEST_LEGS_IRON.clone();
			case ArmorChestLegsLeather:
				if (TOME_ARMOR_CHEST_LEGS_LEATHER == null) {
					TOME_ARMOR_CHEST_LEGS_LEATHER = generateCraftingBook(CraftingBook.ArmorChestLegsLeather);
				}
				return TOME_ARMOR_CHEST_LEGS_LEATHER.clone();
			// -- Armor: Hat & Boots - //
			case ArmorHatBootsChainmail:
				if (TOME_ARMOR_HAT_BOOTS_CHAINMAIL == null) {
					TOME_ARMOR_HAT_BOOTS_CHAINMAIL = generateCraftingBook(CraftingBook.ArmorHatBootsChainmail);
				}
				return TOME_ARMOR_HAT_BOOTS_CHAINMAIL.clone();
			case ArmorHatBootsIron:
				if (TOME_ARMOR_HAT_BOOTS_IRON == null) {
					TOME_ARMOR_HAT_BOOTS_IRON = generateCraftingBook(CraftingBook.ArmorHatBootsIron);
				}
				return TOME_ARMOR_HAT_BOOTS_IRON.clone();
			case ArmorHatBootsLeather:
				if (TOME_ARMOR_HAT_BOOTS_LEATHER == null) {
					TOME_ARMOR_HAT_BOOTS_LEATHER = generateCraftingBook(CraftingBook.ArmorHatBootsLeather);
				}
				return TOME_ARMOR_HAT_BOOTS_LEATHER.clone();
			// --------- Misc --------- //
			case CraftingTable:
				if (TOME_CRAFTING_TABLE == null) {
					TOME_CRAFTING_TABLE = generateCraftingBook(CraftingBook.CraftingTable);
				}
				return TOME_CRAFTING_TABLE.clone();
			// -------- Swords -------- //
			case SwordIron:
				if (TOME_SWORD_IRON == null) {
					TOME_SWORD_IRON = generateCraftingBook(CraftingBook.SwordIron);
				}
				return TOME_SWORD_IRON.clone();
			case SwordStone:
				if (TOME_SWORD_STONE == null) {
					TOME_SWORD_STONE = generateCraftingBook(CraftingBook.SwordStone);
				}
				return TOME_SWORD_STONE.clone();
			case SwordWooden:
				if (TOME_SWORD_WOODEN == null) {
					TOME_SWORD_WOODEN = generateCraftingBook(CraftingBook.SwordWooden);
				}
				return TOME_SWORD_WOODEN.clone();
			// --------- Tools -------- //
			case ToolsIron:
				if (TOME_TOOLS_IRON == null) {
					TOME_TOOLS_IRON = generateCraftingBook(CraftingBook.ToolsIron);
				}
				return TOME_TOOLS_IRON.clone();
			case ToolsStone:
				if (TOME_TOOLS_STONE == null) {
					TOME_TOOLS_STONE = generateCraftingBook(CraftingBook.ToolsStone);
				}
				return TOME_TOOLS_STONE.clone();
			case ToolsWooden:
				if (TOME_TOOLS_WOODEN == null) {
					TOME_TOOLS_WOODEN = generateCraftingBook(CraftingBook.ToolsWooden);
				}
				return TOME_TOOLS_WOODEN.clone();
			// ------- Pickaxes ------- //
			case PickaxeIron:
				if (TOME_PICKAXE_IRON == null) {
					TOME_PICKAXE_IRON = generateCraftingBook(CraftingBook.PickaxeIron);
				}
				return TOME_PICKAXE_IRON.clone();
			case PickaxeStone:
				if (TOME_PICKAXE_STONE == null) {
					TOME_PICKAXE_STONE = generateCraftingBook(CraftingBook.PickaxeStone);
				}
				return TOME_PICKAXE_STONE.clone();
			case PickaxeWooden:
				if (TOME_PICKAXE_WOODEN == null) {
					TOME_PICKAXE_WOODEN = generateCraftingBook(CraftingBook.PickaxeWooden);
				}
				return TOME_PICKAXE_WOODEN.clone();
			default:
				return generateCraftingBook(book);
		}
	}

	/**
	 * Generate a crafting book of the specified type. Does not cache requests.
	 *
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
			// -- Armor: Chest & Legs - //
			case ArmorChestLegsIron:
				itemMeta.setDisplayName(PREFIX_RECIPE + "Advanced Iron Armour");

				ArrayList<String> loreArmorChestLegsIron = new ArrayList<String>();

				loreArmorChestLegsIron.add(ChatColor.RESET + "" + ChatColor.AQUA + "Use to unlock Advanced Iron Armor.");
				loreArmorChestLegsIron.add(PREFIX_LORE_COMMON + "Platemail is not just a sheet of iron,");
				loreArmorChestLegsIron.add(PREFIX_LORE_COMMON + "it is a mechanical masterpiece.");
				loreArmorChestLegsIron.add("");
				loreArmorChestLegsIron.add(ChatColor.GREEN + "Unlocks:");
				loreArmorChestLegsIron.add(PREFIX_LORE_COMMON + "- Iron Chestplate");
				loreArmorChestLegsIron.add(PREFIX_LORE_COMMON + "- Iron Leggings");
				loreArmorChestLegsIron.add("");
				loreArmorChestLegsIron.add(ChatColor.MAGIC + "Surprisingly flexible!");

				itemMeta.setLore(loreArmorChestLegsIron);

				itemMeta.addRecipe(new NamespacedKey("minecraft", "iron_chestplate"));
				itemMeta.addRecipe(new NamespacedKey("minecraft", "iron_leggings"));
				break;
			case ArmorChestLegsChainmail:
				itemMeta.setDisplayName(PREFIX_RECIPE + "Advanced Chainmail Armour");

				ArrayList<String> loreArmorChestLegsChainmail = new ArrayList<String>();

				loreArmorChestLegsChainmail.add(ChatColor.RESET + "" + ChatColor.AQUA + "Use to unlock Advanced Chainmail Armor.");
				loreArmorChestLegsChainmail.add(PREFIX_LORE_COMMON + "New iron techniques makes");
				loreArmorChestLegsChainmail.add(PREFIX_LORE_COMMON + "rings harder and faster to make.");
				loreArmorChestLegsChainmail.add("");
				loreArmorChestLegsChainmail.add(ChatColor.GREEN + "Unlocks:");
				loreArmorChestLegsChainmail.add(PREFIX_LORE_COMMON + "- Chainmail Chestplate");
				loreArmorChestLegsChainmail.add(PREFIX_LORE_COMMON + "- Chainmail Leggings");
				loreArmorChestLegsChainmail.add("");
				loreArmorChestLegsChainmail.add(ChatColor.MAGIC + "we all fall down");

				itemMeta.setLore(loreArmorChestLegsChainmail);

				itemMeta.addRecipe(new NamespacedKey("minecraft", "chainmail_chestplate"));
				itemMeta.addRecipe(new NamespacedKey("minecraft", "chainmail_leggings"));
				break;
			case ArmorChestLegsLeather:
				itemMeta.setDisplayName(PREFIX_RECIPE + "Advanced Leather Armour");

				ArrayList<String> loreArmorChestLegsLeather = new ArrayList<String>();

				loreArmorChestLegsLeather.add(ChatColor.RESET + "" + ChatColor.AQUA + "Use to unlock Advanced Leather Armor.");
				loreArmorChestLegsLeather.add(PREFIX_LORE_COMMON + "Boiled leather and iron studs,");
				loreArmorChestLegsLeather.add(PREFIX_LORE_COMMON + "a stiff but sturdy protection.");
				loreArmorChestLegsLeather.add("");
				loreArmorChestLegsLeather.add(ChatColor.GREEN + "Unlocks:");
				loreArmorChestLegsLeather.add(PREFIX_LORE_COMMON + "- Leather Tunic");
				loreArmorChestLegsLeather.add(PREFIX_LORE_COMMON + "- Leather Pants");
				loreArmorChestLegsLeather.add("");
				loreArmorChestLegsLeather.add(ChatColor.MAGIC + "Don't forget the dye!");

				itemMeta.setLore(loreArmorChestLegsLeather);

				itemMeta.addRecipe(new NamespacedKey("minecraft", "leather_chestplate"));
				itemMeta.addRecipe(new NamespacedKey("minecraft", "leather_leggings"));
				break;
			// -- Armor: Hat & Boots - //
			case ArmorHatBootsIron:
				itemMeta.setDisplayName(PREFIX_RECIPE + "Simple Iron Armour");

				ArrayList<String> loreArmorHatBootsIron = new ArrayList<String>();

				loreArmorHatBootsIron.add(ChatColor.RESET + "" + ChatColor.AQUA + "Use to unlock Simple Iron Armor.");
				loreArmorHatBootsIron.add(PREFIX_LORE_COMMON + "Pounding iron into shape is");
				loreArmorHatBootsIron.add(PREFIX_LORE_COMMON + "hard work, but rewarding.");
				loreArmorHatBootsIron.add("");
				loreArmorHatBootsIron.add(ChatColor.GREEN + "Unlocks:");
				loreArmorHatBootsIron.add(PREFIX_LORE_COMMON + "- Iron Helmet");
				loreArmorHatBootsIron.add(PREFIX_LORE_COMMON + "- Iron Boots");
				loreArmorHatBootsIron.add("");
				loreArmorHatBootsIron.add(ChatColor.MAGIC + "tin can roof");

				itemMeta.setLore(loreArmorHatBootsIron);

				itemMeta.addRecipe(new NamespacedKey("minecraft", "iron_boots"));
				itemMeta.addRecipe(new NamespacedKey("minecraft", "iron_helmet"));
				break;
			case ArmorHatBootsChainmail:
				itemMeta.setDisplayName(PREFIX_RECIPE + "Simple Chainmail Armour");

				ArrayList<String> loreArmorHatBootsChainmail = new ArrayList<String>();

				loreArmorHatBootsChainmail.add(ChatColor.RESET + "" + ChatColor.AQUA + "Use to unlock Simple Chainmail Armor.");
				loreArmorHatBootsChainmail.add(PREFIX_LORE_COMMON + "Connecting iron rings makes");
				loreArmorHatBootsChainmail.add(PREFIX_LORE_COMMON + "a flexible but resistant covering.");
				loreArmorHatBootsChainmail.add("");
				loreArmorHatBootsChainmail.add(ChatColor.GREEN + "Unlocks:");
				loreArmorHatBootsChainmail.add(PREFIX_LORE_COMMON + "- Chainmail Helmet");
				loreArmorHatBootsChainmail.add(PREFIX_LORE_COMMON + "- Chainmail Boots");
				loreArmorHatBootsChainmail.add("");
				loreArmorHatBootsChainmail.add(ChatColor.MAGIC + "ring a ring a roses");

				itemMeta.setLore(loreArmorHatBootsChainmail);

				itemMeta.addRecipe(new NamespacedKey("minecraft", "chainmail_boots"));
				itemMeta.addRecipe(new NamespacedKey("minecraft", "chainmail_helmet"));
				break;
			case ArmorHatBootsLeather:
				itemMeta.setDisplayName(PREFIX_RECIPE + "Simple Leather Armour");

				ArrayList<String> loreArmorHatBootsLeather = new ArrayList<String>();

				loreArmorHatBootsLeather.add(ChatColor.RESET + "" + ChatColor.AQUA + "Use to unlock Simple Leather Armor.");
				loreArmorHatBootsLeather.add(PREFIX_LORE_COMMON + "The basic technique of bending");
				loreArmorHatBootsLeather.add(PREFIX_LORE_COMMON + "and reinforcing leather.");
				loreArmorHatBootsLeather.add("");
				loreArmorHatBootsLeather.add(ChatColor.GREEN + "Unlocks:");
				loreArmorHatBootsLeather.add(PREFIX_LORE_COMMON + "- Leather Cap");
				loreArmorHatBootsLeather.add(PREFIX_LORE_COMMON + "- Leather Boots");
				loreArmorHatBootsLeather.add("");
				loreArmorHatBootsLeather.add(ChatColor.MAGIC + "Boots with the fur");

				itemMeta.setLore(loreArmorHatBootsLeather);

				itemMeta.addRecipe(new NamespacedKey("minecraft", "leather_boots"));
				itemMeta.addRecipe(new NamespacedKey("minecraft", "leather_helmet"));
				break;
			// --------- Misc --------- //
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
			// -------- Swords -------- //
			case SwordIron:
				itemMeta.setDisplayName(PREFIX_RECIPE + "Iron Sword");

				ArrayList<String> loreSwordIron = new ArrayList<String>();

				loreSwordIron.add(ChatColor.RESET + "" + ChatColor.AQUA + "Use to unlock Iron Swords");
				loreSwordIron.add(PREFIX_LORE_COMMON + "A sharp blade makes cutting and");
				loreSwordIron.add(PREFIX_LORE_COMMON + "stabbing into light work.");
				loreSwordIron.add("");
				loreSwordIron.add(ChatColor.GREEN + "Unlocks:");
				loreSwordIron.add(PREFIX_LORE_COMMON + "- Iron Sword");
				loreSwordIron.add("");
				loreSwordIron.add(ChatColor.MAGIC + "Stabby Stabby");

				itemMeta.setLore(loreSwordIron);

				itemMeta.addRecipe(new NamespacedKey("minecraft", "iron_sword"));
				break;
			case SwordStone:
				itemMeta.setDisplayName(PREFIX_RECIPE + "Stone Sword");

				ArrayList<String> loreSwordStone = new ArrayList<String>();

				loreSwordStone.add(ChatColor.RESET + "" + ChatColor.AQUA + "Use to unlock Stone Swords");
				loreSwordStone.add(PREFIX_LORE_COMMON + "A sharpened rock makes it easier,");
				loreSwordStone.add(PREFIX_LORE_COMMON + "but you know there is something better.");
				loreSwordStone.add("");
				loreSwordStone.add(ChatColor.GREEN + "Unlocks:");
				loreSwordStone.add(PREFIX_LORE_COMMON + "- Stone Sword");
				loreSwordStone.add("");
				loreSwordStone.add(ChatColor.MAGIC + "Large thin rock");

				itemMeta.setLore(loreSwordStone);

				itemMeta.addRecipe(new NamespacedKey("minecraft", "stone_sword"));
				break;
			case SwordWooden:
				itemMeta.setDisplayName(PREFIX_RECIPE + "Wooden Sword");

				ArrayList<String> loreSwordWooden = new ArrayList<String>();

				loreSwordWooden.add(ChatColor.RESET + "" + ChatColor.AQUA + "Use to unlock Wooden Swords");
				loreSwordWooden.add(PREFIX_LORE_COMMON + "A wooden sword isn't the best,");
				loreSwordWooden.add(PREFIX_LORE_COMMON + "but it's sharper than some sticks.");
				loreSwordWooden.add("");
				loreSwordWooden.add(ChatColor.GREEN + "Unlocks:");
				loreSwordWooden.add(PREFIX_LORE_COMMON + "- Wooden Sword");
				loreSwordWooden.add("");
				loreSwordWooden.add(ChatColor.MAGIC + "Sharp Pointy Stick");

				itemMeta.setLore(loreSwordWooden);

				itemMeta.addRecipe(new NamespacedKey("minecraft", "wooden_sword"));
				break;
			// --------- Tools -------- //
			case ToolsIron:
				itemMeta.setDisplayName(PREFIX_RECIPE + "Iron Tools");

				ArrayList<String> loreToolsIron = new ArrayList<String>();

				loreToolsIron.add(ChatColor.RESET + "" + ChatColor.AQUA + "Use to unlock Iron Tools");
				loreToolsIron.add(PREFIX_LORE_COMMON + "Hardened iron is the material");
				loreToolsIron.add(PREFIX_LORE_COMMON + "favoured by anyone with a brain.");
				loreToolsIron.add("");
				loreToolsIron.add(ChatColor.GREEN + "Unlocks:");
				loreToolsIron.add(PREFIX_LORE_COMMON + "- Iron Axe");
				loreToolsIron.add(PREFIX_LORE_COMMON + "- Iron Shovel");
				loreToolsIron.add(PREFIX_LORE_COMMON + "- Iron Hoe");
				loreToolsIron.add("");
				loreToolsIron.add(ChatColor.MAGIC + "(not that good)");

				itemMeta.setLore(loreToolsIron);

				itemMeta.addRecipe(new NamespacedKey("minecraft", "iron_axe"));
				itemMeta.addRecipe(new NamespacedKey("minecraft", "iron_hoe"));
				itemMeta.addRecipe(new NamespacedKey("minecraft", "iron_shovel"));
				break;
			case ToolsStone:
				itemMeta.setDisplayName(PREFIX_RECIPE + "Stone Tools");

				ArrayList<String> loreToolsStonne = new ArrayList<String>();

				loreToolsStonne.add(ChatColor.RESET + "" + ChatColor.AQUA + "Use to unlock Stone Tools");
				loreToolsStonne.add(PREFIX_LORE_COMMON + "Turns out that stone is harder");
				loreToolsStonne.add(PREFIX_LORE_COMMON + "than wood, who would've guessed?");
				loreToolsStonne.add("");
				loreToolsStonne.add(ChatColor.GREEN + "Unlocks:");
				loreToolsStonne.add(PREFIX_LORE_COMMON + "- Stone Axe");
				loreToolsStonne.add(PREFIX_LORE_COMMON + "- Stone Shovel");
				loreToolsStonne.add(PREFIX_LORE_COMMON + "- Stone Hoe");
				loreToolsStonne.add("");
				loreToolsStonne.add(ChatColor.MAGIC + "/kit tools");

				itemMeta.setLore(loreToolsStonne);

				itemMeta.addRecipe(new NamespacedKey("minecraft", "stone_axe"));
				itemMeta.addRecipe(new NamespacedKey("minecraft", "stone_hoe"));
				itemMeta.addRecipe(new NamespacedKey("minecraft", "stone_shovel"));
				break;
			case ToolsWooden:
				itemMeta.setDisplayName(PREFIX_RECIPE + "Wooden Tools");

				ArrayList<String> loreToolsWooden = new ArrayList<String>();

				loreToolsWooden.add(ChatColor.RESET + "" + ChatColor.AQUA + "Use to unlock Wooden Tools");
				loreToolsWooden.add(PREFIX_LORE_COMMON + "Wooden tools are the most basic,");
				loreToolsWooden.add(PREFIX_LORE_COMMON + "but still better than punching.");
				loreToolsWooden.add("");
				loreToolsWooden.add(ChatColor.GREEN + "Unlocks:");
				loreToolsWooden.add(PREFIX_LORE_COMMON + "- Wooden Axe");
				loreToolsWooden.add(PREFIX_LORE_COMMON + "- Wooden Shovel");
				loreToolsWooden.add(PREFIX_LORE_COMMON + "- Wooden Hoe");
				loreToolsWooden.add("");
				loreToolsWooden.add(ChatColor.MAGIC + "Only used once");

				itemMeta.setLore(loreToolsWooden);

				itemMeta.addRecipe(new NamespacedKey("minecraft", "wooden_axe"));
				itemMeta.addRecipe(new NamespacedKey("minecraft", "wooden_hoe"));
				itemMeta.addRecipe(new NamespacedKey("minecraft", "wooden_shovel"));
				break;
			// ------- Pickaxes ------- //
			case PickaxeIron:
				itemMeta.setDisplayName(PREFIX_RECIPE + "Iron Pickaxe");

				ArrayList<String> lorePickaxeIron = new ArrayList<String>();

				lorePickaxeIron.add(ChatColor.RESET + "" + ChatColor.AQUA + "Use to unlock Iron Pickaxes");
				lorePickaxeIron.add(PREFIX_LORE_COMMON + "Using a material stronger than");
				lorePickaxeIron.add(PREFIX_LORE_COMMON + "stone is surprisingly effective.");
				lorePickaxeIron.add("");
				lorePickaxeIron.add(ChatColor.GREEN + "Unlocks:");
				lorePickaxeIron.add(PREFIX_LORE_COMMON + "- Iron Pickaxe");
				lorePickaxeIron.add("");
				lorePickaxeIron.add(ChatColor.MAGIC + "I am a dwarf");

				itemMeta.setLore(lorePickaxeIron);

				itemMeta.addRecipe(new NamespacedKey("minecraft", "iron_pickaxe"));
				break;
			case PickaxeStone:
				itemMeta.setDisplayName(PREFIX_RECIPE + "Stone Pickaxe");

				ArrayList<String> lorePickaxeStone = new ArrayList<String>();

				lorePickaxeStone.add(ChatColor.RESET + "" + ChatColor.AQUA + "Use to unlock Stone Pickaxes");
				lorePickaxeStone.add(PREFIX_LORE_COMMON + "A cost-effective way of mining,");
				lorePickaxeStone.add(PREFIX_LORE_COMMON + "your arms will hurt after a while.");
				lorePickaxeStone.add("");
				lorePickaxeStone.add(ChatColor.GREEN + "Unlocks:");
				lorePickaxeStone.add(PREFIX_LORE_COMMON + "- Stone Pickaxe");
				lorePickaxeStone.add("");
				lorePickaxeStone.add(ChatColor.MAGIC + "/kit tools");

				itemMeta.setLore(lorePickaxeStone);

				itemMeta.addRecipe(new NamespacedKey("minecraft", "stone_pickaxe"));
				break;
			case PickaxeWooden:
				itemMeta.setDisplayName(PREFIX_RECIPE + "Wooden Pickaxe");

				ArrayList<String> lorePickaxeWooden = new ArrayList<String>();

				lorePickaxeWooden.add(ChatColor.RESET + "" + ChatColor.AQUA + "Use to unlock Wooden Pickaxes");
				lorePickaxeWooden.add(PREFIX_LORE_COMMON + "A piece of wood on a stick.");
				lorePickaxeWooden.add(PREFIX_LORE_COMMON + "Stops your hands from bleeding.");
				lorePickaxeWooden.add("");
				lorePickaxeWooden.add(ChatColor.GREEN + "Unlocks:");
				lorePickaxeWooden.add(PREFIX_LORE_COMMON + "- Wooden Pickaxe");
				lorePickaxeWooden.add("");
				lorePickaxeWooden.add(ChatColor.MAGIC + "nobody ever uses this");

				itemMeta.setLore(lorePickaxeWooden);

				itemMeta.addRecipe(new NamespacedKey("minecraft", "wooden_pickaxe"));
				break;
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
