package io.github.the_dwellers.fyreplugin.features;

import io.github.the_dwellers.fyreplugin.Feature;
import io.github.the_dwellers.fyreplugin.FyrePlugin;
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
public class AIFixes extends Feature implements Listener {

	public static MinecraftVersion minVersion = SupportedVersions.MIN;
	protected static String name = "AI Fixes";
	private static AIFixes featureInstance;
	protected boolean enabled = false;

	public static AIFixes getInstance() {
		if (featureInstance == null) {
			featureInstance = new AIFixes();
		}
		return featureInstance;
	}

	@Override
	public MinecraftVersion getMinecraftVersion() {
		return minVersion;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public boolean setup(FyrePlugin plugin) {
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
