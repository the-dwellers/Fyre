package io.github.the_dwellers.fyreplugin.features;

import com.destroystokyo.paper.event.server.ServerTickEndEvent;
import io.github.the_dwellers.fyreplugin.Feature;
import io.github.the_dwellers.fyreplugin.FyrePlugin;
import io.github.the_dwellers.fyreplugin.configuration.SupportedVersions;
import io.github.the_dwellers.fyreplugin.util.MinecraftVersion;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.List;
import java.util.Random;

/**
 * Prolong daylight by randomly delaying day advancement.
 * <p>
 * Note: Currently there is a problem with day reversal.
 */
public class DaylightExtension extends Feature implements Listener {

	public static MinecraftVersion minVersion = SupportedVersions.MIN;
	protected static String name = "Daylight Extension";
	private static DaylightExtension featureInstance;
	protected boolean enabled = false;
	private Random rand;

	public static DaylightExtension getInstance() {
		if (featureInstance == null) {
			featureInstance = new DaylightExtension();
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
		// ! Bug in onTickEndEvent(), causes crazy day progression
		// plugin.getServer().getPluginManager().registerEvents(this, plugin);
		// enabled = true;
		// return isEnabled();
	}

	@EventHandler()
	public void onTickEndEvent(ServerTickEndEvent event) {
		if (rand == null) {
			rand = new Random();
		}

		List<World> worlds = Bukkit.getWorlds();
		for (World world : worlds) {
			if (world.isDayTime() && rand.nextInt(2) == 0) {
				// ! Setting a time negative from the current time advances the day forward!
				// ! Thus breaking any mechanic related to days passed.
				world.setTime(world.getTime() - 1);
			}
		}
	}
}
