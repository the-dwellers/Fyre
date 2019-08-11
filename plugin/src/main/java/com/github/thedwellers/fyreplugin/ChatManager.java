package com.github.thedwellers.fyreplugin;

import java.util.ArrayList;

import com.github.thedwellers.fyreplugin.configuration.Strings;
import com.github.thedwellers.fyreplugin.exceptions.ReflectionFailedException;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.HoverEvent.Action;
import net.milkbowl.vault.chat.Chat;

/**
 * Functions dedicated to chat manipulation and formatting.
 */
public abstract class ChatManager {

	/**
	 * Send the provided {@link TextComponent} to all players on the server as the
	 * provided player. This is the default method for sending all chat messages.
	 * <p>
	 * This function will automatically add the required prefixes and suffixes to the player's name.
	 * This is done vua Vault, or by using the default in-built configuration.
	 * <p>
	 * For parsing of a players message, such as resolving {@code @hand}, use {@link ChatManager#sendPlayerMessage(Player,String)}
	 * @param player Sender of the message
	 * @param message TextComponent of the message to send. No Additional parsing will be performed on the message.
	 */
	public static void sendPlayerMessage(Player player, TextComponent message) {
		// Master text component
		TextComponent text = new TextComponent();

		// Attempt to get chat manager, or information provided by vault
		Chat vaultChat = FyrePlugin.getInstance().getVaultChat();

		// Setup prefix and suffix defaults
		String prefix = "" + ChatColor.DARK_GREEN;
		String suffix = "";

		if (vaultChat != null) {
			// If vault provides a chat manager, then apply the suffix and prefix
			prefix = vaultChat.getPlayerPrefix(player);
			suffix = vaultChat.getPlayerSuffix(player);
		}

		// Construct text message
		// USERNAME: message
		text.addExtra(prefix);
		text.addExtra(player.getDisplayName());
		text.addExtra(suffix);
		text.addExtra(ChatColor.RESET + ": ");
		text.addExtra(message);

		// Send to all players and console
		Bukkit.broadcast(text);
		Bukkit.getConsoleSender().sendMessage(text);
	}

