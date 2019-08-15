package com.github.thedwellers.fyreplugin.events;

import com.github.thedwellers.fyreplugin.configuration.MerchantOperations;
import com.github.thedwellers.fyreplugin.configuration.MerchantRecipeList;
import com.github.thedwellers.fyreplugin.configuration.PlayerOperations;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class MerchantClick implements Listener {

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
					//Bukkit.broadcastMessage(PlayerOperations.getMerchantTier("BUTHC", event.getPlayer().getUniqueId().toString()));
					break;
				case CARTOGRAPHER:
					break;
				case CLERIC:
					break;
				case FARMER:
				//TODO: Define a list of recipes for each trader and the player tier
					//MerchantOperations.setFarmerRecipies(villager, PlayerOperations.getMerchantTier("FARMER", event.getPlayer().getUniqueId().toString()));
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
