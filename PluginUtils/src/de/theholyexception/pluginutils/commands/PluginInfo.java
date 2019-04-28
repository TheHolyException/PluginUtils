package de.theholyexception.pluginutils.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;

import de.theholyexception.pluginutils.Messaging;
import de.theholyexception.pluginutils.PluginHelper;
import de.theholyexception.pluginutils.PluginUtils;
import net.md_5.bungee.api.chat.TextComponent;

public class PluginInfo implements CommandExecutor {
	
	private PluginHelper pluginhelper = PluginUtils.getInstance().getPluginHelper();
	
	
	public boolean onCommand(CommandSender sender, Command cmd, String lable, String[] args) {
		
		Player player = (Player) sender;
		if (!player.hasPermission("pluginutils.plugininfo")) return true;
		
		if (args.length == 1) {
			if (pluginhelper.pluginExists(args[0])) {
				
				Plugin plugin = pluginhelper.getByName(args[0]);
				PluginDescriptionFile pldes = plugin.getDescription();
				

				
				String header = PluginUtils.getInstance().getDefaultConfiguration().getConfiguration().getString("message.plugininfoheader");
				if (header != null) header = header.replace("&", "§");
				
				player.sendMessage(header);
				
				player.sendMessage("§6Plugin §7-> §e" + plugin.getName());
				player.sendMessage("§6Load §7-> §e" + pldes.getLoad());
				player.sendMessage("§6LoadBefore §7-> §e" + pldes.getLoadBefore());
				player.sendMessage("§6Depends §7-> §e" + pldes.getDepend());
				player.sendMessage("§6SoftDepends §7-> §e" + pldes.getSoftDepend());
				player.sendMessage("§6Commands:");
				TextComponent commandlist = new TextComponent("");
				int i = 0;
				for (String command : pluginhelper.getPluginCommands(pluginhelper.getByName(args[0]))) {
					
					TextComponent cmddata = Messaging.createCommandHoverText("§7"+command, "§cClick to Execute this Command", "/"+command);
					
					if (i == 1) {
						commandlist.addExtra(new TextComponent("§e, "));
					} else {
						i = 1;
					}
					commandlist.addExtra(cmddata);
					
					//Prevents from kicking player
					if (commandlist.toPlainText().length() > 150) {
						commandlist.addExtra(cmddata);
						player.spigot().sendMessage(commandlist);
						commandlist = new TextComponent();
						i = 0;
					} else commandlist.addExtra(cmddata);
				}
				player.spigot().sendMessage(commandlist);
				
				player.sendMessage(header);
			} else player.sendMessage("§cThis Plugin does not Exists");
		}
		
		
		
		return true;
	}

}
