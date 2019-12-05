package com.github.thedwellers.fyreplugin.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.potion.PotionEffectType;

/**
 * PlayerDeath
 */

public class PlayerDeath implements Listener{

	@EventHandler()
	public void onPlayerDeath(PlayerDeathEvent event) {
		if (!(event.getEntity().getLastDamageCause() instanceof EntityDamageByEntityEvent)) {
			return;
		}

		EntityDamageByEntityEvent lastDamage = (EntityDamageByEntityEvent) event.getEntity().getLastDamageCause();
		if (lastDamage.getDamager() instanceof Player) {
			if (((Player) lastDamage.getDamager()).hasPotionEffect(PotionEffectType.INVISIBILITY)) {
				event.setDeathMessage(event.getEntity().getDisplayName() + " was killed by something invisible");
			}
		}
	}
}
