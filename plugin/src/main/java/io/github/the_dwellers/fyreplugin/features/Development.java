package io.github.the_dwellers.fyreplugin.features;

import io.github.the_dwellers.fyreplugin.core.AbstractFeature;
import io.github.the_dwellers.fyreplugin.core.AbstractCommand;
import io.github.the_dwellers.fyreplugin.configuration.SupportedVersions;
import io.github.the_dwellers.fyreplugin.core.MinecraftVersion;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;

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

		plugin.getLogger().warning("Development mode is enabled");
		plugin.getCommand("debug").setExecutor(new DebugCommand());
		enabled = true;
		return isEnabled();
	}

	public class DebugCommand extends AbstractCommand {
		@Override
		public String getPermission() {
			return "fyre.debug.use";
		}

		@Override
		public boolean execute(CommandSender sender, Command command, String label, String[] args) {
			// ? For Development use only. Do not push changes!
			sender.sendMessage("Feature " + getName() + " requires Minecraft " + getMinecraftVersion());
			sender.sendMessage("This server is on IP: " + plugin.getServer().getIp());
			return true;
		}
	}
}
