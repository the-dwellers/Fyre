package com.github.thedwellers.fyreplugin.configuration;

import java.util.ArrayList;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;

/**
 * MerchantRecipes
 */
public abstract class MerchantRecipes {

	private static MerchantRecipe BUY_WOOD_AXE;
	private static MerchantRecipe SELL_SPLINTERS;

	public static MerchantRecipe getWoodAxe() {
		if (BUY_WOOD_AXE == null) {
			BUY_WOOD_AXE = new MerchantRecipe(Items.getWoodAxe(), 90);

			ArrayList<ItemStack> ingredients = new ArrayList<ItemStack>();
			ingredients.add(silver(10));

			BUY_WOOD_AXE.setIngredients(ingredients);
		}
		return BUY_WOOD_AXE;
	}

	public static MerchantRecipe sellSplinters() {
		if (SELL_SPLINTERS == null) {
			SELL_SPLINTERS = new MerchantRecipe(silver(1), 90);

			ArrayList<ItemStack> ingredients = new ArrayList<ItemStack>();
			ItemStack splinters = Items.getSplinters();
			splinters.setAmount(3);
			ingredients.add(splinters);
			SELL_SPLINTERS.setIngredients(ingredients);
		}
		return SELL_SPLINTERS;
	}

	private static ItemStack silver(int amount) {
		ItemStack silver = Items.getSilverCoin();
		silver.setAmount(amount);
		return silver;
	}
}
