package io.github.the_dwellers.fyreplugin;

import ch.jalu.injector.Injector;
import ch.jalu.injector.InjectorBuilder;
import io.github.the_dwellers.fyreplugin.configuration.Strings;
import io.github.the_dwellers.fyreplugin.configuration.SupportedVersions;
import io.github.the_dwellers.fyreplugin.core.AbstractFeature;
import io.github.the_dwellers.fyreplugin.features.*;
import io.github.the_dwellers.fyreplugin.features.tagdata.TagDataHolderFeature;
import io.github.the_dwellers.fyreplugin.features.tagdata.TagInventoryFeature;
import io.github.the_dwellers.fyreplugin.core.MinecraftVersion;
import io.github.the_dwellers.fyreplugin.model.PluginConfig;
import io.github.the_dwellers.fyreplugin.provider.PluginConfigProvider;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.math.RoundingMode;
import java.text.DecimalFormat;
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
public class FyrePlugin extends JavaPlugin {

	private static final Logger log = Logger.getLogger("Minecraft");
	// No other way to do this? Complains about Type safety, but 'Class<?
	// extends AbstractFeature>[]' turns into 'Cannot create a generic array of
	// Class<? extends AbstractFeature>'
	@SuppressWarnings("unchecked")
	public static Class<? extends AbstractFeature>[] features = new Class[] {
			Development.class, // Development Features (must be enabled first)
			NBTAdapter.class, // NBT functions such as saving, loading, generating chat text, etc...
			TagDataHolderFeature.class, // Store arbitrary data in entity nbt
			TagInventoryFeature.class, // Store inventories in entity tags
			BoatInventories.class, // Open boats like chests
			Advancements.class, // Advancement Progression
			AIFixes.class, // AI Bugfixes and improvements
			// ChatManagerFeature.class, // Chat formatting
			ClientBreakItem.class, // Client tool break (Why is this not in the api‽)
			EntityAttributes.class, // Entity Value changes
			ItemFeatures.class, // Functionality relating to items
			LandTrampling.class, // Trample grass and crops into dirt
			Management.class, // Server management utilities
			Merchants.class, // Trade with NPCs and unlock levels
			PlantHoeHarvest.class, // Right-click to harvest crops
			Compost.class, // Compost extra items
			BloodMoon.class, // Blood moon event
			Mobs.class, // Mob tweaks, including equipment
			SpikyCactus.class, // Damage player when punching cactuses
	};
	public MinecraftVersion mcVersion;
	private Injector injector;

	public PluginConfig config;

	public FyrePlugin() {
	}

	@Override
	public void onEnable() {
		long tStart = System.currentTimeMillis();

		initialize();

		// Version string format is normally `git-Paper-1618 (MC: 1.12.2)`
		// We want `1.12.2`
		String versionString = Bukkit.getVersion().substring(Bukkit.getVersion().indexOf("(") + 5,
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
		if (mcVersion.compareTo(SupportedVersions.MC1164) != 0) {
			log.warning(
					Strings.LOG_PREFIX + "Loading for unsupported minecraft version! Some features may be disabled!");
		}

		for (Class<? extends AbstractFeature> featureClass : features) {
			try {
				AbstractFeature feature = injector.getSingleton(featureClass);

				if (mcVersion.compareTo(feature.getMinecraftVersion()) > -1) {
					boolean result = feature.setup();
					if (!result) {
						log.warning(Strings.LOG_PREFIX + "Failed to load " + feature.getName());
					} else {
						// Dev messages only work when the class is enabled, they fail silently
						// otherwise.
						injector.getSingleton(Development.class).devMsg("Loaded " + feature.getName());
					}
				} else {
					log.warning(Strings.LOG_PREFIX + "Skipped " + feature.getName() + ", requires MC v"
							+ feature.getMinecraftVersion().toString());
				}
			} catch (NullPointerException e) {
				log.severe(Strings.LOG_PREFIX + "Failed to instantiate Feature :" + featureClass.getName());
				if (injector.getSingleton(Development.class).isEnabled()) {
					e.printStackTrace();
				}
			} catch (Exception e) {
				// Unsure on the full list of exceptions possible from using
				// injector-instantiated singletons, they need to update their docs!
				log.severe(Strings.LOG_PREFIX + "Unknown severe error with feature : " + featureClass.getName()
						+ " please report this error!");
				e.printStackTrace();
			}
		}

		// Time taken to load
		DecimalFormat decimalFormat = new DecimalFormat("0.00");
		decimalFormat.setRoundingMode(RoundingMode.DOWN);
		log.info(Strings.LOG_PREFIX + "Fyre Loaded in "
				+ decimalFormat.format((System.currentTimeMillis() - tStart) / 1000.0) + "s");
	}

	private void initialize() {
		if (!getDataFolder().exists()) {
			boolean created = getDataFolder().mkdirs();
			if (!created)
				log.warning(Strings.LOG_PREFIX + "Plugin data folder wasn't created!");
		}

		injector = new InjectorBuilder().addDefaultHandlers("io.github.the_dwellers.fyreplugin").create();

		injector.register(FyrePlugin.class, this);

		injector.registerProvider(PluginConfig.class, PluginConfigProvider.class);

		config = injector.getSingleton(PluginConfig.class);
	}
}
