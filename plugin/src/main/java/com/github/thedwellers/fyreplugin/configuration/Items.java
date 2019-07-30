package com.github.thedwellers.fyreplugin.configuration;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;

/**
 * Items
 */
public abstract class Items {

	private static ItemStack SILVER_COIN;

	public static ItemStack getSilverCoin(){
		if (SILVER_COIN == null) {
			SILVER_COIN = new ItemStack(Material.IRON_NUGGET, 1);
			ItemMeta meta = SILVER_COIN.getItemMeta();
			meta.setLore(Arrays.asList(ChatColor.RESET+"Simple Currency"));
			meta.setDisplayName(ChatColor.GOLD+"Silver Coin");
			SILVER_COIN.setItemMeta(meta);
		}
		return SILVER_COIN;
	}
}
