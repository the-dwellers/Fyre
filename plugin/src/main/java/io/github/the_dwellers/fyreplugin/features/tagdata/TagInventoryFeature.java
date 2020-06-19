package io.github.the_dwellers.fyreplugin.features.tagdata;

import io.github.the_dwellers.fyreplugin.core.AbstractFeature;
import io.github.the_dwellers.fyreplugin.core.MinecraftVersion;

import javax.inject.Inject;

/**
 * Feature wrapper for {@link TagInventory}
 * <hr>
 * Represents an entity that contains an inventory defined within their 'Tags'
 * nbt Tag.
 */
public class TagInventoryFeature extends AbstractFeature {
	@Inject
	private TagDataHolderFeature tagDataHolderFeature;

	@Override
	public MinecraftVersion getMinecraftVersion() {
		return tagDataHolderFeature.getMinecraftVersion();
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	@Override
	public boolean setup() {
		if (tagDataHolderFeature.isEnabled()) {
			enabled = true;
		}
		return enabled;
	}

	@Override
	public String getName() {
		return "NBT TagInventory";
	}
}
