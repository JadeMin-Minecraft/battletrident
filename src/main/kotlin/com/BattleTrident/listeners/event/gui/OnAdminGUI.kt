package com.BattleTrident.listeners.event.gui

import com.BattleTrident.Manager.dispatchCommand
import com.BattleTrident.gameManager
import com.BattleTrident.guis.adminGUI.AdminGUI
import com.BattleTrident.guis.adminGUI.settingGUI.SettingGUI
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent

class OnAdminGUI : Listener {
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

	@EventHandler
	fun onInitClick(event: InventoryClickEvent) {
		val clickedItem = event.currentItem ?: return
		val player = event.whoClicked as Player

		if (event.inventory.holder is SettingGUI) {
			event.isCancelled = true

			when (clickedItem.type) {
				Material.CLOCK -> {
					listOf(
						"gamerule doDaylightCycle false",
						"gamerule doWeatherCycle false",
						"gamerule doMobSpawning false",
						"gamerule doMobLoot false",
						"gamerule mobGriefing false",
						"gamerule doTileDrops false",
						"gamerule doFireTick false",
						"gamerule doEntityDrops false",
						"gamerule doImmediateRespawn true",
						"gamerule keepInventory true",
						"gamerule naturalRegeneration false"
					).forEach {
						dispatchCommand(it)
					}
				}
				Material.PLAYER_HEAD -> {
					val playerLoc = player.location

					player.world.setSpawnLocation(
						playerLoc.blockX,
						playerLoc.blockY,
						playerLoc.blockZ
					).also {
						if (it) {
							player.sendMessage("현재 위치가 맵 중앙 지점으로 설정되었습니다.")
						} else {
							player.sendMessage("맵 중앙 지점 설정에 실패했습니다.")
						}
					}
				}
				else -> return
			}
		}
	}
}