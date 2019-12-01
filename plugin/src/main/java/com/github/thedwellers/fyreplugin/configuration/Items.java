package com.github.thedwellers.fyreplugin.configuration;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

/**
 * Static class of specific items that may may be referenced from other places
 * inside the application. This includes items such as Currency or other unique
 * materials. This class uses lazy instantiation.
 */
public abstract class Items {

	private static ItemStack SILVER_COIN;
	private static ItemStack ERROR_ITEM;

	public static ItemStack getSilverCoin() {
		if (SILVER_COIN == null) {
			SILVER_COIN = new ItemStack(Material.IRON_NUGGET, 1);
			ItemMeta meta = SILVER_COIN.getItemMeta();
			meta.setLore(Arrays.asList(ChatColor.RESET + "Simple Currency"));
			meta.setDisplayName(ChatColor.GOLD + "Silver Coin");
			SILVER_COIN.setItemMeta(meta);
		}
		return SILVER_COIN;
	}

	public static ItemStack getErrorItem() {
		if (ERROR_ITEM == null) {
			ERROR_ITEM = new ItemStack(Material.GLASS, 1);
			ItemMeta meta = ERROR_ITEM.getItemMeta();
			meta.setDisplayName(ChatColor.RED + "Error with inventory");
			meta.setLore(Arrays.asList(ChatColor.GOLD + "Please contact a server admin"));
			ERROR_ITEM.setItemMeta(meta);
		}
		return ERROR_ITEM;
	}
}
