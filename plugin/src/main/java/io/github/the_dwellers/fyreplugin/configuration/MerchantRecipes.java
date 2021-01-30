package io.github.the_dwellers.fyreplugin.configuration;

import io.github.the_dwellers.fyreplugin.configuration.Items.CraftingBook;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;

import java.util.ArrayList;

/**
 * Recipes sold by merchants.
 * TODO: Pricing
 */
public abstract class MerchantRecipes {

	// ~~~~~~~~~~~~~~ Buy Tools ~~~~~~~~~~~~~~ //
	private static MerchantRecipe BUY_WOOD_AXE;
	private static MerchantRecipe BUY_WOOD_SPADE;
	private static MerchantRecipe BUY_WOOD_SWORD;
	private static MerchantRecipe BUY_WOOD_PICKAXE;

	private static MerchantRecipe BUY_STONE_AXE;
	private static MerchantRecipe BUY_STONE_SPADE;
	private static MerchantRecipe BUY_STONE_SWORD;
	private static MerchantRecipe BUY_STONE_PICKAXE;

	private static MerchantRecipe BUY_IRON_AXE;
	private static MerchantRecipe BUY_IRON_SPADE;
	private static MerchantRecipe BUY_IRON_SWORD;
	private static MerchantRecipe BUY_IRON_PICKAXE;

	private static MerchantRecipe BUY_DIAMOND_AXE;
	private static MerchantRecipe BUY_DIAMOND_SPADE;
	private static MerchantRecipe BUY_DIAMOND_SWORD;
	private static MerchantRecipe BUY_DIAMOND_PICKAXE;

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
	private static MerchantRecipe LEARN_STONE_TOOLS;
	private static MerchantRecipe LEARN_IRON_TOOLS;
	private static MerchantRecipe LEARN_WOODEN_SWORD;
	private static MerchantRecipe LEARN_STONE_SWORD;
	private static MerchantRecipe LEARN_IRON_SWORD;

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