	/**
	 * Parses the provided string and returns a correctly formatted TextComponent.
	 * This text component will contain additional information, such as items resolved from {@code @} notation within the message.
	 * <p>
	 * If any notations fail, a message is sent to the player, and the notation is replaced by a placeholder.
	 * @param player Sender of the message. Will receive any error messages.
	 * @param message Message sent by the sender. This will be parsed.
	 * @return Returns a constructed {@link TextComponent} with formatted {@code @} notations and any other parsing results
	 */
	private static TextComponent parseTextMessage(Player player, String message) {
		// Text Components keeps a rolling list of constructs to be added together at the end
		ArrayList<TextComponent> textComponents = new ArrayList<TextComponent>();
		// Convert the message to a character array for easier token-checking
		char[] chars = message.toCharArray();

		TextComponent currentText = new TextComponent();

		// flag to state if current cursor is within a notated area
		boolean inAt = false;
		// @ notations cannot be larger than 10 characters long. Therefore use this size to construct
		// an array to store the current notation
		char[] atArray = new char[10];
		int atArrayCount = 0;

		// O(n) efficiency
		for (int i = 0; i < chars.length; i++) {
			// Loop each individual character of the message
			boolean last = i == chars.length - 1;
			char letter = chars[i];

			// @ notation can be one of three different types
			// @hand
			// @offhand
			// @armor
			// If any of these sequences are matched, the text is replaced with item
			// representations
			// If an invalid @ is present, it is printed normally
			if (!inAt && !last && letter == '@') {
				// At notation started, and not at the end of the message

				// Duplicate the current text component and add it to the list
				textComponents.add((TextComponent) currentText.duplicate());

				// Reset text component
				currentText = new TextComponent(ChatColor.RESET+" ");

				// Mark current region
				inAt = true;
				continue;
			} else if (inAt && letter != ' ') {
				// in @ region and character isn't terminator
				// Continue adding to current @ notation
				atArray[atArrayCount++] = letter;
			}
			if (inAt && (letter == ' ' || last || atArray[9] != 0)) {
				// Region has ended, either due to size, message, or reached array limits

				String arrayString = String.valueOf(atArray, 0, atArrayCount);
				// Construct the stored array into a string for easier comparison

				if (arrayString.equals("hand")) {
					// @hand
					// This displays the currently held item. Attempts MainHand and then offhand

					TextComponent hand = getItemText(getDisplayStackMainHand(player));

					if (hand == null) {
						// No item in main hand
						TextComponent offhand = getItemText(getDisplayStackOffHand(player));
						if (offhand == null) {
							// No item in offhand
							player.sendMessage(Strings.NO_ITEM_HELD);
							textComponents.add(new TextComponent(ChatColor.RED + "[Nothing]"));
						} else {
							// Item in offhand
							textComponents.add((TextComponent) offhand.duplicate());
						}
					} else {
						// Item in MainHand
						textComponents.add(hand);
					}

				} else if (arrayString.equals("offhand")) {
					// @offhand
					// This displays the currently held item in the offhand.
					TextComponent offhand = getItemText(getDisplayStackOffHand(player));
					if (offhand == null) {
						// No item in offhand
							player.sendMessage(Strings.NO_ITEM_HELD);
							textComponents.add(new TextComponent(ChatColor.RED + "[Nothing]"));
					} else {
						// Item in offhand
						textComponents.add(offhand);
					}
				} else if (arrayString.equals("armor")) {
					// @armor
					// Prints a list of all currently equipped armor pieces by the player
					// Only fails if no armor is worn. Does not check hands
					TextComponent armor = getArmourText(player);
					if (armor == null) {
						// No armor worn
						player.sendMessage(Strings.NO_ITEM_WORN);
						textComponents.add(new TextComponent(ChatColor.RED + "[Nothing]"));
					} else {
						// Armor worn
						textComponents.add(armor);
					}
				} else {
					// Either we're at the end of the message with an invalid @,
					// or the notation is too long.
					// Print out the array to the message (ignore it)
					currentText.addExtra(new TextComponent("@" + arrayString));
				}

				// We've reached the end of the @ region, reset variables
				inAt = false;
				atArray = new char[10];
				atArrayCount = 0;
				continue;
			}
			if (inAt) {
				// Still in the notation region. Do not continue
				continue;
			}

			// Not inside any region, append the character to the current text
			currentText.addExtra(String.valueOf(chars[i]));
		}

		// Add any leftover text
		textComponents.add(currentText);

		// Merge all text into one text component
		TextComponent finalText = new TextComponent();
		for (TextComponent text : textComponents) {
			finalText.addExtra(text);
		}

		return finalText;
	}

	/**
	 * Sends the player's message to all chat after parsing via {@link ChatManager#parseTextMessage(Player, String)}
	 * @see ChatManager#sendPlayerMessage(Player, TextComponent)
	 * @see ChatManager#parseTextMessage(Player, String)
	 * @param player Player to send message as
	 * @param message Message to send (will be parsed)
	 */
	public static void sendPlayerMessage(Player player, String message) {
		sendPlayerMessage(player, parseTextMessage(player, message));
	}

