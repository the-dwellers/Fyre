package io.github.the_dwellers.fyreplugin.features;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.Plugin;

import io.github.the_dwellers.fyreplugin.FyrePlugin;
import io.github.the_dwellers.fyreplugin.commands.AbstractCommand;
import io.github.the_dwellers.fyreplugin.Feature;
import io.github.the_dwellers.fyreplugin.configuration.Strings;
import io.github.the_dwellers.fyreplugin.configuration.SupportedVersions;
import io.github.the_dwellers.fyreplugin.util.MinecraftVersion;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

/**
 * Management
 */
public class Management extends Feature implements Listener {

	public static MinecraftVersion minVersion = SupportedVersions.MIN;

	protected boolean enabled = false;
	protected static String name = "Management";
	private static Management featureInstance;

	public static Management getInstance() {
		if (featureInstance == null) {
			featureInstance = new Management();
		}
		return featureInstance;
	}

	@Override
	public MinecraftVersion getMinecraftVersion() {
		return minVersion;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public boolean setup(FyrePlugin plugin) {
		plugin.getCommand("status").setExecutor(new StatusCommand());
		plugin.getCommand("plugins").setExecutor(new PluginsCommand());
		plugin.getCommand("list").setExecutor(new ListCommand());
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		enabled = true;
		return isEnabled();
	}

	/**
	 * Events related to heuristics and logs to assist administration.
	 * <p>
	 * This includes:
	 * <ul>
	 * <li>Printing of IP address of connecting users</li>
	 * </ul>
	 */
	@EventHandler()
	public static void onConnect(PlayerLoginEvent event) {
		ChatManager.sendStaffMessage(
				"'" + event.getPlayer().getName() + "' is connecting from " + event.getAddress().toString());
	}

	/**
	 * View server status information, such as ram usage, plugins, players, tps, etc
	 */
	public class StatusCommand extends AbstractCommand {
		private FyrePlugin plugin = FyrePlugin.getInstance();

		@Override
		public String getPermission() {
			return "fyre.status.use";
		}

		@Override
		public boolean execute(CommandSender sender, Command command, String label, String[] args) {
			Player[] players = plugin.getServer().getOnlinePlayers()
					.toArray(new Player[plugin.getServer().getOnlinePlayers().size()]);
			boolean isPlayer = sender instanceof Player;
			TextComponent responseText = new TextComponent();
			DecimalFormat decimalFormat = new DecimalFormat("0.00");
			decimalFormat.setRoundingMode(RoundingMode.DOWN);

			// Players
			responseText.addExtra(getPlayers(sender, false));

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
					latencyText.setHoverEvent(
							new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponent[] { hoverText }));

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
				chunkText.setHoverEvent(
						new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponent[] { chunkHoverText }));
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
			responseText.addExtra(getPlugins(sender, false));

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
				TextComponent hoverText = new TextComponent("" + tpsColors[0] + decimalFormat.format(tps[0]) + " "
						+ tpsColors[1] + decimalFormat.format(tps[1]) + " " + tpsColors[2]
						+ decimalFormat.format(tps[2]) + "\n");
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
			TextComponent memoryText = new TextComponent(Strings.OUT_PREFIX + Strings.C_DEFAULT);

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
				memoryText.addExtra("Using " + Strings.C_ACCENT + usedMemory + "MB " + Strings.C_DEFAULT + "of memory");
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
				memoryText.addExtra("Using " + memoryColor + usedMemory + "MB / " + maxMemory + "MB ("
						+ decimalFormat.format((((double) usedMemory / (double) maxMemory) * 100)) + "%)"
						+ Strings.C_DEFAULT + " of memory");
			}

			if (isPlayer) {
				TextComponent memoryHover = new TextComponent(Strings.C_DEFAULT + "Max Memory: " + Strings.C_ACCENT
						+ maxMemory + Strings.C_DEFAULT + "\nAllocated Memory: " + Strings.C_ACCENT
						+ (Runtime.getRuntime().totalMemory() / (int) 0x100000) + Strings.C_DEFAULT + "\nUsed Memory: "
						+ Strings.C_ACCENT + usedMemory + Strings.C_DEFAULT + "\nUnused Memory: " + Strings.C_ACCENT
						+ (Runtime.getRuntime().freeMemory() / (int) 0x100000) + Strings.C_DEFAULT + "\nFree Memory: "
						+ Strings.C_ACCENT + (maxMemory - usedMemory) + Strings.C_MUTED
						+ "\nAll values in Megabytes (MB)");

				memoryText.setHoverEvent(
						new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponent[] { memoryHover }));
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

