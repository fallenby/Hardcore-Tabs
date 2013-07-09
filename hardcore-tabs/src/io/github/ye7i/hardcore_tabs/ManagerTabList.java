package io.github.ye7i.hardcore_tabs;

import org.bukkit.entity.Player;

public class ManagerTabList {

	private static HardcoreTabs plugin;
	
	public ManagerTabList(HardcoreTabs plugin)
	{
		ManagerTabList.plugin = plugin;
	}
	
	public void applyTabListForAllPlayers()
	{
		for (Player player : plugin.getServer().getOnlinePlayers())
		{
			applyTabListForPlayer(player);
		}
	}
	
	public void applyTabListForPlayer(Player player)
	{
		if (plugin.perms.has(player, plugin.getConfig().getString("PERM_TAB_LIST")))
		{
			player.setPlayerListName(HctUtils.getPlayerListMod(player, plugin) + player.getName());
		} else {
			player.setPlayerListName(player.getName());
		}
	}
	
}