	/**
	 * Get the {@link TextComponent} representing the provided {@link ItemStack}
	 * If the provided {@link ItemStack} is Air, or invalid, {@code null} is returned.
	 * <p>
	 * Supports stacks larger than 64
	 * <p>
	 * If any reflection issues are encountered, {@code TextComponent(ChatColor.RED + "[Invalid Item!]")} is returned.
	 * @param item Item to represent as a TextComponent
	 * @return Returns {@code null} if the item is air or invalid. Otherwise a TextComponent representing the item
	 */
	public static TextComponent getItemText(ItemStack item) {
		if (item == null || item.getType() == Material.AIR) {
			// No ItemStack
			return null;
		}

		try {
			String nbt = Reflected.stackToJson(item);
			TextComponent text;

			String itemName = item.getItemMeta().hasDisplayName() ? item.getItemMeta().getDisplayName()
					: item.getI18NDisplayName();

			// Check to see if the item has a custom colored name. This includes enchanted
			// and renamed items
			boolean customItem = itemName.toCharArray()[0] == '\u00a7' && itemName.toCharArray()[1] != 'o';

			// Default to a light green color
			String color = "\u00a7a";

			if (customItem) {
				// Item has a custom name / color
				color = String.valueOf(new char[] { '\u00a7', itemName.toCharArray()[1] });
			} else {
				// If the item is not a custom item, then display the default name to chat,
				// rather than the rename
				itemName = item.getI18NDisplayName();
			}

			if (item.getAmount() == 1) {
				// Singular item
				text = new TextComponent(color + "[" + itemName + color + "]");
			} else {
				// Multiple items
				text = new TextComponent(color + "[" + item.getAmount() + "x " + itemName + color + "]");
			}

			text.setHoverEvent(new HoverEvent(Action.SHOW_ITEM, new TextComponent[] { new TextComponent(nbt) }));
			return text;

		} catch (ReflectionFailedException e) {
			// Encountered an issue during reflection
			return new TextComponent(ChatColor.RED + "[Invalid Item!]");
		}
	}

	/**
	 * Obtains the ItemStack of the player's Main Hand item. This includes a quantity of all items
	 * exactly like the held item in the player's inventory.
	 * @param player Player to get main hand item of
	 * @return ItemStack of player's main hand, with a quantity of all items of the same kind in the player's inventory
	 * @see ChatManager#getDisplayStackOffHand(Player)
	 * @see ChatManager#getArmourText(Player)
	 */
	public static ItemStack getDisplayStackMainHand(Player player) {
		ItemStack item = player.getInventory().getItemInMainHand();
		int amount = 0;

		for (ItemStack stack : player.getInventory().getContents()) {
			if (stack != null && stack.isSimilar(item)) {
				amount += stack.getAmount();
			}
		}

		ItemStack newItem = item.clone();
		newItem.setAmount(amount);
		return newItem;
	}

	/**
	 * Returns a TextComponent representing all the worn armor of the player. Quite long!
	 * <p>
	 * If the player is not wearing any armor, {@code null} is returned
	 * @param player Player to obtain armor from
	 * @return Returns {@code null} if the player is not wearing armor. Otherwise returns a TextComponent of all worn items
	 * @see ChatManager#getItemText(ItemStack)
	 * @see ChatManager#getDisplayStackMainHand(Player)
	 * @see ChatManager#getDisplayStackOffHand(Player)
	 */
	public static TextComponent getArmourText(Player player) {
		TextComponent armorText = new TextComponent();
		ItemStack[] armor = player.getInventory().getArmorContents();
		boolean isFirst = true;
		for (ItemStack item : armor) {
			if (item != null) {
				if (!isFirst) {
					armorText.addExtra(new TextComponent(" "));
				}
				isFirst=false;
				armorText.addExtra(getItemText(item));
			}
		}
		if (isFirst) {
			// No item was added, return null
			return null;
		}
		return armorText;
	}

	/**
	 * Obtains the ItemStack of the player's Off-Hand item. This includes a quantity of all items
	 * exactly like the held item in the player's inventory.
	 * @param player Player to get off-hand item of
	 * @return ItemStack of player's off-hand, with a quantity of all items of the same kind in the player's inventory
	 * @see ChatManager#getDisplayStackMainHand(Player)
	 * @see ChatManager#getArmourText(Player)
	 */
	public static ItemStack getDisplayStackOffHand(Player player) {
		ItemStack item = player.getInventory().getItemInOffHand();

		int amount = 0;

		for (ItemStack stack : player.getInventory().getContents()) {
			if (stack != null && stack.isSimilar(item)) {
				amount += stack.getAmount();
			}
		}

		ItemStack newItem = item.clone();
		newItem.setAmount(amount);
		return newItem;
	}
}
