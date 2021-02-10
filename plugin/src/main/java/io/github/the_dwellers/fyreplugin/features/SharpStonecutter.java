package io.github.the_dwellers.fyreplugin.features;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import io.github.the_dwellers.fyreplugin.configuration.SupportedVersions;
import io.github.the_dwellers.fyreplugin.core.AbstractFeature;
import io.github.the_dwellers.fyreplugin.core.MinecraftVersion;

public class SharpStonecutter extends AbstractFeature implements Listener {
	public MinecraftVersion getMinecraftVersion() {
		return SupportedVersions.MIN;
	}

	public String getName() {
		return "Sharp Stonecutter";
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
	public void onPlayerMove(PlayerMoveEvent event) {
		if (event.getPlayer().getGameMode() != GameMode.CREATIVE && !event.getPlayer().isInvulnerable() && event.getPlayer().getNoDamageTicks() == 0) {

			Block block = event.getPlayer().getLocation().getBlock();
			if (block != null && block.getType() == Material.STONECUTTER) {
				event.getPlayer().damage(0.5);
				event.getPlayer().getLocation().getWorld().playSound(event.getPlayer().getLocation(), Sound.UI_STONECUTTER_TAKE_RESULT, 1, 1);
			}
		}
	}
}
