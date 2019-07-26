package com.github.thedwellers.fyreplugin.commands;

import com.github.thedwellers.fyreplugin.configuration.Strings;

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

/**
 * StatusCommand
 */
public class StatusCommand extends AbstractCommand {

	public StatusCommand(JavaPlugin plugin) {
		super(plugin);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player[] players = plugin.getServer().getOnlinePlayers()
				.toArray(new Player[plugin.getServer().getOnlinePlayers().size()]);
		boolean isPlayer = sender instanceof Player;
		TextComponent responseText = new TextComponent();

		// Players
		responseText.addExtra(ListCommand.getPlayers(sender));

		// Player Ping
		if (players.length != 0) {
			int average = 0;
			for (int i = 0; i < players.length; i++) {
				Player player = players[i];
				average += player.spigot().getPing();
			}
			average /= players.length;
			TextComponent latencyText = new TextComponent(Strings.OUT_PREFIX);
			if (isPlayer) {
				TextComponent hoverText = new TextComponent(Strings.C_DEFAULT);
				hoverText.addExtra("Average Ping: " + Strings.C_ACCENT + average + Strings.C_DEFAULT + "ms\n");
				latencyText.addExtra("Your Ping: ");

				Player player = (Player) sender;
				int playerLatency = player.spigot().getPing();
				if (playerLatency < average * 0.9) {
					hoverText.addExtra(ChatColor.GREEN + "Your ping is better than average");
					latencyText.addExtra("" + ChatColor.GREEN + playerLatency + "ms");
				} else if (playerLatency < average * 1.1) {
					hoverText.addExtra(ChatColor.RED + "Your ping is worse than average");
					latencyText.addExtra("" + ChatColor.RED + playerLatency + "ms");
				} else {
					hoverText.addExtra(ChatColor.YELLOW + "Your ping is average");
					latencyText.addExtra("" + ChatColor.YELLOW + playerLatency + "ms");
				}
				latencyText
						.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponent[] { hoverText }));

			} else {
				latencyText.addExtra("Average Ping: ");
				ChatColor color;
				if (average < 50) {
					color = ChatColor.DARK_GREEN;
				} else if (average < 100) {
					color = ChatColor.GREEN;
				} else if (average < 150) {
					color = ChatColor.YELLOW;
				} else {
					color = ChatColor.RED;
				}
				latencyText.addExtra("" + color + average + "ms");
			}
			responseText.addExtra("\n");
			responseText.addExtra(latencyText);
		}

		// Loaded Chunks
		int loadedChunks = 0;
		for (World world : plugin.getServer().getWorlds()) {
			loadedChunks += world.getLoadedChunks().length;
		}

		TextComponent chunkText = new TextComponent("\n"+
				Strings.OUT_PREFIX + "Loaded Chunks: " + Strings.C_ACCENT + loadedChunks);

		if (isPlayer) {
			// Add hover for player
			TextComponent chunkHoverText = new TextComponent(
					Strings.C_DEFAULT + "Chunks per player: " + Strings.C_ACCENT + loadedChunks / players.length);
			chunkText
					.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponent[] { chunkHoverText }));
		} else {
			// Caller is console
			if (players.length != 0) {
				chunkText.addExtra("\n"+Strings.OUT_PREFIX + "Chunks per player: " + Strings.C_ACCENT + loadedChunks / players.length);
			}
		}
		responseText.addExtra(chunkText);

		// Plugins
		responseText.addExtra("\n");
		responseText.addExtra(PluginCommand.getPlugins(sender));

		// TPS
		// TODO: Show Server Ticks Per Second

		// RAM
		// TODO: Show used Memory

		sender.spigot().sendMessage(responseText);
		return true;
	}

}
