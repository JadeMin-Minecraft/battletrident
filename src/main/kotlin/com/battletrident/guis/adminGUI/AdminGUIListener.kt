package com.battletrident.listeners.event.gui

import com.battletrident.BattleTrident.Companion.gameManager
import com.battletrident.guis.adminGUI.AdminGUI
import com.battletrident.guis.adminGUI.settingGUI.SettingGUI
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent

class AdminGUIListener : Listener {
	@EventHandler
	fun onAdminClick(event: InventoryClickEvent) {
		val clickedItem = event.currentItem ?: return
		val player = event.whoClicked as Player

		if (event.inventory.holder is AdminGUI) {
			event.isCancelled = true

			when (clickedItem.type) {
				Material.WRITABLE_BOOK -> {
					player.openInventory(SettingGUI().getInventory())
				}
				Material.FIREWORK_ROCKET -> {
					gameManager.start(player)
				}
				Material.BARRIER -> {
					gameManager.stop(player)
				}
				else -> return
			}
		}
	}
}