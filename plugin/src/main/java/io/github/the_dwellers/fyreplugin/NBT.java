package io.github.the_dwellers.fyreplugin;

// haha, need to import just for comment references, nice!
import io.github.the_dwellers.fyreplugin.features.TagDataHolder;

/**
 * Functions pertaining to manipulating NBT in a {@link String} format.
 * <p>
 * This does not include reading or writing NBT to or from entitys, for that
 * please see the {@link Reflected} class.
 * <p>
 * For data that will be stored inside an Entity's {@code Tags} tag, take a look
 * at {@link TagDataHolder}
 * <p>
 *
 * @see Reflected
 * @see TagDataHolder
 */
public abstract class NBT {

	/**
	 * Return the portion of the provided nbt with the provided tag. If the tag does
	 * not exist, {@code null} is returned.
	 * <p>
	 * For example: if the provided nbt was
	 * <code>{Motion:[1.0d, 2.0d, 3.0d], Tree:{1b}}</code> and the given key is
	 * {@code Tree} the value {@code 1b} will be returned. if {@code withBrackets}
	 * is true, the outer brackets will be returned with the value:
	 * <code>{1b}</code>.
	 * <p>
	 * If multiple tags with the same name are present (e.g, subtags) only the first
	 * instance will be returned.
	 *
	 * @param nbt          NBT string to get values from
	 * @param tag          Tag to get
	 * @param withBrackets True if the outer brackets should be returned, false if
	 *                     they should be stripped.
	 * @return Return the inner NBT defined by the provided tag
	 */
	public static String getTag(String nbt, String tag, boolean withBrackets) {
		int loc = nbt.indexOf(tag);
		if (loc == -1) {
			// No tag found
			return null;
		}

		// loc will be the start of the found tag
		// Add the size of the tag to get the beginning of that tag's content
		// nbt tags have a colon after their tag name for the start of their nbt data,
		// so increase offset by 1
		loc += tag.length() + 1;

		// Get the end of the tag by using bracket analysis
		int end = getOtherBracket(nbt, loc);
		if (end == -1) {
			// Can't find end of tag, is nbt malformed?
			// Return data to the end of the string
			end = nbt.length();
		}

		// Return the content between the start location and the end location
		return withBrackets ? nbt.substring(loc, end + 1) : nbt.substring(loc + 1, end);
	}

	/**
	 * Set the value for the provided tag. This will replace everything in the tag.
	 * If the tag does not exist, it is appended to the NBT.
	 * <p>
	 * This functions on the first found tag, multiple tags will only have one
	 * instance replaced.
	 * <p>
	 * It is expected that the provided value is encapsulated in supported brackets
	 * <code>{</code>, <code>[</code> If no bracket is at the start of the provided
	 * value, it will be encapsulated with <code>{ }</code>
	 *
	 * @param nbt   NBT to set value inside of
	 * @param tag   Tag to set (only first instance is set)
	 * @param value Value to set
	 * @return Return an nbt tag with the provided tag and value set
	 */
	public static String setTag(String nbt, String tag, String value) {
		if (!value.startsWith("[") && !value.startsWith("{")) {
			// Nbt not start with a bracket, wrap in {}
			value = "{" + value + "}";
		}

		int loc = nbt.indexOf(tag);
		if (loc == -1) {
			loc = nbt.length() - 1;
			nbt = nbt.substring(0, loc) + ", " + tag + ":";
			return nbt + value + "}";
		} else {
			loc = loc + tag.length() + 1;
			int end = getOtherBracket(nbt, loc) + 1;
			return nbt.substring(0, loc) + value + nbt.substring(end, nbt.length());
		}
	}

	/**
	 * Returns the pairing tag of the tag in the text at the provided offset.
	 * <p>
	 * Returns -1 if no paired tag can be found
	 *
	 * @param text   Text to find tag pair inside
	 * @param offset location of opening tag
	 * @return Location of closing tag
	 */
	private static int getOtherBracket(String text, int offset) {
		char[] textArray = text.toCharArray();
		char bracket = textArray[offset];
		char pair = getMatchingBracket(bracket);

		if (pair == ' ') {
			// Cannot determine type of ending bracket
			return -1;
		}

		int depth = 1;
		for (int i = offset + 1; i < textArray.length; i++) {
			// For each character after the current bracket
			char character = textArray[i];

			if (isOpeningBracket(character)) {
				// If the character matches a bracket
				// Increase depth
				depth++;
			} else if (isClosingBracket(character)) {
				// If the character matches a closing bracket
				// Decrease depth
				depth--;
				if (depth == 0 && character == pair) {
					// If the depth is 0 after reducing depth, and the bracket matches the searched
					// pair
					// Return with current character
					return i;
				}
			}
		}
		// No matching bracket was found, return with -1
		return -1;
	}

	/**
	 * Returns the closing bracket. Will return ' ' (space) if no bracket is found.
	 * <p>
	 * Example: '[' will return ']'
	 *
	 * @param bracket Bracket to close
	 * @return Closing bracket, ' ' (space) if none found.
	 */
	private static char getMatchingBracket(char bracket) {
		char pair = ' ';
		switch (bracket) {
			case '[':
				pair = ']';
				break;
			case '{':
				pair = '}';
				break;
			case '(':
				pair = ')';
				break;
			default:
				break;
		}
		return pair;
	}

	/**
	 * Returns true if the provided bracket is an opening bracket.
	 * <p>
	 * Currently checks for '{', '[', '('
	 *
	 * @param bracket Bracket to check
	 * @return True if bracket is an opening bracket, false otherwise
	 */
	private static boolean isOpeningBracket(char bracket) {
		return bracket == '{' || bracket == '[' || bracket == '(';
	}

	/**
	 * Returns true if the provided bracket is a closing bracket.
	 * <p>
	 * Currently checks for '}', ']', ')'
	 *
	 * @param bracket Bracket to check
	 * @return True if bracket is a closing bracket, false otherwise
	 */
	private static boolean isClosingBracket(char bracket) {
		return bracket == '}' || bracket == ']' || bracket == ')';
	}
}
