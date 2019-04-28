package de.theholyexception.pluginutils.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import de.theholyexception.pluginutils.Messaging;
import de.theholyexception.pluginutils.PluginUtils;
import net.md_5.bungee.api.chat.TextComponent;

public class SearchCommand implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String lable, String[] args) {
		
		Player player = (Player) sender;
		if (!player.hasPermission("pluginutils.searchcommand")) return true;
		
		if (args.length == 0) {
			
		} else if (args.length == 1) {
			Plugin plugin = PluginUtils.getInstance().getPluginHelper().getPluginByCommand(args[0]);
			if (plugin == null) {
				player.sendMessage("§cThis Command was not found");
			} else {
				TextComponent t1 = new TextComponent("§aThe Command is registered in the Plugin §6" + plugin.getName());
				TextComponent tc = Messaging.createCommandHoverText("§7[§cPluginInfo§7]", "§cClick for PluginInformations", "/plugininfo " + plugin.getName());
				t1.addExtra(tc);
				player.spigot().sendMessage(t1);
			}
		}
		return true;
	}
}