	private static TextComponent getPlugins(CommandSender sender, boolean longForm) {
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
			return pluginLongForm(plugins);
		}

		TextComponent pluginText = new TextComponent(Strings.OUT_PREFIX + "In total, there are " + Strings.C_ACCENT
				+ enabled + Strings.C_DEFAULT + " enabled plugins");
		if (isPlayer) {
			pluginText.setHoverEvent(
					new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponent[] { pluginLongForm(plugins) }));
		}
		return pluginText;
	}

	private static TextComponent pluginLongForm(Plugin[] plugins) {
		TextComponent text = new TextComponent();
		for (int i = 0; i < plugins.length; i++) {
			Plugin plugin = plugins[i];
			ChatColor color = plugin.isEnabled() ? ChatColor.DARK_GREEN : ChatColor.RED;
			text.addExtra("" + color + plugin.getDescription().getName() + Strings.C_DEFAULT + " v"
					+ Strings.C_ACCENT + plugin.getDescription().getVersion());
			if (i < plugins.length - 1) {
				text.addExtra("\n");
			}
		}
		return text;
	}

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
	}

	private static TextComponent playerLongForm(Player[] players) {

		if (players.length == 0) {
			return new TextComponent();
		}

		TextComponent longFormText = new TextComponent(Strings.C_DEFAULT);

		// Construct list of player names
		for (int i = 0; i < players.length; i++) {
			Player player = players[i];
			longFormText.addExtra(player.getDisplayName());

			if (i + 1 != players.length) {
				longFormText.addExtra(Strings.C_DEFAULT);
			}
		}
		return longFormText;
	}

	public static TextComponent getPlayers(CommandSender src, boolean longForm) {
		boolean isPlayer = src instanceof Player;

		Player[] players = src.getServer().getOnlinePlayers()
				.toArray(new Player[src.getServer().getOnlinePlayers().size()]);
		if (players.length == 0) {
			// No players online
			return new TextComponent(Strings.OUT_PREFIX + "There are no players online");
		}

		if (longForm) {
			// Return longform output
			return playerLongForm(players);
		}

		TextComponent text = new TextComponent();
		if (players.length == 1) {
			// English single
			text.addExtra(Strings.OUT_PREFIX + "There is " + Strings.C_ACCENT + players.length + Strings.C_DEFAULT
					+ " player online");
		} else {
			text.addExtra(Strings.OUT_PREFIX + "There is " + Strings.C_ACCENT + players.length + Strings.C_DEFAULT
					+ " players online");

		}

		if (!isPlayer) {
			return text;
		}

		text.setHoverEvent(
				new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponent[] { playerLongForm(players) }));
		return text;
	}

	/**
	 * List the players currently online on the server.
	 */
	public class ListCommand extends AbstractCommand {

		@Override
		public String getPermission() {
			return "fyre.list.use";
		}

		@Override
		public boolean execute(CommandSender sender, Command command, String label, String[] args) {
			sender.spigot().sendMessage(getPlayers(sender, !(sender instanceof Player)));
			return true;
		}
	}

	/**
	 * Event to be executed when a command is attempted to be ran by a player.
	 * <p>
	 * This is currently present to catch all calls to {@code bukkit:plugins} and
	 * convert them to {@code fyre:plugins}
	 */
	public class PlayerPreProcessorCommand implements Listener {

		private FyrePlugin plugin = FyrePlugin.getInstance();

		@EventHandler(priority = EventPriority.HIGH)
		public void onPlayerCommand(PlayerCommandPreprocessEvent event) {
			String command = event.getMessage().split(" ")[0].toLowerCase();

			// Capture plugin command calls, and run fyre:plugins
			if (command.equals("/pl") || command.equals("/plugins")) {
				event.setCancelled(true);
				plugin.getServer().dispatchCommand(event.getPlayer(), "fyre:plugins");
			}
		}
	}
}
