package io.github.the_dwellers.fyreplugin;

import io.github.the_dwellers.fyreplugin.commands.*;
import io.github.the_dwellers.fyreplugin.configuration.ServerOperations;
import io.github.the_dwellers.fyreplugin.configuration.Strings;
import io.github.the_dwellers.fyreplugin.entity.TagInventory;
import io.github.the_dwellers.fyreplugin.events.*;
import io.github.the_dwellers.fyreplugin.exceptions.ReflectionFailedException;
// import net.milkbowl.vault.chat.Chat;
// import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

/**
 * The Fyre Plugin is a helper plugin to implement features such as server
 * administration tools, merchant functionality, and a few other features.
 * <p>
 * For more information about the Fyre project please view the documentation.
 *
 * @author WYVERN, Brandagot, ChargedByte
 * @version 0.1
 * @see https://github.com/the-dwellers/Fyre
 */
public final class FyrePlugin extends JavaPlugin {

	private static final Logger log = Logger.getLogger("Minecraft");
	private static FyrePlugin instance;
	// private Chat vaultChat;
	// private Permission vaultPerms;

	public FyrePlugin() {
		instance = this;
	}

	public static FyrePlugin getInstance() {
		return instance;
	}

	// public Chat getVaultChat() {
	//  return vaultChat;
	// }

	// public Permission getVaultPerms() {
	//  return vaultPerms;
	// }

	@Override
	public void onEnable() {
		switch (Reflected.setupCache(log)) {
		case 0:
			// If Fyre failed to setup reflections, stop loading the plugin
			this.setEnabled(false);
			return;
		case 2:
			log.warning(Strings.LOG_PREFIX + "Server jar is not patched with merchant fixes. XP and level bars will be disabled on traders.");
			break;
		case 1:
			log.info(Strings.LOG_PREFIX + "Server jar is fully patched.");
			break;
		}

		setupDependencies();
		serverSetUp();
		registerCommands();
		registerListeners();
		patchNMS();
	}

	@Override
	public void onDisable() {
		for (Player player : Bukkit.getOnlinePlayers()) {
			if (player.getOpenInventory().getTopInventory().getHolder() != null) {
				if (player.getOpenInventory().getTopInventory().getHolder().getClass() == TagInventory.class) {
					((TagInventory) player.getOpenInventory().getTopInventory().getHolder()).closeInventory();
				}
			}
		}
	}

	/**
	 * Executes reflected code to patch vanilla Minecraft
	 */
	private void patchNMS() {
		try {
			Reflected.patchCompost();
		} catch (ReflectionFailedException e) {
			log.severe(Strings.LOG_PREFIX + "Unable to Patch Composter due to reflection errors. \n" + e.getMessage());
		}
	}

	/**
	 * Registers all commands provided by the Fyre plugin.
	 * <p>
	 * Also attempts to replace {@code bukkit:plugins} with {@code fyre:plugins}
	 */
	private void registerCommands() {
		// Remove Bukkit plugin command
		getServer().getCommandMap().getCommand("plugins");

		this.getCommand("armor").setExecutor(new ArmorCommand());
		this.getCommand("debug").setExecutor(new DebugCommand());
		this.getCommand("item").setExecutor(new ItemCommand());
		this.getCommand("list").setExecutor(new ListCommand());
		this.getCommand("money").setExecutor(new MoneyCommand());
		this.getCommand("plugins").setExecutor(new PluginsCommand());
		this.getCommand("status").setExecutor(new StatusCommand());
		this.getCommand("trader").setExecutor(new TraderCommand());
	}

	/**
	 * Register all event listeners provided by the Fyre plugin.
	 */
	private void registerListeners() {
		getServer().getPluginManager().registerEvents(new BlockBreak(), this);
		getServer().getPluginManager().registerEvents(new BoatClick(), this);
		getServer().getPluginManager().registerEvents(new CropClick(), this);
		getServer().getPluginManager().registerEvents(new Dismount(), this);
		getServer().getPluginManager().registerEvents(new EntitySpawn(), this);
		getServer().getPluginManager().registerEvents(new FallDamage(), this);
		getServer().getPluginManager().registerEvents(new InventoryClosed(), this);
		getServer().getPluginManager().registerEvents(new KnowledgeBookUse(), this);
		getServer().getPluginManager().registerEvents(new OnDamage(), this);
		getServer().getPluginManager().registerEvents(new PlayerChat(), this);
		getServer().getPluginManager().registerEvents(new PlayerDeath(), this);
		getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
		getServer().getPluginManager().registerEvents(new PlayerLeave(), this);
		getServer().getPluginManager().registerEvents(new PlayerMove(), this);
		getServer().getPluginManager().registerEvents(new PlayerPreProcessorCommand(), this);
		getServer().getPluginManager().registerEvents(new PlayerRespawn(), this);
		getServer().getPluginManager().registerEvents(new staffLog(), this);
		getServer().getPluginManager().registerEvents(new TickEnd(), this);
		getServer().getPluginManager().registerEvents(new TraderClick(), this);
		getServer().getPluginManager().registerEvents(new VehicleDestroy(), this);
		getServer().getPluginManager().registerEvents(new CropTrample(), this);
	}

	private void serverSetUp() {
		ServerOperations.createPlayerDataDirectory();
	}

	private void setupDependencies() {
		// ! Upstream Dependency issue
		// if (getServer().getPluginManager().getPlugin("Vault") == null) {
			log.warning(Strings.LOG_PREFIX + "Vault is not installed, some features may be unavailable"); /*
		} else {
			RegisteredServiceProvider<Chat> chatService = getServer().getServicesManager().getRegistration(Chat.class);
			RegisteredServiceProvider<Permission> permissionService = getServer().getServicesManager()
					.getRegistration(Permission.class);

			if (permissionService == null) {
				log.info(Strings.LOG_PREFIX + "No Permission Services registered. Default permissions will be used");
			} else {
				this.vaultPerms = permissionService.getProvider();
			}

			if (chatService == null) {
				log.info(Strings.LOG_PREFIX + "No Chat Services registered. Will use default Fyre values");
			} else {
				this.vaultChat = chatService.getProvider();
			}
		}*/
	}
}
