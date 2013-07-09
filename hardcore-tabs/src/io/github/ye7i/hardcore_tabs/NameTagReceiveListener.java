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
		
		if (plugin.perms.has(event.getNamedPlayer(), plugin.getConfig().getString("PERM_NAME_TAG")))
		{
			event.setTag(HctUtils.getPlayerListMod(event.getNamedPlayer(), plugin) + event.getNamedPlayer().getName());
		} else {
			event.setTag(event.getNamedPlayer().getName());
		}
		
	}
	
}
