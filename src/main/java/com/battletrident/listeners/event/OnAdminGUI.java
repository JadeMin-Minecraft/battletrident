package com.battletrident.listeners.event;

import com.battletrident.games.state.GameManager;
import com.battletrident.guis.admin.AdminGUI;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class OnAdminGUI implements Listener {
	@EventHandler
	public void onAdminClick(InventoryClickEvent event) {
		ItemStack clickedItem = event.getCurrentItem();
		HumanEntity player = event.getWhoClicked();

		if (clickedItem != null) {
			if (event.getInventory().getHolder() instanceof AdminGUI) {
				event.setCancelled(true);

				switch (clickedItem.getType()) {
					case Material.FIREWORK_ROCKET:
						GameManager.playGame((Player) player);
						break;
					case Material.BARRIER:
						GameManager.stopGame((Player) player);
						break;
				}
				player.closeInventory();
			}
		}
	}
}