package io.github.ye7i.hardcore_tabs;

import net.milkbowl.vault.permission.Permission;

import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class HardcoreTabs extends JavaPlugin {
	
	public ConfigAccessor groupConfig = null;
	
	public Permission perms = null;
	
	public ManagerNameTags nameTags = null;
	
	public ManagerTabList tabList = null;
	
	@Override
	public void onEnable()
	{
		nameTags = new ManagerNameTags(this);
		tabList = new ManagerTabList(this);
		
		groupConfig = new ConfigAccessor(this, getConfig().getString("GROUP_CONFIG_FILE_NAME"));
		groupConfig.saveDefaultConfig();
		
		getCommand("hctreload").setExecutor(new CommandExecutorReload(this));
		
		getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
		getServer().getPluginManager().registerEvents(new NameTagReceiveListener(this), this);
		getServer().getPluginManager().registerEvents(new PlayerChangedWorldListener(this), this);
		
		if (!setupPermissions()) {
			 this.getServer().getPluginManager().disablePlugin(this);
	         
			 return;
	    }
		
		getLogger().info(String.format("%s %s %s", getDescription().getName(), getDescription().getVersion(), getConfig().getString("MSG_ENABLE")));
	}
	
	@Override
	public void onDisable()
	{
		getLogger().info(String.format("%s %s %s", getDescription().getName(), getDescription().getVersion(), getConfig().getString("MSG_DISABLE")));
	}
	
	private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = this.getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }
	
}