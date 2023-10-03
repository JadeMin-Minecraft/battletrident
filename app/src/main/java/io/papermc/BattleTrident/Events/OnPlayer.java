package io.papermc.BattleTrident.Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.PlayerInventory;

import io.papermc.BattleTrident.Games.PlayerManager;



public final class OnPlayer implements Listener {
	@EventHandler
	public final void onPlayerJoin(final PlayerJoinEvent event) {
		PlayerManager.update();
	}

	@EventHandler
	public final void onPlayerQuit(final PlayerQuitEvent event) {
		PlayerManager.update();
	}

	@EventHandler
	public final void onPlayerDropItem(final PlayerDropItemEvent event) {
		event.setCancelled(true);
	}

	@EventHandler
	public final void onPlayerInventoryClick(final InventoryClickEvent event) {
		if(event.getClickedInventory() instanceof PlayerInventory) {
			event.setCancelled(true);
		}
	}
}