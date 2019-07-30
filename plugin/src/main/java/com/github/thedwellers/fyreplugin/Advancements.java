package com.github.thedwellers.fyreplugin;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Statistic;
import org.bukkit.advancement.Advancement;
import org.bukkit.advancement.AdvancementProgress;
import org.bukkit.entity.Player;

/**
 * Handles advancement functions
 */
public class Advancements {

	/**
	 * Awards the provided player any advancements they are entitled to.
	 * This only applies plugin-controlled advancements in the fyre datapack.
	 * Check the documentation for advancements and their types.
	 * @param player
	 */
	public static void updateAdvancements(Player player){
		// Mining

		// Volcano Stack
		awardBlockBreak(player, Material.ANDESITE, 64, "mining/volcano-stack");

		// Andesine Container
		awardBlockBreak(player, Material.DIORITE, 64, "mining/andesine-container");

		// Intrusive Rock
		awardBlockBreak(player, Material.GRANITE, 64, "mining/intrusive-rock");

		// Fossil Fuels
		awardBlockBreak(player, Material.COAL_ORE, 1, "mining/fossil-fuels");

		// Dead Plant Collector
		awardBlockBreak(player, Material.COAL_ORE, 64, "mining/dead-plant-collector");

		// Climate Changer
		awardBlockBreak(player, Material.COAL_ORE, 1728, "mining/climate-changer");

		// Took you Long Enough
		awardBlockBreak(player, Material.STONE, 1, "mining/took-you-long-enough");

		// Stack'o Cobble
		awardBlockBreak(player, Material.STONE, 64, "mining/stacko-cobble");

		// Red-Bearded Dwarf
		awardBlockBreak(player, Material.STONE, 1728, "mining/red-bearded-dwarf");

		// No Longer in the Woods
		awardBlockBreak(player, Material.LAPIS_ORE, 1, "mining/no-longer-in-the-woods");

		// Rusty Stone
		awardBlockBreak(player, Material.IRON_ORE, 1, "mining/rusty-stone");

		// Magnetite and Hematite
		awardBlockBreak(player, Material.IRON_ORE, 1728, "mining/magnetite-and-hematite");

		// Kingmaker
		awardBlockBreak(player, Material.GOLD_ORE, 1, "mining/kingmaker");

		// Useless Material
		awardBlockBreak(player, Material.DIAMOND_ORE, 1, "mining/useless-material");

		// A Small loan
		awardBlockBreak(player, Material.GOLD_ORE, 1728, "mining/a-small-loan");
	}

	/**
	 * Awards the advancement if the player has broken equal or over the amount
	 * of blocks. Does not provide the advancement if the player already has it.
	 * @param player player to check
	 * @param block block broken
	 * @param amount amount of blocks broken
	 * @param advancement advancement to award
	 */
	@SuppressWarnings("deprecation")
	private static void awardBlockBreak(Player player, Material block, int amount, String advancement) {
		// NamespacedKey is deprecated, however there is no other alternative way to do this in O(1)
		// The only other alternative would be looping over *all* advancements in the game!
		Advancement adv = player.getServer().getAdvancement(new NamespacedKey("fyre", advancement));
		AdvancementProgress progress = player.getAdvancementProgress(adv);
		if (progress.isDone()) {
			// Player already has advancement
			return;
		}

		if (player.getStatistic(Statistic.MINE_BLOCK, block) >= amount-1) {
			// ? If anyone knows a shorter way to convert to an array while
			// ? conserving type, please message me - WYVERN
			progress.awardCriteria(progress.getRemainingCriteria().toArray(new String[progress.getRemainingCriteria().size()])[0]);
		}
	}
}
