package com.github.thedwellers.fyreplugin.commands;

import com.github.thedwellers.fyreplugin.configuration.Strings;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.List;

/**
 * Show which plugins are loaded by the server
 */
public class PluginsCommand extends AbstractCommand {
	@Override
	public String getPermission() {
		return "fyre.plugins.use";
	}

	@Override
	public boolean execute(CommandSender sender, Command command, String label, String[] args) {
		sender.sendMessage(getPlugins(sender, !(sender instanceof Player)));
		return true;
	}

	public static TextComponent getPlugins(CommandSender sender) {
		return getPlugins(sender, false);
	}

	public static TextComponent getPlugins(CommandSender sender, boolean longForm) {
		boolean isPlayer = sender instanceof Player;
		Plugin[] plugins = sender.getServer().getPluginManager().getPlugins();
		int enabled = 0;

		for (Plugin plugin : plugins) {
			if (plugin.isEnabled()) {
				// No Casting boolean to int in java?
				enabled += 1;
			}
		}

		if (longForm) {
			return buildLongForm(plugins);
		}

		TextComponent pluginText = new TextComponent(Strings.OUT_PREFIX + "In total, there are " + Strings.C_ACCENT + enabled + Strings.C_DEFAULT + " enabled plugins");
		if (isPlayer) {
			pluginText.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponent[] { buildLongForm(plugins) }));
		}
		return pluginText;

	}

	private static TextComponent buildLongForm(Plugin[] plugins) {
		TextComponent text = new TextComponent();
		for (int i = 0; i < plugins.length; i++) {
			Plugin plugin = plugins[i];
			ChatColor color = plugin.isEnabled() ? ChatColor.DARK_GREEN : ChatColor.RED;
			text.addExtra("" + color + plugin.getDescription().getName() + Strings.C_DEFAULT + " v" + Strings.C_ACCENT
					+ plugin.getDescription().getVersion());
			if (i < plugins.length - 1) {
				text.addExtra("\n");
			}
		}
		return text;
	}
}
