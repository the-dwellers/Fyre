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

	// ~~~~~~~~~~~~ Coin Exchange ~~~~~~~~~~~~ //
	private static MerchantRecipe EXCHANGE_SILVER_GOLD;
	private static MerchantRecipe EXCHANGE_GOLD_SILVER;

	// ~~~~~~~~~~~~~~ Buy Tools ~~~~~~~~~~~~~~ //
	// Spades
	private static MerchantRecipe BUY_SPADE_WOOD;
	private static MerchantRecipe BUY_SPADE_STONE;
	private static MerchantRecipe BUY_SPADE_IRON;
	private static MerchantRecipe BUY_SPADE_DIAMOND;

	// Axes
	private static MerchantRecipe BUY_AXE_WOOD;
	private static MerchantRecipe BUY_AXE_STONE;
	private static MerchantRecipe BUY_AXE_IRON;
	private static MerchantRecipe BUY_AXE_DIAMOND;

	// Hoes
	private static MerchantRecipe BUY_HOE_WOOD;
	private static MerchantRecipe BUY_HOE_STONE;
	private static MerchantRecipe BUY_HOE_IRON;
	private static MerchantRecipe BUY_HOE_DIAMOND;

	// Pickaxes
	private static MerchantRecipe BUY_PICKAXE_WOOD;
	private static MerchantRecipe BUY_PICKAXE_STONE;
	private static MerchantRecipe BUY_PICKAXE_IRON;
	private static MerchantRecipe BUY_PICKAXE_DIAMOND;

	// Swords
	private static MerchantRecipe BUY_SWORD_WOOD;
	private static MerchantRecipe BUY_SWORD_STONE;
	private static MerchantRecipe BUY_SWORD_IRON;
	private static MerchantRecipe BUY_SWORD_DIAMOND;

	// ~~~~~~~~~~~~~~ Buy Armour ~~~~~~~~~~~~~ //
	// Helmets
	private static MerchantRecipe BUY_HELMET_LEATHER;
	private static MerchantRecipe BUY_HELMET_CHAINMAIL;
	private static MerchantRecipe BUY_HELMET_IRON;

	// Chestplates
	private static MerchantRecipe BUY_CHESTPLATE_LEATHER;
	private static MerchantRecipe BUY_CHESTPLATE_CHAINMAIL;
	private static MerchantRecipe BUY_CHESTPLATE_IRON;

	// Leggings
	private static MerchantRecipe BUY_LEGGINGS_LEATHER;
	private static MerchantRecipe BUY_LEGGINGS_CHAINMAIL;
	private static MerchantRecipe BUY_LEGGINGS_IRON;

	// Boots
	private static MerchantRecipe BUY_BOOTS_LEATHER;
	private static MerchantRecipe BUY_BOOTS_CHAINMAIL;
	private static MerchantRecipe BUY_BOOTS_IRON;

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

	private static MerchantRecipe LEARN_PICKAXE_WOODEN;
	private static MerchantRecipe LEARN_PICKAXE_STONE;
	private static MerchantRecipe LEARN_PICKAXE_IRON;

	private static MerchantRecipe LEARN_TOOLS_WOODEN;
	private static MerchantRecipe LEARN_TOOLS_STONE;
	private static MerchantRecipe LEARN_TOOLS_IRON;

	private static MerchantRecipe LEARN_SWORD_WOODEN;
	private static MerchantRecipe LEARN_SWORD_STONE;
	private static MerchantRecipe LEARN_SWORD_IRON;

	private static MerchantRecipe LEARN_ARMOR_HAT_BOOTS_LEATHER;
	private static MerchantRecipe LEARN_ARMOR_HAT_BOOTS_CHAINMAIL;
	private static MerchantRecipe LEARN_ARMOR_HAT_BOOTS_IRON;

	private static MerchantRecipe LEARN_ARMOR_CHEST_LEGS_LEATHER;
	private static MerchantRecipe LEARN_ARMOR_CHEST_LEGS_CHAINMAIL;
	private static MerchantRecipe LEARN_ARMOR_CHEST_LEGS_IRON;

	// ~~~~~~~~~ Buy Food ~~~~~~~~ //
	// MEATS //
	private static MerchantRecipe STEAK_TIER_1;
	private static MerchantRecipe STEAK_TIER_2;
	private static MerchantRecipe STEAK_TIER_3;
	private static MerchantRecipe STEAK_TIER_4;
	private static MerchantRecipe STEAK_TIER_5;

	private static MerchantRecipe MUTTON_TIER_1;
	private static MerchantRecipe MUTTON_TIER_2;
	private static MerchantRecipe MUTTON_TIER_3;
	private static MerchantRecipe MUTTON_TIER_4;
	private static MerchantRecipe MUTTON_TIER_5;

	private static MerchantRecipe PORK_TIER_1;
	private static MerchantRecipe PORK_TIER_2;
	private static MerchantRecipe PORK_TIER_3;
	private static MerchantRecipe PORK_TIER_4;
	private static MerchantRecipe PORK_TIER_5;

	private static MerchantRecipe CHICKEN_TIER_1;
	private static MerchantRecipe CHICKEN_TIER_2;
	private static MerchantRecipe CHICKEN_TIER_3;
	private static MerchantRecipe CHICKEN_TIER_4;
	private static MerchantRecipe CHICKEN_TIER_5;

	private static MerchantRecipe RABBIT_STEW_TIER_1;
	private static MerchantRecipe RABBIT_STEW_TIER_2;
	private static MerchantRecipe RABBIT_STEW_TIER_3;
	private static MerchantRecipe RABBIT_STEW_TIER_4;
	private static MerchantRecipe RABBIT_STEW_TIER_5;

	private static MerchantRecipe COOKED_COD_TIER_1;
	private static MerchantRecipe COOKED_COD_TIER_2;
	private static MerchantRecipe COOKED_COD_TIER_3;
	private static MerchantRecipe COOKED_COD_TIER_4;
	private static MerchantRecipe COOKED_COD_TIER_5;

	// VEGGIES //
	private static MerchantRecipe POTATO_TIER_1;
	private static MerchantRecipe POTATO_TIER_2;
	private static MerchantRecipe POTATO_TIER_3;
	private static MerchantRecipe POTATO_TIER_4;

	private static MerchantRecipe BREAD_TIER_1;
	private static MerchantRecipe BREAD_TIER_2;
	private static MerchantRecipe BREAD_TIER_3;
	private static MerchantRecipe BREAD_TIER_4;

	private static MerchantRecipe MUSHROOM_SOUP_TIER_1;
	private static MerchantRecipe MUSHROOM_SOUP_TIER_2;
	private static MerchantRecipe MUSHROOM_SOUP_TIER_3;
	private static MerchantRecipe MUSHROOM_SOUP_TIER_4;
	private static MerchantRecipe MUSHROOM_SOUP_TIER_5;

	private static MerchantRecipe BEETROOT_SOUP_TIER_1;
	private static MerchantRecipe BEETROOT_SOUP_TIER_2;
	private static MerchantRecipe BEETROOT_SOUP_TIER_3;
	private static MerchantRecipe BEETROOT_SOUP_TIER_4;
	private static MerchantRecipe BEETROOT_SOUP_TIER_5;



	// ====================================================== //
	// ====================== Functions ===================== //
	// ====================================================== //

	// ~~~~~~~~~~~~ Coin Exchange ~~~~~~~~~~~~ //


	public static MerchantRecipe exchangeSilverGold() {

		if (EXCHANGE_SILVER_GOLD == null) {
			EXCHANGE_SILVER_GOLD = new MerchantRecipe(silver(64), 90);

			ArrayList<ItemStack> ingredients = new ArrayList<ItemStack>();
			ingredients.add(gold(1));

			EXCHANGE_SILVER_GOLD.setIngredients(ingredients);
		}
		return EXCHANGE_SILVER_GOLD;
	}

	public static MerchantRecipe exchangeGoldSilver() {

		if (EXCHANGE_GOLD_SILVER == null) {
			EXCHANGE_GOLD_SILVER = new MerchantRecipe(gold(1), 90);

			ArrayList<ItemStack> ingredients = new ArrayList<ItemStack>();
			ingredients.add(silver(64));

			EXCHANGE_GOLD_SILVER.setIngredients(ingredients);
		}
		return EXCHANGE_GOLD_SILVER;
	}

	// ~~~~~~~~~~~~~~ Buy Tools ~~~~~~~~~~~~~~ //
	// -------- Spades -------- //
	public static MerchantRecipe buyWoodSpade() {
		if (BUY_SPADE_WOOD == null) {
			BUY_SPADE_WOOD = new MerchantRecipe(Items.getWoodShovel(), 90);
			setPrice(BUY_SPADE_WOOD, 5);
		}
		return BUY_SPADE_WOOD;
	}

	public static MerchantRecipe buyStoneSpade() {
		if (BUY_SPADE_STONE == null) {
			BUY_SPADE_STONE = new MerchantRecipe(Items.getStoneShovel(), 90);
			setPrice(BUY_SPADE_STONE, 10);
		}
		return BUY_SPADE_STONE;
	}

	public static MerchantRecipe buyIronSpade() {
		if (BUY_SPADE_IRON == null) {
			BUY_SPADE_IRON = new MerchantRecipe(Items.getIronShovel(), 90);
			setPrice(BUY_SPADE_IRON, 20);
		}
		return BUY_SPADE_IRON;
	}

	public static MerchantRecipe buyDiamondSpade() {
		if (BUY_SPADE_DIAMOND == null) {
			BUY_SPADE_DIAMOND = new MerchantRecipe(Items.getDiamondShovel(), 90);
			setPrice(BUY_SPADE_DIAMOND, 40);
		}
		return BUY_SPADE_DIAMOND;
	}

	// --------- Axes --------- //
	public static MerchantRecipe buyWoodAxe() {
		if (BUY_AXE_WOOD == null) {
			BUY_AXE_WOOD = new MerchantRecipe(Items.getWoodAxe(), 90);
			setPrice(BUY_AXE_WOOD, 10);
		}
		return BUY_AXE_WOOD;
	}

	public static MerchantRecipe buyStoneAxe() {
		if (BUY_AXE_STONE == null) {
			BUY_AXE_STONE = new MerchantRecipe(Items.getStoneAxe(), 90);
			setPrice(BUY_AXE_STONE, 20);
		}
		return BUY_AXE_STONE;
	}

	public static MerchantRecipe buyIronAxe() {
		if (BUY_AXE_IRON == null) {
			BUY_AXE_IRON = new MerchantRecipe(Items.getIronAxe(), 90);
			setPrice(BUY_AXE_IRON, 40);
		}
		return BUY_AXE_IRON;
	}

	public static MerchantRecipe buyDiamondAxe() {
		if (BUY_AXE_DIAMOND == null) {
			BUY_AXE_DIAMOND = new MerchantRecipe(Items.getDiamondAxe(), 90);
			setPrice(BUY_AXE_DIAMOND, 80);
		}
		return BUY_AXE_DIAMOND;
	}

	// --------- Hoes --------- //
	public static MerchantRecipe buyWoodHoe() {
		if (BUY_HOE_WOOD == null) {
			BUY_HOE_WOOD = new MerchantRecipe(Items.getWoodHoe(), 90);
			setPrice(BUY_HOE_WOOD, 10);
		}
		return BUY_HOE_WOOD;
	}

	public static MerchantRecipe buyStoneHoe() {
		if (BUY_HOE_STONE == null) {
			BUY_HOE_STONE = new MerchantRecipe(Items.getStoneHoe(), 90);
			setPrice(BUY_HOE_STONE, 20);
		}
		return BUY_HOE_STONE;
	}

	public static MerchantRecipe buyIronHoe() {
		if (BUY_HOE_IRON == null) {
			BUY_HOE_IRON = new MerchantRecipe(Items.getIronHoe(), 90);
			setPrice(BUY_HOE_IRON, 40);
		}
		return BUY_HOE_IRON;
	}

	public static MerchantRecipe buyDiamondHoe() {
		if (BUY_HOE_DIAMOND == null) {
			BUY_HOE_DIAMOND = new MerchantRecipe(Items.getDiamondHoe(), 90);
			setPrice(BUY_HOE_DIAMOND, 80);
		}
		return BUY_HOE_DIAMOND;
	}

	// ------- Pickaxes ------- //

	public static MerchantRecipe buyWoodPickaxe() {
		if (BUY_PICKAXE_WOOD == null) {
			BUY_PICKAXE_WOOD = new MerchantRecipe(Items.getWoodPickaxe(), 90);
			setPrice(BUY_PICKAXE_WOOD, 10);
		}
		return BUY_PICKAXE_WOOD;
	}

	public static MerchantRecipe buyStonePickaxe() {
		if (BUY_PICKAXE_STONE == null) {
			BUY_PICKAXE_STONE = new MerchantRecipe(Items.getStonePickaxe(), 90);
			setPrice(BUY_PICKAXE_STONE, 20);
		}
		return BUY_PICKAXE_STONE;
	}

	public static MerchantRecipe buyIronPickaxe() {
		if (BUY_PICKAXE_IRON == null) {
			BUY_PICKAXE_IRON = new MerchantRecipe(Items.getIronPickaxe(), 90);
			setPrice(BUY_PICKAXE_IRON, 45);
		}
		return BUY_PICKAXE_IRON;
	}

	public static MerchantRecipe buyDiamondPickaxe() {
		if (BUY_PICKAXE_DIAMOND == null) {
			BUY_PICKAXE_DIAMOND = new MerchantRecipe(Items.getDiamondPickaxe(), 90);
			setPrice(BUY_PICKAXE_DIAMOND, 90);
		}
		return BUY_PICKAXE_DIAMOND;
	}

	// -------- Swords -------- //
	public static MerchantRecipe buyWoodSword() {
		if (BUY_SWORD_WOOD == null) {
			BUY_SWORD_WOOD = new MerchantRecipe(Items.getWoodSword(), 90);
			setPrice(BUY_SWORD_WOOD, 10);
		}
		return BUY_SWORD_WOOD;
	}

	public static MerchantRecipe buyStoneSword() {
		if (BUY_SWORD_STONE == null) {
			BUY_SWORD_STONE = new MerchantRecipe(Items.getStoneSword(), 90);
			setPrice(BUY_SWORD_STONE, 20);
		}
		return BUY_SWORD_STONE;
	}

	public static MerchantRecipe buyIronSword() {
		if (BUY_SWORD_IRON == null) {
			BUY_SWORD_IRON = new MerchantRecipe(Items.getIronSword(), 90);
			setPrice(BUY_SWORD_IRON, 40);
		}
		return BUY_SWORD_IRON;
	}

	public static MerchantRecipe buyDiamondSword() {
		if (BUY_SWORD_DIAMOND == null) {
			BUY_SWORD_DIAMOND = new MerchantRecipe(Items.getDiamondSword(), 90);
			setPrice(BUY_SWORD_DIAMOND, 80);
		}
		return BUY_SWORD_DIAMOND;
	}

	// ~~~~~~~~~~~~~~ Buy Armour ~~~~~~~~~~~~~ //

	// Helmets
	public static MerchantRecipe buyLeatherHelmet() {
		if (BUY_HELMET_LEATHER == null) {
			BUY_HELMET_LEATHER = new MerchantRecipe(Items.getLeatherHelmet(), 90);
			setPrice(BUY_HELMET_LEATHER, 10);
		}
		return BUY_HELMET_LEATHER;
	}

	public static MerchantRecipe buyChainmailHelmet() {
		if (BUY_HELMET_CHAINMAIL == null) {
			BUY_HELMET_CHAINMAIL = new MerchantRecipe(Items.getChainmailHelmet(), 90);
			setPrice(BUY_HELMET_CHAINMAIL, 20);
		}
		return BUY_HELMET_CHAINMAIL;
	}

	public static MerchantRecipe buyIronHelmet() {
		if (BUY_HELMET_IRON == null) {
			BUY_HELMET_IRON = new MerchantRecipe(Items.getIronHelmet(), 90);
			setPrice(BUY_HELMET_IRON, 40);
		}
		return BUY_HELMET_IRON;
	}

	// Chestplates
	public static MerchantRecipe buyLeatherChestPlate() {
		if (BUY_CHESTPLATE_LEATHER == null) {
			BUY_CHESTPLATE_LEATHER = new MerchantRecipe(Items.getLeatherChestplate(), 90);
			setPrice(BUY_CHESTPLATE_LEATHER, 15);
		}
		return BUY_CHESTPLATE_LEATHER;
	}

	public static MerchantRecipe buyChainmailChestPlate() {
		if (BUY_CHESTPLATE_CHAINMAIL == null) {
			BUY_CHESTPLATE_CHAINMAIL = new MerchantRecipe(Items.getChainmailChestplate(), 90);
			setPrice(BUY_CHESTPLATE_CHAINMAIL, 30);
		}
		return BUY_CHESTPLATE_CHAINMAIL;
	}

	public static MerchantRecipe buyIronChestPlate() {
		if (BUY_CHESTPLATE_IRON == null) {
			BUY_CHESTPLATE_IRON = new MerchantRecipe(Items.getIronChestplate(), 90);
			setPrice(BUY_CHESTPLATE_IRON, 60);
		}
		return BUY_CHESTPLATE_IRON;
	}

	// Leggings
	public static MerchantRecipe buyLeatherLeggings() {
		if (BUY_LEGGINGS_LEATHER == null) {
			BUY_LEGGINGS_LEATHER = new MerchantRecipe(Items.getLeatherLeggings(), 90);
			setPrice(BUY_LEGGINGS_LEATHER, 15);
		}
		return BUY_LEGGINGS_LEATHER;
	}

	public static MerchantRecipe buyChainmailLeggings() {
		if (BUY_LEGGINGS_CHAINMAIL == null) {
			BUY_LEGGINGS_CHAINMAIL = new MerchantRecipe(Items.getChainmailLeggings(), 90);
			setPrice(BUY_LEGGINGS_CHAINMAIL, 30);
		}
		return BUY_LEGGINGS_CHAINMAIL;
	}

	public static MerchantRecipe buyIronLeggings() {
		if (BUY_LEGGINGS_IRON == null) {
			BUY_LEGGINGS_IRON = new MerchantRecipe(Items.getIronLeggings(), 90);
			setPrice(BUY_LEGGINGS_IRON, 60);
		}
		return BUY_LEGGINGS_IRON;
	}

	// Boots
	public static MerchantRecipe buyLeatherBoots() {
		if (BUY_BOOTS_LEATHER == null) {
			BUY_BOOTS_LEATHER = new MerchantRecipe(Items.getLeatherBoots(), 90);
			setPrice(BUY_BOOTS_LEATHER, 10);
		}
		return BUY_BOOTS_LEATHER;
	}

	public static MerchantRecipe buyChainmailBoots() {
		if (BUY_BOOTS_CHAINMAIL == null) {
			BUY_BOOTS_CHAINMAIL = new MerchantRecipe(Items.getChainmailBoots(), 90);
			setPrice(BUY_BOOTS_CHAINMAIL, 20);
		}
		return BUY_BOOTS_CHAINMAIL;
	}

	public static MerchantRecipe buyIronBoots() {
		if (BUY_BOOTS_IRON == null) {
			BUY_BOOTS_IRON = new MerchantRecipe(Items.getIronBoots(), 90);
			setPrice(BUY_BOOTS_IRON, 40);
		}
		return BUY_BOOTS_IRON;
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
			setPrice(BUY_LOG_OAK, 5);
		}
		return BUY_LOG_OAK;
	}

	public static MerchantRecipe buySpruceLog() {
		if (BUY_LOG_SPRUCE == null) {
			BUY_LOG_SPRUCE = new MerchantRecipe(new ItemStack(Material.SPRUCE_LOG), 90);
			setPrice(BUY_LOG_SPRUCE, 3);
		}
		return BUY_LOG_SPRUCE;
	}

	public static MerchantRecipe buyBirchLog() {
		if (BUY_LOG_BIRCH == null) {
			BUY_LOG_BIRCH = new MerchantRecipe(new ItemStack(Material.BIRCH_LOG), 90);
			setPrice(BUY_LOG_BIRCH, 3);
		}
		return BUY_LOG_BIRCH;
	}

	public static MerchantRecipe buyJungleLog() {
		if (BUY_LOG_JUNGLE == null) {
			BUY_LOG_JUNGLE = new MerchantRecipe(new ItemStack(Material.JUNGLE_LOG), 90);
			setPrice(BUY_LOG_JUNGLE, 3);
		}
		return BUY_LOG_JUNGLE;
	}

	public static MerchantRecipe buyAcaciaLog() {
		if (BUY_LOG_ACACIA == null) {
			BUY_LOG_ACACIA = new MerchantRecipe(new ItemStack(Material.ACACIA_LOG), 90);
			setPrice(BUY_LOG_ACACIA, 3);
		}
		return BUY_LOG_ACACIA;
	}

	public static MerchantRecipe buyDarkOakLog() {
		if (BUY_LOG_OAK_DARK == null) {
			BUY_LOG_OAK_DARK = new MerchantRecipe(new ItemStack(Material.DARK_OAK_LOG), 90);
			setPrice(BUY_LOG_OAK_DARK, 3);
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

	// ~~~~~~~~~ Buy Crafting Recipes ~~~~~~~~ //

	public static MerchantRecipe learnCraftingTable() {
		if (LEARN_CRAFTING_TABLE == null) {
			LEARN_CRAFTING_TABLE = new MerchantRecipe(Items.craftingBook(CraftingBook.CraftingTable), 1);
			setPrice(LEARN_CRAFTING_TABLE, 10);
		}
		return LEARN_CRAFTING_TABLE;
	}

	public static MerchantRecipe learnWoodenPickaxe() {
		if (LEARN_PICKAXE_WOODEN == null) {
			LEARN_PICKAXE_WOODEN = new MerchantRecipe(Items.craftingBook(CraftingBook.PickaxeWooden), 1);
			setPrice(LEARN_PICKAXE_WOODEN, 20);
		}
		return LEARN_PICKAXE_WOODEN;
	}

	public static MerchantRecipe learnStonePickaxe() {
		if (LEARN_PICKAXE_STONE == null) {
			LEARN_PICKAXE_STONE = new MerchantRecipe(Items.craftingBook(CraftingBook.PickaxeStone), 1);
			setPrice(LEARN_PICKAXE_STONE, 40);
		}
		return LEARN_PICKAXE_STONE;
	}

	public static MerchantRecipe learnIronPickaxe() {
		if (LEARN_PICKAXE_IRON == null) {
			LEARN_PICKAXE_IRON = new MerchantRecipe(Items.craftingBook(CraftingBook.PickaxeIron), 1);
			setPrice(LEARN_PICKAXE_IRON, 90);
		}
		return LEARN_PICKAXE_IRON;
	}

	public static MerchantRecipe learnWoodenTools() {
		if (LEARN_TOOLS_WOODEN == null) {
			LEARN_TOOLS_WOODEN = new MerchantRecipe(Items.craftingBook(CraftingBook.ToolsWooden), 1);
			setPrice(LEARN_TOOLS_WOODEN, 40);
		}
		return LEARN_TOOLS_WOODEN;
	}

	public static MerchantRecipe learnStoneTools() {
		if (LEARN_TOOLS_STONE == null) {
			LEARN_TOOLS_STONE = new MerchantRecipe(Items.craftingBook(CraftingBook.ToolsStone), 1);
			setPrice(LEARN_TOOLS_STONE, 80);
		}
		return LEARN_TOOLS_STONE;
	}

	public static MerchantRecipe learnIronTools() {
		if (LEARN_TOOLS_IRON == null) {
			LEARN_TOOLS_IRON = new MerchantRecipe(Items.craftingBook(CraftingBook.ToolsIron), 1);
			setPrice(LEARN_TOOLS_IRON, 160);
		}
		return LEARN_TOOLS_IRON;
	}

	public static MerchantRecipe learnWoodenSword() {
		if (LEARN_SWORD_WOODEN == null) {
			LEARN_SWORD_WOODEN = new MerchantRecipe(Items.craftingBook(CraftingBook.SwordWooden), 1);
			setPrice(LEARN_SWORD_WOODEN, 20);
		}
		return LEARN_SWORD_WOODEN;
	}

	public static MerchantRecipe learnStoneSword() {
		if (LEARN_SWORD_STONE == null) {
			LEARN_SWORD_STONE = new MerchantRecipe(Items.craftingBook(CraftingBook.SwordStone), 1);
			setPrice(LEARN_SWORD_STONE, 40);
		}
		return LEARN_SWORD_STONE;
	}

	public static MerchantRecipe learnIronSword() {
		if (LEARN_SWORD_IRON == null) {
			LEARN_SWORD_IRON = new MerchantRecipe(Items.craftingBook(CraftingBook.SwordIron), 1);
			setPrice(LEARN_SWORD_IRON, 80);
		}
		return LEARN_SWORD_IRON;
	}

	public static MerchantRecipe learnArmorAdvancedLeather() {
		if (LEARN_ARMOR_HAT_BOOTS_LEATHER == null) {
			LEARN_ARMOR_HAT_BOOTS_LEATHER = new MerchantRecipe(Items.craftingBook(CraftingBook.ArmorChestLegsLeather), 1);
			setPrice(LEARN_ARMOR_HAT_BOOTS_LEATHER, 60);
		}
		return LEARN_ARMOR_HAT_BOOTS_LEATHER;
	}

	public static MerchantRecipe learnArmorAdvancedChainmail() {
		if (LEARN_ARMOR_HAT_BOOTS_CHAINMAIL == null) {
			LEARN_ARMOR_HAT_BOOTS_CHAINMAIL = new MerchantRecipe(Items.craftingBook(CraftingBook.ArmorChestLegsChainmail), 1);
			setPrice(LEARN_ARMOR_HAT_BOOTS_CHAINMAIL, 120);
		}
		return LEARN_ARMOR_HAT_BOOTS_CHAINMAIL;
	}

	public static MerchantRecipe learnArmorAdvancedIron() {
		if (LEARN_ARMOR_HAT_BOOTS_IRON == null) {
			LEARN_ARMOR_HAT_BOOTS_IRON = new MerchantRecipe(Items.craftingBook(CraftingBook.ArmorChestLegsIron), 1);
			setPrice(LEARN_ARMOR_HAT_BOOTS_IRON, 240);
		}
		return LEARN_ARMOR_HAT_BOOTS_IRON;
	}

	public static MerchantRecipe learnArmorSimpleLeather() {
		if (LEARN_ARMOR_CHEST_LEGS_LEATHER == null) {
			LEARN_ARMOR_CHEST_LEGS_LEATHER = new MerchantRecipe(Items.craftingBook(CraftingBook.ArmorHatBootsLeather), 1);
			setPrice(LEARN_ARMOR_CHEST_LEGS_LEATHER, 40);
		}
		return LEARN_ARMOR_CHEST_LEGS_LEATHER;
	}

	public static MerchantRecipe learnArmorSimpleChainmail() {
		if (LEARN_ARMOR_CHEST_LEGS_CHAINMAIL == null) {
			LEARN_ARMOR_CHEST_LEGS_CHAINMAIL = new MerchantRecipe(Items.craftingBook(CraftingBook.ArmorHatBootsChainmail), 1);
			setPrice(LEARN_ARMOR_CHEST_LEGS_CHAINMAIL, 80);
		}
		return LEARN_ARMOR_CHEST_LEGS_CHAINMAIL;
	}

	public static MerchantRecipe learnArmorSimpleIron() {
		if (LEARN_ARMOR_CHEST_LEGS_IRON == null) {
			LEARN_ARMOR_CHEST_LEGS_IRON = new MerchantRecipe(Items.craftingBook(CraftingBook.ArmorHatBootsIron), 1);
			setPrice(LEARN_ARMOR_CHEST_LEGS_IRON, 160);
		}
		return LEARN_ARMOR_CHEST_LEGS_IRON;
	}

	/**
	 * Sets the components of a recipe to stacks of silver and gold coins.
	 * @param recipe Recipe to change components of
	 * @param priceInSilver Price in silver to be converted to silver and gold coins.
	 * @return A MerchantRecipe with coins as components.
	 */
	private static void setPrice(MerchantRecipe recipe, int priceInSilver) {
		int gold = priceInSilver / 64;
		int silver = priceInSilver % 64;
		ArrayList<ItemStack> ingredients = new ArrayList<ItemStack>();
		if (gold > 0) {
			ingredients.add(gold(gold));
		}
		if (silver > 0) {
			ingredients.add(silver(silver));
		}
		recipe.setIngredients(ingredients);
	}

	private static ItemStack gold(int amount) {
		ItemStack gold = Items.getGoldCoin();
		gold.setAmount(amount);
		return gold;
	}

	private static ItemStack silver(int amount) {
		ItemStack silver = Items.getSilverCoin();
		silver.setAmount(amount);
		return silver;
	}

}
