package io.github.the_dwellers.fyreplugin.configuration;

import io.github.the_dwellers.fyreplugin.configuration.Items.CraftingBook;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;

import java.util.ArrayList;

/**
 * Recipes sold by merchants.
 */
public abstract class MerchantRecipes {

	// ~~~~~~~~~~~~~~ Buy Tools ~~~~~~~~~~~~~~ //
	private static MerchantRecipe BUY_WOOD_AXE;
	private static MerchantRecipe BUY_WOOD_SPADE;
	private static MerchantRecipe BUY_WOOD_SWORD;
	private static MerchantRecipe BUY_WOOD_PICKAXE;

	// ~~~~~~~~~~~~ Sell Resources ~~~~~~~~~~~ //
	private static MerchantRecipe SELL_SPLINTERS;

	private static MerchantRecipe SELL_COAL;

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

	// ~~~~~~~~~ Buy Crafting Recipes ~~~~~~~~ //
	private static MerchantRecipe LEARN_CRAFTING_TABLE;
	private static MerchantRecipe LEARN_WOODEN_TOOLS;
	private static MerchantRecipe LEARN_WOODEN_SWORD;

	// ~~~~~~~~~~~~~~ Buy Armour ~~~~~~~~~~~~~ //
	private static MerchantRecipe BUY_LEATHER_HELMET;
	private static MerchantRecipe BUY_LEATHER_CHESTPLATE;
	private static MerchantRecipe BUY_LEATHER_LEGGINGS;
	private static MerchantRecipe BUY_LEATHER_BOOTS;

	private static MerchantRecipe BUY_CHAINMAIL_HELMET;
	private static MerchantRecipe BUY_CHAINMAIL_CHESTPLATE;
	private static MerchantRecipe BUY_CHAINMAIL_LEGGINGS;
	private static MerchantRecipe BUY_CHAINMAIL_BOOTS;

	private static MerchantRecipe BUY_IRON_HELMET;
	private static MerchantRecipe BUY_IRON_CHESTPLATE;
	private static MerchantRecipe BUY_IRON_LEGGINGS;
	private static MerchantRecipe BUY_IRON_BOOTS;

	// ====================================================== //
	// ====================== Functions ===================== //
	// ====================================================== //
	public static MerchantRecipe sellCoal() {
		if (SELL_COAL == null) {
			SELL_COAL = new MerchantRecipe(silver(3), 90);

			ArrayList<ItemStack> ingredients = new ArrayList<ItemStack>();
			ingredients.add(new ItemStack(Material.COAL, 2));

			SELL_COAL.setIngredients(ingredients);
		}
		return SELL_COAL;
	}

	public static MerchantRecipe buyLeatherHelmet() {
		if (BUY_LEATHER_HELMET == null) {
			BUY_LEATHER_HELMET = new MerchantRecipe(Items.getLeatherHelmet(), 90);

			ArrayList<ItemStack> ingredients = new ArrayList<ItemStack>();
			ingredients.add(silver(15));

			BUY_LEATHER_HELMET.setIngredients(ingredients);
		}
		return BUY_LEATHER_HELMET;
	}

	public static MerchantRecipe buyLeatherChestPlate() {
		if (BUY_LEATHER_CHESTPLATE == null) {
			BUY_LEATHER_CHESTPLATE = new MerchantRecipe(Items.getLeatherChestplate(), 90);

			ArrayList<ItemStack> ingredients = new ArrayList<ItemStack>();
			ingredients.add(silver(40));

			BUY_LEATHER_CHESTPLATE.setIngredients(ingredients);
		}
		return BUY_LEATHER_CHESTPLATE;
	}

	public static MerchantRecipe buyLeatherLeggings() {
		if (BUY_LEATHER_LEGGINGS == null) {
			BUY_LEATHER_LEGGINGS = new MerchantRecipe(Items.getLeatherLeggings(), 90);

			ArrayList<ItemStack> ingredients = new ArrayList<ItemStack>();
			ingredients.add(silver(25));

			BUY_LEATHER_LEGGINGS.setIngredients(ingredients);
		}
		return BUY_LEATHER_LEGGINGS;
	}

	public static MerchantRecipe buyLeatherBoots() {
		if (BUY_LEATHER_BOOTS == null) {
			BUY_LEATHER_BOOTS = new MerchantRecipe(Items.getLeatherBoots(), 90);

			ArrayList<ItemStack> ingredients = new ArrayList<ItemStack>();
			ingredients.add(silver(15));

			BUY_LEATHER_BOOTS.setIngredients(ingredients);
		}
		return BUY_LEATHER_BOOTS;
	}

