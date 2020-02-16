package io.github.the_dwellers.fyreplugin.features;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Statistic;
import org.bukkit.advancement.Advancement;
import org.bukkit.advancement.AdvancementProgress;
import org.bukkit.entity.Player;

import io.github.the_dwellers.fyreplugin.FyrePlugin;
import io.github.the_dwellers.fyreplugin.configuration.SupportedVersions;
import io.github.the_dwellers.fyreplugin.util.MinecraftVersion;

public class Advancements implements Listener, AbstractFeature {

	public static MinecraftVersion minVersion = SupportedVersions.MIN;

	protected boolean enabled = false;
	protected static String name = "Advancements";
	private static ClientBreakItem instance;

	public static ClientBreakItem getInstance() {
		if (instance == null) {
			instance = new ClientBreakItem();
		}
		return instance;
	}

	@Override
	public MinecraftVersion getMinecraftVersion() {
		return minVersion;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public boolean setup(FyrePlugin plugin) {return false;

	}


	@EventHandler()
	public void onBlockBreak(BlockBreakEvent event) {
		Advancements.updateBlockBreakAdvancement(event.getPlayer(), event.getBlock().getType());
	}

	/**
	 * Awards the provided player any advancements they are entitled to. This only
	 * applies to plugin-controlled advancements related to breaking blocks.
	 *
	 * @param player Player to check and award advancements
	 * @param block  Block broken
	 */
	public static void updateBlockBreakAdvancement(Player player, Material block) {
		switch (block) {
			case ANDESITE:
				// Volcano Stack
				awardBlockBreak(player, block, 64, "mining/volcano-stack");
				break;
			case DIORITE:
				// Andesine Container
				awardBlockBreak(player, block, 64, "mining/andesine-container");
				break;
			case GRANITE:
				// Intrusive Rock
				awardBlockBreak(player, block, 64, "mining/intrusive-rock");
				break;
			case COAL_ORE:
				// Fossil Fuels
				awardBlockBreak(player, block, 1, "mining/fossil-fuels");
				// Dead Plant Collector
				awardBlockBreak(player, block, 64, "mining/dead-plant-collector");
				// Climate Changer
				awardBlockBreak(player, block, 1728, "mining/climate-changer");
				break;
			case STONE:
				// Took you Long Enough
				awardBlockBreak(player, block, 1, "mining/took-you-long-enough");
				// Stack'o Cobble
				awardBlockBreak(player, block, 64, "mining/stacko-cobble");
				// Red-Bearded Dwarf
				awardBlockBreak(player, block, 1728, "mining/red-bearded-dwarf");
				break;
			case LAPIS_ORE:
				// No Longer in the Woods
				awardBlockBreak(player, block, 1, "mining/no-longer-in-the-woods");
				break;
			case IRON_ORE:
				// Rusty Stone
				awardBlockBreak(player, block, 1, "mining/rusty-stone");
				// Magnetite and Hematite
				awardBlockBreak(player, block, 1728, "mining/magnetite-and-hematite");
				break;
			case GOLD_ORE:
				// Kingmaker
				awardBlockBreak(player, block, 1, "mining/kingmaker");
				// A Small loan
				awardBlockBreak(player, block, 1728, "mining/a-small-loan");
				break;
			case DIAMOND_ORE:
				// Useless Material
				awardBlockBreak(player, block, 1, "mining/useless-material");
				break;
			default:
				break;
		}
	}

	/**
	 * Awards the advancement if the player has broken equal or over the amount of
	 * blocks. Does not provide the advancement if the player already has it.
	 *
	 * @param player      player to check
	 * @param block       block broken
	 * @param amount      amount of blocks broken
	 * @param advancement advancement to award
	 */
	@SuppressWarnings("deprecation")
	private static void awardBlockBreak(Player player, Material block, int amount, String advancement) {
		// NamespacedKey is deprecated, however there is no other alternative
		// way to do this in O(1). The only other alternative would be looping
		// over *all* advancements in the game!
		Advancement adv = player.getServer().getAdvancement(new NamespacedKey("fyre", advancement));
		AdvancementProgress progress = player.getAdvancementProgress(adv);
		if (progress.isDone()) {
			// Player already has advancement
			return;
		}

		if (player.getStatistic(Statistic.MINE_BLOCK, block) >= amount) {
			// ? If anyone knows a shorter way to convert to an array while
			// ? conserving type, please message me - WYVERN
			progress.awardCriteria(
				progress.getRemainingCriteria().toArray(new String[progress.getRemainingCriteria().size()])[0]);
		}
	}
}
