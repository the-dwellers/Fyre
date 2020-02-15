package io.github.the_dwellers.fyreplugin;

import io.github.the_dwellers.fyreplugin.configuration.Strings;
import io.github.the_dwellers.fyreplugin.exceptions.ReflectionFailedException;
import io.github.the_dwellers.fyreplugin.util.MinecraftVersion;
import io.github.the_dwellers.fyreplugin.configuration.SupportedVersions;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Merchant;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.logging.Logger;
import java.util.UUID;

/**
 * Provides functions integrated into Net.Minecraft.Server via reflection Note:
 * due to the nature of reflection, this class is Minecraft version-dependent!
 * <p>
 *
 * @version Minecraft 1.14.4
 * @version Minecraft 1.15.1
 * @version Minecraft 1.15.2
 */
public abstract class Reflected {
	public static MinecraftVersion mcVersion;
	private static String packageVersion = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",")
			.split(",")[3] + ".";
	private static String nmsClass = "net.minecraft.server." + packageVersion;
	private static String obcClass = "org.bukkit.craftbukkit." + packageVersion;

	// Server supports custom merchant XP
	private static boolean merchantShowXP;

	private static HashMap<String, Class<?>> classCache;
	private static HashMap<String, Method> methodCache;
	private static Logger log;

	/**
	 * Get the named method from {@code classCache} If the class does not exist
	 * under the provided key, {@code null} is returned.
	 * <p>
	 * Equivalent to:
	 *
	 * <pre>
	 * return classCache.get(name);
	 * </pre>
	 *
	 * @param name Key of method
	 * @return {@link Class} stored under the named key. {@code Null} otherwise.
	 */
	private static Class<?> getClass(String name) {
		return classCache.get(name);
	}

	/**
	 * Cache a class to the {@code classCache}.
	 *
	 * @param name Name of the class to cache
	 * @return true if method was cached, false otherwise. A log is made on
	 *         failures.
	 */
	private static boolean cacheClass(String name) {
		try {
			classCache.put(name, Class.forName(name));
			return true;
		} catch (ClassNotFoundException e) {
			log.severe(Strings.LOG_PREFIX + "Failed to Cache " + name + " Class.");
			return false;
		}
	}

	/**
	 * Get the named method from {@code classCache} If the method does not exist
	 * under the provided key, {@code null} is returned.
	 * <p>
	 * Equivalent to:
	 *
	 * <pre>
	 * return methodCache.get(name);
	 * </pre>
	 *
	 * @param name Key of method
	 * @return {@link java.lang.reflect.Method} stored under the named key.
	 *         {@code Null} otherwise.
	 */
	private static Method getMethod(String name) {
		return methodCache.get(name);
	}

	/**
	 * Cache a method to the {@code methodCache}. Prints an error message to console
	 * if fails.
	 * <p>
	 * Works on <b>public</b> inherited and defined methods.
	 * <p>
	 * Use {@link Reflected#cacheDeclaredMethod(String, Class, Class...)} if the
	 * method is private.
	 *
	 * @param name           Name of the method in the format "Class#function" Where
	 *                       'function' is the exact name of the function to find.
	 *                       'Class' may be any value.
	 * @param class1         Class to reflect the function from.
	 * @param parameterTypes Parameters that the function takes
	 * @return true if method was cached, false otherwise. A log is made on
	 *         failures.
	 */
	private static boolean cacheMethod(String key, Class<?> class1, Class<?>... parameterTypes) {
		return cacheMethod(key.split("#")[1], key, class1, parameterTypes);
	}

	private static boolean cacheMethod(String name, String key, Class<?> class1, Class<?>... parameterTypes) {
		boolean succeeded = cacheMethodNoLog(name, key, class1, parameterTypes);
		if (!succeeded) {
			log.severe(Strings.LOG_PREFIX + "Failed to Cache " + class1.getName() + "#" + name + " Method.");
		}
		return succeeded;
	}

	/**
	 * Cache a method to the {@code methodCache}.
	 * <p>
	 * Works on <b>public</b> inherited and defined methods.
	 * <p>
	 * Use {@link Reflected#cacheDeclaredMethod(String, Class, Class...)} if the
	 * method is private.
	 *
	 * @param name           Name of the method in the format "Class#function" Where
	 *                       'function' is the exact name of the function to find.
	 *                       'Class' may be any value.
	 * @param class1         Class to reflect the function from.
	 * @param parameterTypes Parameters that the function takes
	 * @return true if method was cached, false otherwise. A log is made on
	 *         failures.
	 */
	private static boolean cacheMethodNoLog(String name, String key, Class<?> class1, Class<?>... parameterTypes) {
		try {
			methodCache.put(key, class1.getMethod(name, parameterTypes));
			return true;
		} catch (NoSuchMethodException e) {
			return false;
		}
	}

	/**
	 * Cache a method to the {@code methodCache}.
	 * <p>
	 * Only works on methods defined in the exact class given (Inherited methods are
	 * not included). <b>Works on private methods.</b>
	 * <p>
	 * It is recommended to use
	 * {@link Reflected#cacheMethod(String, Class, Class...)} if the method is not
	 * private.
	 *
	 * @param name           Name of the method in the format "Class#function" Where
	 *                       'function' is the exact name of the function to find.
	 *                       'Class' may be any value.
	 * @param class1         Class to reflect the function from.
	 * @param parameterTypes Parameters that the function takes
	 * @return true if method was cached, false otherwise. A log is made on
	 *         failures.
	 */
	private static boolean cacheDeclaredMethod(String name, Class<?> class1, Class<?>... parameterTypes) {
		try {
			Method method = class1.getDeclaredMethod(name.split("#")[1], parameterTypes);
			method.setAccessible(true);
			methodCache.put(name, method);
			return true;
		} catch (NoSuchMethodException e) {
			log.severe(
					Strings.LOG_PREFIX + "Failed to Cache " + class1.getName() + "#" + name.split("#")[1] + " Method.");
			return false;
		}
	}

	/**
	 * Setup and error check cached classes and methods used in reflection. If any
	 * errors are encountered, logs are made on the provided logger.
	 * <p>
	 * Unlike other caching solutions, reflections are cached statically to ensure
	 * any potential issues are caught before the plugin is enabled. This method
	 * ensures that all possibilities of data corruption can be avoided.
	 * <p>
	 * Any modifications that rely on a patched server are also checked, such as
	 * custom merchant modifications. A warning is produced for any missing patches.
	 *
	 * @param log Logger to output to.
	 * @return Integer representing reflection and patch state.
	 *         <ul>
	 *         <li>0 - Standard reflection failed.</li>
	 *         <li>1 - Server is fully patched.</li>
	 *         <li>2 - Standard reflections succeeded, but server is not patched
	 *         </li>
	 *         </ul>
	 */
	public static int setupCache(Logger log) {
		Reflected.log = log;

		// Version string format is normally `git-Paper-1618 (MC: 1.12.2)`
		// We want `1.12.2`
		String versionString = Bukkit.getVersion().substring(Bukkit.getVersion().indexOf("(", 0) + 5,
				Bukkit.getVersion().length() - 1);

		try {
			// Attempt to parse mc version
			Reflected.mcVersion = new MinecraftVersion(versionString);

		} catch (IllegalArgumentException e) {
			log.severe(Strings.LOG_PREFIX + "Unable to decipher Minecraft version from '" + versionString
					+ "' Fyre cannot safely load, and will now disable.");
			return 0;
		}

		log.info("Setting up for " + mcVersion.toString());
		if (!mcVersion.equals(SupportedVersions.MC1152)) {
			log.warning(
					Strings.LOG_PREFIX + "Loading for unsupported minecraft version! Some features may be disabled!");
		}

		classCache = new HashMap<String, Class<?>>();
		methodCache = new HashMap<String, Method>();

		// Reflection Setup
		if (SupportedVersions.MC1144.compareTo(SupportedVersions.MC1122) != -1) {

			// -------------- Classes
			cacheClass(nmsClass + "Entity");
			cacheClass(nmsClass + "EntityHuman");
			cacheClass(nmsClass + "EntityLiving");
			cacheClass(nmsClass + "EnumItemSlot");
			cacheClass(nmsClass + "IChatBaseComponent");
			cacheClass(nmsClass + "Items");
			cacheClass(nmsClass + "ItemStack");
			cacheClass(nmsClass + "MojangsonParser");
			cacheClass(nmsClass + "NBTTagCompound");

			cacheClass(obcClass + "entity.CraftEntity");
			cacheClass(obcClass + "entity.CraftHumanEntity");
			cacheClass(obcClass + "entity.CraftLivingEntity");
			cacheClass(obcClass + "inventory.CraftItemStack");
			cacheClass(obcClass + "inventory.CraftMerchantCustom");
			cacheClass(obcClass + "inventory.CraftMerchantCustom$MinecraftMerchant");

			// -------------- Methods

			// org.bukkit.craftbukkit.entity.CraftEntity
			cacheMethod("CraftEntity#getHandle", getClass(obcClass + "entity.CraftEntity"));

			// org.bukkit.craftbukkit.inventory.CraftItem
			cacheMethod("CraftItemStack#asBukkitCopy", getClass(obcClass + "inventory.CraftItemStack"),
					getClass(nmsClass + "ItemStack"));
			cacheMethod("CraftItemStack#asNMSCopy", getClass(obcClass + "inventory.CraftItemStack"), ItemStack.class);

			// org.bukkit.craftbukkit.inventory.CraftMerchantCustom
			cacheMethod("CraftMerchantCustom#getMerchant", getClass(obcClass + "inventory.CraftMerchantCustom"));
			cacheMethod("CraftMerchantCustom$MinecraftMerchant#getScoreboardDisplayName",
					getClass(obcClass + "inventory.CraftMerchantCustom$MinecraftMerchant"));

			// net.minecraft.server.Entity
			cacheMethod("Entity#a", getClass(nmsClass + "Entity"), UUID.class);
			cacheMethod("Entity#f", getClass(nmsClass + "Entity"), getClass(nmsClass + "NBTTagCompound"));
			cacheMethod("Entity#getUniqueID", getClass(nmsClass + "Entity"));
			cacheMethod("Entity#save", getClass(nmsClass + "Entity"), getClass(nmsClass + "NBTTagCompound"));



			// net.minecraft.server.MojangsonParser
			cacheMethod("MojangsonParser#parse", getClass(nmsClass + "MojangsonParser"), String.class);

			// net.minecraft.server.NBTTagCompound
			cacheMethod("NBTTagCompound#toString", getClass(nmsClass + "NBTTagCompound"));
		}

		if (mcVersion.compareTo(SupportedVersions.MC1144) != -1) {
			cacheClass(nmsClass + "BlockComposter");
			cacheClass(nmsClass + "IMaterial");
			cacheClass(nmsClass + "IMerchant");

			// net.minecraft.server.BlockComposter
			cacheDeclaredMethod("BlockComposter#a", getClass(nmsClass + "BlockComposter"), float.class,
				getClass(nmsClass + "IMaterial"));


			// net.minecraft.server.ItemStack
			cacheMethod("ItemStack#a", getClass(nmsClass + "ItemStack"), getClass(nmsClass + "NBTTagCompound")); // 1.13
			cacheMethod("ItemStack#save", getClass(nmsClass + "ItemStack"), getClass(nmsClass + "NBTTagCompound"));

			// net.minecraft.server.EntityHuman
			cacheMethod("EntityHuman#openTrade", getClass(nmsClass + "IMerchant"), getClass(nmsClass + "EntityHuman"),
					getClass(nmsClass + "IChatBaseComponent"), int.class);
			cacheMethod("EntityHuman#setTradingPlayer", getClass(nmsClass + "IMerchant"),
					getClass(nmsClass + "EntityHuman"));

			// net.minecraft.server.EnumItemSlot
			cacheMethod("EnumItemSlot#fromName", getClass(nmsClass + "EnumItemSlot"), String.class);

			// net.minecraft.server.EntityLiving
			cacheMethod("EntityLiving#broadcastItemBreak", getClass(nmsClass + "EntityLiving"),
					getClass(nmsClass + "EnumItemSlot"));
			if (methodCache.get("EntityLiving#broadcastItemBreak") == null) {
				cacheMethod("EntityLiving#broadcastItemBreak", "c", getClass(nmsClass + "EntityLiving"));
			}

		}
		log.info(Strings.LOG_PREFIX + "Cached " + (classCache.size() + methodCache.size()) + " reflections.");

		// Error checking
		int status = 1;

		// Check patch status
		try {
			getClass(obcClass + "inventory.CraftMerchantCustom$MinecraftMerchant").getDeclaredField("experience");
			getClass(obcClass + "inventory.CraftMerchantCustom$MinecraftMerchant").getDeclaredField("regularVillager");
			merchantShowXP = true;
		} catch (NoSuchFieldException e) {
			log.warning(Strings.LOG_PREFIX + "Unable to find experience and regularVillager fields on " + obcClass
					+ "inventory.CraftMerchantCustom$MinecraftMerchant");
			merchantShowXP = false;
			status = 2;
		}

		// if (classCache.size() != classCacheTarget) {
		// log.severe("Failed to reflect " + (classCacheTarget - classCache.size() + "
		// classes"));
		// status = 0;
		// }

		// if (methodCache.size() != methodCacheTarget) {
		// log.severe("Failed to reflect " + (methodCacheTarget - methodCache.size() + "
		// methods"));
		// status = 0;
		// }

		if (status == 0) {
			// Reflection Failed
			log.severe(
					"Fyre is unable to start due to failing to hook into Minecraft internals for mechanic tweaks.\n As a result, Fyre will now disable in order to prevent any data corruption.\n To fix this problem, please ensure that the current Minecraft version is supported.\n If you are still getting the problem. Please reinstall your server jar.");
			status = 0;
		}

		return status;
	}

	/**
	 * Serialize the provided {@link ItemStack} into an nbt string.
	 * <p>
	 * Due to the dependence on reflection, this method may fail entirely depending
	 * on the Minecraft version. Please check {@link Reflected} for supported
	 * versions.
	 *
	 * <pre>
	 * net.minecraft.server.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(itemStack);
	 * net.minecraft.server.NBTTagCompound compound = new NBTTagCompound();
	 * compound = nmsItemStack.save(compound);
	 * return compound.toString();
	 * </pre>
	 *
	 * @param item ItemStack to convert to nbt
	 * @return nbt of item
	 * @throws ReflectionFailedException thrown when any exception is encountered
	 *                                   during reflection
	 */
	public static String itemStackToNBT(ItemStack item) throws ReflectionFailedException {
		try {
			// Get org.bukkit.inventory.CraftItemStack#asNMSCopy(ItemStack)
			Method asNMSCopyMethod = getMethod("CraftItemStack#asNMSCopy");

			// Convert org.bukkit.inventory.ItemStack to net.minecraft.ItemStack using
			// org.bukkit.inventory.CraftItemStack#asNMSCopy(ItemStack)
			Object nmsItemStack = asNMSCopyMethod.invoke(null, item);

			// Get net.minecraft.NBTTagCompound
			Class<?> nmsNBTTagCompoundClass = getClass(nmsClass + "NBTTagCompound");

			// Get net.minecraft.ItemStack#save(net.minecraft.NBTTagCompound)
			Method nmsItemStackSaveMethod = getMethod("ItemStack#save");

			// Create an instance of net.minecraft.NBTTagCompound
			Object nmsNBTTagCompoundObject = nmsNBTTagCompoundClass.newInstance();

			// use net.minecraft.NBTTagCompound#save(net.minecraft.NBTTagCompound) on
			// nmsItemStack
			Object nbtObject = nmsItemStackSaveMethod.invoke(nmsItemStack, nmsNBTTagCompoundObject);

			return nbtObject.toString();
		} catch (LinkageError | IllegalAccessException | InvocationTargetException | InstantiationException e) {
			throw new ReflectionFailedException(e);
		}
	}

	/**
	 * Converts the provided nbt string into an {@link ItemStack}
	 * <p>
	 * Returns {@code null} on an invalid nbt tag
	 * <p>
	 * Due to the dependence on reflection, this method may fail entirely depending
	 * on the Minecraft version. Please check {@link Reflected} for supported
	 * versions.
	 *
	 * <pre>
	 * return (ItemStack) org.bukkit.craftbukkit.inventory.CraftItemStack
	 *      .asBukkitCopy(net.minecraft.item.ItemStack(net.minecraft.MojangsonParser.parse((nbt)));
	 * </pre>
	 *
	 * @param nbt NBT tag to convert to an ItemStack
	 * @return Returns ItemStack of the provided NBT. {@code null} if decoding
	 *         errors occur;
	 * @throws ReflectionFailedException thrown when any exception is encountered
	 *                                   during reflection
	 */
	public static ItemStack nbtToItem(String nbt) throws ReflectionFailedException {
		try {
			// Get net.minecraft.MojangsonParser#parse(String)
			Method getTagFromJson = getMethod("MojangsonParser#parse");

			// Convert nbt (string) tag to component using
			// net.minecraft.MojangsonParser#parse(String)
			Object nbtItem = getTagFromJson.invoke(null, nbt);

			// Get net.minecraft.item.ItemStack#a(net.minecraft.nbt.NBTTagCompound)
			Method nmsItemStackFromNBT = getMethod("ItemStack#a");

			// Construct class using
			// net.minecraft.item.ItemStack#a(net.minecraft.nbt.NBTTagCompound)
			Object nmsItemStack = nmsItemStackFromNBT.invoke(null, nbtItem);

			Method nmsToBukkit = getMethod("CraftItemStack#asBukkitCopy");

			// Convert nms ItemStack to CraftBukkit Item Stack
			Object bukkitItemStack = nmsToBukkit.invoke(null, nmsItemStack);

			// Convert to spigot ItemStack and return
			return (ItemStack) bukkitItemStack;

		} catch (LinkageError | IllegalAccessException | InvocationTargetException e) {
			throw new ReflectionFailedException(e);
		}
	}

	/**
	 * Returns the full nbt in a textual format of the provided entity.
	 * <p>
	 * Due to the dependence on reflection, this method may fail entirely depending
	 * on the Minecraft version. Please check {@link Reflected} for supported
	 * versions.
	 *
	 * <pre>
	 * net.minecraft.NBTTagCompound nbtData = new NBTTagCompound();
	 * net.minecraft.Entity nmsEntity = ((org.bukkit.craftbukkit.CraftEntity) entity).getHandle();
	 * nmsEntity.save(nbtData);
	 * return nbtData.toString();
	 * </pre>
	 *
	 * @param entity
	 * @return
	 * @throws ReflectionFailedException
	 */
	public static String getNBTOfEntity(Entity entity) throws ReflectionFailedException {
		try {
			// Get net.minecraft.NBTTagCompound
			Class<?> NBTTagCompoundClass = getClass(nmsClass + "NBTTagCompound");

			// Get net.minecraft.Entity#save(NBTTagCompound)
			Method entitySave = getMethod("Entity#save");

			// net.minecraft.NBTTagCompound()
			Object nbtData = NBTTagCompoundClass.newInstance();

			// Get org.bukkit.craftbukkit.CraftEntity
			Class<?> bukkitEntity = getClass(obcClass + "entity.CraftEntity");

			// Get org.bukkit.craftbukkit.CraftEntity#getHandle()
			Method getHandle = getMethod("CraftEntity#getHandle");

			// Get CraftBukkit entity
			Object craftBukkitEntity = bukkitEntity.cast(entity);

			// Get NMS entity
			Object nmsEntity = getHandle.invoke(craftBukkitEntity);

			// Save entity nbt to tag
			entitySave.invoke(nmsEntity, nbtData);

			// Get net.minecraft.NBTTagCompoundClass#toString()
			Method toString = getMethod("NBTTagCompound#toString");

			return (String) toString.invoke(nbtData);

		} catch (LinkageError | IllegalAccessException | InstantiationException | InvocationTargetException e) {
			throw new ReflectionFailedException(e);
		}
	}

	/**
	 * Writes the provided nbt to the entity. Note that this must be a full valid
	 * nbt, otherwise errors may occur during gameplay.
	 * <p>
	 * Due to the dependence on reflection, this method may fail entirely depending
	 * on the Minecraft version. Please check {@link Reflected} for supported
	 * versions.
	 *
	 * <pre>
	 * net.minecraft.NBTTagCompound nbtTag = net.minecraft.MojangsonParser.parse((nbt))
	 * net.minecraft.Entity ent = org.bukkit.craftbukkit.entity.CraftEntity.getHandle()
	 * UUID uuid = ent.getUniqueID();
	 * ent.f(nbtTag);
	 * ent.a(uuid);
	 * </pre>
	 *
	 * @param nbt    Nbt to write
	 * @param entity entity to write to
	 * @throws ReflectionFailedException thrown when any exception is encountered
	 *                                   during reflection
	 */
	public static void saveNBTToEntity(String nbt, Entity entity) throws ReflectionFailedException {
		try {
			// Get net.minecraft.MojangsonParser#parse(String)
			Method getTagFromJson = getMethod("MojangsonParser#parse");

			// Convert nbt (string) tag to component using
			// net.minecraft.MojangsonParser#parse(String)
			Object nbtItem = getTagFromJson.invoke(null, nbt);

			// Get net.minecraft.Entity#f(NBTTagCompound)
			Method entityF = getMethod("Entity#f");

			// Get net.minecraft.Entity#getUniqueID()
			Method entityGetUUID = getMethod("Entity#getUniqueID");

			// Get net.minecraft.Entity#a(UUID)
			Method entityA = getMethod("Entity#a");

			// Get org.bukkit.craftbukkit.CraftEntity
			Class<?> bukkitEntity = getClass(obcClass + "entity.CraftEntity");

			// Get org.bukkit.craftbukkit.CraftEntity#getHandle()
			Method getHandle = getMethod("CraftEntity#getHandle");

			// Get CraftBukkit entity
			Object craftBukkitEntity = bukkitEntity.cast(entity);

			// Get NMS entity
			Object nmsEntity = getHandle.invoke(craftBukkitEntity);

			// save UUID of nms entity
			UUID uuid = (UUID) entityGetUUID.invoke(nmsEntity);

			// force load entity with nbt data
			entityF.invoke(nmsEntity, nbtItem);

			// Reset uuid to saved uuid
			entityA.invoke(nmsEntity, uuid);

		} catch (LinkageError | IllegalAccessException | InvocationTargetException e) {
			throw new ReflectionFailedException(e);
		}
	}

	/**
	 * Add Rotten Flesh to the compost's list of allowed resources.
	 * <p>
	 * Due to the dependence on reflection, this method may fail entirely depending
	 * on the Minecraft version. Please check {@link Reflected} for supported
	 * versions.
	 *
	 * <pre>
	 * net.minecraft.server.BlockComposter.a(0.5F, Items.ROTTEN_FLESH)
	 * </pre>
	 *
	 * @param item ItemStack to convert to nbt
	 * @return nbt of item
	 * @throws ReflectionFailedException thrown when any exception is encountered
	 *                                   during reflection
	 */
	public static void patchCompost() throws ReflectionFailedException {
		try {
			// Get BlockComposter class
			Class<?> nmsBlockComposterClass = getClass(nmsClass + "BlockComposter");

			// Get Items Class
			Class<?> nmsItemsClass = getClass(nmsClass + "Items");

			// Get the ROTTEN_FLESH Item from the Items class
			Field itemsRottenFleshField = nmsItemsClass.getField("ROTTEN_FLESH");

			// Get the add method from the BlockComposter class
			Method a = getMethod("BlockComposter#a");
			a.setAccessible(true);

			// Execute the add method using the ROTTEN_FLESH item
			a.invoke(nmsBlockComposterClass, 0.5F, itemsRottenFleshField.get(nmsItemsClass));

		} catch (LinkageError | IllegalAccessException | NoSuchFieldException | InvocationTargetException e) {
			throw new ReflectionFailedException(e);
		}
	}

	/**
	 * Open a merchant trade window using custom xp and level amounts.
	 * <p>
	 * <b> ! Displaying xp and level amounts currently relies on a patch to the
	 * server jar! </b> If {@link Reflected#setupCache(Logger)} failed to find
	 * patched fields, no alterations are made.
	 * <p>
	 * Due to the dependence on reflection, this method may fail entirely depending
	 * on the Minecraft version. Please check {@link Reflected} for supported
	 * versions.
	 *
	 * <pre>
	 * org.bukkit.craftbukkit.CraftMerchantCustom mcMerchant = ((org.bukkit.craftbukkit.inventory.CraftMerchantCustom) merchant).getMerchant();
	 * org.bukkit.craftbukkit.inventory.CraftMerchantCustom.MinecraftMerchant mcMinecraftMerchant = (org.bukkit.craftbukkit.inventory.CraftMerchantCustom.MinecraftMerchant) mcMerchant
	 * net.minecraft.server.IChatBaseComponent name = mcMerchant.getScoreboardDisplayName();
	 * org.bukkit.entity.CraftHumanEntity playerEntity = (org.bukkit.entity.CraftHumanEntity) player
	 * mcMerchant.setTradingPlayer(playerEntity.getHandle());
	 * mcMerchant.openTrade(playerEntity, name, level);
	 * </pre>
	 *
	 * @param player   Player to display interface to
	 * @param merchant Merchant inventory with trades
	 * @param level    Level of merchant (1 - 5)
	 * @param xp       Experience of trader
	 * @throws ReflectionFailedException thrown when any exception is encountered
	 *                                   during reflection
	 */
	public static void showTraderUI(Player player, Merchant merchant, int level, int xp)
			throws ReflectionFailedException {
		try {
			Class<?> bukkitCraftMerchantCustom = getClass(obcClass + "inventory.CraftMerchantCustom");
			Class<?> bukkitCraftMinecraftMerchant = getClass(
					obcClass + "inventory.CraftMerchantCustom$MinecraftMerchant");
			Class<?> bukkitCraftHumanEntity = getClass(obcClass + "entity.CraftHumanEntity");
			Method getScoreboardDisplayName = getMethod(
					"CraftMerchantCustom$MinecraftMerchant#getScoreboardDisplayName");
			Method setTradingPlayer = getMethod("EntityHuman#setTradingPlayer");
			Method getHandle = getMethod("CraftEntity#getHandle");
			Method openTrade = getMethod("EntityHuman#openTrade");
			Method getMerchant = getMethod("CraftMerchantCustom#getMerchant");

			// org.bukkit.craftbukkit.CraftMerchantCustom mcMerchant =
			// ((org.bukkit.craftbukkit.inventory.CraftMerchantCustom)
			// merchant).getMerchant();
			Object mcMerchant = getMerchant.invoke(bukkitCraftMerchantCustom.cast(merchant));

			// org.bukkit.craftbukkit.inventory.CraftMerchantCustom.MinecraftMerchant
			// mcMinecraftMerchant =
			// (org.bukkit.craftbukkit.inventory.CraftMerchantCustom.MinecraftMerchant)
			// mcMerchant
			Object mcMinecraftMerchant = bukkitCraftMinecraftMerchant.cast(mcMerchant);

			// net.minecraft.server.IChatBaseComponent name =
			// mcMerchant.getScoreboardDisplayName();
			Object name = getScoreboardDisplayName.invoke(mcMinecraftMerchant);

			// org.bukkit.entity.CraftHumanEntity playerEntity =
			// (org.bukkit.entity.CraftHumanEntity) player
			Object playerEntity = getHandle.invoke(bukkitCraftHumanEntity.cast(player));

			if (merchantShowXP) {
				// Change CraftMerchantCustom fields to represent xp
				Field experience = bukkitCraftMinecraftMerchant.getDeclaredField("experience");
				experience.setAccessible(true);
				experience.set(mcMinecraftMerchant, xp);

				Field regularVillager = bukkitCraftMinecraftMerchant.getDeclaredField("regularVillager");
				regularVillager.setAccessible(true);
				regularVillager.set(mcMinecraftMerchant, true);
			}

			// mcMerchant.setTradingPlayer(playerEntity.getHandle());
			setTradingPlayer.invoke(mcMerchant, playerEntity);

			// mcMerchant.openTrade(playerEntity, name, level);
			openTrade.invoke(mcMerchant, playerEntity, name, level);

		} catch (LinkageError | IllegalAccessException | NoSuchFieldException | InvocationTargetException e) {
			throw new ReflectionFailedException(e);
		}
	}

	/**
	 * Play the effect of an entity's item breaking.
	 * <p>
	 * Due to the dependence on reflection, this method may fail entirely depending
	 * on the Minecraft version. Please check {@link Reflected} for supported
	 * versions.
	 *
	 * <pre>
	 * net.minecraft.server.LivingEntity nmsEntity = ((org.bukkit.craftbukkit.craftLivingEntity) entity).getHandle();
	 * nmsEntity.c(net.minecraft.server.EquipItemSlot.fromName(slotName));
	 * </pre>
	 *
	 * @param entity Entity who's equipment will be broken
	 * @param slot   Slot to break
	 * @throws ReflectionFailedException thrown when any exception is encountered
	 *                                   during reflection
	 */
	public static void breakEquipmentEffect(LivingEntity entity, EquipmentSlot slot) throws ReflectionFailedException {
		try {

			String slotName = "mainhand";

			// Slot name are defined in `net.minecraft.server.EnumItemSlot`
			switch (slot) {
			case HEAD:
				slotName = "head";
				break;
			case CHEST:
				slotName = "chest";
				break;
			case LEGS:
				slotName = "legs";
				break;
			case FEET:
				slotName = "feet";
				break;
			case OFF_HAND:
				slotName = "offhand";
				break;
			case HAND:
			default:
				slotName = "mainhand";
				break;
			}

			Object nmsEnum = getMethod("EnumItemSlot#fromName").invoke(getClass(nmsClass + "EnumItemSlot"), slotName);

			Object nmsEntity = getMethod("CraftEntity#getHandle")
					.invoke(getClass(obcClass + "entity.CraftLivingEntity").cast(entity));

			Object nmsLivingEntity = getClass(nmsClass + "EntityLiving").cast(nmsEntity);

			getMethod("EntityLiving#c").invoke(nmsLivingEntity, nmsEnum);

		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new ReflectionFailedException(e);
		}
	}

	/**
	 * Break equipment in the entity's slot. An attempt will be made to play
	 * breaking animations, but will fail silently if errors occur.
	 *
	 * @param entity Entity of equipment to break
	 * @param slot   Slot of equipment
	 */
	public static void breakEquipment(LivingEntity entity, EquipmentSlot slot) {
		try {
			breakEquipmentEffect(entity, slot);
		} catch (ReflectionFailedException e) {
			// Failed to play breaking effect.
			// the effect is only cosmetic, so we don't care.
		}

		// Remove equipment depending on slot
		switch (slot) {
		case HEAD:
			entity.getEquipment().getHelmet().subtract();
			break;
		case CHEST:
			entity.getEquipment().getChestplate().subtract();
			break;
		case LEGS:
			entity.getEquipment().getLeggings().subtract();
			break;
		case FEET:
			entity.getEquipment().getBoots().subtract();
			break;
		case OFF_HAND:
			entity.getEquipment().getItemInOffHand().subtract();
			break;
		case HAND:
		default:
			entity.getEquipment().getItemInMainHand().subtract();
			break;
		}
	}
}
