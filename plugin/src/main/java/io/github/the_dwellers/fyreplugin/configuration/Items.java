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
	private static ItemStack SILVER_COIN;
	private static ItemStack ERROR_ITEM;

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

	private static ItemStack SPLINTERS;

	private static ItemStack TOME_CRAFTING_TABLE;
	private static ItemStack TOME_TOOLS_WOODEN;
	private static ItemStack TOME_TOOLS_STONE;
	private static ItemStack TOME_TOOLS_IRON;
	private static ItemStack TOME_SWORD_WOODEN;
	private static ItemStack TOME_SWORD_STONE;
	private static ItemStack TOME_SWORD_IRON;

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

	private static ItemStack SHIELD;

	private static ItemStack ARROW_ENDER;
	private static ItemStack ARROW_PIONEER;

	// ~~~~~~~~~~~~~ Item Methods ~~~~~~~~~~~~ //

	public static ItemStack getLeatherHelmet() {
		if (ARMOR_HELMET_LEATHER == null) {
			ARMOR_HELMET_LEATHER = new ItemStack(Material.LEATHER_HELMET, 1);
			ItemMeta meta = ARMOR_HELMET_LEATHER.getItemMeta();

			Multimap<Attribute, AttributeModifier> modifiers = HashMultimap.create();

			modifiers.put(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(),
					"generic.max_health", 2, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
			modifiers.put(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(),
					"generic.generic_armor", 1, Operation.ADD_NUMBER, EquipmentSlot.HEAD));

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

			modifiers.put(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(),
					"generic.max_health", 4, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
			modifiers.put(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(),
					"generic.generic_armor", 3, Operation.ADD_NUMBER, EquipmentSlot.CHEST));

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

			modifiers.put(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(),
					"generic.max_health", 2, Operation.ADD_NUMBER, EquipmentSlot.LEGS));
			modifiers.put(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(),
					"generic.generic_armor", 2, Operation.ADD_NUMBER, EquipmentSlot.LEGS));

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

			modifiers.put(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(),
					"generic.max_health", 2, Operation.ADD_NUMBER, EquipmentSlot.FEET));
			modifiers.put(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(),
					"generic.generic_armor", 1, Operation.ADD_NUMBER, EquipmentSlot.FEET));

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

			modifiers.put(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(),
					"generic.max_health", 4, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
			modifiers.put(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(),
					"generic.generic_armor", 3, Operation.ADD_NUMBER, EquipmentSlot.HEAD));

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

			modifiers.put(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(),
					"generic.max_health", 4, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
			modifiers.put(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(),
					"generic.generic_armor", 5, Operation.ADD_NUMBER, EquipmentSlot.CHEST));

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

			modifiers.put(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(),
					"generic.max_health", 4, Operation.ADD_NUMBER, EquipmentSlot.LEGS));
			modifiers.put(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(),
					"generic.generic_armor", 4, Operation.ADD_NUMBER, EquipmentSlot.LEGS));

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
			modifiers.put(Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(), "generic.movement_speed", -0.03,
					Operation.MULTIPLY_SCALAR_1, EquipmentSlot.CHEST));

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

			modifiers.put(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(),
					"generic.max_health", 6, Operation.ADD_NUMBER, EquipmentSlot.LEGS));
			modifiers.put(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(),
					"generic.generic_armor", 4, Operation.ADD_NUMBER, EquipmentSlot.LEGS));
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

			modifiers.put(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(),
					"generic.max_health", 8, Operation.ADD_NUMBER, EquipmentSlot.LEGS));
			modifiers.put(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(),
					"generic.generic_armor", 5, Operation.ADD_NUMBER, EquipmentSlot.LEGS));
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
				if (TOME_TOOLS_WOODEN == null) {
					TOME_TOOLS_WOODEN = generateCraftingBook(CraftingBook.WoodenTools);
				}
				return TOME_TOOLS_WOODEN.clone();
			case StoneTools:
				if (TOME_TOOLS_STONE == null) {
					TOME_TOOLS_STONE = generateCraftingBook(CraftingBook.StoneTools);
				}
				return TOME_TOOLS_STONE.clone();
			case IronTools:
				if (TOME_TOOLS_IRON == null) {
					TOME_TOOLS_IRON = generateCraftingBook(CraftingBook.IronTools);
				}
				return TOME_TOOLS_IRON.clone();
			case WoodenSword:
				if (TOME_SWORD_WOODEN == null) {
					TOME_SWORD_WOODEN = generateCraftingBook(CraftingBook.WoodenSword);
				}
				return TOME_SWORD_WOODEN.clone();
			case StoneSword:
				if (TOME_SWORD_STONE == null) {
					TOME_SWORD_STONE = generateCraftingBook(CraftingBook.StoneSword);
				}
			return TOME_SWORD_STONE.clone();
			case IronSword:
				if (TOME_SWORD_IRON == null) {
					TOME_SWORD_IRON = generateCraftingBook(CraftingBook.IronSword);
				}
				return TOME_SWORD_IRON.clone();
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
