package com.github.thedwellers.fyreplugin;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

/**
 * Static class for applying standard entity and item attributes.
 */
public abstract class Attributes {

	/**
	 * Update player attributes.
	 * @param player Player to update attributes of
	 */
	public static void applyPlayer(Player player) {
		// 5 hearts of health
		player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(10);
	}

}
