package io.github.the_dwellers.fyreplugin.events;

import com.destroystokyo.paper.event.server.ServerTickEndEvent;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.List;
import java.util.Random;

/**
 * TickEnd
 */
public class TickEnd implements Listener {
	private Random rand;

	@EventHandler()
	public void onTickEndEvent(ServerTickEndEvent event) {
		if (rand == null) {
			rand = new Random();
		}

		List<World> worlds = Bukkit.getWorlds();
		for (World world : worlds) {
			if (world.isDayTime() && rand.nextInt(2) == 0) {
				world.setTime(world.getTime() - 1);
			}
		}
	}
}
