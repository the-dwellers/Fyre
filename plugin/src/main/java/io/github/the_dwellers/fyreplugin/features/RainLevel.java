package io.github.the_dwellers.fyreplugin.features;

import io.github.the_dwellers.fyreplugin.core.AbstractFeature;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.inject.Inject;

import org.bukkit.entity.Player;

import io.github.the_dwellers.fyreplugin.configuration.SupportedVersions;
import io.github.the_dwellers.fyreplugin.core.MinecraftVersion;
import io.github.the_dwellers.fyreplugin.core.Reflected;
import io.github.the_dwellers.fyreplugin.exceptions.ReflectionFailedException;

/**
 * Custom Client Rain Levels
 */
public class RainLevel extends AbstractFeature {

	@Inject
	private Development development;

	public MinecraftVersion getMinecraftVersion() {
		return SupportedVersions.MIN;
	}

	public String getName() {
		return "Rain Level";
	}

	public boolean setup() {
		if (isEnabled()) {
			// Enable-gate
			return isEnabled();
		}

		if (Reflected.getClass("EntityPlayer") == null) {
			if (!Reflected.cacheClass(Reflected.nmsClass + "EntityPlayer")) {
				plugin.getLogger().warning("Unable to Cache EntityPlayer");
				return false;
			}
		}

		// EntityPlayer#setPlayerRainLevel(float rainLevel) is added via a custom patch
		// Check patch status
		try {
			Reflected.getClass("EntityPlayer").getDeclaredField("setRainLevel");
			Reflected.getClass("EntityPlayer").getDeclaredField("pluginRainMaxLevel");
			Reflected.getClass("EntityPlayer").getDeclaredField("pluginMaxThunderLevel");
			Reflected.getClass("EntityPlayer").getDeclaredField("setThunderLevel");
		} catch (NoSuchFieldException e) {
			plugin.getLogger().warning(
					"Server jar is not patched with custom rain level support. Some lighting effects will be disabled.");
			development.devWarning("Unable to find setRainLevel and pluginMaxThunderLevel fields on " + Reflected.nmsClass
					+ "EntityPlayer");
				return false;
		}

		// Patch fields are present, attempt to get EntityPlayer#setPlayerRainLevel

		if (Reflected.getClass("CraftHumanEntity") == null) {
			if (!Reflected.cacheClass(Reflected.obcClass + "entity.CraftHumanEntity")) {
				plugin.getLogger().warning("Unable to Cache CraftHumanEntity");
				return false;
			}
		}

		if (Reflected.getMethod("CraftEntity#getHandle") == null) {
			if (!Reflected.cacheClassMethod("CraftEntity#getHandle")) {
				plugin.getLogger().warning("Unable to Cache CraftEntity#getHandle");
				return false;
			}
		}

		if (Reflected.getMethod("EntityPlayer#setPlayerRainLevel") == null) {
			if (!Reflected.cacheClassMethod("EntityPlayer#setPlayerRainLevel", float.class)) {
				plugin.getLogger().warning("Unable to Cache EntityPlayer#setPlayerRainLevel");
				plugin.getLogger().severe("The server jar is mispatched, as the patched fields EntityPlayer#setRainLevel and EntityPlayer#pluginRainMaxLevel are present, but EntityPlayer#setPlayerRainLevel() is not. Please report this!");
				return false;
			}
		}

		if (Reflected.getMethod("EntityPlayer#setPlayerThunderLevel") == null) {
			if (!Reflected.cacheClassMethod("EntityPlayer#setPlayerThunderLevel", float.class)) {
				plugin.getLogger().warning("Unable to Cache EntityPlayer#setPlayerThunderLevel");
				plugin.getLogger().severe("The server jar is mispatched, as the patched fields EntityPlayer#pluginMaxThunderLevel and EntityPlayer#setThunderLevel are present, but EntityPlayer#setPlayerThunderLevel() is not. Please report this!");
				return false;
			}
		}

		enabled = true;
		return isEnabled();
	}

