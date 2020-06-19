package io.github.the_dwellers.fyreplugin.features;

import io.github.the_dwellers.fyreplugin.core.AbstractFeature;
import io.github.the_dwellers.fyreplugin.core.Reflected;
import io.github.the_dwellers.fyreplugin.configuration.SupportedVersions;
import io.github.the_dwellers.fyreplugin.core.MinecraftVersion;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Add additional items to the composter.
 */
public class Compost extends AbstractFeature {
	@Override
	public MinecraftVersion getMinecraftVersion() {
		return SupportedVersions.MC1144;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	@Override
	public String getName() {
		return "Extended Compost";
	}

	@Override
	public boolean setup() {

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
