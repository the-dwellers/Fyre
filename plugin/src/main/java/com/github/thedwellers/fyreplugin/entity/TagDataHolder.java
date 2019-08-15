package com.github.thedwellers.fyreplugin.entity;

import java.util.Base64;

import com.github.thedwellers.fyreplugin.Reflected;
import com.github.thedwellers.fyreplugin.exceptions.ReflectionFailedException;

import org.bukkit.entity.Entity;

/**
 * Represents any object that may have external data serialized to their 'Tag' nbt
 * value
 */
public abstract class TagDataHolder {

	protected Entity entity;

	public TagDataHolder(Entity entity) {
		this.entity = entity;
	}

	/**
	 * Serialize any stored data into the nbt tag.
	 */
	protected abstract void serialize();

	/**
	 * Deserialize any stored data from the nbt tag.
	 */
	protected abstract void deserialize();

	protected static String toBase64(String string) {
		return Base64.getEncoder().withoutPadding().encodeToString(string.getBytes());
	}

	protected static String fromBase64(String string) {
		return new String(Base64.getDecoder().decode(string.getBytes()));
	}

	protected void writeToEntity(String data) throws ReflectionFailedException {
		String entityNBT = Reflected.getNBTOfEntity(entity);
	}
}
