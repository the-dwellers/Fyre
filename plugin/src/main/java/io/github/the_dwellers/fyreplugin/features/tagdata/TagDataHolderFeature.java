package io.github.the_dwellers.fyreplugin.features.tagdata;

import io.github.the_dwellers.fyreplugin.core.AbstractFeature;
import io.github.the_dwellers.fyreplugin.features.NBTAdapter;
import io.github.the_dwellers.fyreplugin.core.MinecraftVersion;

import javax.inject.Inject;

/**
 * Feature wrapper for {@link TagDataHolder}
 * <hr>
 * Represents any {@link Entity} that may have external data serialized to their
 * 'Tag' nbt value.
 */
public class TagDataHolderFeature extends AbstractFeature {
	@Inject
	private NBTAdapter nbtAdapter;

	@Override
	public MinecraftVersion getMinecraftVersion() {
		return nbtAdapter.getMinecraftVersion();
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	@Override
	public boolean setup() {
		enabled = nbtAdapter.isEnabled();
		return enabled;
	}

	@Override
	public String getName() {
		return "NBT TagDataHolder";
	}

}
