package io.github.the_dwellers.fyreplugin.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class OnDamage implements Listener {

	@EventHandler()
	public void onPlayerDamage(EntityDamageByEntityEvent event) {
		if (!(event.getEntity() instanceof Player)) {
			return;
		}
		Player player = (Player) event.getEntity();

		switch (event.getDamager().getType()) {
			case ZOMBIE:
				// ? why is duration in ticks??
				player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 5 * 20, 1));
				break;
			case PHANTOM:
				player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 5 * 20, 0));
				break;
			case ENDERMAN:
				player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 10 * 20, 0));
				break;
			default:
				break;
		}

		if (player.getHealth() != 1 && event.getFinalDamage() >= player.getHealth()) {
			// One-shot protection
			event.setDamage(0);
			player.setHealth(1);
		}
	}
}
