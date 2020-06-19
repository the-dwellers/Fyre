package io.github.the_dwellers.fyreplugin.features;

import io.github.the_dwellers.fyreplugin.core.AbstractFeature;
import io.github.the_dwellers.fyreplugin.core.AbstractCommand;
import io.github.the_dwellers.fyreplugin.configuration.Strings;
import io.github.the_dwellers.fyreplugin.core.MinecraftVersion;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffectType;

import javax.inject.Inject;

/**
 * Functions dedicated to chat manipulation and formatting.
 */
public class ChatManagerFeature extends AbstractFeature implements Listener {
	@Inject
	private NBTAdapter nbtAdapter;

	@Override
	public MinecraftVersion getMinecraftVersion() {
		return nbtAdapter.getMinecraftVersion();
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	@Override
	public String getName() {
		return "Chat Manager";
	}

	@Override
	public boolean setup() {
		if (!nbtAdapter.isEnabled()) {
			return false;
		} else {
			plugin.getServer().getPluginManager().registerEvents(this, plugin);
			plugin.getCommand("armor").setExecutor(new ArmorCommand());
			plugin.getCommand("item").setExecutor(new ItemCommand());
			enabled = true;
		}
		return enabled;
	}

	/**
	 * Sends chat events into Fyre's {@link ChatManager}
	 * <p>
	 * This is almost guaranteed to be incompatible with any other chat systems!
	 */
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerJoin(AsyncPlayerChatEvent event) {
		// Cancel the chat event and dispatch the message to the ChatManager
		event.setCancelled(true);
		ChatManager.sendPlayerMessage(event.getPlayer(), event.getMessage());
	}

	@EventHandler()
	public void onPlayerDeath(PlayerDeathEvent event) {
		if (!(event.getEntity().getLastDamageCause() instanceof EntityDamageByEntityEvent)) {
			return;
		}

		EntityDamageByEntityEvent lastDamage = (EntityDamageByEntityEvent) event.getEntity().getLastDamageCause();
		if (lastDamage.getDamager() instanceof Player) {
			if (((Player) lastDamage.getDamager()).hasPotionEffect(PotionEffectType.INVISIBILITY)) {
				event.setDeathMessage(event.getEntity().getDisplayName() + " was killed by something invisible");
			}
		}
	}

	@EventHandler()
	public void onPlayerJoin(PlayerJoinEvent event) {
		// Custom join message
		event.setJoinMessage("");
		ChatManager.sendPlayerJoin(event.getPlayer());
	}

	@EventHandler()
	public void onPlayerJoin(PlayerQuitEvent event) {
		// Custom quit message
		event.setQuitMessage("");
		ChatManager.sendPlayerLeave(event.getPlayer());
	}

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

	/**
	 * Displays the currently held item to main chat
	 */
	public class ItemCommand extends AbstractCommand {
		@Override
		public String getPermission() {
			return "fyre.item.use";
		}

		@Override
		public boolean execute(CommandSender sender, Command command, String label, String[] args) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				TextComponent itemText = ChatManager.getItemText(ChatManager.getDisplayStackMainHand(player));
				if (itemText == null) {
					player.sendMessage(Strings.NO_ITEM_HELD);
				} else {
					ChatManager.sendPlayerMessage(player, itemText);
				}
			}
			return true;
		}
	}

}
