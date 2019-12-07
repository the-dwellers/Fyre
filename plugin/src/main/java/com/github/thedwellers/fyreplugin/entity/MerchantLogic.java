package com.github.thedwellers.fyreplugin.entity;

import java.util.ArrayList;

import com.github.thedwellers.fyreplugin.configuration.MerchantRecipes;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager.Profession;
import org.bukkit.inventory.Merchant;
import org.bukkit.inventory.MerchantRecipe;

/**
 * Merchant
 */
public abstract class MerchantLogic {

	public static void showMerchantUI(Player player, Profession profession) {
		Merchant merchant = Bukkit.getServer().createMerchant(profession.toString());

		merchant.setRecipes(get(profession, 1));

		player.openMerchant(merchant, true);
	}

	public static ArrayList<MerchantRecipe> get(Profession profession, int level) {
		ArrayList<MerchantRecipe> recipes = new ArrayList<MerchantRecipe>();

		switch (profession) {
			case TOOLSMITH:
				switch (level) {
					default:
						recipes.add(MerchantRecipes.getWoodAxe());
				}
				break;
			default:
				break;
		}
		recipes.add(MerchantRecipes.sellSplinters());
		return recipes;
	}
}
