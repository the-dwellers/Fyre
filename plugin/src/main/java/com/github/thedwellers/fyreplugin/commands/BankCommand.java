package com.github.thedwellers.fyreplugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BankCommand extends AbstractCommand {
	@Override
	public String getPermission() {
		return "fyre.bank.use";
	}

	@Override
	public boolean execute(CommandSender sender, Command command, String label, String[] args) {
		return false;
	}
}
