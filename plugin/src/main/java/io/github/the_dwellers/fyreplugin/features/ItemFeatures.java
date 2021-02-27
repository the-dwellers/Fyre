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
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.destroystokyo.paper.event.entity.EndermanEscapeEvent;

import java.util.List;


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
		} else if (event.getConsumable().equals(Items.getMilkArrow())) {
			event.getProjectile().setCustomName("Milky Arrow");
		} else if (event.getConsumable().equals(Items.getAnchorArrow())) {
			event.getProjectile().setCustomName("Anchor Arrow");
		} else if (event.getConsumable().equals(Items.getBlindnessArrow())) {
			event.getProjectile().setCustomName("Blindness Arrow");
		} else if (event.getConsumable().equals(Items.getDarknessArrow())) {
			event.getProjectile().setCustomName("Darkness Arrow");
		}
	}

	/**
	 * Enderman teleport event. Currently used to support Anchor Arrow's
	 * function in dealing damage to enderman
	 * @param event {@link EndermanEscapeEvent}
	 */
	@EventHandler()
	public void onEndermanTeleport(EndermanEscapeEvent event) {
		// event does not give the entity which caused the escape event.
		// We want to check for a specific arrow type, and so we get the nearest arrow entity
		List<Entity> entities = event.getEntity().getNearbyEntities(4, 4, 4);
		Arrow closestArrow = null;

		// Get closest arrow to enderman
		for (Entity entity : entities) {
			if (entity instanceof Arrow) {
				// Filter for arrows
				if (((Arrow)entity).isInBlock()) {
					// Ignore arrows on ground
					continue;
				}

				if (closestArrow == null) {
					closestArrow = (Arrow) entity;
					continue;
				} else {
					if (closestArrow.getLocation().distance(event.getEntity().getLocation()) > entity.getLocation().distance(event.getEntity().getLocation())) {
						// Change arrow if it's closer than the current arrow
						closestArrow = (Arrow) entity;
					}
				}
			}
		}
		// If there is no arrow near the enderman
		if (closestArrow == null) {
			return;
		}

		if (closestArrow.getCustomName().equals("Anchor Arrow")) {
			// Cancel event if the closest arrow to the enderman is an anchor arrow
			event.setCancelled(true);

			Entity shooterEntity = null;
			if (closestArrow.getShooter() instanceof Entity) {
				shooterEntity = (Entity) closestArrow.getShooter();
			}

			// We also damage the enderman here and remove the arrow, to prevent
			// issues with ProjectileHitEvent and enderman damage mitigation.
			if (shooterEntity == null) {
				// Unknown source of damage.
				event.getEntity().damage(15);
			} else {
				event.getEntity().damage(15, shooterEntity);
			}

			// Remove arrow
			event.getEntity().setArrowsInBody(event.getEntity().getArrowsInBody() + 1);
			closestArrow.remove();
		}
	}

	/**
	 * Manage projectile events.
	 * <ul>
	 * <li><b>Ender Arrows</b> - Teleport the user to the location of the arrow.</li>
	 * <li><b>Milk Arrows</b> - Remove potion effects from the target.</li>
	 * <li><b>Anchor Arrows</b> - Apply a brief slowness to the target. Also works on enderman.</li>
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

			if (arrow.getCustomName().equals("Milky Arrow")) {
				arrow.setPickupStatus(PickupStatus.DISALLOWED);
				// Milky Arrow. Removes all potion effects
				if (event.getHitEntity() != null && event.getHitEntity() instanceof LivingEntity) {
					// Loop though entity and remove their effects.
					LivingEntity entity = (LivingEntity) event.getHitEntity();
					for (PotionEffect effect : entity.getActivePotionEffects()) {
						entity.removePotionEffect(effect.getType());
					}
					entity.getLocation().getWorld().playSound(entity.getLocation(), Sound.ITEM_BUCKET_EMPTY, 1, 1);
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
					newLocation.getWorld().spawnParticle(Particle.REVERSE_PORTAL, shooter.getLocation().add(0, shooter.getHeight()/2, 0), 30);
				}
			} else if (arrow.getCustomName().equals("Anchor Arrow")) {
				arrow.setPickupStatus(PickupStatus.DISALLOWED);
				// Anchor arrow. Hits enderman and applies slowness.
				if (event.getHitEntity() != null && event.getHitEntity() instanceof LivingEntity) {
					LivingEntity entity = (LivingEntity) event.getHitEntity();
					// Slowness III (0:03)
					entity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 3 * 20, 3));
				}
			} else if (arrow.getCustomName().equals("Blindness Arrow")) {
				arrow.setPickupStatus(PickupStatus.DISALLOWED);
				// Blindness arrow, apply blinding
				if (event.getHitEntity() != null && event.getHitEntity() instanceof LivingEntity) {
					LivingEntity entity = (LivingEntity) event.getHitEntity();
					// Blindness I (0:15)
					entity.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 15 * 20, 1));
				}
			} else if (arrow.getCustomName().equals("Darkness Arrow")) {
				arrow.setPickupStatus(PickupStatus.DISALLOWED);
				// Blindness arrow, apply blinding and nightvision
				// Result is that all the world becomes dark for the player
				if (event.getHitEntity() != null && event.getHitEntity() instanceof LivingEntity) {
					LivingEntity entity = (LivingEntity) event.getHitEntity();
					// Blindness I (0:15)
					entity.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 5 * 20, 0));
					// NightVision I (0:15)
					entity.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 5 * 20, 0));
				}
			}
		}
	}

	/**
	 *
	 * Eat event for special food from farming levels that applies boons based on item desc.
	 *
	 */
	@EventHandler()
	public void onPlayerItemConsume(PlayerItemConsumeEvent event){
		List<String> item_desc = event.getItem().getLore();
		if (item_desc != null){
			Player target  = event.getPlayer();

			for (String line : item_desc )
			{
				if (line.contains("Hunger"))
				{
					int value = Integer.parseInt(line.substring(line.lastIndexOf(" ") + 1));
					target.setFoodLevel(target.getFoodLevel() + value );
				}
				else if (line.contains("Saturation"))
				{
					int value = Integer.parseInt(line.substring(line.lastIndexOf(" ") + 1));
					target.setSaturation(target.getSaturation() + value );
				}
				else if (line.contains("Effect"))
				{

					String effect_full = line.substring(line.lastIndexOf(": ") + 2, line.lastIndexOf("- "));
					// Get the first number in the desc, which will be the strength of the effect by splitting the string at the number.
					String[] strength_effect = effect_full.split("(?<=\\D)(?=\\d)");
					String effect = strength_effect[0];
					String strength = strength_effect[1];

					String duration_mins = line.substring(line.lastIndexOf("- ") + 2, line.lastIndexOf(":"));
					String duration_secs = line.substring(line.lastIndexOf(":") + 1, line.lastIndexOf(" min"));

					int amplifier = Integer.parseInt(strength.trim()) - 1;
					// Here we have to convert mins to seconds and also multiply the entire duration by the tick rate, which is default 20.
					int duration = (Integer.parseInt(duration_mins.trim()) * 60 + Integer.parseInt(duration_secs.trim())) * 20;

					PotionEffectType pot_effect = PotionEffectType.getByName(effect.trim());

					PotionEffect food_effect = new PotionEffect(pot_effect, duration , amplifier);

					target.addPotionEffect(food_effect);

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
