package io.github.the_dwellers.fyreplugin.features;

import io.github.the_dwellers.fyreplugin.core.AbstractFeature;
import io.github.the_dwellers.fyreplugin.configuration.SupportedVersions;
import io.github.the_dwellers.fyreplugin.core.MinecraftVersion;
import org.bukkit.entity.AbstractHorse;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.spigotmc.event.entity.EntityDismountEvent;

/**
 * Miscellaneous Entity AI fixes.
 *
 * @see EntityAttributes
 * @see Mobs
 */
public class AIFixes extends AbstractFeature implements Listener {

	public MinecraftVersion getMinecraftVersion() {
		return SupportedVersions.MIN;
	}

	public String getName() {
		return "AI Fixes";
	}

	public boolean setup() {
		if (isEnabled()) {
			// Enable-gate
			return isEnabled();
		}

		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		enabled = true;
		return isEnabled();
	}

	/**
	 * Disable horse AI when dismounted and not wearing a saddle.
	 * @param event {@link EntityDismountEvent}
	 */
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
