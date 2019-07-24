package com.github.thedwellers.fyreplugin.commands;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Merchant;

public class MerchantCommand implements CommandExecutor{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player){
			sender.sendMessage("Hello there");
			return true;
		}
        return false;
    }
}

