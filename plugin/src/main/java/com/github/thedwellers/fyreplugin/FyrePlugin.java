package com.github.thedwellers.fyreplugin;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.github.thedwellers.fyreplugin.commands.*;
import com.github.thedwellers.fyreplugin.configuration.ServerOperations;
import com.github.thedwellers.fyreplugin.entity.TagInventory;
import com.github.thedwellers.fyreplugin.events.*;
import com.github.thedwellers.fyreplugin.exceptions.ReflectionFailedException;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.permission.Permission;
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
	public ProtocolManager protocolManager;
	private Chat vaultChat;
	private Permission vaultPerms;

	public FyrePlugin() {
		instance = this;
	}

	public static FyrePlugin getInstance() {
		return instance;
	}

	public Chat getVaultChat() {
		return vaultChat;
	}

	public Permission getVaultPerms() {
		return vaultPerms;
	}

	@Override
	public void onEnable() {
		setupDependencies();
		serverSetUp();
		registerCommands();
		registerListeners();
		patchNMS();
	}

	@Override
	public void onDisable() {
		for (Player player : Bukkit.getOnlinePlayers()) {
			if (player.getOpenInventory().getTopInventory().getHolder().getClass() == TagInventory.class) {
				((TagInventory) player.getOpenInventory().getTopInventory().getHolder()).closeInventory();
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
			log.severe("[Fyre] Unable to Patch Composter due to reflection errors. /n" + e.getMessage());
		}
	}

	/**
	 * Registers all commands provided by the Fyre plugin.
	 * <p>
	 * Also attempts to replace {@code bukkit:plugins} with {@code fyre:plugins}
	 */
	private void registerCommands() {
		this.getCommand("armor").setExecutor(new ArmorCommand());
		this.getCommand("bank").setExecutor(new BankCommand());
		this.getCommand("debug").setExecutor(new DebugCommand());
		this.getCommand("item").setExecutor(new ItemCommand());
		this.getCommand("list").setExecutor(new ListCommand());
		this.getCommand("merchant").setExecutor(new MerchantCommand());
		this.getCommand("money").setExecutor(new MoneyCommand());
		// Remove Bukkit plugin command
		getServer().getCommandMap().getCommand("plugins");
		this.getCommand("plugins").setExecutor(new PluginsCommand());
		this.getCommand("status").setExecutor(new StatusCommand());
	}

	/**
	 * Register all event listeners provided by the Fyre plugin.
	 */
	private void registerListeners() {
		getServer().getPluginManager().registerEvents(new BlockBreak(), this);
		getServer().getPluginManager().registerEvents(new Dismount(), this);
		getServer().getPluginManager().registerEvents(new PlayerChat(), this);
		getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
		getServer().getPluginManager().registerEvents(new PlayerLeave(), this);
		getServer().getPluginManager().registerEvents(new PlayerMove(), this);
		getServer().getPluginManager().registerEvents(new PlayerPreProcessorCommand(), this);
		getServer().getPluginManager().registerEvents(new BoatClick(), this);
		getServer().getPluginManager().registerEvents(new InventoryClosed(), this);
		getServer().getPluginManager().registerEvents(new VehicleDestroy(), this);
		getServer().getPluginManager().registerEvents(new TickEnd(), this);
		getServer().getPluginManager().registerEvents(new OnDamage(), this);
		getServer().getPluginManager().registerEvents(new PlayerRespawn(), this);
		getServer().getPluginManager().registerEvents(new EntitySpawn(), this);
		getServer().getPluginManager().registerEvents(new FallDamage(), this);
		// getServer().getPluginManager().registerEvents(new PlayerInteraction(protocolManager), this);
		// getServer().getPluginManager().registerEvents(new BrickHit(), this);
		getServer().getPluginManager().registerEvents(new PlayerDeath(), this);
	}

	private void serverSetUp() {
		ServerOperations.createPlayerDataDirectory();
	}

	private void setupDependencies() {
		if (getServer().getPluginManager().getPlugin("Vault") == null) {
			log.warning("[Fyre] Vault is not installed, some features may be unavailable");
		} else {
			RegisteredServiceProvider<Chat> chatService = getServer().getServicesManager().getRegistration(Chat.class);
			RegisteredServiceProvider<Permission> permissionService = getServer().getServicesManager()
					.getRegistration(Permission.class);

			if (permissionService == null) {
				log.info("[Fyre] No Permission Services registered. Default permissions will be used");
			} else {
				this.vaultPerms = permissionService.getProvider();
			}

			if (chatService == null) {
				log.info("[Fyre] No Chat Services registered. Will use default Fyre values");
			} else {
				this.vaultChat = chatService.getProvider();
			}
		}

		if (getServer().getPluginManager().getPlugin("Protocol Lib") == null) {
			log.warning("[Fyre] Protocol Lib not installed, bricks will not be throwable");
		} else {
			protocolManager = ProtocolLibrary.getProtocolManager();
		}
	}
}
