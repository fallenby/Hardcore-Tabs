package io.github.ye7i.hardcore_tabs;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class HctUtils {
	
	private static final char MOD_SEPERATOR = '§';
	
	public static List<Player> getNearbyPlayers(Player player, int sightDistance)
	{
		List<Player> nearbyPlayers = new ArrayList<Player>();
		
		List<Entity> nearbyEntities = player.getNearbyEntities(sightDistance, sightDistance, sightDistance);
		
		for (Entity entity : nearbyEntities)
		{
			if (entity instanceof Player)
			{
				nearbyPlayers.add((Player)entity);
			}
		}
		
		return nearbyPlayers;
	}
	
	public static String getPlayerListMod(Player player, HardcoreTabs plugin)
	{
		String group = plugin.perms.getPrimaryGroup(player);
		
		if (plugin.groupConfig.getConfig().getString(group + ".color") == null)
		{
			plugin.getLogger().info(String.format("[%s] Attempted to load color configuration for group %s, but could not find them in %s.", plugin.getDescription().getName(), group, plugin.getConfig().getString("GROUP_CONFIG_FILE_NAME")));
			return "";
		}
		
		String playerListMod = "";
		
		if (plugin.getConfig().getString("color." + plugin.groupConfig.getConfig().getString(plugin.perms.getPrimaryGroup(player) + ".color")) == null)
		{
			plugin.getLogger().info(String.format("[%s] Attempted to load color configuration for group %s, but found invalid color name in %s.", plugin.getDescription().getName(), group, plugin.getConfig().getString("GROUP_CONFIG_FILE_NAME")));
		} else {
			playerListMod += MOD_SEPERATOR;
			playerListMod += plugin.getConfig().getString("color." + plugin.groupConfig.getConfig().getString(plugin.perms.getPrimaryGroup(player) + ".color"));
		}	
		
		for (String style : plugin.groupConfig.getConfig().getStringList(plugin.perms.getPrimaryGroup(player) + ".font-styles"))
		{
			if (plugin.getConfig().getString("style." + style) == null)
			{
				plugin.getLogger().info(String.format("[%s] Attempted to load font style configuration for group %s, but found invalid font style name in %s.", plugin.getDescription().getName(), group, plugin.getConfig().getString("GROUP_CONFIG_FILE_NAME")));
			} else {
				playerListMod += MOD_SEPERATOR;
				playerListMod += plugin.getConfig().getString("style." + style);
			}
		}
		
		return playerListMod;
	}
	
}
