package io.github.the_dwellers.fyreplugin.features;

import io.github.the_dwellers.fyreplugin.core.AbstractFeature;

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
import org.bukkit.scheduler.BukkitRunnable;

import io.github.the_dwellers.fyreplugin.configuration.Strings;
import io.github.the_dwellers.fyreplugin.configuration.SupportedVersions;
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
		return SupportedVersions.MC1164;
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

			// TODO Auto-generated method stub
			boolean curMoon = bloodMoon;
			bloodMoon = !world.isDayTime() && (world.getMoonPhase() == MoonPhase.FULL_MOON);
			// bloodMoon = !world.isDayTime();

			if (curMoon != bloodMoon) {
				if (bloodMoon) {
					plugin.getServer().broadcast(new TextComponent(Strings.EVENT_BLOODMOON_START));
				} else {
					plugin.getServer().broadcast(new TextComponent(Strings.EVENT_BLOODMOON_END));
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
}
