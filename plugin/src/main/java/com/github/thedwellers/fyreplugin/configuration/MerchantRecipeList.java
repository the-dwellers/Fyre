package com.github.thedwellers.fyreplugin.configuration;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;
import org.bukkit.Material;

public class MerchantRecipeList {
	private static ItemStack item;
	private static MerchantRecipe recipe;

	public static List<MerchantRecipe> farmerTier1() {
		List<MerchantRecipe> recipies = new ArrayList<MerchantRecipe>();

		recipe = new MerchantRecipe((item = new ItemStack(Material.ACACIA_DOOR, 1)), 100);
		recipe.addIngredient(item = new ItemStack(Material.DIRT, 3));
		recipies.add(recipe);

		recipe = new MerchantRecipe((item = new ItemStack(Material.DIRT, 10)), 100);
		recipe.addIngredient(item = new ItemStack(Material.GOLDEN_CARROT, 3));
		recipies.add(recipe);

		return recipies;
	}
	 
}
