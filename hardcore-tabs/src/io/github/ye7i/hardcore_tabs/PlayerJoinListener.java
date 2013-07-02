package io.github.ye7i.hardcore_tabs;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

	private static HardcoreTabs plugin;
	
	public PlayerJoinListener(HardcoreTabs plugin)
	{
		PlayerJoinListener.plugin = plugin;
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent joinEvent)
	{
		
		Player player = joinEvent.getPlayer();
		
		plugin.colorizer.applyForPlayer(player);
		
	}
	
}
