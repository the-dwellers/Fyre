package io.github.the_dwellers.fyreplugin.features.tagdata;

import io.github.the_dwellers.fyreplugin.FyrePlugin;
import io.github.the_dwellers.fyreplugin.Feature;
import io.github.the_dwellers.fyreplugin.features.NBTAdapter;
import io.github.the_dwellers.fyreplugin.util.MinecraftVersion;

/**
 * Feature wrapper for {@link TagDataHolder}
 * <hr>
 * Represents any {@link Entity} that may have external data serialized to their
 * 'Tag' nbt value.
 */
public class TagDataHolderFeature extends Feature {

	public static MinecraftVersion minVersion = NBTAdapter.minVersion;

	protected boolean enabled = false;
	protected static String name = "NBT TagDataHolder";
	private static TagDataHolderFeature instance;

	public static TagDataHolderFeature getInstance() {
		if (instance == null) {
			instance = new TagDataHolderFeature();
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
		enabled = NBTAdapter.getInstance().isEnabled();
		return enabled;
	}

	@Override
	public String getName() {
		return name;
	}

}
