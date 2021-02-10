package io.github.the_dwellers.fyreplugin.provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.the_dwellers.fyreplugin.FyrePlugin;
import io.github.the_dwellers.fyreplugin.model.PluginConfig;

import javax.inject.Inject;
import javax.inject.Provider;
import java.io.File;
import java.io.IOException;

public class PluginConfigProvider implements Provider<PluginConfig> {
	@Inject
	private FyrePlugin plugin;

	@Override
	public PluginConfig get() {
		ObjectMapper mapper = new ObjectMapper();

		File configFile = new File(plugin.getDataFolder(), "config.json");
		// Todo: warn if values do not exist in current config
		if (!configFile.exists()) {
			try {
				mapper.writerWithDefaultPrettyPrinter().writeValue(configFile, new PluginConfig());
			} catch (IOException e) {
				// TODO: Handle properly
				throw new RuntimeException(e);
			}
		}

		try {
			return mapper.readValue(configFile, PluginConfig.class);
		} catch (IOException e) {
			// TODO: Handle properly
			throw new RuntimeException(e);
		}
	}
}
