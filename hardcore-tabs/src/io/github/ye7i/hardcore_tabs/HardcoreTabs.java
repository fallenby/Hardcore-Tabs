package io.github.ye7i.hardcore_tabs;

import org.bukkit.plugin.java.JavaPlugin;

public final class HardcoreTabs extends JavaPlugin {
	
	public ConfigAccessor groupConfig = null;
	
	Colorizer colorizer = null;
	
	@Override
	public void onEnable()
	{
		colorizer = new Colorizer(this);
		
		groupConfig = new ConfigAccessor(this, getConfig().getString("GROUP_CONFIG_FILE_NAME"));
		groupConfig.saveDefaultConfig();
		
		getCommand("hctreload").setExecutor(new CommandExecutorReload(this));
		
		getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
		getServer().getPluginManager().registerEvents(new NameTagReceiveListener(this), this);
		
		getLogger().info(String.format("$0 $1 $2", getDescription().getName(), getDescription().getVersion(), getConfig().getString("MSG_ENABLE")));
	}
	
	@Override
	public void onDisable()
	{
		getLogger().info(String.format("$0 $1 $2", getDescription().getName(), getDescription().getVersion(), getConfig().getString("MSG_DISABLE")));
	}
	
}