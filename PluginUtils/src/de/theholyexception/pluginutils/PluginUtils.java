package de.theholyexception.pluginutils;

import java.io.File;

import org.bukkit.plugin.java.JavaPlugin;

import de.theholyexception.pluginutils.commands.PluginInfo;
import de.theholyexception.pluginutils.commands.SearchCommand;
import de.theholyexception.pluginutils.listeners.PlayerCommandPreprocess;

public class PluginUtils extends JavaPlugin {
	
	private ConfigurationManager defaultcfg;
	private PluginHelper pluginhelper;
	private static PluginUtils instance;
	
	
	public void onEnable() {
		PluginUtils.instance = this;
		if (!getDataFolder().exists()) getDataFolder().mkdir();
		defaultcfg = new ConfigurationManager(new File(getDataFolder()+"//config.yml"));
		pluginhelper = new PluginHelper();
		
		
		
		getServer().getPluginManager().registerEvents(new PlayerCommandPreprocess(), this);
		
		getCommand("plugininfo").setExecutor(new PluginInfo());
		getCommand("searchcommand").setExecutor(new SearchCommand());
	}
	
	public static PluginUtils getInstance() {
		return instance;
	}
	
	public ConfigurationManager getDefaultConfiguration() {
		return defaultcfg;
	}
	
	public PluginHelper getPluginHelper() {
		return pluginhelper;
	}

}
