package com.github.thedwellers.fyreplugin.events;

import org.bukkit.attribute.Attributable;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.attribute.AttributeModifier.Operation;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

/**
 * EntitySpawn
 */
public class EntitySpawn implements Listener {

	@EventHandler()
	private void onEntitySpawn(EntitySpawnEvent event) {
		if (event.getEntity() instanceof LivingEntity) {
			LivingEntity livingEntity = (LivingEntity) event.getEntity();
			if (livingEntity instanceof Attributable) {
				Attributable attrib = (Attributable) livingEntity;
				AttributeModifier mod1 = new AttributeModifier("Fyre Spawn Health 1.5", 0.5, Operation.MULTIPLY_SCALAR_1);
				AttributeModifier mod2 = new AttributeModifier("Fyre Spawn Health 2", 1, Operation.MULTIPLY_SCALAR_1);
				switch (livingEntity.getType()) {
				case ZOMBIE:
				case SKELETON:
				case SPIDER:
				case ENDERMAN:
				attrib.getAttribute(Attribute.GENERIC_MAX_HEALTH).addModifier(mod1);
				break;
				case BLAZE:
				case GHAST:
				case WITHER_SKELETON:
					attrib.getAttribute(Attribute.GENERIC_MAX_HEALTH).addModifier(mod2);
				default:
					break;
				}

			}
		}

	}

}
