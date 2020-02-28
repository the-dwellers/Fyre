package io.github.the_dwellers.fyreplugin.features;

import java.util.List;
import java.util.Random;

import com.destroystokyo.paper.event.server.ServerTickEndEvent;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import io.github.the_dwellers.fyreplugin.FyrePlugin;
import io.github.the_dwellers.fyreplugin.configuration.SupportedVersions;
import io.github.the_dwellers.fyreplugin.util.MinecraftVersion;

/**
 * Prolong daylight by randomly delaying day advancement
 */
public class DaylightExtension implements AbstractFeature, Listener {

	public static MinecraftVersion minVersion = SupportedVersions.MIN;

	protected boolean enabled = false;
	protected static String name = "DaylightExtension";
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

	private Random rand;

	@EventHandler()
	public void onTickEndEvent(ServerTickEndEvent event) {
		if (rand == null) {
			rand = new Random();
		}

		List<World> worlds = Bukkit.getWorlds();
		for (World world : worlds) {
			if (world.isDayTime() && rand.nextInt(2) == 0) {
				world.setTime(world.getTime() - 1);
			}
		}
	}
}
