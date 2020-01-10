package io.github.the_dwellers.fyreplugin.entity;

import io.github.the_dwellers.fyreplugin.configuration.MerchantRecipes;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
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
		Merchant merchant = Bukkit.getServer().createMerchant(profession.toString());

		merchant.setRecipes(get(profession, 1, type));


		// Levels are not implemented for custom merchants in CraftBukkit
		// So we just have to make our own implementation...

		// CraftHumanEntity:588

		// IMerchant mcMerchant.setTradingPlayer((CraftHumanEntity Player).getHandle());
		// IMerchant mcMerchant.openTrade((CraftHumanEntity Player).getHandle(), name, level);


		// net.minecraft.server.IMerchant mcMerchant = (net.minecraft.server.IMerchant merchant);
		// net.minecraft.server.IChatBaseComponent name = ((net.minecraft.server.Entity) mcMerchant).getScoreboardDisplayName();
		// mcMerchant.setTradingPlayer((org.bukkit.craftbukkit.entity.CraftHumanEntity Player).getHandle());
		// mcMerchant.openTrade((org.bukkit.craftbukkit.entity.CraftHumanEntity Player).getHandle(), net.minecraft.server.IChatBaseComponent name, int level);


		player.openMerchant(merchant, true);
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
			break;
		default:
			break;
		}
		return recipes;
	}
}
