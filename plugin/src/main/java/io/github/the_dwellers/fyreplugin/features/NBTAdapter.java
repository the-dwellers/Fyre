package io.github.the_dwellers.fyreplugin.features;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.UUID;

import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;

import io.github.the_dwellers.fyreplugin.FyrePlugin;
import io.github.the_dwellers.fyreplugin.Reflected;
import io.github.the_dwellers.fyreplugin.configuration.SupportedVersions;
import io.github.the_dwellers.fyreplugin.exceptions.ReflectionFailedException;
import io.github.the_dwellers.fyreplugin.util.MinecraftVersion;

/**
 * NBTAdapter
 */
public class NBTAdapter implements AbstractFeature {

	public static MinecraftVersion minVersion = SupportedVersions.MIN;

	protected boolean enabled = false;
	protected static String name = "NBT Features";
	private static ClientBreakItem instance;

	public static ClientBreakItem getInstance() {
		if (instance == null) {
			instance = new ClientBreakItem();
		}
		return instance;
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
	public boolean setup(FyrePlugin plugin) {
		return false;
	}

	@Override
	public String getName() {
		return name;
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
			Class<?> nmsNBTTagCompoundClass = Reflected.getClass(Reflected.nmsClass + "NBTTagCompound");

			// Get net.minecraft.ItemStack#save(net.minecraft.NBTTagCompound)
			Method nmsItemStackSaveMethod = Reflected.getMethod("ItemStack#save");

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
			Method getTagFromJson = Reflected.getMethod("MojangsonParser#parse");

			// Convert nbt (string) tag to component using
			// net.minecraft.MojangsonParser#parse(String)
			Object nbtItem = getTagFromJson.invoke(null, nbt);

			// Get net.minecraft.item.ItemStack#a(net.minecraft.nbt.NBTTagCompound)
			Method nmsItemStackFromNBT = Reflected.getMethod("ItemStack#a");

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
			Class<?> NBTTagCompoundClass = Reflected.getClass(Reflected.nmsClass + "NBTTagCompound");

			// Get net.minecraft.Entity#save(NBTTagCompound)
			Method entitySave = Reflected.getMethod("Entity#save");

			// net.minecraft.NBTTagCompound()
			Object nbtData = NBTTagCompoundClass.newInstance();

			// Get org.bukkit.craftbukkit.CraftEntity
			Class<?> bukkitEntity = Reflected.getClass(Reflected.obcClass + "entity.CraftEntity");

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
			Method getTagFromJson = Reflected.getMethod("MojangsonParser#parse");

			// Convert nbt (string) tag to component using
			// net.minecraft.MojangsonParser#parse(String)
			Object nbtItem = getTagFromJson.invoke(null, nbt);

			// Get net.minecraft.Entity#f(NBTTagCompound)
			Method entityF = Reflected.getMethod("Entity#f");

			// Get net.minecraft.Entity#getUniqueID()
			Method entityGetUUID = Reflected.getMethod("Entity#getUniqueID");

			// Get net.minecraft.Entity#a(UUID)
			Method entityA = Reflected.getMethod("Entity#a");

			// Get org.bukkit.craftbukkit.CraftEntity
			Class<?> bukkitEntity = Reflected.getClass(Reflected.obcClass + "entity.CraftEntity");

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
