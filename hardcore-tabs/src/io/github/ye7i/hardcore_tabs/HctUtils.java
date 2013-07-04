package io.github.ye7i.hardcore_tabs;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class HctUtils {
	
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
	
}
