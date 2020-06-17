package io.github.the_dwellers.fyreplugin;

import io.github.the_dwellers.fyreplugin.util.MinecraftVersion;

/**
 * Represents a feature provided by the FyrePlugin. Features include event
 * handlers, net.minecraft.server hooks, and commands. They have a minimum
 * required Minecraft version and will setup depending on this version. They
 * should assume that other features are not available, and be self-contained.
 *
 * @see MinecraftVersion
 */
public class Feature {

	protected boolean enabled = false;

	public static Feature getInstance() {
		return null;
	}

	public MinecraftVersion getMinecraftVersion() {
		return new MinecraftVersion("0.0.0");
	}

	/**
	 * Setup and start the feature. It is assumed that the version has already been
	 * checked and is compatible. Any errors encountered will output to the log, and
	 * disable the feature.
	 *
	 * @param plugin FyrePlugin to handle registrations.
	 * @return True if registration succeeded without errors.
	 */
	public boolean setup(FyrePlugin plugin) {
		return false;
	}

	/**
	 * Is the feature setup and ready for use?
	 *
	 * @return True if feature is enabled.
	 */
	public boolean isEnabled() {
		return enabled;
	}

	public String getName() {
		return null;
	}
}
