package de.theholyexception.pluginutils;

import java.util.HashSet;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public class PluginHelper {

	private PluginManager pluginmanager = PluginUtils.getInstance().getServer().getPluginManager();
	
	public HashSet<String> getPluginCommands(Plugin plugin) {
		HashSet<String> list = new HashSet<String>();
		Map<String, Map<String, Object>> commanddata = plugin.getDescription().getCommands();
		for (String cmd : commanddata.keySet()) {
			list.add(cmd);
			if (commanddata.get(cmd).keySet().contains("aliases")) {
				String a = commanddata.get(cmd).get("aliases").toString();
				a = a.replace("[", "");
				a = a.replace("]", "");
				a = a.replace(", ", ",");
				for (String alias : a.split(",")) {
					list.add(alias);
				}
			}
		}
		
		
		
		return list;
	}

	public boolean pluginExists(String name) {
		for (Plugin plugin : pluginmanager.getPlugins()) {
			if (plugin.getName().equalsIgnoreCase(name)) return true;
		}
		return false;
	}
	
	public Plugin getByName(String name) {
		for (Plugin pl : Bukkit.getServer().getPluginManager().getPlugins()) {
			if (pl.getName().equalsIgnoreCase(name)) {
				return pl;
			}
		}
		return null;
	}
	
	public Plugin getPluginByCommand(String command) {
		for (Plugin plugin : pluginmanager.getPlugins()) {
			Map<String, Map<String, Object>> commanddata = plugin.getDescription().getCommands();
			for (String cmd : commanddata.keySet()) {
				if (cmd.equalsIgnoreCase(command)) return plugin;
				if (commanddata.get(cmd).keySet().contains("aliases")) {
					String a = commanddata.get(cmd).get("aliases").toString();
					a = a.replace("[", "");
					a = a.replace("]", "");
					a = a.replace(", ", ",");
					for (String alias : a.split(",")) {
						if (alias.equalsIgnoreCase(command)) return plugin;
					}
				}
			}
		}
		return null;
	}
	
}
