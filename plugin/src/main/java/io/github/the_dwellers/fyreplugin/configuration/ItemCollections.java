package io.github.the_dwellers.fyreplugin.configuration;

import io.github.the_dwellers.fyreplugin.util.RandomUtil;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * Helper functions to retrieve specific types and collections of projects.
 */
public abstract class ItemCollections {

	/**
	 * Retrieve a Fyre-copy of a specific tool of a specific material. Includes swords.
	 * @param material Tool material type. (e.g, Wooden, Golden, etc)
	 * @param tool Tool type. (Axe, Pickaxe, etc)
	 */
	public static ItemStack getTool(EquipmentMaterial material, Tool tool) {
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

	/**
	 * Get a random tool type. Normal distribution is 20% (including swords.)
	 * <p> If swords are preferred, sword drop chance increases to 60%, with
	 * only 10% chance for other tools.
	 * @param preferSword Increase chances for sword to be selected.
	 * @return Random Tool type.
	 */
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

	/**
	 * Randomly damage the given {@link ItemStack} between 20% - 80%
	 * @param item Itemstack to damage.
	 * @return Damaged Itemstack
	 */
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
/**
	 * Retrieve a Fyre-copy of a specific farm food of a specific tier.
	 * @param food Food Name (Beef, Chicken, Bread etc...)
	 * @param tier Tier (The strength of the Food, 1-5)
	 */
	public static ItemStack getFarmFood(Food_Name food, Tier tier) {
		switch (food) {
			case Beef:
				switch (tier) {
					case Two:
						return Items.getSteakTier2();
					case Three:
						return Items.getSteakTier3();
					case Four:
						return Items.getSteakTier4();
					case Five:
						return Items.getSteakTier5();
					default:
						return Items.getSteakTier1();
				}
			default:
			case Mutton:
				switch (tier) {
					case Two:
						return Items.getMuttonTier2();
					case Three:
						return Items.getMuttonTier3();
					case Four:
						return Items.getMuttonTier4();
					case Five:
						return Items.getMuttonTier5();
					default:
						return Items.getMuttonTier1();
				}
				// case Pork:
				// case Chicken:
				// case Cod:
				// case Rabbit:
				// case Mushroom:
				// case Beetroot:
				// case Bread:
				// case Potato:
		}
	}


	/**
	 * Material-based minecraft tools:
	 * <ul>
	 * <li>Sword</li>
	 * <li>Axe</li>
	 * <li>Pickaxe</li>
	 * <li>Hoe</li>
	 * <li>Shovel</li>
	 */
	public enum Tool {
		Sword, Axe, Pickaxe, Hoe, Shovel
	}

	/**
	 * Materials {@link Tool}s can be made from:
	 * <li>Wood</li>
	 * <li>Stone</li>
	 * <li>Iron</li>
	 * <li>Gold</li>
	 * <li>Diamond</li>
	 */
	public enum EquipmentMaterial {
		Wood, Stone, Iron, Gold, Diamond
	}

	public enum Food_Name{
		Beef, Pork, Mutton, Chicken, Cod, Rabbit_Stew, Mushroom_Soup, Beetroot_Soup, Bread, Potato
	}

	public enum Tier{
		One, Two, Three, Four, Five
	}
}
