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
 * @see TagDataHolder
 */
public class TagDataHolderFeature extends AbstractFeature {

	@Inject
	private NBTAdapter nbtAdapter;

	public MinecraftVersion getMinecraftVersion() {
		return nbtAdapter.getMinecraftVersion();
	}

	public String getName() {
		return "NBT TagDataHolder";
	}

	public boolean setup() {
		if (isEnabled()) {
			// Enable-gate
			return isEnabled();
		}

		if (nbtAdapter.isEnabled()) {
			enabled = true;
		} else {
			plugin.getLogger().warning(getName() + " disabled as "+ nbtAdapter.getName() + " is not loaded");
		}
		return isEnabled();
	}

}
