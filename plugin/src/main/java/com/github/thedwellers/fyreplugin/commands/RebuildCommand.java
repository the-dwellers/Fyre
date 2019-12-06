package com.github.thedwellers.fyreplugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.io.File;

/**
 * Reload the server
 */
public class RebuildCommand extends AbstractCommand {
	@Override
	public String getPermission() {
		return "fyre.rebuild.use";
	}

	@Override
	public boolean execute(CommandSender sender, Command command, String label, String[] args) {
		System.out.println(new File("."));
		return true;
	}
}
