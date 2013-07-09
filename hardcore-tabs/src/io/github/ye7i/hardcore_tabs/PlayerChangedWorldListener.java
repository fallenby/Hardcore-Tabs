package io.github.ye7i.hardcore_tabs;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

public class PlayerChangedWorldListener implements Listener {

	private static HardcoreTabs plugin;
	
	public PlayerChangedWorldListener(HardcoreTabs plugin)
	{
		PlayerChangedWorldListener.plugin = plugin;
	}
	
	@EventHandler
	public void onPlayerChangedWorld(PlayerChangedWorldEvent event)
	{

		Player player = event.getPlayer();
		
		plugin.tabList.applyTabListForPlayer(player);
		
	}
	
}
