package de.theholyexception.pluginutils;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.HoverEvent.Action;

// @author Created by TheHolyException at 29.01.2019 - 14:54:44

public class Messaging {
	
    public static TextComponent createHoverText(String text, String hovertext) {
		TextComponent t1 = new TextComponent();
		t1.setText(text);
		t1.setHoverEvent(new HoverEvent(Action.SHOW_TEXT, new ComponentBuilder(hovertext).create()));
		return t1;
    }
    
    public static TextComponent createHoverText(TextComponent t1, String hovertext) {
		t1.setHoverEvent(new HoverEvent(Action.SHOW_TEXT, new ComponentBuilder(hovertext).create()));
		return t1;
    }
    
    public static TextComponent createCommandHoverText(String text, String hovertext, String command) {
    	TextComponent t1 = new TextComponent();
    	t1.setText(text);
    	t1.setHoverEvent(new HoverEvent(Action.SHOW_TEXT, new ComponentBuilder(hovertext).create()));
    	t1.setClickEvent(new ClickEvent(net.md_5.bungee.api.chat.ClickEvent.Action.RUN_COMMAND, command));
    	return t1;
    	
    }
    
    public static TextComponent createCommandHoverText(TextComponent t1, String hovertext, String command) {
    	t1.setHoverEvent(new HoverEvent(Action.SHOW_TEXT, new ComponentBuilder(hovertext).create()));
    	t1.setClickEvent(new ClickEvent(net.md_5.bungee.api.chat.ClickEvent.Action.RUN_COMMAND, command));
    	return t1;
    	
    }


}
