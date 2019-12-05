package com.github.thedwellers.fyreplugin.model;

import lombok.Data;

@Data
public class MerchantModel {
	private int armorer = 0;
	private int butcher = 0;
	private int cartographer = 0;
	private int cleric = 0;
	private int farmer = 0;
	private int fisherman = 0;
	private int fletcher = 0;
	private int leatherworker = 0;
	private int librarian = 0;
	private int mason = 0;
	private int shepherd = 0;
	private int toolsmith = 0;
	private int weaponsmith = 0;

	@Override
	public String toString() {
		return "MerchantModel{" +
				"armorer=" + armorer +
				", butcher=" + butcher +
				", cartographer=" + cartographer +
				", cleric=" + cleric +
				", farmer=" + farmer +
				", fisherman=" + fisherman +
				", fletcher=" + fletcher +
				", leatherworker=" + leatherworker +
				", librarian=" + librarian +
				", mason=" + mason +
				", shepherd=" + shepherd +
				", toolsmith=" + toolsmith +
				", weaponsmith=" + weaponsmith +
				'}';
	}
}
