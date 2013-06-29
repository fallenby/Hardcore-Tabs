package io.github.ye7i.hardcore_tabs;

import org.bukkit.plugin.java.JavaPlugin;

public final class HardcoreTabs extends JavaPlugin {
	
	public ConfigAccessor groupConfig = null;
	
	@Override
	public void onEnable()
	{
		groupConfig = new ConfigAccessor(this, getConfig().getString("GROUP_CONFIG_FILE_NAME"));
		groupConfig.saveDefaultConfig();
		
		getCommand("hctreload").setExecutor(new CommandExecutorReload(this));
		
		getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
		
		getLogger().info(String.format("$0 $1 $2", getDescription().getName(), getDescription().getVersion(), getConfig().getString("MSG_ENABLE")));
	}
	
	@Override
	public void onDisable()
	{
		getLogger().info(String.format("$0 $1 $2", getDescription().getName(), getDescription().getVersion(), getConfig().getString("MSG_DISABLE")));
	}
	
}