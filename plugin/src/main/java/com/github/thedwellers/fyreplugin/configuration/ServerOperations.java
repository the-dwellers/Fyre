package com.github.thedwellers.fyreplugin.configuration;

import java.io.File;

public class ServerOperations {

	public static void createPlayerFolder(File dataFolder){
		File folder = new File(dataFolder+File.separator+"player_data");
		if(!folder.exists()){
			folder.mkdirs();
		}
	}
}
