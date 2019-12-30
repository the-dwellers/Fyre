package io.github.the_dwellers.fyreplugin.configuration;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;

import io.github.the_dwellers.fyreplugin.configuration.Items.CraftingBook;

import java.util.ArrayList;

/**
 * MerchantRecipes
 */
public abstract class MerchantRecipes {

	private static MerchantRecipe BUY_WOOD_AXE;
	private static MerchantRecipe BUY_WOOD_SPADE;
	private static MerchantRecipe SELL_SPLINTERS;
	private static MerchantRecipe LEARN_CRAFTING_TABLE;
	private static MerchantRecipe LEARN_WOODEN_TOOLS;
	private static MerchantRecipe SELL_LOG;


	public static MerchantRecipe getWoodAxe() {
		if (BUY_WOOD_AXE == null) {
			BUY_WOOD_AXE = new MerchantRecipe(Items.getWoodAxe(), 90);

			ArrayList<ItemStack> ingredients = new ArrayList<ItemStack>();
			ingredients.add(silver(10));

			BUY_WOOD_AXE.setIngredients(ingredients);
		}
		return BUY_WOOD_AXE;
	}

	public static MerchantRecipe sellOakLog() {
		if (SELL_LOG == null) {
			SELL_LOG = new MerchantRecipe(silver(1), 90);

			ArrayList<ItemStack> ingredients = new ArrayList<ItemStack>();
			ingredients.add(new ItemStack(Material.OAK_LOG));

			SELL_LOG.setIngredients(ingredients);
		}
		return SELL_LOG;
	}

	public static MerchantRecipe getWoodSpade() {
		if (BUY_WOOD_SPADE == null) {
			BUY_WOOD_SPADE = new MerchantRecipe(Items.getWoodShovel(), 90);

			ArrayList<ItemStack> ingredients = new ArrayList<ItemStack>();
			ingredients.add(silver(5));

			BUY_WOOD_SPADE.setIngredients(ingredients);
		}
		return BUY_WOOD_SPADE;
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

	public static MerchantRecipe learnCraftingTable() {
		if (LEARN_CRAFTING_TABLE == null) {
			LEARN_CRAFTING_TABLE = new MerchantRecipe(Items.craftingBook(CraftingBook.CraftingTable), 1);
			ArrayList<ItemStack> ingredients = new ArrayList<ItemStack>();
			ingredients.add(silver(10));
			LEARN_CRAFTING_TABLE.setIngredients(ingredients);
		}
		return LEARN_CRAFTING_TABLE;
	}

	public static MerchantRecipe learnWoodenTools() {
		if (LEARN_WOODEN_TOOLS == null) {
			LEARN_WOODEN_TOOLS = new MerchantRecipe(Items.craftingBook(CraftingBook.WoodenTools), 1);
			ArrayList<ItemStack> ingredients = new ArrayList<ItemStack>();
			ingredients.add(silver(40));
			LEARN_WOODEN_TOOLS.setIngredients(ingredients);
		}
		return LEARN_WOODEN_TOOLS;
	}

	private static ItemStack silver(int amount) {
		ItemStack silver = Items.getSilverCoin();
		silver.setAmount(amount);
		return silver;
	}
}
