package de.theholyexception.pluginutils;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigurationManager {
	
	private File file;
	private FileConfiguration cfg;
	
	
	public ConfigurationManager(File file) {
		this.file = file;
		if (!file.exists()) {
			try {
				file.createNewFile();
				cfg = YamlConfiguration.loadConfiguration(file);
				cfg.set("message.nopermissions", "&cYou dont have Permissions to use this Command");
				cfg.set("message.pluginlistheader", "&8[]&7==================&8[&cPlugins&8]&7==================&8[]");
				cfg.set("message.plugininfoheader", "&8[]&7==================&8[&cPlugin Info&8]&7==================&8[]");
				save();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			cfg = YamlConfiguration.loadConfiguration(file);
		}
	}
	
	public FileConfiguration getConfiguration() {
		return cfg;
	}
	
	public void save() {
		try {
			cfg.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
