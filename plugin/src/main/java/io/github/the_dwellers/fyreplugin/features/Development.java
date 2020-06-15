package io.github.the_dwellers.fyreplugin.features;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import io.github.the_dwellers.fyreplugin.FyrePlugin;
import io.github.the_dwellers.fyreplugin.commands.AbstractCommand;
import io.github.the_dwellers.fyreplugin.configuration.SupportedVersions;
import io.github.the_dwellers.fyreplugin.Feature;
import io.github.the_dwellers.fyreplugin.features.Mobs.BossType;
import io.github.the_dwellers.fyreplugin.util.MinecraftVersion;

/**
 * Development module for random testing.
 */
public class Development extends Feature implements Listener {

	public static MinecraftVersion minVersion = SupportedVersions.MIN;

	protected boolean enabled = false;
	protected static String name = "Development";
	private static Development instance;

	public static Development getInstance() {
		if (instance == null) {
			instance = new Development();
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
	public String getName() {
		return name;
	}

	@Override
	public boolean setup(FyrePlugin plugin) {
		plugin.getLogger().warning("Development mode is enabled");
		plugin.getCommand("debug").setExecutor(new DebugCommand());
		enabled = true;
		return true;
	}

	public class DebugCommand extends AbstractCommand {
		@Override
		public String getPermission() {
			return "fyre.debug.use";
		}

		@Override
		public boolean execute(CommandSender sender, Command command, String label, String[] args) {
			// ? For Development use only. Do not push changes!
			Location loc;
			Block aim = ((Player) sender).getTargetBlock(100);
			if (aim != null) {
				loc = aim.getRelative(BlockFace.UP).getLocation();
			} else {
				loc = ((Player) sender).getLocation();
			}
			Mobs.getInstance().spawnBoss(BossType.GuardCaptain, loc);
			return true;
		}
	}
}
