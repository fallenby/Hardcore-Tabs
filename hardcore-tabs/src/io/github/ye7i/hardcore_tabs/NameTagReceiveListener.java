package io.github.ye7i.hardcore_tabs;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.kitteh.tag.PlayerReceiveNameTagEvent;

public class NameTagReceiveListener implements Listener {

	private static HardcoreTabs plugin;
	
	public NameTagReceiveListener(HardcoreTabs plugin)
	{
		NameTagReceiveListener.plugin = plugin;
	}
	
	@EventHandler
	public void onNameTag(PlayerReceiveNameTagEvent event) {
		
		event.setTag(plugin.colorizer.getPlayerListMod(event.getNamedPlayer()) + event.getNamedPlayer().getName());
		
	}
	
}
