package com.github.thedwellers.fyreplugin.events;

import com.github.thedwellers.fyreplugin.configuration.MerchantRecipeList;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class MerchantClick extends AbstractEvent {
	public MerchantClick(JavaPlugin plugin) {
		super(plugin);
	}

	@EventHandler()
	public void onPlayerInteractEntityEvent(PlayerInteractEntityEvent event) {
		Entity entity = event.getRightClicked();
			
		//sets trade options depending on villager profession and use tier
		//TODO: get player tier for profession and set recipies.
		if(entity.getType().equals(EntityType.VILLAGER)) {
			Villager villager = (Villager) entity;
			
			switch(villager.getProfession()) {
				case ARMORER:

					break;
				case BUTCHER:
					break;
				case CARTOGRAPHER:
					break;
				case CLERIC:
					break;
				case FARMER:
				//TODO: Define a list of recipes for each trader and the player tier
					villager.setRecipes(MerchantRecipeList.farmerTier1());
					break;
				case FISHERMAN:
					break;
				case FLETCHER:
					break;
				case LEATHERWORKER:
					break;
				case LIBRARIAN:
					break;
				case MASON:
					break;
				case SHEPHERD:
					break;
				case TOOLSMITH:
					break;
				case WEAPONSMITH:
					break;
				case NITWIT:
					break;
				case NONE:
					break;
			}
		}
	}
}
