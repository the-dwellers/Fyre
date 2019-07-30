package com.github.thedwellers.fyreplugin.configuration;

/**
 * Class to hold all permissions related to this plugin.
 * Permissions control what players can and can't do.
 * @see Strings
 */
public abstract class Permissions {
	private static final String BASE = "fyre.";

	public static final String BANK = BASE + "bank";
	public static final String LIST = BASE + "list";
	public static final String MERCHANT = BASE + "merchant";
	public static final String PLUGIN = BASE + "plugin";
	public static final String STATUS = BASE + "status";
}
