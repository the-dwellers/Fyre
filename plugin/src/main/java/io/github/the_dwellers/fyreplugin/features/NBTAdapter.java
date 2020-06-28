package io.github.the_dwellers.fyreplugin.features;

import io.github.the_dwellers.fyreplugin.core.AbstractFeature;
import io.github.the_dwellers.fyreplugin.core.Reflected;
import io.github.the_dwellers.fyreplugin.configuration.SupportedVersions;
import io.github.the_dwellers.fyreplugin.exceptions.ReflectionFailedException;
import io.github.the_dwellers.fyreplugin.core.MinecraftVersion;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.UUID;

/**
 * Adapter for general NBT interactions with Minecraft.
 * <p>
 * This is the most likely part to break during version changes!
 */
public class NBTAdapter extends AbstractFeature {

	public MinecraftVersion getMinecraftVersion() {
		return SupportedVersions.MC1132;
	}

	public String getName() {
		return "NBT Features";
	}

	public boolean setup() {
		if (isEnabled()) {
			// Enable-gate
			return isEnabled();
		}

		if (Reflected.getClass("ItemStack") == null) {
			if (!Reflected.cacheClass(Reflected.nmsClass + "ItemStack")) {
				plugin.getLogger().warning("Unable to Cache ItemStack");
				return false;
			}
		}

		if (Reflected.getClass("NBTTagCompound") == null) {
			if (!Reflected.cacheClass(Reflected.nmsClass + "NBTTagCompound")) {
				plugin.getLogger().warning("Unable to Cache NBTTagCompound");
				return false;
			}
		}

		if (Reflected.getMethod("NBTTagCompound#toString") == null) {
			if (!Reflected.cacheClassMethod("NBTTagCompound#toString")) {
				plugin.getLogger().warning("Unable to Cache NBTTagCompound#toString");
				return false;
			}
		}

		if (Reflected.getMethod("ItemStack#save") == null) {
			// Save Item to NBT
			if (!Reflected.cacheClassMethod("ItemStack#save", Reflected.getClass("NBTTagCompound"))) {
				plugin.getLogger().warning("Unable to Cache ItemStack#save");
				return false;
			}
		}

		if (Reflected.getMethod("ItemStack#load") == null) {
			// Load item From NBT (Requires 1.13)
			if (!Reflected.cacheMethod("a", "ItemStack#load", Reflected.getClass("ItemStack"), Reflected.getClass("NBTTagCompound"))) {
				plugin.getLogger().warning("Unable to Cache ItemStack#a");
				return false;
			}
		}

		if (Reflected.getClass("MojangsonParser") == null) {
			if (!Reflected.cacheClass(Reflected.nmsClass + "MojangsonParser")) {
				plugin.getLogger().warning("Unable to Cache MojangsonParser");
				return false;
			}
		}
		if (Reflected.getMethod("MojangsonParser#parse") == null) {
			if (!Reflected.cacheClassMethod("MojangsonParser#parse", String.class)) {
				plugin.getLogger().warning("Unable to Cache MojangsonParser#parse");
				return false;
			}
		}

		if (Reflected.getClass("Entity") == null) {
			if (!Reflected.cacheClass(Reflected.nmsClass + "Entity")) {
				plugin.getLogger().warning("Unable to Cache Entity");
				return false;
			}
		}
		if (Reflected.getMethod("Entity#getUniqueID") == null) {
			if (!Reflected.cacheClassMethod("Entity#getUniqueID")) {
				plugin.getLogger().warning("Unable to Cache Entity#getUniqueID");
				return false;
			}
		}

		if (Reflected.getMethod("Entity#save") == null) {
			if (!Reflected.cacheClassMethod("Entity#save", Reflected.getClass("NBTTagCompound"))) {
				plugin.getLogger().warning("Unable to Cache Entity#save");
				return false;
			}
		}
		if (Reflected.getMethod("Entity#load") == null) {
			if (plugin.mcVersion.compareTo(SupportedVersions.MC1152) < 1) {
				Reflected.cacheMethod("f", "Entity#load", Reflected.getClass("Entity"), Reflected.getClass("NBTTagCompound"));

			} else {
				Reflected.cacheMethod("load", "Entity#load", Reflected.getClass("Entity"), Reflected.getClass("NBTTagCompound"));
			}

			if (Reflected.getMethod("Entity#load") == null) {
				plugin.getLogger().warning("Unable to Cache Entity#load");
				return false;
			}
		}
		if (Reflected.getMethod("Entity#setUUID") == null) {
			if (plugin.mcVersion.compareTo(SupportedVersions.MC1152) < 1) {
				Reflected.cacheMethod("a", "Entity#setUUID", Reflected.getClass("Entity"), UUID.class);
			} else {
				Reflected.cacheMethod("a_", "Entity#setUUID", Reflected.getClass("Entity"), UUID.class);
			}

			if (Reflected.getMethod("Entity#setUUID") == null) {
				plugin.getLogger().warning("Unable to Cache Entity#setUUID");
				return false;
			}
		}
		if (Reflected.getClass("CraftItemStack") == null) {
			if (!Reflected.cacheClass(Reflected.obcClass + "inventory.CraftItemStack")) {
				plugin.getLogger().warning("Unable to Cache CraftItemStack");
				return false;
			}
		}
		if (Reflected.getMethod("CraftItemStack#asBukkitCopy") == null) {
			if (!Reflected.cacheClassMethod("CraftItemStack#asBukkitCopy", Reflected.getClass("ItemStack"))) {
				plugin.getLogger().warning("Unable to Cache CraftItemStack#asBukkitCopy");
				return false;
			}
		}

		if (Reflected.getMethod("CraftItemStack#asNMSCopy") == null) {
			if (!Reflected.cacheMethod("CraftItemStack#asNMSCopy", Reflected.getClass("CraftItemStack"), ItemStack.class)) {
				plugin.getLogger().warning("Unable to Cache CraftItemStack#asNMSCopy");
				return false;
			}
		}

		if (Reflected.getClass("CraftEntity") == null) {
			if (!Reflected.cacheClass(Reflected.obcClass + "entity.CraftEntity")) {
				plugin.getLogger().warning("Unable to Cache CraftEntity");
				return false;
			}
		}
		if (Reflected.getMethod("CraftEntity#getHandle") == null) {
			if (!Reflected.cacheClassMethod("CraftEntity#getHandle")) {
				plugin.getLogger().warning("Unable to Cache CraftEntity#getHandle");
				return false;
			}
		}

		enabled = true;
		return isEnabled();
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
			Method asNMSCopyMethod = Reflected.getMethod("CraftItemStack#asNMSCopy");

			// Convert org.bukkit.inventory.ItemStack to net.minecraft.ItemStack using
			// org.bukkit.inventory.CraftItemStack#asNMSCopy(ItemStack)
			Object nmsItemStack = asNMSCopyMethod.invoke(null, item);

			// Get net.minecraft.NBTTagCompound
			Class<?> nmsNBTTagCompoundClass = Reflected.getClass("NBTTagCompound");

			// Get net.minecraft.ItemStack#save(net.minecraft.NBTTagCompound)
			Method nmsItemStackSaveMethod = Reflected.getMethod("ItemStack#save");

			// Create an instance of net.minecraft.NBTTagCompound
			Object nmsNBTTagCompoundObject = nmsNBTTagCompoundClass.getDeclaredConstructor().newInstance();

			// use net.minecraft.NBTTagCompound#save(net.minecraft.NBTTagCompound) on
			// nmsItemStack
			Object nbtObject = nmsItemStackSaveMethod.invoke(nmsItemStack, nmsNBTTagCompoundObject);

			return nbtObject.toString();
		} catch (LinkageError | IllegalAccessException | InvocationTargetException | InstantiationException | NoSuchMethodException e) {
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
	 * errors occur;
	 * @throws ReflectionFailedException thrown when any exception is encountered
	 *                                   during reflection
	 */
	public static ItemStack nbtToItem(String nbt) throws ReflectionFailedException {
		try {
			// Get net.minecraft.MojangsonParser#parse(String)
			Method getTagFromJson = Reflected.getMethod("MojangsonParser#parse");

			// Convert nbt (string) tag to component using
			// net.minecraft.MojangsonParser#parse(String)
			Object nbtItem = getTagFromJson.invoke(null, nbt);

			// Get net.minecraft.item.ItemStack#a(net.minecraft.nbt.NBTTagCompound)
			Method nmsItemStackFromNBT = Reflected.getMethod("ItemStack#load");

			// Construct class using
			// net.minecraft.item.ItemStack#a(net.minecraft.nbt.NBTTagCompound)
			Object nmsItemStack = nmsItemStackFromNBT.invoke(null, nbtItem);

			Method nmsToBukkit = Reflected.getMethod("CraftItemStack#asBukkitCopy");

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
			Class<?> NBTTagCompoundClass = Reflected.getClass("NBTTagCompound");

			// Get net.minecraft.Entity#save(NBTTagCompound)
			Method entitySave = Reflected.getMethod("Entity#save");

			// net.minecraft.NBTTagCompound()
			Object nbtData = NBTTagCompoundClass.getDeclaredConstructor().newInstance();

			// Get org.bukkit.craftbukkit.CraftEntity
			Class<?> bukkitEntity = Reflected.getClass("CraftEntity");

			// Get org.bukkit.craftbukkit.CraftEntity#getHandle()
			Method getHandle = Reflected.getMethod("CraftEntity#getHandle");

			// Get CraftBukkit entity
			Object craftBukkitEntity = bukkitEntity.cast(entity);

			// Get NMS entity
			Object nmsEntity = getHandle.invoke(craftBukkitEntity);

			// Save entity nbt to tag
			entitySave.invoke(nmsEntity, nbtData);

			// Get net.minecraft.NBTTagCompoundClass#toString()
			Method toString = Reflected.getMethod("NBTTagCompound#toString");

			return (String) toString.invoke(nbtData);

		} catch (LinkageError | IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException e) {
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
			Method getTagFromJson = Reflected.getMethod("MojangsonParser#parse");

			// Convert nbt (string) tag to component using
			// net.minecraft.MojangsonParser#parse(String)
			Object nbtItem = getTagFromJson.invoke(null, nbt);

			// Get net.minecraft.Entity#f(NBTTagCompound)
			Method entityF = Reflected.getMethod("Entity#load");

			// Get net.minecraft.Entity#getUniqueID()
			Method entityGetUUID = Reflected.getMethod("Entity#getUniqueID");

			// Get net.minecraft.Entity#a(UUID)
			Method entityA = Reflected.getMethod("Entity#setUUID");

			// Get org.bukkit.craftbukkit.CraftEntity
			Class<?> bukkitEntity = Reflected.getClass("CraftEntity");

			// Get org.bukkit.craftbukkit.CraftEntity#getHandle()
			Method getHandle = Reflected.getMethod("CraftEntity#getHandle");

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

}
