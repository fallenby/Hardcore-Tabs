package io.github.ye7i.hardcore_tabs;

import net.milkbowl.vault.permission.Permission;

import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

/* Welcome any nosy hackers!
 * 
 * If you're reading this you've either managed to acquire the source code legitimately
 * or you've gone and decompiled my source files as well as somehow managed to acquire the plug-in.
 * 
 * As the code stands now (9 July 2013), it is unreleased and still closed-source.
 * I really do want to release this plug-in as well as its code, but the plug-in was developed
 * for a specific server in return for monetary compensation. I hope that you understand.
 * If not, oh well.
 * 
 * If you've managed to acquire this code via legitimate means then hey, it seems that I decided to
 * release the code.
 * 
 * If you've done the latter I don't blame you. My plug-ins are simply amazing, aren't they?
 * 
 * Either way I hope you have fun and learn something here, though there's not much to be learned.
 * Also, if you decide to use bits of my code directly or even go so far as to completely copy
 * the code and modify it, please give credit where it's due and add a reference to myself (Ye7i).
 * 
 * If you decide not to add a reference I hope that your toes are nibbled off by Silverfish
 * and your mind lost to an Enderman.
 * 
 * Peace,
 * Ye7i.
 * 
 */

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
			getLogger().info("[%s] Could not be enabled because permissions could not be set up.");
			
			this.getServer().getPluginManager().disablePlugin(this);
			return;
	    }
		
		getLogger().info(String.format("[%s] %s %s", getDescription().getName(), getDescription().getVersion(), getConfig().getString("MSG_ENABLE")));
	}
	
	@Override
	public void onDisable()
	{
		getLogger().info(String.format("[%s] %s %s", getDescription().getName(), getDescription().getVersion(), getConfig().getString("MSG_DISABLE")));
	}
	
	private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = this.getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }
	
}