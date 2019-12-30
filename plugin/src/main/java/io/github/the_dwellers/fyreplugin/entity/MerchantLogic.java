package io.github.the_dwellers.fyreplugin.entity;

import io.github.the_dwellers.fyreplugin.configuration.MerchantRecipes;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager.Profession;
import org.bukkit.inventory.Merchant;
import org.bukkit.inventory.MerchantRecipe;

import java.util.ArrayList;

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
				recipes.add(MerchantRecipes.getWoodSpade());
				recipes.add(MerchantRecipes.learnWoodenTools());
			}
			break;
		case MASON:
			switch (level) {
			default:
				recipes.add(MerchantRecipes.sellOakLog());
			}
			break;
		case NONE:
			recipes.add(MerchantRecipes.sellSplinters());
			recipes.add(MerchantRecipes.learnCraftingTable());
		default:
			break;
		}
		return recipes;
	}
}
