package io.github.the_dwellers.fyreplugin.configuration;

import io.github.the_dwellers.fyreplugin.core.MinecraftVersion;

/**
 * Versions supported by Fyre. <p>
 * Note: While Fyre will most likely work on the
 * listed versions, actual support is only provided for the most recent version.
 */
public abstract class SupportedVersions {
	/**
	 * Minecraft 1.15.2
	 */
	public static final MinecraftVersion MC1152 = new MinecraftVersion("1.15.2");

	/**
	 * Minecraft 1.15.1
	 */
	public static final MinecraftVersion MC1151 = new MinecraftVersion("1.15.1");

	/**
	 * Minecraft 1.14.4
	 */
	public static final MinecraftVersion MC1144 = new MinecraftVersion("1.14.4");

	/**
	 * Minecraft 1.13.2
	 */
	public static final MinecraftVersion MC1132 = new MinecraftVersion("1.13.2");

	/**
	 * Minimum supported MC version, refuse to load if lower than this version.<p>
	 * (Used for API-only functions)
	 */
	public static final MinecraftVersion MIN = MC1132;
}
