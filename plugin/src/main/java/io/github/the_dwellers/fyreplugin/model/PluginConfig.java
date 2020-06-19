package io.github.the_dwellers.fyreplugin.model;

import lombok.Data;

@Data
public class PluginConfig {
	private int oneHitProtectionThreshold = 3;

	private int dirtTrampleWalkChance = 5;
	private int dirtTrampleRideChance = 10;

	private int mediumMobDepth = 60;
	private int strongMobDepth = 40;
	private int veryStrongMobDepth = 21;

	private int randomBossChance = 1;

	private String[] motdTips = new String[0];
}
