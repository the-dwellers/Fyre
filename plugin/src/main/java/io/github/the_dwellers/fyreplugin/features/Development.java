package io.github.the_dwellers.fyreplugin.features;

import io.github.the_dwellers.fyreplugin.core.AbstractFeature;
import io.github.the_dwellers.fyreplugin.core.AbstractCommand;
import io.github.the_dwellers.fyreplugin.configuration.SupportedVersions;
import io.github.the_dwellers.fyreplugin.core.MinecraftVersion;
import net.md_5.bungee.api.chat.hover.content.Item;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import io.github.the_dwellers.fyreplugin.configuration.Items;

/**
 * Development module for random testing.
 */
public class Development extends AbstractFeature implements Listener {

	public MinecraftVersion getMinecraftVersion() {
		return SupportedVersions.MIN;
	}

	public String getName() {
		return "Development";
	}

	public boolean setup() {
		if (isEnabled()) {
			// Enable-gate
			return isEnabled();
		}

		plugin.getLogger().warning("Development mode is enabled [D]");
		plugin.getCommand("debug").setExecutor(new DebugCommand());
		enabled = true;
		return isEnabled();
	}


	/**
	 * Print out a warning message only if the Development feature is enabled.
	 * @param msg Message
	 */
	public void devWarning(String msg) {
		if (enabled) {
			plugin.getLogger().warning("[D] "+ msg);
		}
	}

	/**
	 * Print out a warning message only if the Development feature is enabled.
	 * @param msg Message
	 */
	public void devMsg(String msg) {
		if (enabled) {
			plugin.getLogger().info("[D] "+ msg);
		}
	}

	public class DebugCommand extends AbstractCommand {
		@Override
		public String getPermission() {
			return "fyre.debug.use";
		}

		@Override
		public boolean execute(CommandSender sender, Command command, String label, String[] args) {
			sender.sendMessage("Feature " + getName() + " requires Minecraft " + getMinecraftVersion());
			sender.sendMessage("This server is on IP: " + plugin.getServer().getIp());
			((Player) sender).getInventory().addItem(Items.getSteakTier5());
			return true;

		}
	}

}
