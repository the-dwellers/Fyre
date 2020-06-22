package io.github.the_dwellers.fyreplugin.features;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDamageEvent;

import io.github.the_dwellers.fyreplugin.configuration.SupportedVersions;
import io.github.the_dwellers.fyreplugin.core.AbstractFeature;
import io.github.the_dwellers.fyreplugin.core.MinecraftVersion;

public class SpikyCactus extends AbstractFeature implements Listener {

	public MinecraftVersion getMinecraftVersion() {
		return SupportedVersions.MIN;
	}

	public String getName() {
		return "Spikey Cactus";
	}

	public boolean setup() {
		if (isEnabled()) {
			// Enable-gate
			return isEnabled();
		}

		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		enabled = true;
		return isEnabled();
	}

	@EventHandler()
	public void onBlockBreaking(BlockDamageEvent event) {
		if (event.getBlock().getType() == Material.CACTUS &&
				event.getPlayer().getEquipment().getItemInMainHand().getType() == Material.AIR) {
			// Empty hand punching a cactus
			event.getPlayer().damage(0.5);
		}
	}
}
