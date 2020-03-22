package io.github.the_dwellers.fyreplugin.features;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import io.github.the_dwellers.fyreplugin.Feature;
import io.github.the_dwellers.fyreplugin.FyrePlugin;
import io.github.the_dwellers.fyreplugin.Reflected;
import io.github.the_dwellers.fyreplugin.configuration.SupportedVersions;
import io.github.the_dwellers.fyreplugin.util.MinecraftVersion;

/**
 * Add additional items to the composter
 */
public class Compost extends Feature {

	public static MinecraftVersion minVersion = SupportedVersions.MC1144;

	protected boolean enabled = false;
	protected static String name = "Extended Compost";
	private static Compost instance;

	public static Compost getInstance() {
		if (instance == null) {
			instance = new Compost();
		}
		return instance;
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

		if (Reflected.getClass("Items") == null) {
			if (!Reflected.cacheClass(Reflected.nmsClass + "Items")) {
				plugin.getLogger().warning("Unable to Cache Items");
				return false;
			}
		}

		if (Reflected.getClass("IMaterial") == null) {
			if (!Reflected.cacheClass(Reflected.nmsClass + "IMaterial")) {
				plugin.getLogger().warning("Unable to Cache IMaterial");
				return false;
			}
		}

		if (Reflected.getClass("BlockComposter") == null) {
			if (!Reflected.cacheClass(Reflected.nmsClass + "BlockComposter")) {
				plugin.getLogger().warning("Unable to Cache BlockComposter");
				return false;
			}
		}

		if (Reflected.getMethod("BlockComposter#add") == null) {
			if (!Reflected.cacheDeclaredMethod("a", "BlockComposter#add", Reflected.getClass("BlockComposter"), float.class, Reflected.getClass("IMaterial"))) {
				plugin.getLogger().warning("Unable to Cache BlockComposter#add");
				return false;
			}
		}

		try {
			// Get the ROTTEN_FLESH Item from the Items class
			Field itemsRottenFleshField = Reflected.getClass("Items").getField("ROTTEN_FLESH");

			// Get the add method from the BlockComposter class
			Method a = Reflected.getMethod("BlockComposter#add");

			// Execute the add method using the ROTTEN_FLESH item
			a.invoke(Reflected.getClass("BlockComposter"), 0.5F,
					itemsRottenFleshField.get(Reflected.getClass("Items")));
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchFieldException
				| SecurityException e) {
			plugin.getLogger().warning("Unable to Add Items To Composter: " + e.getMessage());
			return false;
		}
		return true;
	}
}