package io.github.the_dwellers.fyreplugin.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.the_dwellers.fyreplugin.FyrePlugin;
import io.github.the_dwellers.fyreplugin.model.PlayerConfig;
import org.bukkit.entity.Player;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;

public class PlayerConfigService {
	@Inject
	private FyrePlugin plugin;

	public PlayerConfig get(Player player) {
		ObjectMapper mapper = new ObjectMapper();

		File playerConfigFile = new File(plugin.playerDataFolder, String.format("%s.json", player.getUniqueId()));
		// Todo: warn if values do not exist in current config
		if (!playerConfigFile.exists()) {
			try {
				PlayerConfig config = new PlayerConfig();

				mapper.writerWithDefaultPrettyPrinter().writeValue(playerConfigFile, config);

				return config;
			} catch (IOException e) {
				// TODO: Handle properly
				throw new RuntimeException(e);
			}
		}

		try {
			return mapper.readValue(playerConfigFile, PlayerConfig.class);
		} catch (IOException e) {
			// TODO: Handle properly
			throw new RuntimeException(e);
		}
	}
}
