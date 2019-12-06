package com.github.thedwellers.fyreplugin.events;

import com.github.thedwellers.fyreplugin.entity.MerchantLogic;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

/**
 * TraderClick
 */
public class TraderClick implements Listener {

	@EventHandler()
	public void onTraderClick(PlayerInteractEntityEvent event) {
		if (event.getRightClicked().getType() != EntityType.VILLAGER) {
			return;
		}
		event.setCancelled(true);
		Villager villager = (Villager) event.getRightClicked();
		MerchantLogic.showMerchantUI(event.getPlayer(), villager.getProfession());
	}
}
