package com.github.thedwellers.fyreplugin.configuration;

import org.bukkit.entity.Villager;
import com.github.thedwellers.fyreplugin.configuration.MerchantRecipeList;

public class MerchantOperations {
	public static void setFarmerRecipies(Villager villager, String tier){
		switch(tier) {
			case "0":
				villager.setRecipes(MerchantRecipeList.farmerTier0());
				break;
		}
	}
}
