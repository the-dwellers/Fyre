package com.github.thedwellers.fyreplugin.entity;

import java.util.Base64;

import com.github.thedwellers.fyreplugin.NBT;
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

	protected static String encodeString(String string) {
		return Base64.getEncoder().withoutPadding().encodeToString(string.getBytes());
	}

	protected static String decodeString(String string) throws IllegalArgumentException {
		return new String(Base64.getDecoder().decode(string.getBytes()));
	}

	protected void writeToEntity(String data) throws ReflectionFailedException {
		data = encodeString(data);
		data = "['" + data + "']";
		Reflected.saveNBTToEntity(NBT.setTag(Reflected.getNBTOfEntity(entity), "Tags", data), entity);
	}

	protected String readFromEntity() throws ReflectionFailedException {
		String data = NBT.getTag(Reflected.getNBTOfEntity(entity), "Tags", true);
		if (data != null) {
			data = data.substring(2,data.length()-2);
			data = decodeString(data);
		}
		return data;
	}

}
