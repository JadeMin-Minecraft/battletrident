package com.battletrident.listeners.event.gui;

import com.battletrident.games.state.GameManager;
import com.battletrident.guis.adminGUI.AdminGUI;
import com.battletrident.guis.adminGUI.settingGUI.SettingGUI;
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
					case Material.WRITABLE_BOOK:
						player.openInventory(new SettingGUI().getInventory());
						break;
					case Material.FIREWORK_ROCKET:
						GameManager.playGame((Player) player);
						break;
					case Material.BARRIER:
						GameManager.stopGame((Player) player);
						break;
				}
			}
		}
	}

	@EventHandler
	public void onInitClick(InventoryClickEvent event) {
		ItemStack clickedItem = event.getCurrentItem();
		HumanEntity player = event.getWhoClicked();

		if (clickedItem != null) {
			if (event.getInventory().getHolder() instanceof SettingGUI) {
				event.setCancelled(true);

				switch (clickedItem.getType()) {
					case Material.PLAYER_HEAD:
						boolean result = player.getWorld().setSpawnLocation(
							player.getLocation().getBlockX(),
							player.getLocation().getBlockY(),
							player.getLocation().getBlockZ()
						);

						if (result) {
							player.sendMessage("현재 위치가 맵 중앙 지점으로 설정되었습니다.");
						} else {
							player.sendMessage("맵 중앙 지점 설정에 실패했습니다.");
						}
						break;
				}
			}
		}
	}
}