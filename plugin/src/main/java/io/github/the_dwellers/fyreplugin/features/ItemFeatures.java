package io.github.the_dwellers.fyreplugin.features;

import io.github.the_dwellers.fyreplugin.core.AbstractFeature;
import io.github.the_dwellers.fyreplugin.core.AbstractCommand;
import io.github.the_dwellers.fyreplugin.configuration.Items;
import io.github.the_dwellers.fyreplugin.configuration.SupportedVersions;
import io.github.the_dwellers.fyreplugin.core.MinecraftVersion;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.AbstractArrow.PickupStatus;
import org.bukkit.event.Cancellable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.EntityToggleGlideEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.projectiles.ProjectileSource;

/**
 * Features and logic required by custom items.
 * <p>
 * This is specific to item reworks and effects, mechanics and tweaks to
 * existing items will be done in separate features, such as
 * {@link PlantHoeHarvest}
 */
public class ItemFeatures extends AbstractFeature implements Listener {

	public MinecraftVersion getMinecraftVersion() {
		return SupportedVersions.MIN;
	}

	public String getName() {
		return "Item Features";
	}

	public boolean setup() {
		if (isEnabled()) {
			// Enable-gate
			return isEnabled();
		}

		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		plugin.getCommand("money").setExecutor(new MoneyCommand());
		enabled = true;
		return isEnabled();
	}

	/**
	 * Prevent crop trample if entity is wearing leather boots
	 * <p>
	 * Note: does not fire for player entities.
	 *
	 * @param event {@link EntityInteractEvent}
	 * @see ItemFeatures#onPlayerCropTrample(PlayerInteractEvent)
	 */
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

	/**
	 * Prevent crop trample if player is wearing leather boots
	 *
	 * @param event {@link PlayerInteractEvent}
	 * @see ItemFeatures#onEntityCropTrample(EntityInteractEvent)
	 */
	@EventHandler()
	public static void onPlayerCropTrample(PlayerInteractEvent event) {
		Block block = event.getClickedBlock();
		Player player = event.getPlayer();
		if (block != null && block.getType() == Material.FARMLAND && event.getAction() == Action.PHYSICAL) {
			preventCropTrample(event, player);
		}
	}

	/**
	 * Cancel crop trample event if leather boots are worn by the entity
	 *
	 * @param event  CropTrample event
	 * @param entity Entity to check for leather boots
	 */
	public static void preventCropTrample(Cancellable event, LivingEntity entity) {
		ItemStack boots = entity.getEquipment().getBoots();
		if (boots == null) {
			return;
		} else if (boots.getType() == Material.LEATHER_BOOTS) {
			event.setCancelled(true);
		}
	}

	/**
	 * Knowledge book sound effects
	 *
	 * @param event {@link PlayerInteractEvent}
	 */
	@EventHandler()
	public void onKnowledgeBook(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if (player.getInventory().getItemInMainHand().getType() == Material.KNOWLEDGE_BOOK) {
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_ENDER_EYE_DEATH,
					SoundCategory.MASTER, 1, 1);
		}
	}

	/**
	 * Label custom arrow entities using a custom name
	 *
	 * @param event {@link EntityShootBowEvent}
	 */
	@EventHandler()
	public void onBowShoot(EntityShootBowEvent event) {
		if (event.getConsumable().equals(Items.getEnderArrow())) {
			event.getProjectile().setCustomName("Ender Arrow");
		} else if (event.getConsumable().equals(Items.getSaddledArrow())) {
			event.getProjectile().setCustomName("Saddled Arrow");
			if (!event.getEntity().isInsideVehicle() ) {
				// wheeeee :D
				event.getProjectile().addPassenger(event.getEntity());
			}
		}
	}

	/**
	 * Manage projectile events.
	 * <ul>
	 * <li>Ender Arrows - Teleport the user to the location of the arrow.</li>
	 * <li>Pioneer Arrows - Player rides the arrow. (currently bugged)</li>
	 * </ul>
	 * @param event {@link PlayerJoinEvent}
	 */
	@EventHandler()
	public void onProjectileHit(ProjectileHitEvent event) {
		if (event.getEntityType() == EntityType.ARROW) {
			Arrow arrow = (Arrow) event.getEntity();
			ProjectileSource projectileSource = arrow.getShooter();

			if (arrow.getCustomName() == null) {
				// Standard arrow
				return;
			}

			if (arrow.getCustomName().equals("Saddled Arrow")) {
				arrow.setPickupStatus(PickupStatus.DISALLOWED);
				// Pioneer arrow. (Currently kicks player due to flying)
				if (projectileSource instanceof Entity) {
					arrow.removePassenger((Entity) projectileSource);
				}

			} else if (arrow.getCustomName().equals("Ender Arrow")) {
				arrow.setPickupStatus(PickupStatus.DISALLOWED);
				// Ender Arrow
				if (projectileSource instanceof Entity) {
					// Teleport entity to arrow
					Entity shooter = ((Entity) projectileSource);

					Location newLocation = arrow.getLocation().setDirection(shooter.getLocation().getDirection());

					shooter.teleport(newLocation);
					newLocation.getWorld().playSound(shooter.getLocation(), Sound.ITEM_CHORUS_FRUIT_TELEPORT, SoundCategory.MASTER, 1, 1);
					newLocation.getWorld().spawnParticle(Particle.REVERSE_PORTAL,shooter.getLocation().add(0, shooter.getHeight()/2, 0), 30);
				}
			}
		}
	}

	/**
	 * Debug command: Give money to player
	 */
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

				ItemStack gold = Items.getGoldCoin();
				gold.setAmount(64);
				player.getInventory().addItem(gold);
			}
			return true;
		}
	}
}
