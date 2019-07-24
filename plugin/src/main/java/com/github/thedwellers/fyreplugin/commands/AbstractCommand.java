package com.github.thedwellers.fyreplugin.commands;

import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * AbstractCommand
 */
public abstract class AbstractCommand implements CommandExecutor {

	protected JavaPlugin plugin;

	public AbstractCommand(JavaPlugin plugin){
		this.plugin = plugin;
	}
}
