package com.github.thedwellers.fyreplugin.configuration;

import net.md_5.bungee.api.ChatColor;

/**
 * Strings used throughout the application
 * TODO: Populate using external configuration
 */
public abstract class Strings {

	/** Default Chat Color */
	public static final String C_DEFAULT = ChatColor.GRAY + "";
	/** Chat Muted Color */
	public static final String C_MUTED = ChatColor.DARK_GRAY + "";
	/** Chat Accept Color */
	public static final String C_ACCENT = ChatColor.GOLD + "";
	/** Prefix for all command output */
	public static final String OUT_PREFIX = ChatColor.GOLD + "> " + C_DEFAULT;

	public static final String PERMISSION_DENIED_MSG = OUT_PREFIX + ChatColor.RED + "You don't have access to that command.";
}
