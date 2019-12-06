package com.github.thedwellers.fyreplugin.configuration;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.attribute.AttributeModifier.Operation;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

/**
 * Static class of specific items that may may be referenced from other places
 * inside the application. This includes items such as Currency or other unique
 * materials. This class uses lazy instantiation.
 */
public abstract class Items {

	private static ItemStack SILVER_COIN;
	private static ItemStack ERROR_ITEM;
	private static ItemStack WOOD_AXE;
	private static ItemStack SPLINTERS;


	public static ItemStack getSilverCoin() {
		if (SILVER_COIN == null) {
			SILVER_COIN = new ItemStack(Material.IRON_NUGGET, 1);
			ItemMeta meta = SILVER_COIN.getItemMeta();
			meta.setLore(Arrays.asList(ChatColor.RESET + "Simple Currency"));
			meta.setDisplayName(ChatColor.GOLD + "Silver Coin");
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

	public static ItemStack getWoodAxe() {
		if (WOOD_AXE == null) {
			WOOD_AXE = new ItemStack(Material.WOODEN_AXE, 1);
		}
		return WOOD_AXE.clone();
	}

	public static ItemStack getSplinters() {
		if (SPLINTERS == null) {
			SPLINTERS = new ItemStack(Material.STICK, 1);
			ItemMeta meta = SPLINTERS.getItemMeta();
			meta.setDisplayName(ChatColor.RESET + "Sharp Splinters");

			ArrayList<String> lore = new ArrayList<String>();
			lore.add(ChatColor.RESET + "" + ChatColor.GRAY + "A Bundle of rough sharp sticks");
			meta.setLore(lore);

			Multimap<Attribute, AttributeModifier> modifiers = HashMultimap.create();
			modifiers.put(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(new UUID(2872, 894654), "generic.attackDamage", 2, Operation.ADD_NUMBER, EquipmentSlot.HAND));
			meta.setAttributeModifiers(modifiers);
			SPLINTERS.setItemMeta(meta);
		}

		return SPLINTERS.clone();
	}
}
