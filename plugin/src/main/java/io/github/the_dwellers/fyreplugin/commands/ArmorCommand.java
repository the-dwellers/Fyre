package io.github.the_dwellers.fyreplugin.commands;

import io.github.the_dwellers.fyreplugin.ChatManager;
import io.github.the_dwellers.fyreplugin.configuration.Strings;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Displays the currently worm armor set to chat
 */
public class ArmorCommand extends AbstractCommand {
	@Override
	public String getPermission() {
		return "fyre.armor.use";
	}

	@Override
	public boolean execute(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			TextComponent armorText = ChatManager.getArmourText(player);
			if (armorText == null) {
				player.sendMessage(Strings.NO_ITEM_WORN);
			} else {
				ChatManager.sendPlayerMessage(player, armorText);
			}
		}
		return true;
	}
}
