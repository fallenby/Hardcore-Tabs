package io.github.ye7i.hardcore_tabs;

import net.milkbowl.vault.permission.Permission;

import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.kitteh.tag.TagAPI;

public class Colorizer {

	private Permission perms = null;
	
	private final char MOD_SEPERATOR = '§';
	
	private HardcoreTabs plugin = null;
	
	public Colorizer(HardcoreTabs plugin)
	{
		this.plugin = plugin;
		
		if (!setupPermissions()) {
			 plugin.getServer().getPluginManager().disablePlugin(plugin);
	         
			 return;
	    }
		
	}
	
	public void applyTagForAllPlayers()
	{
		for (Player player : plugin.getServer().getOnlinePlayers())
		{
			applyTagForNearbyPlayers(player);
		}
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
		player.setPlayerListName(getPlayerListMod(player) + player.getName());
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
	
	public String getPlayerListMod(Player player)
	{
		
		if (!player.hasPermission("hardcoretabs.enabled"))
		{
			return "";
		}
		
		String group = perms.getPrimaryGroup(player);
		
		if (plugin.groupConfig.getConfig().getString(group + ".color") == null)
		{
			plugin.getLogger().info(String.format("[%s] Attempted to load color configuration for group %s, but could not find them in %s.", plugin.getDescription().getName(), group, plugin.getConfig().getString("GROUP_CONFIG_FILE_NAME")));
			return "";
		}
		
		String playerListMod = "";
		
		playerListMod += MOD_SEPERATOR;
		playerListMod += plugin.getConfig().getString("color." + plugin.groupConfig.getConfig().getString(perms.getPrimaryGroup(player) + ".color"));
		
		for (String style : plugin.groupConfig.getConfig().getStringList(perms.getPrimaryGroup(player) + ".font-styles"))
		{
			playerListMod += MOD_SEPERATOR;
			playerListMod += plugin.getConfig().getString("style." + style) == null ? "" : plugin.getConfig().getString("style." + style);
		}
		
		plugin.getLogger().info(playerListMod);
		return playerListMod;
	}
	
	private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = plugin.getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }
	
	
}
