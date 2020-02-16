package io.github.the_dwellers.fyreplugin.features;

import java.util.Collection;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import io.github.the_dwellers.fyreplugin.FyrePlugin;
import io.github.the_dwellers.fyreplugin.configuration.SupportedVersions;
import io.github.the_dwellers.fyreplugin.util.MinecraftVersion;

public class PlantHoeHarvest implements Listener, AbstractFeature {

	public static MinecraftVersion minVersion = SupportedVersions.MC1144;

	protected boolean enabled = false;
	protected static String name = "Hoe Harvest";
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
	public boolean setup(FyrePlugin plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		enabled = true;
		return true;
	}

	@EventHandler()
	public void onCropClick(PlayerInteractEvent event) {
		Player player = event.getPlayer();

		Block block = event.getClickedBlock();
		if (block == null) {
			return;
		}
		Material blockMaterial = block.getType();

		if (blockMaterial == Material.WHEAT || blockMaterial == Material.BEETROOTS || blockMaterial == Material.CARROTS
				|| blockMaterial == Material.POTATOES) {

			Ageable Blockdata = (Ageable) block.getBlockData();
			if (Blockdata.getAge() != Blockdata.getMaximumAge()) {
				return;
			}

			ItemStack right = player.getInventory().getItemInMainHand();
			ItemStack left = player.getInventory().getItemInOffHand();

			Material rightMat = right.getType();
			Material leftMat = left.getType();
			Collection<ItemStack> drops;

			// Main Hand
			if (rightMat == Material.WOODEN_HOE || rightMat == Material.STONE_HOE || rightMat == Material.IRON_HOE
					|| rightMat == Material.GOLDEN_HOE || rightMat == Material.DIAMOND_HOE) {

				Damageable itemMeta = (Damageable) right.getItemMeta();
				itemMeta.setDamage(itemMeta.getDamage() + 1);

				// ? Why is there no api method to break items?
				// ? Why do items not naturally break?
				if (itemMeta.getDamage() > right.getType().getMaxDurability()) {
					if (ClientBreakItem.getInstance().isEnabled()) {
						ClientBreakItem.getInstance().breakEquipment((LivingEntity) player, EquipmentSlot.HAND);
					} else {
						player.getEquipment().getItemInMainHand().subtract();
					}
				}

				right.setItemMeta((ItemMeta) itemMeta);
				drops = block.getDrops(right);

				// Off Hand
			} else if (leftMat == Material.WOODEN_HOE || leftMat == Material.STONE_HOE || leftMat == Material.IRON_HOE
					|| leftMat == Material.GOLDEN_HOE || leftMat == Material.DIAMOND_HOE) {

				Damageable itemMeta = (Damageable) left.getItemMeta();
				itemMeta.setDamage(itemMeta.getDamage() + 1);

				if (itemMeta.getDamage() > left.getType().getMaxDurability()) {
					if (ClientBreakItem.getInstance().isEnabled()) {
						ClientBreakItem.getInstance().breakEquipment((LivingEntity) player, EquipmentSlot.HAND);
					} else {
						player.getEquipment().getItemInMainHand().subtract();
					}
				}

				left.setItemMeta((ItemMeta) itemMeta);
				drops = block.getDrops(left);

			} else {
				return;
			}

			// Spawn Drops
			for (ItemStack item : drops) {
				block.getLocation().getWorld().dropItemNaturally(block.getLocation(), item);
			}

			// Reset block age
			Blockdata.setAge(1);
			block.setBlockData(Blockdata);
		}
	}

	@Override
	public String getName() {
		return name;
	}

}
