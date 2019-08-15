package com.github.thedwellers.fyreplugin;

import com.github.thedwellers.fyreplugin.entity.TagDataHolder;

/**
 * Functions pertaining to manipulating NBT in a {@link String} format.
 * <p>
 * This does not include reading or writing NBT to or from entitys, for that please
 * see the {@link Reflected} class.
 * <p>
 * For data that will be stored inside an Entity's {@code Tags} tag, take a look at {@link TagDataHolder}
 * <p>
 * TODO: Documentation
 * @see Reflected
 * @see TagDataHolder
 */
public abstract class NBT {

	public static String getTag(String nbt, String tag, boolean withBrackets) {
		int loc = nbt.indexOf(tag);
		if (loc == -1) {
			// No tag found
			return null;
		}

		// loc will be the start of the found tag
		// Add the size of the tag to get the beginning of that tag's content
		// nbt tags have a colon after their tag name for the start of their nbt data, so increase offset by 1
		loc += tag.length() + 1;

		// Get the end of the tag by using bracket analysis
		int end = getOtherBracket(nbt, loc);
		if (end == -1) {
			// Can't find end of tag, is nbt malformed?
			// Return data to the end of the string
			end = nbt.length();
		}

		// Return the content between the start location and the end location
		return withBrackets ? nbt.substring(loc, end+1) : nbt.substring(loc+1, end);
	}


	private static int getOtherBracket(String text, int offset) {
		char[] textArray = text.toCharArray();
		char bracket = textArray[offset];
		char pair = getMatchingBracket(bracket);

		if (pair == ' ') {
			// Cannot determine type of ending bracket
			return -1;
		}

		int depth = 1;
		// TODO: Reduce complexity to O(n) (Checking brackets)
		for (int i = offset + 1; i < textArray.length; i++) {
			// For each character after the current bracket
			char character = textArray[i];

			if (isOpeningBracket(character)) {
				// If the character matches a bracket
				// Increase depth
				depth++;
			} else if (isClosingBracket(character)){
				// If the character matches a closing bracket
				// Decrease depth
				depth--;
				if (depth == 0 && character == pair) {
					// If the depth is 0 after reducing depth, and the bracket matches the searched pair
					// Return with current character
					return i;
				}
			}
		}
		// No matching bracket was found, return with -1
		return -1;
	}

	// TODO: Documentation

	private static char getMatchingBracket(char bracket) {
		// ! O(n) complexity, needs to be reduced
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

	private static boolean isOpeningBracket(char bracket) {
		// ! O(n) complexity, needs to be reduced
		return bracket == '{' || bracket == '[' || bracket == '(';
	}

	private static boolean isClosingBracket(char bracket) {
		// ! O(n) complexity, needs to be reduced
		return bracket == '}' || bracket == ']' || bracket == ')';
	}
}
