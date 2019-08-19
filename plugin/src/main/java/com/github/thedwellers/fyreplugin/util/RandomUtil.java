package com.github.thedwellers.fyreplugin.util;

import java.security.SecureRandom;

/**
 * An utility class for random numbers, strings and etc.
 */
public final class RandomUtil {
	private static final SecureRandom random = new SecureRandom();

	/**
	 * Generate a random integer between the min and max
	 * @param min Minimum value (Inclusive)
	 * @param max Maximum value (Inclusive)
	 * @return Integer between min and max
	 */
	public static int integer(int min, int max) {
		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}
		
		return random.nextInt((max - min) + 1) + min;
	}

	/**
	 * Generate a random integer between 0 and max
	 * @param max Maximum value (Inclusive)
	 * @return Integer between 0 and max
	 */
	public static int integer(int max) {
		if (max <= 0) {
			throw new IllegalArgumentException("max must be greater than 0");
		}
		
		return integer(0, max);
	}

	/**
	 * Generate a random integer with no bounds
	 * @return An integer
	 */
	public static int integer() {
		return random.nextInt();
	}
}
