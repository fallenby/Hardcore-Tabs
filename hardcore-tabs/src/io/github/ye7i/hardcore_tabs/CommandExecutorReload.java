package io.github.ye7i.hardcore_tabs;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandExecutorReload implements CommandExecutor {

	private static HardcoreTabs plugin = null;
	
	public CommandExecutorReload(HardcoreTabs plugin)
	{
		CommandExecutorReload.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		
		if (!cmd.getName().equalsIgnoreCase("hctreload"))
		{
			return false;
		}
		
		plugin.groupConfig.reloadConfig();

		plugin.tabList.applyTabListForAllPlayers();
		plugin.nameTags.applyTagForAllNearbyPlayers();
		
		sender.sendMessage(String.format("[%s] Configuration has been reloaded.", plugin.getDescription().getName()));
		
		return true;
	} 
	
}
