package io.github.the_dwellers.fyreplugin.events;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * KnowledgeBookUse
 */
public class KnowledgeBookUse implements Listener {

	@EventHandler()
	public void onKnowledgeBook(PlayerInteractEvent event) {
		if (event.getItem().getType() != Material.KNOWLEDGE_BOOK) {
			return;
		}
		event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_ENDER_EYE_DEATH, SoundCategory.MASTER, 1, 1);
	}
}
