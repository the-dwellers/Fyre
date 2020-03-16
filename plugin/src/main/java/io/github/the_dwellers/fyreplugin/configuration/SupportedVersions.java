package io.github.the_dwellers.fyreplugin.configuration;

import io.github.the_dwellers.fyreplugin.util.MinecraftVersion;

public abstract class SupportedVersions {
	public static final MinecraftVersion MC1152 = new MinecraftVersion("1.15.2");
	public static final MinecraftVersion MC1151 = new MinecraftVersion("1.15.1");
	public static final MinecraftVersion MC1144 = new MinecraftVersion("1.14.4");
	public static final MinecraftVersion MC1132 = new MinecraftVersion("1.13.2");
	// Minimum supported MC version, refuse to load if lower than this version.
	public static final MinecraftVersion MIN = MC1132;
}
