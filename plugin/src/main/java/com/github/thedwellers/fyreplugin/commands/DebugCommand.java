package com.github.thedwellers.fyreplugin.commands;

import com.github.thedwellers.fyreplugin.NBT;
import com.github.thedwellers.fyreplugin.configuration.Strings;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Simple command for testing
 */
public class DebugCommand extends AbstractCommand {
	@Override
	public String getPermission() {
		return "fyre.debug.use";
	}

	@Override
	public boolean execute(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			try {
				String boatnbt = "{Motion:[0.0d, 8.054345348849565E-5d, 0.0d], UUIDLeast:-6817029289107105191L, Bukkit.updateLevel:2, Invulnerable:0b, Paper.SpawnReason:\"DEFAULT\", Air:300s, OnGround:0b, Dimension:0, PortalCooldown:0, Rotation:[211.95738f, 0.0f], FallDistance:0.0f, Type:\"oak\", UUIDMost:8470046131573966430L, Pos:[-537.2473562038786d, 62.52301890690381d, -66.44020438695996d], WorldUUIDMost:5463138192493594717L, Fire:-1s, Spigot.ticksLived:1207, WorldUUIDLeast:-8027480841958582722L, Paper.Origin:[-537.2473562038786d, 62.88888889551163d, -66.44020438695996d]}";

				System.out.println(NBT.getTag(boatnbt, "Tags", true));
				System.out.println(NBT.setTag(boatnbt, "Tags", "['Test string']"));
			} catch (Exception e) {
				player.sendMessage(Strings.OUT_PREFIX + Strings.C_ERROR + "Reflection failed:" + e.getMessage());
				e.printStackTrace();
			}
		}
		return true;
	}
}
