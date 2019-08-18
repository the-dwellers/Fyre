package com.github.thedwellers.fyreplugin.configuration;

import java.util.ArrayList;
import java.util.List;

import com.github.thedwellers.fyreplugin.configuration.Items;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;

public class MerchantRecipeList {
	private static MerchantRecipe recipe;
	public static List<List<MerchantRecipe>> farmerRecipes = generateFarmerRecipes();

	private static List<List<MerchantRecipe>> generateFarmerRecipes(){
		List<List<MerchantRecipe>> allRecipes = new ArrayList<List<MerchantRecipe>>();
		List<MerchantRecipe> tierRecipes = new ArrayList<MerchantRecipe>();

		recipe = new MerchantRecipe(new ItemStack(Material.ACACIA_BOAT, 1), Integer.MAX_VALUE);
		recipe.addIngredient(new ItemStack(Material.POTATO, 3));
		tierRecipes.add(recipe);

		recipe = new MerchantRecipe(new ItemStack(Material.STONE_HOE, 1), Integer.MAX_VALUE);
		recipe.addIngredient(Items.getSilverCoin());
		tierRecipes.add(recipe);

		allRecipes.add(tierRecipes);
		tierRecipes.clear();

		recipe = new MerchantRecipe(new ItemStack(Material.WHEAT_SEEDS, 10), Integer.MAX_VALUE);
		recipe.addIngredient(Items.getSilverCoin());
		tierRecipes.add(recipe);

		allRecipes.add(tierRecipes);
		tierRecipes.clear();

		return allRecipes;
	}

	
}
