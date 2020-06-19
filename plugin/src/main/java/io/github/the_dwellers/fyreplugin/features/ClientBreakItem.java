package io.github.the_dwellers.fyreplugin.features;

import io.github.the_dwellers.fyreplugin.core.AbstractFeature;
import io.github.the_dwellers.fyreplugin.core.Reflected;
import io.github.the_dwellers.fyreplugin.configuration.SupportedVersions;
import io.github.the_dwellers.fyreplugin.exceptions.ReflectionFailedException;
import io.github.the_dwellers.fyreplugin.core.MinecraftVersion;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EquipmentSlot;

import java.lang.reflect.InvocationTargetException;

/**
 * Break a tool, including breaking effects.
 */
public class ClientBreakItem extends AbstractFeature {
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

			Object nmsEnum = Reflected.getMethod("EnumItemSlot#fromName")
					.invoke(Reflected.getClass(Reflected.nmsClass + "EnumItemSlot"), slotName);

			Object nmsEntity = Reflected.getMethod("CraftEntity#getHandle")
					.invoke(Reflected.getClass(Reflected.obcClass + "entity.CraftLivingEntity").cast(entity));

			Object nmsLivingEntity = Reflected.getClass(Reflected.nmsClass + "EntityLiving").cast(nmsEntity);

			Reflected.getMethod("EntityLiving#broadcastItemBreak").invoke(nmsLivingEntity, nmsEnum);

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
				entity.getEquipment().getHelmet().setAmount(entity.getEquipment().getHelmet().getAmount() - 1);
				break;
			case CHEST:
				entity.getEquipment().getChestplate().setAmount(entity.getEquipment().getChestplate().getAmount() - 1);
				break;
			case LEGS:
				entity.getEquipment().getLeggings().setAmount(entity.getEquipment().getLeggings().getAmount() - 1);
				break;
			case FEET:
				entity.getEquipment().getBoots().setAmount(entity.getEquipment().getBoots().getAmount() - 1);
				break;
			case OFF_HAND:
				entity.getEquipment().getItemInOffHand().setAmount(entity.getEquipment().getItemInOffHand().getAmount() - 1);
				break;
			case HAND:
			default:
				entity.getEquipment().getItemInMainHand().setAmount(entity.getEquipment().getItemInMainHand().getAmount() - 1);
				break;
		}
	}

	@Override
	public MinecraftVersion getMinecraftVersion() {
		return SupportedVersions.MC1144;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	@Override
	public String getName() {
		return "Client Break Item";
	}

	@Override
	public boolean setup() {
		if (!enabled) {
			if (Reflected.getClass("EnumItemSlot") == null) {
				if (!Reflected.cacheClass(Reflected.nmsClass + "EnumItemSlot")) {
					plugin.getLogger().warning("Unable to Cache EnumItemSlot");
					return false;
				}
			}

			if (Reflected.getMethod("EnumItemSlot#fromName") == null) {
				if (!Reflected.cacheMethod("EnumItemSlot#fromName", Reflected.getClass("EnumItemSlot"), String.class)) {
					plugin.getLogger().warning("Unable to Cache EnumItemSlot#fromName");
					return false;
				}
			}

			if (Reflected.getClass("CraftEntity") == null) {
				if (!Reflected.cacheClass(Reflected.obcClass + "entity.CraftEntity")) {
					plugin.getLogger().warning("Unable to Cache CraftEntity");
					return false;
				}
			}

			if (Reflected.getClass("CraftLivingEntity") == null) {
				if (!Reflected.cacheClass(Reflected.obcClass + "entity.CraftLivingEntity")) {
					plugin.getLogger().warning("Unable to Cache CraftLivingEntity");
					return false;
				}
			}
			if (Reflected.getClass("EntityLiving") == null) {
				if (!Reflected.cacheClass(Reflected.nmsClass + "EntityLiving")) {
					plugin.getLogger().warning("Unable to Cache EntityLiving");
					return false;
				}
			}
			if (Reflected.getMethod("CraftEntity#getHandle") == null) {
				if (!Reflected.cacheMethod("CraftEntity#getHandle", Reflected.getClass("CraftEntity"))) {
					plugin.getLogger().warning("Unable to Cache raftEntity#getHandle");
					return false;
				}
			}

			if (Reflected.getMethod("EntityLiving#broadcastItemBreak") == null) {
				if (plugin.mcVersion.compareTo(SupportedVersions.MC1144) < 1) {
					Reflected.cacheMethod("c", "EntityLiving#broadcastItemBreak", Reflected.getClass("EntityLiving"), Reflected.getClass("EnumItemSlot"));
				} else if (plugin.mcVersion.compareTo(SupportedVersions.MC1152) < 1) {
					Reflected.cacheMethod("EntityLiving#broadcastItemBreak", Reflected.getClass("EntityLiving"), Reflected.getClass("EnumItemSlot"));
				}

				if (Reflected.getMethod("EntityLiving#broadcastItemBreak") == null) {
					plugin.getLogger().warning("Unable to Cache EntityLiving#broadcastItemBreak");
					return false;
				}
			}

			enabled = true;
		}
		return enabled;
	}

}
