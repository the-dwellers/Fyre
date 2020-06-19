package io.github.the_dwellers.fyreplugin.features;

import io.github.the_dwellers.fyreplugin.core.AbstractFeature;
import io.github.the_dwellers.fyreplugin.core.Reflected;
import io.github.the_dwellers.fyreplugin.exceptions.ReflectionFailedException;
import io.github.the_dwellers.fyreplugin.configuration.SupportedVersions;
import io.github.the_dwellers.fyreplugin.core.MinecraftVersion;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Add additional items to the composter.
 */
public class Compost extends AbstractFeature {

	public MinecraftVersion getMinecraftVersion() {
		return SupportedVersions.MC1144;
	}

	public String getName() {
		return "Extended Compost";
	}

	public boolean setup() {
		if (isEnabled()) {
			// Enable-gate
			return isEnabled();
		}

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
			if (!Reflected.cacheDeclaredMethod("a", "BlockComposter#add", Reflected.getClass("BlockComposter"),
					float.class, Reflected.getClass("IMaterial"))) {
				plugin.getLogger().warning("Unable to Cache BlockComposter#add");
				return false;
			}
		}

		try {
			addToCompost("ROTTEN_FLESH", 0.5F);
		} catch (ReflectionFailedException e) {
			plugin.getLogger().warning("Unable to Add Items To Composter: " + e.getMessage());
			return false;
		}
		return true;
	}

	/**
	 * Add a NMS item to the composters list of acceptable items.
	 * <p>
	 * Due to the dependence on reflection, this method may fail entirely depending
	 * on the Minecraft version.
	 *
	 * <pre>
	 * net.minecraft.server.BlockComposter.add(minecraft.server.Items.getField(String nmsItemEnum), amount)
	 * </pre>
	 *
	 * @param nmsItemEnum
	 * @param amount
	 * @throws ReflectionFailedException
	 * @see {@link https://minecraft.gamepedia.com/Composter}
	 */
	private static void addToCompost(String nmsItemEnum, float amount) throws ReflectionFailedException {
		try {
			// Get the NMS Item Enum from the Items class
			Field itemNMSItemEnumField = Reflected.getClass("Items").getField(nmsItemEnum);

			// Get the add method from the BlockComposter class
			Method a = Reflected.getMethod("BlockComposter#add");

			// Execute the add method using the NMS item
			a.invoke(Reflected.getClass("BlockComposter"), amount,
					itemNMSItemEnumField.get(Reflected.getClass("Items")));

		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchFieldException
				| SecurityException e) {
			throw new ReflectionFailedException(e);
		}
	}
}
