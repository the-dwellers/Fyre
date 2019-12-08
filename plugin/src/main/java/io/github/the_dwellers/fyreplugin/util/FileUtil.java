package io.github.the_dwellers.fyreplugin.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * An utility class to interact with {@code java.io.File}s
 *
 * @author ChargedByte
 * @see java.io.File
 */
public final class FileUtil {
	/**
	 * Reads the file and returns a String of the contents of the file
	 *
	 * @param file Target file
	 * @return Contents of the file
	 * @throws IOException If internal method throws
	 * @see java.nio.file.Files#readAllLines(Path)
	 */
	public static String readAllText(File file) throws IOException {
		StringBuilder sb = new StringBuilder();
		List<String> lines = Files.readAllLines(file.toPath());

		for (String line : lines) {
			sb.append(line).append("\n");
		}

		return sb.toString();
	}


	/**
	 * Writes the provided String to a file overwriting it's contents
	 *
	 * @param file    Target file
	 * @param content Content to write
	 * @throws IOException If internal method throws
	 * @see com.github.thedwellers.fyreplugin.util.FileUtil#writeAllText(File, String, boolean)
	 */
	public static void writeAllText(File file, String content) throws IOException {
		writeAllText(file, content, false);
	}

	/**
	 * Writes the provided String to a file overwriting or appending depending on the variable
	 *
	 * @param file    Target file
	 * @param content Content to write
	 * @param append  Whether to append the content or not
	 * @throws IOException If internal method throws
	 * @see java.nio.file.Files#write(Path, Iterable, OpenOption...)
	 */
	public static void writeAllText(File file, String content, boolean append) throws IOException {
		List<String> lines = new LinkedList<>(Arrays.asList(content.split("\n")));
		Files.write(file.toPath(), lines);
	}
}
