package io.github.the_dwellers.fyreplugin;

import io.github.the_dwellers.fyreplugin.configuration.Strings;
import io.github.the_dwellers.fyreplugin.features.*;
import io.github.the_dwellers.fyreplugin.util.MinecraftVersion;
import io.github.the_dwellers.fyreplugin.configuration.SupportedVersions;

// import net.milkbowl.vault.chat.Chat;
// import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
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
	public MinecraftVersion mcVersion;

	// No other way to do this? Complains about Type safety, but 'Class<?
	// extends AbstractFeature>[]' turns into 'Cannot create a generic array of
	// Class<? extends AbstractFeature>'
	public static Class<? extends AbstractFeature>[] features = new Class[] {
		Advancements.class, // Advancement Progression
		AIFixes.class, // AI Bugfixes and improvements
		BoatInventories.class, // Open boats like chests
		ChatManager.class, // Chat formatting
		ClientBreakItem.class, // Client tool break (Why is this not in the api‽)
		DaylightExtension.class, // Daylight Extending
		EntityAttributes.class, // Entity Value changes
		ItemFeatures.class, // Functionality relating to items
		LandTrampling.class, // Trample grass and crops into dirt
		Management.class, // Server management utilities
		Merchants.class, // Trade with NPCs and unlock levels
		NBTAdapter.class, // NBT functions such as saving, loading, generating chat text, etc...
		PlantHoeHarvest.class, // Right-click to harvest crops
		TagDataHolder.class, // Store arbitrary data in entity nbt
		TagInventory.class, // Store inventories in entity tags
	};

	public FyrePlugin() {
		instance = this;
	}

	public static FyrePlugin getInstance() {
		return instance;
	}

	@Override
	public void onEnable() {
		long tStart = System.currentTimeMillis();

		log.info(Strings.LOG_PREFIX + "Loading Experimental Feature Branch...");

		// Version string format is normally `git-Paper-1618 (MC: 1.12.2)`
		// We want `1.12.2`
		String versionString = Bukkit.getVersion().substring(Bukkit.getVersion().indexOf("(", 0) + 5,
				Bukkit.getVersion().length() - 1);

		try {
			// Attempt to parse mc version
			mcVersion = new MinecraftVersion(versionString);

		} catch (IllegalArgumentException e) {
			log.severe(Strings.LOG_PREFIX + "Unable to decipher Minecraft version from '" + versionString
					+ "' Fyre cannot safely load, and will now disable.");
			return;
		}

		log.info(Strings.LOG_PREFIX + "Setting up for " + mcVersion.toString());
		if (!mcVersion.equals(SupportedVersions.MC1152)) {
			log.warning(
					Strings.LOG_PREFIX + "Loading for unsupported minecraft version! Some features may be disabled!");
		}

		for (Class<? extends AbstractFeature> featureClass : features) {
			AbstractFeature feature;
			try {
				feature = featureClass.newInstance();

				if (mcVersion.compareTo(feature.getMinecraftVersion()) > -1) {
					boolean result = feature.setup(this);
					if (!result) {
						log.info("Failed to load " + feature.getName());
					}

				} else {
					log.info(Strings.LOG_PREFIX + "Skipped " + feature.getName() + ", requires MC v"
							+ feature.getMinecraftVersion().toString());
				}
			} catch (InstantiationException | IllegalAccessException e) {
				log.severe(Strings.LOG_PREFIX + "Malformed Feature :" + featureClass.getName());
			} catch (NoClassDefFoundError e) {
				log.info(Strings.LOG_PREFIX + "Skipped " + featureClass.getName() + ", unknown API");
			}
		}

		log.info(Strings.LOG_PREFIX + "Fyre Loaded in " + (System.currentTimeMillis() - tStart / 1000.0) + "s");

	}
}
