package io.github.the_dwellers.fyreplugin.configuration;

import net.md_5.bungee.api.ChatColor;

/**
 * Strings used throughout the application
 * TODO: Populate using external configuration
 */
public abstract class Strings {

	/**
	 * Default Chat Color
	 */
	public static final String C_DEFAULT = ChatColor.GRAY + "";
	/**
	 * Chat Muted Color
	 */
	public static final String C_MUTED = ChatColor.DARK_GRAY + "";
	/**
	 * Chat Accent Color
	 */
	public static final String C_ACCENT = ChatColor.GOLD + "";
	/**
	 * Chat Error Color
	 */
	public static final String C_ERROR = ChatColor.RED + "";
	/**
	 * Chat Positive Color
	 */
	public static final String C_POSITIVE = ChatColor.GREEN + "";

	/**
	 * Prefix for all command output
	 */
	public static final String OUT_PREFIX = ChatColor.GOLD + "> " + C_DEFAULT;

	/**
	 * Default Player Chat Prefix
	 */
	public static final String CHAT_PREFIX = ChatColor.DARK_GREEN + "";
	/**
	 * Default Player Chat Suffix
	 */
	public static final String CHAT_SUFFIX = "";
	/**
	 * Chat Player Name and Message Divider
	 */
	public static final String CHAT_DIVIDER = ChatColor.RESET + ": ";

	/**
	 * Player has no item in their hands
	 */
	public static final String NO_ITEM_HELD = OUT_PREFIX + C_ERROR + "There is no item in your hands";
	/**
	 * Player is not wearing any armor
	 */
	public static final String NO_ITEM_WORN = OUT_PREFIX + C_ERROR + "You are not wearing any armor";

	/**
	 * Player is not allowed to use the command
	 */
	public static final String NO_PERMISSION_COMMAND = OUT_PREFIX + C_ERROR + "You are not allowed to use that command";

	/**
	 * Prefix for server event messages
	 */
	public static final String SERVER_OUT = ChatColor.GOLD + ">" + ChatColor.YELLOW + " ";

	/**
	 * Join Message. %1 is the player's name
	 */
	public static final String JOIN_MESSAGE = SERVER_OUT + "%1 has joined the server";
	/**
	 * First Join Message. %1 is the player's name
	 */
	public static final String FIRST_JOIN_MESSAGE = SERVER_OUT + ChatColor.LIGHT_PURPLE + "Welcome %1 To Fyre for the first time!";
	/**
	 * Leave Message. %1 is the player's name
	 */
	public static final String LEAVE_MESSAGE = SERVER_OUT + "%1 has left the game";

	/**
	 * Default color for usage messages
	 */
	public static final String USAGE_DEFAULT = C_DEFAULT;
	/**
	 * Color for required usage arguments
	 */
	public static final String USAGE_REQUIRED = ChatColor.GREEN + "";
	/**
	 * Color for optional usage arguments
	 */
	public static final String USAGE_OPTIONAL = ChatColor.AQUA + "";
	/**
	 * Color for notification messages sent to staff members
	 * (Examples include; connecting ip addresses)
	 */
	public static final String CHAT_STAFF_MSG = ChatColor.GRAY + "> ";

}