	public static MerchantRecipe buyWoodAxe() {
		if (BUY_WOOD_AXE == null) {
			BUY_WOOD_AXE = new MerchantRecipe(Items.getWoodAxe(), 90);

			ArrayList<ItemStack> ingredients = new ArrayList<ItemStack>();
			ingredients.add(silver(10));

			BUY_WOOD_AXE.setIngredients(ingredients);
		}
		return BUY_WOOD_AXE;
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


	public static MerchantRecipe buyStoneAxe() {
		if (BUY_STONE_AXE == null) {
			BUY_STONE_AXE = new MerchantRecipe(Items.getStoneAxe(), 90);

			ArrayList<ItemStack> ingredients = new ArrayList<ItemStack>();
			ingredients.add(silver(10));

			BUY_STONE_AXE.setIngredients(ingredients);
		}
		return BUY_STONE_AXE;
	}

	public static MerchantRecipe buyStoneSpade() {
		if (BUY_STONE_SPADE == null) {
			BUY_STONE_SPADE = new MerchantRecipe(Items.getStoneShovel(), 90);

			ArrayList<ItemStack> ingredients = new ArrayList<ItemStack>();
			ingredients.add(silver(5));

			BUY_STONE_SPADE.setIngredients(ingredients);
		}
		return BUY_STONE_SPADE;
	}

	public static MerchantRecipe buyStoneSword() {
		if (BUY_STONE_SWORD == null) {
			BUY_STONE_SWORD = new MerchantRecipe(Items.getStoneSword(), 90);

			ArrayList<ItemStack> ingredients = new ArrayList<ItemStack>();
			ingredients.add(silver(10));

			BUY_STONE_SWORD.setIngredients(ingredients);
		}
		return BUY_STONE_SWORD;
	}

	public static MerchantRecipe buyStonePickaxe() {
		if (BUY_STONE_PICKAXE == null) {
			BUY_STONE_PICKAXE = new MerchantRecipe(Items.getStonePickaxe(), 90);

			ArrayList<ItemStack> ingredients = new ArrayList<ItemStack>();
			ingredients.add(silver(10));

			BUY_STONE_PICKAXE.setIngredients(ingredients);
		}
		return BUY_STONE_PICKAXE;
	}

	public static MerchantRecipe buyIronAxe() {
		if (BUY_IRON_AXE == null) {
			BUY_IRON_AXE = new MerchantRecipe(Items.getIronAxe(), 90);

			ArrayList<ItemStack> ingredients = new ArrayList<ItemStack>();
			ingredients.add(silver(10));

			BUY_IRON_AXE.setIngredients(ingredients);
		}
		return BUY_IRON_AXE;
	}

	public static MerchantRecipe buyIronSpade() {
		if (BUY_IRON_SPADE == null) {
			BUY_IRON_SPADE = new MerchantRecipe(Items.getIronShovel(), 90);

			ArrayList<ItemStack> ingredients = new ArrayList<ItemStack>();
			ingredients.add(silver(5));

			BUY_IRON_SPADE.setIngredients(ingredients);
		}
		return BUY_IRON_SPADE;
	}

	public static MerchantRecipe buyIronSword() {
		if (BUY_IRON_SWORD == null) {
			BUY_IRON_SWORD = new MerchantRecipe(Items.getIronSword(), 90);

			ArrayList<ItemStack> ingredients = new ArrayList<ItemStack>();
			ingredients.add(silver(10));

			BUY_IRON_SWORD.setIngredients(ingredients);
		}
		return BUY_IRON_SWORD;
	}

	public static MerchantRecipe buyIronPickaxe() {
		if (BUY_IRON_PICKAXE == null) {
			BUY_IRON_PICKAXE = new MerchantRecipe(Items.getIronPickaxe(), 90);

			ArrayList<ItemStack> ingredients = new ArrayList<ItemStack>();
			ingredients.add(silver(10));

			BUY_IRON_PICKAXE.setIngredients(ingredients);
		}
		return BUY_IRON_PICKAXE;
	}


	public static MerchantRecipe buyDiamondAxe() {
		if (BUY_DIAMOND_AXE == null) {
			BUY_DIAMOND_AXE = new MerchantRecipe(Items.getDiamondAxe(), 90);

			ArrayList<ItemStack> ingredients = new ArrayList<ItemStack>();
			ingredients.add(silver(10));

			BUY_DIAMOND_AXE.setIngredients(ingredients);
		}
		return BUY_DIAMOND_AXE;
	}

	public static MerchantRecipe buyDiamondSpade() {
		if (BUY_DIAMOND_SPADE == null) {
			BUY_DIAMOND_SPADE = new MerchantRecipe(Items.getDiamondShovel(), 90);

			ArrayList<ItemStack> ingredients = new ArrayList<ItemStack>();
			ingredients.add(silver(5));

			BUY_DIAMOND_SPADE.setIngredients(ingredients);
		}
		return BUY_DIAMOND_SPADE;
	}

	public static MerchantRecipe buyDiamondSword() {
		if (BUY_DIAMOND_SWORD == null) {
			BUY_DIAMOND_SWORD = new MerchantRecipe(Items.getDiamondSword(), 90);

			ArrayList<ItemStack> ingredients = new ArrayList<ItemStack>();
			ingredients.add(silver(10));

			BUY_DIAMOND_SWORD.setIngredients(ingredients);
		}
		return BUY_DIAMOND_SWORD;
	}

	public static MerchantRecipe buyDiamondPickaxe() {
		if (BUY_DIAMOND_PICKAXE == null) {
			BUY_DIAMOND_PICKAXE = new MerchantRecipe(Items.getDiamondPickaxe(), 90);

			ArrayList<ItemStack> ingredients = new ArrayList<ItemStack>();
			ingredients.add(silver(10));

			BUY_DIAMOND_PICKAXE.setIngredients(ingredients);
		}
		return BUY_DIAMOND_PICKAXE;
	}

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

	public static MerchantRecipe buyChainmailHelmet() {
		if (BUY_CHAINMAIL_HELMET == null) {
			BUY_CHAINMAIL_HELMET = new MerchantRecipe(Items.getChainmailHelmet(), 90);

			ArrayList<ItemStack> ingredients = new ArrayList<ItemStack>();
			ingredients.add(silver(15));

			BUY_CHAINMAIL_HELMET.setIngredients(ingredients);
		}
		return BUY_CHAINMAIL_HELMET;
	}

	public static MerchantRecipe buyChainmailChestPlate() {
		if (BUY_CHAINMAIL_CHESTPLATE == null) {
			BUY_CHAINMAIL_CHESTPLATE = new MerchantRecipe(Items.getChainmailChestplate(), 90);

			ArrayList<ItemStack> ingredients = new ArrayList<ItemStack>();
			ingredients.add(silver(40));

			BUY_CHAINMAIL_CHESTPLATE.setIngredients(ingredients);
		}
		return BUY_CHAINMAIL_CHESTPLATE;
	}

	public static MerchantRecipe buyChainmailLeggings() {
		if (BUY_CHAINMAIL_LEGGINGS == null) {
			BUY_CHAINMAIL_LEGGINGS = new MerchantRecipe(Items.getChainmailLeggings(), 90);

			ArrayList<ItemStack> ingredients = new ArrayList<ItemStack>();
			ingredients.add(silver(25));

			BUY_CHAINMAIL_LEGGINGS.setIngredients(ingredients);
		}
		return BUY_CHAINMAIL_LEGGINGS;
	}

	public static MerchantRecipe buyChainmailBoots() {
		if (BUY_CHAINMAIL_BOOTS == null) {
			BUY_CHAINMAIL_BOOTS = new MerchantRecipe(Items.getChainmailBoots(), 90);

			ArrayList<ItemStack> ingredients = new ArrayList<ItemStack>();
			ingredients.add(silver(15));

			BUY_CHAINMAIL_BOOTS.setIngredients(ingredients);
		}
		return BUY_CHAINMAIL_BOOTS;
	}

	public static MerchantRecipe buyIronHelmet() {
		if (BUY_IRON_HELMET == null) {
			BUY_IRON_HELMET = new MerchantRecipe(Items.getIronHelmet(), 90);

			ArrayList<ItemStack> ingredients = new ArrayList<ItemStack>();
			ingredients.add(silver(15));

			BUY_IRON_HELMET.setIngredients(ingredients);
		}
		return BUY_IRON_HELMET;
	}

	public static MerchantRecipe buyIronChestPlate() {
		if (BUY_IRON_CHESTPLATE == null) {
			BUY_IRON_CHESTPLATE = new MerchantRecipe(Items.getIronChestplate(), 90);

			ArrayList<ItemStack> ingredients = new ArrayList<ItemStack>();
			ingredients.add(silver(40));

			BUY_IRON_CHESTPLATE.setIngredients(ingredients);
		}
		return BUY_IRON_CHESTPLATE;
	}

	public static MerchantRecipe buyIronLeggings() {
		if (BUY_IRON_LEGGINGS == null) {
			BUY_IRON_LEGGINGS = new MerchantRecipe(Items.getIronLeggings(), 90);

			ArrayList<ItemStack> ingredients = new ArrayList<ItemStack>();
			ingredients.add(silver(25));

			BUY_IRON_LEGGINGS.setIngredients(ingredients);
		}
		return BUY_IRON_LEGGINGS;
	}

	public static MerchantRecipe buyIronBoots() {
		if (BUY_IRON_BOOTS == null) {
			BUY_IRON_BOOTS = new MerchantRecipe(Items.getIronBoots(), 90);

			ArrayList<ItemStack> ingredients = new ArrayList<ItemStack>();
			ingredients.add(silver(15));

			BUY_IRON_BOOTS.setIngredients(ingredients);
		}
		return BUY_IRON_BOOTS;
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
			LEARN_WOODEN_TOOLS = new MerchantRecipe(Items.craftingBook(CraftingBook.ToolsWooden), 1);
			ArrayList<ItemStack> ingredients = new ArrayList<ItemStack>();
			ingredients.add(silver(40));
			LEARN_WOODEN_TOOLS.setIngredients(ingredients);
		}
		return LEARN_WOODEN_TOOLS;
	}

	public static MerchantRecipe learnStoneTools() {
		if (LEARN_STONE_TOOLS == null) {
			LEARN_STONE_TOOLS = new MerchantRecipe(Items.craftingBook(CraftingBook.ToolsStone), 1);
			ArrayList<ItemStack> ingredients = new ArrayList<ItemStack>();
			ingredients.add(silver(40));
			LEARN_STONE_TOOLS.setIngredients(ingredients);
		}
		return LEARN_STONE_TOOLS;
	}

	public static MerchantRecipe learnIronTools() {
		if (LEARN_IRON_TOOLS == null) {
			LEARN_IRON_TOOLS = new MerchantRecipe(Items.craftingBook(CraftingBook.ToolsIron), 1);
			ArrayList<ItemStack> ingredients = new ArrayList<ItemStack>();
			ingredients.add(silver(40));
			LEARN_IRON_TOOLS.setIngredients(ingredients);
		}
		return LEARN_IRON_TOOLS;
	}

	public static MerchantRecipe learnWoodenSword() {
		if (LEARN_WOODEN_SWORD == null) {
			LEARN_WOODEN_SWORD = new MerchantRecipe(Items.craftingBook(CraftingBook.SwordWooden), 1);
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
