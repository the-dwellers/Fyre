package io.github.the_dwellers.fyreplugin.features;

import io.github.the_dwellers.fyreplugin.FyrePlugin;
import io.github.the_dwellers.fyreplugin.configuration.SupportedVersions;
import io.github.the_dwellers.fyreplugin.util.MinecraftVersion;

/**
 * NBTAdapter
 */
public class NBTAdapter implements AbstractFeature {

	public static MinecraftVersion minVersion = SupportedVersions.MIN;

	protected boolean enabled = false;
	protected static String name = "NBT Features";
	private static ClientBreakItem instance;

	public static ClientBreakItem getInstance() {
		if (instance == null) {
			instance = new ClientBreakItem();
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
	public boolean setup(FyrePlugin plugin) {
		return false;
	}

	@Override
	public String getName() {
		return name;
	}

}
