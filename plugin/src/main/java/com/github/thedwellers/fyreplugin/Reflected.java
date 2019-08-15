package com.github.thedwellers.fyreplugin;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.UUID;

import com.github.thedwellers.fyreplugin.exceptions.ReflectionFailedException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;

/**
 * Provides functions integrated into Net.Minecraft.Server via reflection Note:
 * due to the nature of reflection, this class is minecraft version-dependent!
 * <p>
 * ! Unoptimized. Should cache obtain classes for future reflection
 * @version Minecraft 1.14.4
 */
public abstract class Reflected {
	private static String version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3]
			+ ".";
	private static String nmsClass = "net.minecraft.server." + version;
	private static String obcClass = "org.bukkit.craftbukkit." + version;

	/**
	 * Serialize the provided {@link ItemStack} into an nbt string.
	 * <p>
	 * Due to the dependence on reflection, this method may fail entirely depending
	 * on the minecraft version. Please check {@link Reflected} for supported versions.
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
			// Get org.bukkit.inventory.CraftItemStack
			Class<?> craftItemStackClass = Class.forName(obcClass + "inventory.CraftItemStack");

			// Get org.bukkit.inventory.CraftItemStack#asNMSCopy(ItemStack)
			Method asNMSCopyMethod = craftItemStackClass.getMethod("asNMSCopy", ItemStack.class);

			// Get net.minecraft.ItemStack
			Class<?> nmsItemStackClass = Class.forName(nmsClass + "ItemStack");

			// Convert org.bukkit.inventory.ItemStack to net.minecraft.ItemStack using
			// org.bukkit.inventory.CraftItemStack#asNMSCopy(ItemStack)
			Object nmsItemStack = asNMSCopyMethod.invoke(null, item);

			// Get net.minecraft.NBTTagCompound
			Class<?> nmsNBTTagCompoundClass = Class.forName(nmsClass + "NBTTagCompound");

			// Get net.minecraft.ItemStack#save(net.minecraft.NBTTagCompound)
			Method nmsItemStackSaveMethod = nmsItemStackClass.getMethod("save", nmsNBTTagCompoundClass);

			// Create an instance of net.minecraft.NBTTagCompound
			Object nmsNBTTagCompoundObject = nmsNBTTagCompoundClass.newInstance();

			// use net.minecraft.NBTTagCompound#save(net.minecraft.NBTTagCompound) on
			// nmsItemStack
			Object nbtObject = nmsItemStackSaveMethod.invoke(nmsItemStack, nmsNBTTagCompoundObject);

			return nbtObject.toString();
		} catch (LinkageError | ClassNotFoundException | NoSuchMethodException | IllegalAccessException
				| InvocationTargetException | InstantiationException e) {
			throw new ReflectionFailedException(e);
		}
	}

	/**
	 * Converts the provided nbt string into an {@link ItemStack}
	 * <p>
	 * Returns {@code null} on an invalid nbt tag
	 * <p>
	 * Due to the dependence on reflection, this method may fail entirely depending
	 * on the minecraft version. Please check {@link Reflected} for supported versions.
	 * <pre>
	 * return (ItemStack) org.bukkit.craftbukkit.inventory.CraftItemStack
	 *      .asBukkitCopy(net.minecraft.item.ItemStack(net.minecraft.mojangsonParser.parse((nbt)));
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
			// Get net.minecraft.mojangsonParser
			Class<?> mojangsonParserClass = Class.forName(nmsClass + "MojangsonParser");

			// Get net.minecraft.mojangsonParser#parse(String)
			Method getTagFromJson = mojangsonParserClass.getMethod("parse", String.class);

			// Convert nbt (string) tag to component using
			// net.minecraft.mojangsonParser#parse(String)
			Object nbtItem = getTagFromJson.invoke(null, nbt);

			// Get net.minecraft.item.ItemStack
			Class<?> nmsItemStackClass = Class.forName(nmsClass + "ItemStack");

			// Get net.minecraft.nbt.NBTTagCompound
			Class<?> nbtTagCompoundClass = Class.forName(nmsClass + "NBTTagCompound");

			// Get net.minecraft.item.ItemStack#a(net.minecraft.nbt.NBTTagCompound)
			Method nmsItemStackFromNBT = nmsItemStackClass.getMethod("a", nbtTagCompoundClass);

			// Construct class using
			// net.minecraft.item.ItemStack#a(net.minecraft.nbt.NBTTagCompound)
			Object nmsItemStack = nmsItemStackFromNBT.invoke(null, nbtItem);

			// Get org.bukkit.craftbukkit.inventory.CraftItemStack
			Class<?> craftItemStackClass = Class.forName(obcClass + "inventory.CraftItemStack");

			// Get
			// org.bukkit.craftbukkit.inventory.CraftItemStack#asBukkitCopy(net.minecraft.item.ItemStack)
			Method nmsToBukkit = craftItemStackClass.getMethod("asBukkitCopy", nmsItemStackClass);

			// Convert nms ItemStack to craftbukkit Item Stack
			Object bukkitItemStack = nmsToBukkit.invoke(null, nmsItemStack);

			// Convert to spigot ItemStack and return
			return (ItemStack) bukkitItemStack;

		} catch (LinkageError | ClassNotFoundException | NoSuchMethodException | IllegalAccessException
				| InvocationTargetException e) {
			throw new ReflectionFailedException(e);
		}
	}

	/**
	 * Returns the full nbt in a textual format of the provided entity.
	 * <p>
	 * Due to the dependence on reflection, this method may fail entirely depending
	 * on the minecraft version. Please check {@link Reflected} for supported versions.
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
			// Get net.minecraft.Entity
			Class<?> nmsEntityClass = Class.forName(nmsClass + "Entity");

			// Get net.minecraft.NBTTagCompound
			Class<?> NBTTagCompoundClass = Class.forName(nmsClass + "NBTTagCompound");

			// Get net.minecraft.Entity#save(NBTTagCompound)
			Method entitySave = nmsEntityClass.getMethod("save", NBTTagCompoundClass);

			// net.minecraft.NBTTagCompound()
			Object nbtData = NBTTagCompoundClass.newInstance();

			// Get org.bukkit.craftbukkit.CraftEntity
			Class<?> bukkitEntity = Class.forName(obcClass + "entity.CraftEntity");

			// Get org.bukkit.craftbukkit.CraftEntity#getHandle()
			Method getHandle = bukkitEntity.getMethod("getHandle");

			// Get Craftbukkit entity
			Object craftBukkitEntity = bukkitEntity.cast(entity);

			// Get NMS entity
			Object nmsEntity = getHandle.invoke(craftBukkitEntity);

			// Save entity nbt to tag
			entitySave.invoke(nmsEntity, nbtData);

			// Get net.minecraft.NBTTagCompoundClass#toString()
			Method toString = NBTTagCompoundClass.getMethod("toString");

			return (String) toString.invoke(nbtData);

		} catch (LinkageError | ClassNotFoundException | NoSuchMethodException | IllegalAccessException
				| InstantiationException | InvocationTargetException e) {
			throw new ReflectionFailedException(e);
		}
	}

	/**
	 * Writes the provided nbt to the entity. Note that this must be a full valid
	 * nbt, otherwise errors may occur during gameplay.
	 * <p>
	 * Due to the dependence on reflection, this method may fail entirely depending
	 * on the minecraft version. Please check {@link Reflected} for supported versions.
	 * <pre>
	 * net.minecraft.NBTTagCompound nbtTag = net.minecraft.mojangsonParser.parse((nbt))
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
			// Get net.minecraft.mojangsonParser
			Class<?> mojangsonParserClass = Class.forName(nmsClass + "MojangsonParser");

			// Get net.minecraft.mojangsonParser#parse(String)
			Method getTagFromJson = mojangsonParserClass.getMethod("parse", String.class);

			// Convert nbt (string) tag to component using
			// net.minecraft.mojangsonParser#parse(String)
			Object nbtItem = getTagFromJson.invoke(null, nbt);

			// Get net.minecraft.Entity
			Class<?> nmsEntityClass = Class.forName(nmsClass + "Entity");

			// Get net.minecraft.NBTTagCompound
			Class<?> NBTTagCompoundClass = Class.forName(nmsClass + "NBTTagCompound");

			// Get net.minecraft.Entity#f(NBTTagCompound)
			Method entityF = nmsEntityClass.getMethod("f", NBTTagCompoundClass);

			// Get net.minecraft.Entity#getUniqueID()
			Method entityGetUUID = nmsEntityClass.getMethod("getUniqueID");

			// Get net.minecraft.Entity#a(UUID)
			Method entityA = nmsEntityClass.getMethod("a", UUID.class);

			// Get org.bukkit.craftbukkit.CraftEntity
			Class<?> bukkitEntity = Class.forName(obcClass + "entity.CraftEntity");

			// Get org.bukkit.craftbukkit.CraftEntity#getHandle()
			Method getHandle = bukkitEntity.getMethod("getHandle");

			// Get Craftbukkit entity
			Object craftBukkitEntity = bukkitEntity.cast(entity);

			// Get NMS entity
			Object nmsEntity = getHandle.invoke(craftBukkitEntity);

			// save UUID of nms entity
			UUID uuid = (UUID) entityGetUUID.invoke(nmsEntity);

			// force load entity with nbt data
			entityF.invoke(nmsEntity, nbtItem);

			// Reset uuid to saved uuid
			entityA.invoke(nmsEntity, uuid);

		} catch (LinkageError | ClassNotFoundException | NoSuchMethodException | IllegalAccessException
				| InvocationTargetException e) {
			throw new ReflectionFailedException(e);
		}
	}
}