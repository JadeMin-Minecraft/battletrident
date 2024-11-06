package com.battletrident.listeners.event;

import com.battletrident.games.state.GameManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.PlayerInventory;

public class OnPlayer implements Listener {
	@EventHandler
	public void onPlayerDropItem(PlayerDropItemEvent event) {
		if (!GameManager.isPlaying()) return;

		event.setCancelled(true);
	}

	@EventHandler
	public void onPlayerInventoryClick(InventoryClickEvent event) {
		if (!GameManager.isPlaying()) return;

		if (event.getClickedInventory() instanceof PlayerInventory) {
			event.setCancelled(true);
		}
	}
}