	/**
	 * Send a custom rainlevel packet to the player, allowing different
	 * environment colours as a side-effect.
	 * <p>
	 * <b> ! This currently relies on a patch to the server jar to avoid
	 * clamping! server jar! </b> If {@link RainLevel#setup()} failed to find
	 * patched fields, no alterations are made, and rain level will default to
	 * stormy weather.
	 * <p>
	 * Due to the dependence on reflection, this method may fail entirely
	 * depending on the Minecraft version. Please check {@link RainLevel#getMinecraftVersion()} for
	 * supported versions.
	 *
	 * <pre>
	 * net.minecraft.server.Entity craftHumanEntity nmsEntity = ((org.bukkit.entity.CraftHumanEntity) player).getHandle;
	 * net.minecraft.server.EntityPlayer nmsPlayer = (net.minecraft.server.EntityPlayer) nmsEntity;
	 * nmsPlayer.setPlayerRainLevel(rainLevel);
	 * </pre>
	 *
	 * @param player    Player to change rainlevel of.
	 * @param rainLevel Rainlevel to set
	 * @throws ReflectionFailedException thrown when any exception is
	 *                                   encountered during reflection
	 */
	public static void setRainLevel(Player player, float rainLevel)
			throws ReflectionFailedException {
		try {

			Class<?> bukkitCraftHumanEntity = Reflected.getClass("CraftHumanEntity");
			Class<?> nmsEntityPlayer = Reflected.getClass("EntityPlayer");
			Method getHandle = Reflected.getMethod("CraftEntity#getHandle");
			Method setPlayerRainLevel = Reflected.getMethod("EntityPlayer#setPlayerRainLevel");

			// net.minecraft.server.Entity nmsEntity = ((org.bukkit.entity.CraftHumanEntity) player).getHandle();
			Object nmsEntity = getHandle.invoke(bukkitCraftHumanEntity.cast(player));

			// net.minecraft.server.EntityPlayer nmsPlayer = (net.minecraft.server.EntityPlayer) nmsEntity;
	        Object nmsPlayer = nmsEntityPlayer.cast(nmsEntity);

			// nmsEntityHuman.setPlayerRainLevel(rainLevel);
			setPlayerRainLevel.invoke(nmsPlayer, rainLevel);

		} catch (LinkageError | IllegalAccessException | InvocationTargetException e) {
			throw new ReflectionFailedException(e);
		}
	}

	/**
	 * Send a custom thunderLevel packet to the player, allowing different
	 * environment colours as a side-effect.
	 * <p>
	 * <b> ! This currently relies on a patch to the server jar to avoid
	 * clamping! server jar! </b> If {@link RainLevel#setup()} failed to find
	 * patched fields, no alterations are made, and rain level will default to
	 * stormy weather.
	 * <p>
	 * Due to the dependence on reflection, this method may fail entirely
	 * depending on the Minecraft version. Please check {@link RainLevel#getMinecraftVersion()} for
	 * supported versions.
	 *
	 * <pre>
	 * net.minecraft.server.Entity craftHumanEntity nmsEntity = ((org.bukkit.entity.CraftHumanEntity) player).getHandle;
	 * net.minecraft.server.EntityPlayer nmsPlayer = (net.minecraft.server.EntityPlayer) nmsEntity;
	 * nmsPlayer.setPlayerRainLevel(rainLevel);
	 * </pre>
	 *
	 * @param player    Player to change thunderLevel of.
	 * @param thunderLevel ThunderLevel to set
	 * @throws ReflectionFailedException thrown when any exception is
	 *                                   encountered during reflection
	 */
	public static void setThunderLevel(Player player, float thunderLevel)
			throws ReflectionFailedException {
		try {

			Class<?> bukkitCraftHumanEntity = Reflected.getClass("CraftHumanEntity");
			Class<?> nmsEntityPlayer = Reflected.getClass("EntityPlayer");
			Method getHandle = Reflected.getMethod("CraftEntity#getHandle");
			Method setPlayerThunderLevel = Reflected.getMethod("EntityPlayer#setPlayerThunderLevel");

			// net.minecraft.server.Entity nmsEntity = ((org.bukkit.entity.CraftHumanEntity) player).getHandle();
			Object nmsEntity = getHandle.invoke(bukkitCraftHumanEntity.cast(player));

			// net.minecraft.server.EntityPlayer nmsPlayer = (net.minecraft.server.EntityPlayer) nmsEntity;
	        Object nmsPlayer = nmsEntityPlayer.cast(nmsEntity);

			// nmsEntityHuman.setPlayerRainLevel(rainLevel);
			setPlayerThunderLevel.invoke(nmsPlayer, thunderLevel);

		} catch (LinkageError | IllegalAccessException | InvocationTargetException e) {
			throw new ReflectionFailedException(e);
		}
	}
}
