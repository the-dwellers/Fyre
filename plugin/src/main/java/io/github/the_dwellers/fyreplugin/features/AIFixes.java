package io.github.the_dwellers.fyreplugin.features;

import org.bukkit.entity.AbstractHorse;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.spigotmc.event.entity.EntityDismountEvent;

import io.github.the_dwellers.fyreplugin.FyrePlugin;
import io.github.the_dwellers.fyreplugin.configuration.SupportedVersions;
import io.github.the_dwellers.fyreplugin.util.MinecraftVersion;

/**
 * AIFixes
 */
public class AIFixes implements AbstractFeature, Listener {

	public static MinecraftVersion minVersion = SupportedVersions.MIN;

	protected boolean enabled = false;
	protected static String name = "AIFixes";
	private static ClientBreakItem featureInstance;

	public static ClientBreakItem getInstance() {
		if (featureInstance == null) {
			featureInstance = new ClientBreakItem();
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
		return false;
	}

	@EventHandler()
	public void onDismount(EntityDismountEvent event) {
		if (!(event.getDismounted() instanceof AbstractHorse) || event.getEntity().getType() != EntityType.PLAYER) {
			return;
		}

		// Player has dismounted a horse
		AbstractHorse horse = (AbstractHorse) event.getDismounted();

		if (horse.getInventory().getSaddle() != null) {
			// Horse is wearing a saddle, disable the ai
			horse.setAI(false);
		} else {
			// horse is not wearing a saddle, enable the ai
			horse.setAI(true);
		}
	}
}
