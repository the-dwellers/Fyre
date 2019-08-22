package com.github.thedwellers.fyreplugin.commands;

import com.github.thedwellers.fyreplugin.configuration.Strings;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class BankCommand extends AbstractCommand {
	@Override
	public String getPermission() {
		return "fyre.bank.use";
	}

	private String getUsage(String[] args) {
		StringBuilder sb = new StringBuilder();
		sb.append(Strings.OUT_PREFIX).append(Strings.C_ERROR).append("Usage: ").append(Strings.USAGE_DEFAULT).append("/bank").append(" ");

		return sb.toString();
	}

	@Override
	public boolean execute(CommandSender sender, Command command, String label, String[] args) {
		if (args.length < 1) {
			sender.sendMessage(getUsage(args));
			return true;
		}

		return true;
	}
}
