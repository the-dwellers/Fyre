package io.github.the_dwellers.fyreplugin.events;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Player;
import org.bukkit.entity.Spider;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class OnDamage implements Listener {

	@EventHandler()
	public void onDamage(EntityDamageByEntityEvent event) {
		if (event.getEntity() instanceof Creeper) {
			// Creepers explode when hit
			((Creeper) event.getEntity()).ignite();
		}

		if (event.getEntity() instanceof Spider) {
			Block block = event.getEntity().getLocation().getBlock();
			if (block.getType() == Material.AIR) {
				block.setType(Material.COBWEB);
			}
			return;
		}

		if (event.getEntity() instanceof Player) {
			Player player = (Player) event.getEntity();

			switch (event.getDamager().getType()) {
			case ZOMBIE:
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

			// TODO: Add one-shot health threshold to config
			if (player.getHealth() < 3 && event.getFinalDamage() >= player.getHealth()) {
				// One-shot protection
				event.setDamage(0);
				player.setHealth(1);
			}
		}
	}
}
