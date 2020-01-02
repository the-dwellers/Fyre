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
	private static MerchantRecipe BUY_WOOD_SWORD;
	private static MerchantRecipe BUY_WOOD_PICKAXE;

	private static MerchantRecipe SELL_SPLINTERS;

	private static MerchantRecipe LEARN_CRAFTING_TABLE;
	private static MerchantRecipe LEARN_WOODEN_TOOLS;
	private static MerchantRecipe LEARN_WOODEN_SWORD;

	private static MerchantRecipe SELL_LOG_OAK;
	private static MerchantRecipe SELL_LOG_SPRUCE;
	private static MerchantRecipe SELL_LOG_BIRCH;
	private static MerchantRecipe SELL_LOG_JUNGLE;
	private static MerchantRecipe SELL_LOG_ACACIA;
	private static MerchantRecipe SELL_LOG_OAK_DARK;

	private static MerchantRecipe BUY_LOG_OAK;
	private static MerchantRecipe BUY_LOG_SPRUCE;
	private static MerchantRecipe BUY_LOG_BIRCH;
	private static MerchantRecipe BUY_LOG_JUNGLE;
	private static MerchantRecipe BUY_LOG_ACACIA;
	private static MerchantRecipe BUY_LOG_OAK_DARK;

	public static MerchantRecipe buyWoodAxe() {
		if (BUY_WOOD_AXE == null) {
			BUY_WOOD_AXE = new MerchantRecipe(Items.getWoodAxe(), 90);

			ArrayList<ItemStack> ingredients = new ArrayList<ItemStack>();
			ingredients.add(silver(10));

			BUY_WOOD_AXE.setIngredients(ingredients);
		}
		return BUY_WOOD_AXE;
	}

	public static MerchantRecipe sellOakLog() {
		if (SELL_LOG_OAK == null) {
			SELL_LOG_OAK = new MerchantRecipe(silver(1), 90);

			ArrayList<ItemStack> ingredients = new ArrayList<ItemStack>();
			ingredients.add(new ItemStack(Material.OAK_LOG));

			SELL_LOG_OAK.setIngredients(ingredients);
		}
		return SELL_LOG_OAK;
	}

	public static MerchantRecipe sellSpruceLog() {
		if (SELL_LOG_SPRUCE == null) {
			SELL_LOG_SPRUCE = new MerchantRecipe(silver(1), 90);

			ArrayList<ItemStack> ingredients = new ArrayList<ItemStack>();
			ingredients.add(new ItemStack(Material.SPRUCE_LOG));

			SELL_LOG_SPRUCE.setIngredients(ingredients);
		}
		return SELL_LOG_SPRUCE;
	}

	public static MerchantRecipe sellBirchLog() {
		if (SELL_LOG_BIRCH == null) {
			SELL_LOG_BIRCH = new MerchantRecipe(silver(1), 90);

			ArrayList<ItemStack> ingredients = new ArrayList<ItemStack>();
			ingredients.add(new ItemStack(Material.BIRCH_LOG));

			SELL_LOG_BIRCH .setIngredients(ingredients);
		}
		return SELL_LOG_BIRCH ;
	}

	public static MerchantRecipe sellJungleLog() {
		if (SELL_LOG_JUNGLE == null) {
			SELL_LOG_JUNGLE = new MerchantRecipe(silver(1), 90);

			ArrayList<ItemStack> ingredients = new ArrayList<ItemStack>();
			ingredients.add(new ItemStack(Material.JUNGLE_LOG));

			SELL_LOG_JUNGLE.setIngredients(ingredients);
		}
		return SELL_LOG_JUNGLE;
	}

	public static MerchantRecipe sellAcaciaLog() {
		if (SELL_LOG_ACACIA == null) {
			SELL_LOG_ACACIA = new MerchantRecipe(silver(1), 90);

			ArrayList<ItemStack> ingredients = new ArrayList<ItemStack>();
			ingredients.add(new ItemStack(Material.ACACIA_LOG));

			SELL_LOG_ACACIA.setIngredients(ingredients);
		}
		return SELL_LOG_ACACIA;
	}

	public static MerchantRecipe sellDarkOakLog() {
		if (SELL_LOG_OAK_DARK == null) {
			SELL_LOG_OAK_DARK = new MerchantRecipe(silver(1), 90);

			ArrayList<ItemStack> ingredients = new ArrayList<ItemStack>();
			ingredients.add(new ItemStack(Material.DARK_OAK_LOG));

			SELL_LOG_OAK_DARK.setIngredients(ingredients);
		}
		return SELL_LOG_OAK_DARK;
	}

	public static MerchantRecipe buyOakLog() {
		if (BUY_LOG_OAK == null) {
			BUY_LOG_OAK = new MerchantRecipe(new ItemStack(Material.OAK_LOG), 90);

			ArrayList<ItemStack> ingredients = new ArrayList<ItemStack>();
			ingredients.add(silver(3));

			SELL_LOG_OAK.setIngredients(ingredients);
		}
		return BUY_LOG_OAK;
	}

	public static MerchantRecipe buySpruceLog() {
		if (BUY_LOG_SPRUCE == null) {
			BUY_LOG_SPRUCE = new MerchantRecipe(new ItemStack(Material.SPRUCE_LOG), 90);

			ArrayList<ItemStack> ingredients = new ArrayList<ItemStack>();
			ingredients.add(silver(3));

			BUY_LOG_SPRUCE.setIngredients(ingredients);
		}
		return BUY_LOG_SPRUCE;
	}

	public static MerchantRecipe buyBirchLog() {
		if (BUY_LOG_BIRCH == null) {
			BUY_LOG_BIRCH = new MerchantRecipe(new ItemStack(Material.BIRCH_LOG), 90);

			ArrayList<ItemStack> ingredients = new ArrayList<ItemStack>();
			ingredients.add(silver(3));

			BUY_LOG_BIRCH.setIngredients(ingredients);
		}
		return BUY_LOG_BIRCH;
	}

	public static MerchantRecipe buyJungleLog() {
		if (BUY_LOG_JUNGLE == null) {
			BUY_LOG_JUNGLE = new MerchantRecipe(new ItemStack(Material.JUNGLE_LOG), 90);

			ArrayList<ItemStack> ingredients = new ArrayList<ItemStack>();
			ingredients.add(silver(3));

			BUY_LOG_JUNGLE.setIngredients(ingredients);
		}
		return BUY_LOG_JUNGLE;
	}

	public static MerchantRecipe buyAcaciaLog() {
		if (BUY_LOG_ACACIA == null) {
			BUY_LOG_ACACIA = new MerchantRecipe(new ItemStack(Material.ACACIA_LOG), 90);

			ArrayList<ItemStack> ingredients = new ArrayList<ItemStack>();
			ingredients.add(silver(3));

			BUY_LOG_ACACIA.setIngredients(ingredients);
		}
		return BUY_LOG_ACACIA;
	}

	public static MerchantRecipe buyDarkOakLog() {
		if (BUY_LOG_OAK_DARK == null) {
			BUY_LOG_OAK_DARK = new MerchantRecipe(new ItemStack(Material.DARK_OAK_LOG), 90);

			ArrayList<ItemStack> ingredients = new ArrayList<ItemStack>();
			ingredients.add(silver(3));

			BUY_LOG_OAK_DARK.setIngredients(ingredients);
		}
		return BUY_LOG_OAK_DARK;
	}

	public static MerchantRecipe buyWoodSpade() {
		if (BUY_WOOD_SPADE == null) {
			BUY_WOOD_SPADE = new MerchantRecipe(Items.getWoodShovel(), 90);

			ArrayList<ItemStack> ingredients = new ArrayList<ItemStack>();
			ingredients.add(silver(5));

			BUY_WOOD_SPADE.setIngredients(ingredients);
		}
		return BUY_WOOD_SPADE;
	}

	public static MerchantRecipe buyWoodSword() {
		if (BUY_WOOD_SWORD == null) {
			BUY_WOOD_SWORD = new MerchantRecipe(Items.getWoodSword(), 90);

			ArrayList<ItemStack> ingredients = new ArrayList<ItemStack>();
			ingredients.add(silver(10));

			BUY_WOOD_SWORD.setIngredients(ingredients);
		}
		return BUY_WOOD_SWORD;
	}

	public static MerchantRecipe buyWoodPickaxe() {
		if (BUY_WOOD_PICKAXE == null) {
			BUY_WOOD_PICKAXE = new MerchantRecipe(Items.getWoodPickaxe(), 90);

			ArrayList<ItemStack> ingredients = new ArrayList<ItemStack>();
			ingredients.add(silver(10));

			BUY_WOOD_PICKAXE.setIngredients(ingredients);
		}
		return BUY_WOOD_PICKAXE;
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

	public static MerchantRecipe learnWoodenSword() {
		if (LEARN_WOODEN_SWORD == null) {
			LEARN_WOODEN_SWORD = new MerchantRecipe(Items.craftingBook(CraftingBook.WoodenSword), 1);
			ArrayList<ItemStack> ingredients = new ArrayList<ItemStack>();
			ingredients.add(silver(20));
			LEARN_WOODEN_SWORD.setIngredients(ingredients);
		}
		return LEARN_WOODEN_SWORD;
	}

	private static ItemStack silver(int amount) {
		ItemStack silver = Items.getSilverCoin();
		silver.setAmount(amount);
		return silver;
	}

}
