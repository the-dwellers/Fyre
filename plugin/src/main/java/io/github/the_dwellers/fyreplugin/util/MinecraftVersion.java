package io.github.the_dwellers.fyreplugin.util;

public class MinecraftVersion implements Comparable<MinecraftVersion> {
	private final int major;
	private final int minor;
	private final int patch;

	public MinecraftVersion(int major, int minor, int patch) {
		this.major = major;
		this.minor = minor;
		this.patch = patch;
	}

	public MinecraftVersion(String version) {
		if (!version.matches("(\\d+\\.\\d+(\\.\\d+)?)"))
			throw new IllegalArgumentException("Invalid version was given");

		String[] split = version.split("\\.");
		this.major = Integer.parseInt(split[0]);
		this.minor = Integer.parseInt(split[1]);
		if (split.length == 3)
			this.patch = Integer.parseInt(split[2]);
		else
			this.patch = 0;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append(major).append(".").append(minor);

		if (minor == 0 || patch > 0)
			sb.append(".").append(patch);

		return sb.toString();
	}

	@Override
	public int compareTo(MinecraftVersion o) {
		if (this.major > o.major)
			return 1;
		else if (this.major < o.major)
			return -1;

		if (this.minor > o.minor)
			return 1;
		else if (this.minor < o.minor)
			return -1;

		if (this.patch > o.patch)
			return 1;
		else if (this.patch < o.patch)
			return -1;

		return 0;
	}
}
