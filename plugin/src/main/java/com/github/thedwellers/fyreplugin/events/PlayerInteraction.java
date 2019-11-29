package com.github.thedwellers.fyreplugin.events;

import com.comphenix.protocol.ProtocolManager;

import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

/**
 * PlayerInteraction
 */
public class PlayerInteraction implements Listener {

	public PlayerInteraction(ProtocolManager protocolManager) {

	}

	@EventHandler()
	public void onPlayerUseItem(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if (player.getInventory().getItemInMainHand().getType() == Material.BRICK) {
			player.getInventory().removeItemAnySlot(new ItemStack(Material.BRICK, 1));
			Snowball ball = player.launchProjectile(Snowball.class);
			Item item = ball.getWorld().dropItem(ball.getLocation(), new ItemStack(Material.BRICK, 1));
			ball.addPassenger(item);
		}

		// https://github.com/libraryaddict/LibsDisguises/blob/master/src/main/java/me/libraryaddict/disguise/disguisetypes/Disguise.java
		// https://github.com/aadnk/ProtocolLib/blob/39e0c974ee32dcbe108cdd7aa52bbcb9de4a67d6/modules/ProtocolLib/src/main/java/com/comphenix/protocol/injector/DelayedPacketManager.java
		// https://github.com/aadnk/ProtocolLib/blob/39e0c974ee32dcbe108cdd7aa52bbcb9de4a67d6/modules/ProtocolLib/src/main/java/com/comphenix/protocol/injector/PacketFilterManager.java
		// https://github.com/aadnk/ProtocolLib/blob/39e0c974ee32dcbe108cdd7aa52bbcb9de4a67d6/modules/ProtocolLib/src/main/java/com/comphenix/protocol/injector/PacketFilterManager.java#L749
		// https://github.com/aadnk/ProtocolLib/blob/39e0c974ee32dcbe108cdd7aa52bbcb9de4a67d6/modules/ProtocolLib/src/main/java/com/comphenix/protocol/injector/PacketFilterManager.java#L786
		// https://github.com/aadnk/ProtocolLib/blob/39e0c974ee32dcbe108cdd7aa52bbcb9de4a67d6/modules/ProtocolLib/src/main/java/com/comphenix/protocol/injector/netty/ProtocolInjector.java#L402
		// https://github.com/aadnk/ProtocolLib/blob/39e0c974ee32dcbe108cdd7aa52bbcb9de4a67d6/modules/ProtocolLib/src/main/java/com/comphenix/protocol/injector/player/PlayerInjector.java#L243


	}
}
