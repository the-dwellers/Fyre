package io.github.the_dwellers.fyreplugin.features.tagdata;

import io.github.the_dwellers.fyreplugin.FyrePlugin;
import io.github.the_dwellers.fyreplugin.Feature;
import io.github.the_dwellers.fyreplugin.features.NBTAdapter;
import io.github.the_dwellers.fyreplugin.util.MinecraftVersion;

/**
 * Feature wrapper for {@link TagInventory}
 * <hr>
 * Represents an entity that contains an inventory defined within their 'Tags'
 * nbt Tag.
 */
public class TagInventoryFeature extends Feature {

	public static MinecraftVersion minVersion = TagDataHolderFeature.minVersion;

	protected boolean enabled = false;
	protected static String name = "NBT TagInventory";
	private static TagInventoryFeature instance;

	public static TagInventoryFeature getInstance() {
		if (instance == null) {
			instance = new TagInventoryFeature();
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
		if (NBTAdapter.getInstance().isEnabled() && TagDataHolderFeature.getInstance().isEnabled()) {
			enabled = true;
		}
		return enabled;
	}

	@Override
	public String getName() {
		return name;
	}
}
