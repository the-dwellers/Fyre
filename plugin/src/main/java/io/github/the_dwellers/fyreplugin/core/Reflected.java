package io.github.the_dwellers.fyreplugin.core;

import org.bukkit.Bukkit;

import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * Provides reflection helpers for {@code net.minecraft.server} and {@code org.bukkit.craftbukkit}.
 * Reflection calls are cached.
 */
public abstract class Reflected {
	private static final String packageVersion = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",")
			.split(",")[3] + ".";
	private static final HashMap<String, Class<?>> classCache = new HashMap<String, Class<?>>();
	private static final HashMap<String, Method> methodCache = new HashMap<String, Method>();
	public static String nmsClass = "net.minecraft.server." + packageVersion;
	public static String obcClass = "org.bukkit.craftbukkit." + packageVersion;

	/**
	 * Get the named method from {@code classCache} If the class does not exist
	 * under the provided key, {@code null} is returned.
	 * <p>
	 * Equivalent to:
	 *
	 * <pre>
	 * return classCache.get(name);
	 * </pre>
	 *
	 * @param name Key of method
	 * @return {@link Class} stored under the named key. {@code Null} otherwise.
	 */
	public static Class<?> getClass(String name) {
		return classCache.get(name);
	}

	/**
	 * Cache a class to the {@code classCache}.
	 *
	 * @param name Name of the class to cache
	 * @return true if method was cached, false otherwise. A log is made on
	 * failures.
	 */
	public static boolean cacheClass(String name) {
		try {
			Class<?> c = Class.forName(name);
			classCache.put(c.getSimpleName(), c);
			return true;
		} catch (ClassNotFoundException e) {
			return false;
		}
	}

	/**
	 * Cache a class to the {@code classCache}.
	 *
	 * @param key  Key to store class under
	 * @param name Name of the class to cache
	 * @return true if method was cached, false otherwise. A log is made on
	 * failures.
	 */
	public static boolean cacheClass(String key, String name) {
		try {
			classCache.put(key, Class.forName(name));
			return true;
		} catch (ClassNotFoundException e) {
			return false;
		}
	}

	/**
	 * Get the named method from {@code classCache} If the method does not exist
	 * under the provided key, {@code null} is returned.
	 * <p>
	 * Equivalent to:
	 *
	 * <pre>
	 * return methodCache.get(name);
	 * </pre>
	 *
	 * @param name Key of method
	 * @return {@link java.lang.reflect.Method} stored under the named key.
	 * {@code Null} otherwise.
	 */
	public static Method getMethod(String name) {
		return methodCache.get(name);
	}

	/**
	 * Cache a method to the {@code methodCache}. Prints an error message to console
	 * if fails.
	 * <p>
	 * Works on <b>public</b> inherited and defined methods.
	 * <p>
	 * Use {@link Reflected#cacheDeclaredMethod(String, Class, Class...)} if the
	 * method is private.
	 *
	 * @param name           Name of the method in the format "Class#function" Where
	 *                       'function' is the exact name of the function to find.
	 *                       'Class' may be any value.
	 * @param class1         Class to reflect the function from.
	 * @param parameterTypes Parameters that the function takes
	 * @return true if method was cached, false otherwise. A log is made on
	 * failures.
	 */
	public static boolean cacheMethod(String key, Class<?> class1, Class<?>... parameterTypes) {
		return cacheMethod(key.split("#")[1], key, class1, parameterTypes);
	}

	/**
	 * Cache a method to the {@code methodCache}. Prints an error message to console
	 * if fails.
	 * <p>
	 * Works on <b>public</b> inherited and defined methods.
	 * <p>
	 * Use {@link Reflected#cacheDeclaredMethod(String, Class, Class...)} if the
	 * method is private.
	 *
	 * @param name           Name of the method in the format "Class#function" Where
	 *                       'function' is the exact name of the function to find.
	 *                       'Class' may be any value.
	 * @param class1         Class to reflect the function from.
	 * @param parameterTypes Parameters that the function takes
	 * @return true if method was cached, false otherwise. A log is made on
	 * failures.
	 */
	public static boolean cacheClassMethod(String key, Class<?>... parameterTypes) {
		return cacheMethod(key.split("#")[1], key, getClass(key.split("#")[0]), parameterTypes);
	}

	/**
	 * Cache a method to the {@code methodCache}.
	 * <p>
	 * Works on <b>public</b> inherited and defined methods.
	 * <p>
	 * Use {@link Reflected#cacheDeclaredMethod(String, Class, Class...)} if the
	 * method is private.
	 *
	 * @param name           Name of the method in the format "Class#function" Where
	 *                       'function' is the exact name of the function to find.
	 *                       'Class' may be any value.
	 * @param class1         Class to reflect the function from.
	 * @param parameterTypes Parameters that the function takes
	 * @return true if method was cached, false otherwise. A log is made on
	 * failures.
	 */
	public static boolean cacheMethod(String name, String key, Class<?> class1, Class<?>... parameterTypes) {
		try {
			methodCache.put(key, class1.getMethod(name, parameterTypes));
			return true;
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Cache a method to the {@code methodCache}.
	 * <p>
	 * Only works on methods defined in the exact class given (Inherited methods are
	 * not included). <b>Works on private methods.</b>
	 * <p>
	 * It is recommended to use
	 * {@link Reflected#cacheMethod(String, Class, Class...)} if the method is not
	 * private.
	 *
	 * @param name           Name of the method in the format "Class#function" Where
	 *                       'function' is the exact name of the function to find.
	 *                       'Class' may be any value.
	 * @param class1         Class to reflect the function from.
	 * @param parameterTypes Parameters that the function takes
	 * @return true if method was cached, false otherwise. A log is made on
	 * failures.
	 */
	public static boolean cacheDeclaredMethod(String name, String key, Class<?> class1, Class<?>... parameterTypes) {
		try {
			Method method = class1.getDeclaredMethod(name, parameterTypes);
			method.setAccessible(true);
			methodCache.put(key, method);
			return true;
		} catch (NoSuchMethodException e) {
			return false;
		}
	}
}
