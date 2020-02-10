package io.github.the_dwellers.fyreplugin.util;

public class MinecraftVersion implements Comparable<MinecraftVersion> {
	private int major;
	private int minor;
	private int build;

	public MinecraftVersion(int major, int minor, int build) {
		this.major = major;
		this.minor = minor;
		this.build = build;
	}

	public MinecraftVersion(String version) {
		if (!version.matches("(\\d+\\.\\d+(\\.\\d+)?)"))
			throw new IllegalArgumentException("Invalid version was given");

		String[] split = version.split("\\.");
		this.major = Integer.parseInt(split[0]);
		this.minor = Integer.parseInt(split[1]);
		if (split.length == 3)
			this.build = Integer.parseInt(split[2]);
		else
			this.build = 0;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append(major).append(".").append(minor);

		if (minor == 0 || build > 0)
			sb.append(".").append(build);

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

		if (this.build > o.build)
			return 1;
		else if (this.build < o.build)
			return -1;

		return 0;
	}
}