	// TODO: Pricing
	public static MerchantRecipe buyChainmailHelmet() {
		if (BUY_CHAINMAIL_HELMET == null) {
			BUY_CHAINMAIL_HELMET = new MerchantRecipe(Items.getChainmailHelmet(), 90);

			ArrayList<ItemStack> ingredients = new ArrayList<ItemStack>();
			ingredients.add(silver(15));

			BUY_CHAINMAIL_HELMET.setIngredients(ingredients);
		}
		return BUY_CHAINMAIL_HELMET;
	}

	// TODO: Pricing
	public static MerchantRecipe buyChainmailChestPlate() {
		if (BUY_CHAINMAIL_CHESTPLATE == null) {
			BUY_CHAINMAIL_CHESTPLATE = new MerchantRecipe(Items.getChainmailChestplate(), 90);

			ArrayList<ItemStack> ingredients = new ArrayList<ItemStack>();
			ingredients.add(silver(40));

			BUY_CHAINMAIL_CHESTPLATE.setIngredients(ingredients);
		}
		return BUY_CHAINMAIL_CHESTPLATE;
	}

	// TODO: Pricing
	public static MerchantRecipe buyChainmailLeggings() {
		if (BUY_CHAINMAIL_LEGGINGS == null) {
			BUY_CHAINMAIL_LEGGINGS = new MerchantRecipe(Items.getChainmailLeggings(), 90);

			ArrayList<ItemStack> ingredients = new ArrayList<ItemStack>();
			ingredients.add(silver(25));

			BUY_CHAINMAIL_LEGGINGS.setIngredients(ingredients);
		}
		return BUY_CHAINMAIL_LEGGINGS;
	}

	// TODO: Pricing
	public static MerchantRecipe buyChainmailBoots() {
		if (BUY_CHAINMAIL_BOOTS == null) {
			BUY_CHAINMAIL_BOOTS = new MerchantRecipe(Items.getChainmailBoots(), 90);

			ArrayList<ItemStack> ingredients = new ArrayList<ItemStack>();
			ingredients.add(silver(15));

			BUY_CHAINMAIL_BOOTS.setIngredients(ingredients);
		}
		return BUY_CHAINMAIL_BOOTS;
	}

	// TODO: Pricing
	public static MerchantRecipe buyIronHelmet() {
		if (BUY_IRON_HELMET == null) {
			BUY_IRON_HELMET = new MerchantRecipe(Items.getIronHelmet(), 90);

			ArrayList<ItemStack> ingredients = new ArrayList<ItemStack>();
			ingredients.add(silver(15));

			BUY_IRON_HELMET.setIngredients(ingredients);
		}
		return BUY_IRON_HELMET;
	}

	// TODO: Pricing
	public static MerchantRecipe buyIronChestPlate() {
		if (BUY_IRON_CHESTPLATE == null) {
			BUY_IRON_CHESTPLATE = new MerchantRecipe(Items.getIronChestplate(), 90);

			ArrayList<ItemStack> ingredients = new ArrayList<ItemStack>();
			ingredients.add(silver(40));

			BUY_IRON_CHESTPLATE.setIngredients(ingredients);
		}
		return BUY_IRON_CHESTPLATE;
	}

	// TODO: Pricing
	public static MerchantRecipe buyIronLeggings() {
		if (BUY_IRON_LEGGINGS == null) {
			BUY_IRON_LEGGINGS = new MerchantRecipe(Items.getIronLeggings(), 90);

			ArrayList<ItemStack> ingredients = new ArrayList<ItemStack>();
			ingredients.add(silver(25));

			BUY_IRON_LEGGINGS.setIngredients(ingredients);
		}
		return BUY_IRON_LEGGINGS;
	}

	// TODO: Pricing
	public static MerchantRecipe buyIronBoots() {
		if (BUY_IRON_BOOTS == null) {
			BUY_IRON_BOOTS = new MerchantRecipe(Items.getIronBoots(), 90);

			ArrayList<ItemStack> ingredients = new ArrayList<ItemStack>();
			ingredients.add(silver(15));

			BUY_IRON_BOOTS.setIngredients(ingredients);
		}
		return BUY_IRON_BOOTS;
	}

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

			SELL_LOG_BIRCH.setIngredients(ingredients);
		}
		return SELL_LOG_BIRCH;
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
