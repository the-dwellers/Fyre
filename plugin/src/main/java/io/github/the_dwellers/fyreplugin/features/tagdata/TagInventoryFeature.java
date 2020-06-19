package io.github.the_dwellers.fyreplugin.features.tagdata;

import io.github.the_dwellers.fyreplugin.core.AbstractFeature;
import io.github.the_dwellers.fyreplugin.core.MinecraftVersion;

import javax.inject.Inject;

/**
 * Feature wrapper for {@link TagInventory}
 * <hr>
 * Represents an entity that contains an inventory defined within their 'Tags'
 * nbt Tag.
 * @see TagInventory
 */
public class TagInventoryFeature extends AbstractFeature {

	@Inject
	private TagDataHolderFeature tagDataHolderFeature;

	public MinecraftVersion getMinecraftVersion() {
		return tagDataHolderFeature.getMinecraftVersion();
	}

	public String getName() {
		return "NBT TagInventory";
	}

	public boolean setup() {
		if (isEnabled()) {
			// Enable-gate
			return isEnabled();
		}

		if (tagDataHolderFeature.isEnabled()) {
			enabled = true;
		} else {
			plugin.getLogger().warning(getName() + " disabled as "+ tagDataHolderFeature.getName() + " is not loaded");
		}
		return isEnabled();
	}

}
