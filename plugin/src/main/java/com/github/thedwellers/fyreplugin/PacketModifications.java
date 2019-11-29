package com.github.thedwellers.fyreplugin;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;

/**
 * PacketModifications
 */
public abstract class PacketModifications {

	public static void startFilters(FyrePlugin plugin, ProtocolManager protocolManager) {
		protocolManager.addPacketListener(
			new PacketAdapter(plugin, ListenerPriority.NORMAL, PacketType.Play.Server.SPAWN_ENTITY) {
				@Override
				public void onPacketSending(PacketEvent event) {
					if (event.getPacketType() == PacketType.Play.Server.SPAWN_ENTITY) {
						PacketContainer packet = event.getPacket();
					}
				}
			});
	}
}
