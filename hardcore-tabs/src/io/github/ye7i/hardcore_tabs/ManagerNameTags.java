package io.github.ye7i.hardcore_tabs;

import org.bukkit.entity.Player;
import org.kitteh.tag.TagAPI;

public class ManagerNameTags {

	private static HardcoreTabs plugin;
	
	public ManagerNameTags(HardcoreTabs plugin)
	{
		ManagerNameTags.plugin = plugin;
	}
	
	public void applyTagForAllPlayers()
	{
		for (Player player : plugin.getServer().getOnlinePlayers())
		{
			applyTagForPlayer(player);
		}
	}
	
	public void applyTagForAllNearbyPlayers()
	{
		for (Player player : plugin.getServer().getOnlinePlayers())
		{
			applyTagForNearbyPlayers(player);
		}
	}
	
	public void applyTagForPlayer(Player player)
	{
		TagAPI.refreshPlayer(player);
	}
	
	public void applyTagForNearbyPlayers(Player player)
	{
		for (Player nearbyPlayer : HctUtils.getNearbyPlayers(player, plugin.getConfig().getInt("PLAYER_SIGHT_DISTANCE")))
		{
			applyTagForPlayer(nearbyPlayer);
		}
	}
	
}
