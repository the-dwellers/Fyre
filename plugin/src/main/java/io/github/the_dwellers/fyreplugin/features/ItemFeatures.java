package io.github.the_dwellers.fyreplugin.features;

import io.github.the_dwellers.fyreplugin.AbstractFeature;
import io.github.the_dwellers.fyreplugin.commands.AbstractCommand;
import io.github.the_dwellers.fyreplugin.configuration.Items;
import io.github.the_dwellers.fyreplugin.configuration.SupportedVersions;
import io.github.the_dwellers.fyreplugin.util.MinecraftVersion;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Features and logic required by custom items.
 */
public class ItemFeatures extends AbstractFeature implements Listener {
	@EventHandler()
	public static void onEntityCropTrample(EntityInteractEvent event) {
		// ? Why does EntityInteractEvent not fire for players?
		Block block = event.getBlock();
		Entity entity = event.getEntity();
		if (block != null && event.getEntity() instanceof LivingEntity) {
			if (block.getType() == Material.FARMLAND) {
				preventCropTrample(event, (LivingEntity) entity);
			}
		}
	}

	@EventHandler()
	public static void onPlayerCropTrample(PlayerInteractEvent event) {
		Block block = event.getClickedBlock();
		Player player = event.getPlayer();
		if (block != null && block.getType() == Material.FARMLAND && event.getAction() == Action.PHYSICAL) {
			preventCropTrample(event, player);
		}
	}

	public static void preventCropTrample(Cancellable event, LivingEntity entity) {
		ItemStack boots = entity.getEquipment().getBoots();
		if (boots == null) {
			return;
		} else if (boots.getType() == Material.LEATHER_BOOTS) {
			event.setCancelled(true);
		}
	}

	@Override
	public MinecraftVersion getMinecraftVersion() {
		return SupportedVersions.MIN;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	@Override
	public String getName() {
		return "Item Features";
	}

	@Override
	public boolean setup() {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		plugin.getCommand("money").setExecutor(new MoneyCommand());
		enabled = true;
		return isEnabled();
	}

	@EventHandler()
	public void onKnowledgeBook(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if (player.getInventory().getItemInMainHand().getType() == Material.KNOWLEDGE_BOOK) {
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_ENDER_EYE_DEATH,
					SoundCategory.MASTER, 1, 1);
		}
	}

	public class MoneyCommand extends AbstractCommand {
		@Override
		public String getPermission() {
			return "fyre.money.use";
		}

		@Override
		public boolean execute(CommandSender sender, Command command, String label, String[] args) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				ItemStack silver = Items.getSilverCoin();
				silver.setAmount(64);
				player.getInventory().addItem(silver);
			}
			return true;
		}
	}
}
