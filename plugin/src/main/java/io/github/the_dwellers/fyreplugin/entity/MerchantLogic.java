package io.github.the_dwellers.fyreplugin.entity;

import io.github.the_dwellers.fyreplugin.Reflected;
import io.github.the_dwellers.fyreplugin.configuration.MerchantRecipes;
import io.github.the_dwellers.fyreplugin.exceptions.ReflectionFailedException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager.Profession;
import org.bukkit.entity.Villager.Type;
import org.bukkit.inventory.Merchant;
import org.bukkit.inventory.MerchantRecipe;

import java.util.ArrayList;

/**
 * Merchant
 */
public abstract class MerchantLogic {

	public static void showMerchantUI(Player player, Profession profession, Type type) {
		Merchant merchant = Bukkit.getServer().createMerchant(getName(profession));

		// TODO: Replace with player's levels
		int level = 1;
		int xp = 0;

		merchant.setRecipes(get(profession, level, type));

		try {
			Reflected.showTraderUI(player, merchant, level, xp);
		} catch (ReflectionFailedException e) {
			e.printStackTrace();
		}
	}

	public static String getName(Profession profession) {
		switch (profession) {
		case ARMORER:
			return "Armorer";
		case BUTCHER:
			return "Butcher";
		case CARTOGRAPHER:
			return "Antique Seller";
		case CLERIC:
			return "Potioneer";
		case FARMER:
			return "Farming";
		case FISHERMAN:
			return "Fisherman";
		case FLETCHER:
			return "Archer";
		case LEATHERWORKER:
			return "";
		case LIBRARIAN:
			return "Researcher";
		case MASON:
			return "Stone Mason";
		case NITWIT:
			return "NitWit";
		case NONE:
			return "Villager";
		case SHEPHERD:
			return "Stableman";
		case TOOLSMITH:
			return "Toolsmith";
		case WEAPONSMITH:
			return "Swordsman";
		default:
			return "";
		}
	}

	public static ArrayList<MerchantRecipe> get(Profession profession, int level, Type type) {
		ArrayList<MerchantRecipe> recipes = new ArrayList<MerchantRecipe>();

		switch (profession) {
		case TOOLSMITH:
			switch (level) {
			case 1:
				recipes.add(MerchantRecipes.buyWoodAxe());
				recipes.add(MerchantRecipes.buyWoodSpade());
				recipes.add(MerchantRecipes.buyWoodPickaxe());
				recipes.add(MerchantRecipes.learnWoodenTools());
				break;
			default:
				break;
			}
			break;
		case MASON:
			switch (level) {
			case 2:
				switch (type) {
				case JUNGLE:
					recipes.add(MerchantRecipes.buyJungleLog());
					break;
				case PLAINS:
					recipes.add(MerchantRecipes.buyBirchLog());
					recipes.add(MerchantRecipes.buyDarkOakLog());
				case SWAMP:
					recipes.add(MerchantRecipes.buyOakLog());
					break;
				case SAVANNA:
					recipes.add(MerchantRecipes.buyAcaciaLog());
					break;
				case TAIGA:
					recipes.add(MerchantRecipes.buySpruceLog());
					break;
				case DESERT:
				case SNOW:
					break;
				default:
					break;
				}
			default:
				switch (type) {
				case JUNGLE:
					recipes.add(MerchantRecipes.sellJungleLog());
					break;
				case PLAINS:
					recipes.add(MerchantRecipes.sellBirchLog());
					recipes.add(MerchantRecipes.sellDarkOakLog());
				case SWAMP:
					recipes.add(MerchantRecipes.sellOakLog());
					break;
				case SAVANNA:
					recipes.add(MerchantRecipes.sellAcaciaLog());
					break;
				case TAIGA:
					recipes.add(MerchantRecipes.sellSpruceLog());
					break;
				case DESERT:
				case SNOW:
					break;
				default:
					break;
				}
			}
			break;
		case WEAPONSMITH:
			switch (level) {
			case 1:
				recipes.add(MerchantRecipes.buyWoodSword());
				recipes.add(MerchantRecipes.learnWoodenSword());
				break;
			default:
				break;
			}
		case NONE:
			recipes.add(MerchantRecipes.sellSplinters());
			recipes.add(MerchantRecipes.learnCraftingTable());
			break;
		case ARMORER:
			recipes.add(MerchantRecipes.sellCoal());
			recipes.add(MerchantRecipes.buyLeatherHelmet());
			recipes.add(MerchantRecipes.buyLeatherChestPlate());
			recipes.add(MerchantRecipes.buyLeatherLeggings());
			recipes.add(MerchantRecipes.buyLeatherBoots());

			recipes.add(MerchantRecipes.buyChainmailHelmet());
			recipes.add(MerchantRecipes.buyChainmailChestPlate());
			recipes.add(MerchantRecipes.buyChainmailLeggings());
			recipes.add(MerchantRecipes.buyChainmailBoots());

			recipes.add(MerchantRecipes.buyIronHelmet());
			recipes.add(MerchantRecipes.buyIronChestPlate());
			recipes.add(MerchantRecipes.buyIronLeggings());
			recipes.add(MerchantRecipes.buyIronBoots());

			break;
		default:
			break;
		}
		return recipes;
	}
}
