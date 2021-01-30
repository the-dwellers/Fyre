package io.github.the_dwellers.fyreplugin.features;

import javax.inject.Inject;

import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.attribute.AttributeModifier.Operation;
import org.bukkit.entity.Monster;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

import io.github.the_dwellers.fyreplugin.configuration.Strings;
import io.github.the_dwellers.fyreplugin.configuration.SupportedVersions;
import io.github.the_dwellers.fyreplugin.core.AbstractFeature;
import io.github.the_dwellers.fyreplugin.core.MinecraftVersion;
import io.papermc.paper.world.MoonPhase;
import net.md_5.bungee.api.chat.TextComponent;

/**
 * Empower mobs on full moons
 */
public class BloodMoon extends AbstractFeature implements Listener {

	private BloodMoonTask moonTask;

	@Inject
	private Mobs mobs;

	public MinecraftVersion getMinecraftVersion() {
		return SupportedVersions.MIN;
	}

	public String getName() {
		return "Blood Moon";
	}

	public boolean setup() {
		if (isEnabled()) {
			// Enable-gate
			return isEnabled();
		}

		// Default world is index 0
		moonTask = new BloodMoonTask();

		moonTask.runTaskTimer(plugin, 20, 20);
		plugin.getServer().getPluginManager().registerEvents(this, plugin);

		enabled = true;
		return isEnabled();
	}

	// Note: no event for specific world times, must check every tick.
	private class BloodMoonTask extends BukkitRunnable {

		private boolean bloodMoon = false;

		@Override
		public void run() {
			World world = plugin.getServer().getWorlds().get(0);
			boolean curMoon = bloodMoon;

			bloodMoon = !world.isDayTime() && (world.getMoonPhase() == MoonPhase.FULL_MOON);

			if (curMoon != bloodMoon) {
				if (bloodMoon) {
					plugin.getServer().broadcast(new TextComponent(Strings.EVENT_BLOODMOON_START));
					world.setStorm(true);
				} else {
					plugin.getServer().broadcast(new TextComponent(Strings.EVENT_BLOODMOON_END));
					world.setStorm(false);
				}
			}
		}

		public boolean isBloodMoon() {
			return bloodMoon;
		}

	}

	public boolean isBloodMoon() {
		return moonTask.isBloodMoon();
	}

	/**
	 * Apply equipment and difficulty levels to spawned mobs
	 * @param event {@link CreatureSpawnEvent}
	 */
	@EventHandler()
	public void onCreatureSpawned(CreatureSpawnEvent event) {
		if (event.getEntity() instanceof Monster && event.getSpawnReason() == SpawnReason.NATURAL) {
			Monster monster = (Monster) event.getEntity();
			if (isBloodMoon()) {
				Monster moonspawn = (Monster) monster.getLocation().getWorld().spawnEntity(monster.getLocation(), monster.getType());

				moonspawn.getAttribute(Attribute.GENERIC_FOLLOW_RANGE)
					.addModifier(new AttributeModifier("Fyre Boss Follow Range", 100, Operation.ADD_NUMBER));

				if (mobs.isEnabled()) {
					mobs.setupMobEquipment(moonspawn, Mobs.MobDifficulty.Strong);
				}
			}
		}
	}

	/**
	 * Display to connecting players that a blood moon is in effect.
	 * @param event {@link PlayerJoinEvent}
	 */
	@EventHandler()
	public void onPlayerJoined(PlayerJoinEvent event) {
		// Announce to new players when a blood moon is in progress
		if (isBloodMoon()) {
			event.getPlayer().sendMessage(new TextComponent(Strings.EVENT_BLOODMOON_START));
		}
	}
}
