package de.theholyexception.pluginutils.listeners;

import java.util.ArrayList;
import java.util.Collections;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;

import de.theholyexception.pluginutils.Messaging;
import de.theholyexception.pluginutils.PluginUtils;
import net.md_5.bungee.api.chat.TextComponent;

public class PlayerCommandPreprocess implements Listener {
	
	private FileConfiguration cfg = PluginUtils.getInstance().getDefaultConfiguration().getConfiguration();
	
	@EventHandler
	public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
		Player player = event.getPlayer();
		String cmd = event.getMessage();
		
		if (cmd.equalsIgnoreCase("/plugins") || cmd.equalsIgnoreCase("/pl") || cmd.equalsIgnoreCase("/?")) {
			event.setCancelled(true);
			if (!player.hasPermission("pluginutils.pluginlist")) {
				player.sendMessage(cfg.getString("message.nopermissions"));
				return;
			}
			Plugin[] plugins = Bukkit.getServer().getPluginManager().getPlugins();
			ArrayList<String> names = new ArrayList<String>();
			for (Plugin plugin : plugins) {
				names.add(plugin.getName());
			}
			Collections.sort(names, String.CASE_INSENSITIVE_ORDER);
			
			String header = cfg.getString("message.pluginlistheader");
			if (header != null) header = header.replace("&", "§");
			
			player.sendMessage(header);
			player.sendMessage(" ");
			player.sendMessage("§6Plugins §7[§e"+plugins.length+"§7] §8§l:");
			player.sendMessage(" ");
			
			TextComponent pluginlist = new TextComponent();
			boolean a = false;
			for (String pluginname : names) {
				Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin(pluginname);
				PluginDescriptionFile pldes = plugin.getDescription();
				String pluginprefix = "§7";
				if (!plugin.isEnabled()) {
					pluginprefix = "§4";
				}
				
				TextComponent plugindata = Messaging.createCommandHoverText(pluginprefix+pluginname,
				"§6Name §7-> §e"+pldes.getName()
				+"\n§6FullName §7-> §e"+pldes.getFullName()
				+"\n§6Description §7-> §e"+pldes.getDescription()
				+"\n§6Authors §7-> §e"+pldes.getAuthors()
				+"\n§6Version §7-> §e"+pldes.getVersion()
				+"\n§6Website §7-> §e"+pldes.getWebsite()
				+"\n\n§cClick for more Informations", "/plugininfo " + pluginname);
				
				if (a) {
					pluginlist.addExtra(new TextComponent("§e, "));
				} else {
					a = true;
				}
				
				//Prevents from kicking player
				if (pluginlist.toPlainText().length() > 150) {
					pluginlist.addExtra(plugindata);
					player.spigot().sendMessage(pluginlist);
					pluginlist = new TextComponent();
					a = false;
				} else pluginlist.addExtra(plugindata);
				
			}
			
			player.spigot().sendMessage(pluginlist);
			player.sendMessage(" ");
			player.sendMessage(header);
			
		}
	}

}
