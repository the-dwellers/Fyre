package com.github.thedwellers.fyreplugin.entity;

import java.util.Base64;

import com.github.thedwellers.fyreplugin.NBT;
import com.github.thedwellers.fyreplugin.Reflected;
import com.github.thedwellers.fyreplugin.exceptions.ReflectionFailedException;

import org.bukkit.entity.Entity;

/**
 * Represents any {@link Entity} that may have external data serialized to their
 * 'Tag' nbt value.
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

	/**
	 * Encode the string for safe storage inside a nbt text tag
	 *
	 * @param string String to encode
	 * @return Safe string for storage inside an nbt text tag
	 * @see TagDataHolder#decodeString(String)
	 */
	protected static String encodeString(String string) {
		return Base64.getEncoder().withoutPadding().encodeToString(string.getBytes());
	}

	/**
	 * Decode the provided string that was encoded with
	 * {@link TagDataHolder#encodeString(String)}
	 *
	 * @param string String to decode
	 * @return Decoded string
	 * @throws IllegalArgumentException thrown when the provided string cannot be
	 *                                  decoded
	 * @see TagDataHolder#encodeString(String)
	 */
	protected static String decodeString(String string) throws IllegalArgumentException {
		return new String(Base64.getDecoder().decode(string.getBytes()));
	}

	/**
	 * Write data to entity's 'Tags' tag. Ensures data is stored in a safe format.
	 *
	 * @param data Data to save to entity
	 * @throws ReflectionFailedException thrown when any issue is encountered during
	 *                                   reflection while saving nbt to the entity.
	 */
	protected void writeToEntity(String data) throws ReflectionFailedException {
		data = encodeString(data);
		data = "['" + data + "']";
		Reflected.saveNBTToEntity(NBT.setTag(Reflected.getNBTOfEntity(entity), "Tags", data), entity);
	}

	/**
	 * Read data from the entity's 'Tags' tag. Return 'null' if no data is present.
	 *
	 * @return Data in entity's 'Tags' tag, null if not present.
	 * @throws ReflectionFailedException thrown when any issue is encountered during
	 *                                   reflection to read entity nbt
	 */
	protected String readFromEntity() throws ReflectionFailedException {
		String data = NBT.getTag(Reflected.getNBTOfEntity(entity), "Tags", true);
		if (data != null) {
			data = data.substring(2, data.length() - 2);
			data = decodeString(data);
		}
		return data;
	}
}
