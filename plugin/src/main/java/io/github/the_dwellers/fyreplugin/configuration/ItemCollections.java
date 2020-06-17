package io.github.the_dwellers.fyreplugin.configuration;

import io.github.the_dwellers.fyreplugin.util.RandomUtil;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * Helper functions to retrieve specific types and collections of projects.
 */
public abstract class ItemCollections {

	public static ItemStack getTool(ToolMaterial material, Tool tool) {
		switch (material) {
			case Stone:
				switch (tool) {
					case Axe:
						return Items.getStoneAxe();
					case Pickaxe:
						return Items.getStonePickaxe();
					case Hoe:
						return Items.getStoneHoe();
					case Shovel:
						return Items.getStoneShovel();
					case Sword:
					default:
						return Items.getStoneSword();
				}
			default:
			case Wood:
				switch (tool) {
					case Axe:
						return Items.getWoodAxe();
					case Pickaxe:
						return Items.getWoodPickaxe();
					case Hoe:
						return Items.getWoodHoe();
					case Shovel:
						return Items.getWoodShovel();
					case Sword:
					default:
						return Items.getWoodSword();
				}
				// case Iron:
				// case Gold:
				// case Diamond:
		}
	}

	public static Tool getRandomTool(boolean preferSword) {
		int rand = RandomUtil.integer(10);

		if (preferSword) {
			if (rand < 1) {
				// 10%: Axe
				return Tool.Axe;
			} else if (rand < 2) {
				// 10%: Pickaxe
				return Tool.Pickaxe;
			} else if (rand < 3) {
				// 10%: Hoe
				return Tool.Hoe;
			} else if (rand < 4) {
				// 10%: Shovel
				return Tool.Shovel;
			} else {
				// 60%: Sword
				return Tool.Sword;
			}
		} else {
			if (rand < 2) {
				// 20%: Axe
				return Tool.Axe;
			} else if (rand < 4) {
				// 20%: Pickaxe
				return Tool.Pickaxe;
			} else if (rand < 6) {
				// 20%: Hoe
				return Tool.Hoe;
			} else if (rand < 8) {
				// 20%: Shovel
				return Tool.Shovel;
			} else {
				// 20%: Sword
				return Tool.Sword;
			}
		}
	}

	public static ItemStack randomlyBreak(ItemStack item) {
		if (item.getItemMeta() instanceof Damageable) {
			// More complex than it has to be, who designed this api?
			Damageable toolMeta = (Damageable) item.getItemMeta();
			toolMeta.setDamage(RandomUtil.integer(Math.round(item.getType().getMaxDurability() * 0.2f),
				Math.round(item.getType().getMaxDurability() * 0.8f)));
			item.setItemMeta((ItemMeta) toolMeta);
		}
		return item;
	}

	public enum Tool {
		Sword, Axe, Pickaxe, Hoe, Shovel
	}

	public enum ToolMaterial {
		Wood, Stone, Iron, Gold, Diamond
	}

}
