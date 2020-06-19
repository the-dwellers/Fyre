package io.github.the_dwellers.fyreplugin.features;

import io.github.the_dwellers.fyreplugin.AbstractFeature;
import io.github.the_dwellers.fyreplugin.configuration.SupportedVersions;
import io.github.the_dwellers.fyreplugin.util.MinecraftVersion;
import org.bukkit.entity.AbstractHorse;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.spigotmc.event.entity.EntityDismountEvent;

/**
 * Miscellaneous Entity AI fixes. <p>
 *
 * @see EntityAttributes
 * @see Mobs
 */
public class AIFixes extends AbstractFeature implements Listener {
	@Override
	public MinecraftVersion getMinecraftVersion() {
		return SupportedVersions.MIN;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	@Override
	public String getName() {
		return "AI Fixes";
	}

	@Override
	public boolean setup() {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		enabled = true;
		return enabled;
	}

	@EventHandler()
	public void onDismount(EntityDismountEvent event) {
		if (!(event.getDismounted() instanceof AbstractHorse) || event.getEntity().getType() != EntityType.PLAYER) {
			return;
		}

		// Player has dismounted a horse
		AbstractHorse horse = (AbstractHorse) event.getDismounted();

		// Horse is wearing a saddle, disable the ai
		// horse is not wearing a saddle, enable the ai
		horse.setAI(horse.getInventory().getSaddle() == null);
	}
}
