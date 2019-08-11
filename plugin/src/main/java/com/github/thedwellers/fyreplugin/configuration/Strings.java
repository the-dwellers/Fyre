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
	/** Chat Error Color */
	public static final String C_ERROR = ChatColor.RED + "";
	/** Prefix for all command output */
	public static final String OUT_PREFIX = ChatColor.GOLD + "> " + C_DEFAULT;

	/** Player has no permission to use a command */
	public static final String PERMISSION_DENIED_MSG = OUT_PREFIX + ChatColor.RED + "You don't have access to that command.";

	/** Player has no item in their hands */
	public static final String NO_ITEM_HELD = OUT_PREFIX + C_ERROR + "There is no item in your hands";
	/** Player is not wearing any armor */
	public static final String NO_ITEM_WORN = OUT_PREFIX + C_ERROR + "You are not wearing any armor";

}
