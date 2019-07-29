package com.github.thedwellers.fyreplugin.commands;

import java.text.DecimalFormat;

import com.github.thedwellers.fyreplugin.FyrePlugin;
import com.github.thedwellers.fyreplugin.configuration.Strings;

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

/**
 * StatusCommand
 */
public class StatusCommand implements CommandExecutor {

	private FyrePlugin plugin = FyrePlugin.getInstance();

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player[] players = plugin.getServer().getOnlinePlayers()
				.toArray(new Player[plugin.getServer().getOnlinePlayers().size()]);
		boolean isPlayer = sender instanceof Player;
		TextComponent responseText = new TextComponent();
		DecimalFormat decimalFormat = new DecimalFormat("0.00");

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

		TextComponent chunkText = new TextComponent(
				"\n" + Strings.OUT_PREFIX + "Loaded Chunks: " + Strings.C_ACCENT + loadedChunks);

		if (isPlayer) {
			// Add hover for player
			TextComponent chunkHoverText = new TextComponent(
					Strings.C_DEFAULT + "Chunks per player: " + Strings.C_ACCENT + loadedChunks / players.length);
			chunkText
					.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponent[] { chunkHoverText }));
		} else {
			// Caller is console
			if (players.length != 0) {
				chunkText.addExtra("\n" + Strings.OUT_PREFIX + "Chunks per player: " + Strings.C_ACCENT
						+ loadedChunks / players.length);
			}
		}
		responseText.addExtra(chunkText);

		// Plugins
		responseText.addExtra("\n");
		responseText.addExtra(PluginsCommand.getPlugins(sender));

		// TPS
		ChatColor[] tpsColors = new ChatColor[4];

		// tps[] = [1m, 5m, 15m, average]
		double[] tps = new double[4];
		tps[0] = plugin.getServer().getTPS()[0];
		tps[1] = plugin.getServer().getTPS()[1];
		tps[2] = plugin.getServer().getTPS()[2];
		tps[3] = (tps[0] + tps[1] + tps[2]) / 3;

		for (int i = 0; i < tps.length; i++) {
			if (tps[i] >= 20.0) {
				tpsColors[i] = ChatColor.DARK_GREEN;
			} else if (tps[i] > 19.95) {
				tpsColors[i] = ChatColor.GREEN;
			} else if (tps[i] > 18.5) {
				tpsColors[i] = ChatColor.YELLOW;
			} else if (tps[i] > 17.0) {
				tpsColors[i] = ChatColor.RED;
			} else {
				tpsColors[i] = ChatColor.DARK_RED;
			}
		}

		TextComponent tpsText = new TextComponent(
				Strings.OUT_PREFIX + "Ticks Per Second: " + tpsColors[0] + decimalFormat.format(tps[0]));

		if (isPlayer) {
			TextComponent hoverText = new TextComponent(
					"" + tpsColors[0] + decimalFormat.format(tps[0]) + " " + tpsColors[1] + decimalFormat.format(tps[1])
							+ " " + tpsColors[2] + decimalFormat.format(tps[2]) + "\n");
			switch (tpsColors[3]) {
			case DARK_GREEN:
				hoverText.addExtra(tpsColors[3] + "TPS is perfect");
				break;
			case GREEN:
				hoverText.addExtra(tpsColors[3] + "Unnoticeable TPS loss");
				break;
			case YELLOW:
				hoverText.addExtra(tpsColors[3] + "Some Minor Problems");
				break;
			case RED:
				hoverText.addExtra(tpsColors[3] + "Major Server Problems");
				break;
			default:
				hoverText.addExtra(tpsColors[3] + "Unplayable");
				break;
			}
			tpsText.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponent[] { hoverText }));
		} else {
			tpsText.addExtra(" " + tpsColors[1] + decimalFormat.format(tps[1]) + " " + tpsColors[2]
					+ decimalFormat.format(tps[2]));
		}
		responseText.addExtra("\n");
		responseText.addExtra(tpsText);

		// RAM
		TextComponent memoryText = new TextComponent(Strings.OUT_PREFIX);

		// Note that freeMemory() is the memory inside the JVM that is ready for new
		// objects In the case of the server, we are only concerned with the amount of
		// memory the process has reserved, and the maximum amount we can reserve.
		// We divide the values by 0x100000 to return the amount of memory used in MB

		// Maximum amount of memory the server process can use (-Xmx)
		long maxMemory = Runtime.getRuntime().maxMemory() / (int) 0x100000;

		// Memory already allocated to the JVM, excluding empty memory
		long usedMemory = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (int) 0x100000;

		if (maxMemory == Integer.MAX_VALUE) {
			// No max memory limit
			memoryText.addExtra("Using " + Strings.C_ACCENT + usedMemory + "MB " + Strings.C_DEFAULT + "of RAM");
		} else {
			ChatColor memoryColor;
			if (usedMemory > maxMemory * 0.9) {
				memoryColor = ChatColor.DARK_RED;
			} else if (usedMemory > maxMemory * 0.8) {
				memoryColor = ChatColor.RED;
			} else if (usedMemory > maxMemory * 0.7) {
				memoryColor = ChatColor.GOLD;
			} else if (usedMemory > maxMemory * 0.6) {
				memoryColor = ChatColor.YELLOW;
			} else if (usedMemory > maxMemory * 0.5) {
				memoryColor = ChatColor.GREEN;
			} else {
				memoryColor = ChatColor.DARK_GREEN;
			}
			memoryText.addExtra("Using " + memoryColor + usedMemory + "MB / " +
					maxMemory + "MB (" + decimalFormat.format((((double) usedMemory / (double) maxMemory) * 100)) + "%)" +
					Strings.C_DEFAULT + " of memory");
		}

		if (isPlayer) {
			TextComponent memoryHover = new TextComponent(Strings.C_DEFAULT + "Max Memory: " + Strings.C_ACCENT + maxMemory +
				Strings.C_DEFAULT + "\nAllocated Memory: " + Strings.C_ACCENT + (Runtime.getRuntime().totalMemory() / (int) 0x100000) +
				Strings.C_DEFAULT + "\nUsed Memory: " + Strings.C_ACCENT + usedMemory +
				Strings.C_DEFAULT + "\nUnused Memory: " + Strings.C_ACCENT + (Runtime.getRuntime().freeMemory() / (int) 0x100000) +
				Strings.C_DEFAULT + "\nFree Memory: " + Strings.C_ACCENT + (maxMemory - usedMemory) +
				Strings.C_MUTED + "\nAll values in Megabytes (MB)");

			memoryText.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponent[] { memoryHover }));
		}

		responseText.addExtra("\n");
		responseText.addExtra(memoryText);

		if (sender instanceof Player) {
			sender.spigot().sendMessage(responseText);

		} else {
			// Prepend a newline for console formatting
			TextComponent out = new TextComponent("\n");
			out.addExtra(responseText);
			sender.spigot().sendMessage(out);
		}
		return true;
	}

}
