package io.github.ye7i.hardcore_tabs;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class HardcoreTabs extends JavaPlugin {

	@Override
	public void onEnable()
	{
		getLogger().info(getConfig().getString("PLUGIN_NAME") + " " + getConfig().getString("MSG_ENABLE"));
	
		Player[] onlinePlayers = getServer().getOnlinePlayers();
		for (Player player : onlinePlayers)
		{
			//getLogger().info("Set player " + player.getDisplayName() + " name to " + "§4" + player.getDisplayName());
			player.setDisplayName(player.getName());
			if (player.isOp())
				player.setPlayerListName("§2§l§o" + player.getName());
		}
		
		getCommand("bcast").setExecutor(new CommandExecutorBroadcast(this));
		getCommand("test").setExecutor(new CommandExecutorTest(this));
	}
	
	@Override
	public void onDisable()
	{
		getLogger().info(getConfig().getString("PLUGIN_NAME") + " " + getConfig().getString("MSG_DISABLE"));
	}
	
}
