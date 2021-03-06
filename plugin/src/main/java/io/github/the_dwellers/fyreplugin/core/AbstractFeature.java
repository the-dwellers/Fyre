package io.github.the_dwellers.fyreplugin.core;

import io.github.the_dwellers.fyreplugin.FyrePlugin;

import javax.inject.Inject;

/**
 * Represents a feature provided by the FyrePlugin. Features include event
 * handlers, net.minecraft.server hooks, and commands. They have a minimum
 * required Minecraft version and will setup depending on this version. They
 * should assume that other features are not available, and be self-contained.
 *
 * @see MinecraftVersion
 */
public abstract class AbstractFeature {
	@Inject
	protected FyrePlugin plugin;

	protected boolean enabled = false;

	/**
	 * @return The minimum version of minecraft this feature will work on. It is assumed that any higher version is compatible.
	 */
	public abstract MinecraftVersion getMinecraftVersion();

		/**
	 * Is the feature setup and ready for use?
	 *
	 * @return True if feature is enabled.
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * @return The display name of the feature.
	 */
	public abstract String getName();

	/**
	 * Setup and start the feature. It is assumed that the version has already been
	 * checked and is compatible. Any errors encountered will output to the log, and
	 * disable the feature.
	 *
	 * @return True if registration succeeded without errors.
	 */
	public abstract boolean setup();

}
