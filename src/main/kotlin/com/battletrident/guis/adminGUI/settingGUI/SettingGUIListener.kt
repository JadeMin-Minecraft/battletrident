package com.battletrident.guis.adminGUI.settingGUI

import com.battletrident.BattleTrident.Companion.commandManager
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent

class SettingGUIListener : Listener {
	@EventHandler
	fun onInitClick(event: InventoryClickEvent) {
		val clickedItem = event.currentItem ?: return
		val player = event.whoClicked as Player

		if (event.inventory.holder is SettingGUI) {
			event.isCancelled = true

			when (clickedItem.type) {
				Material.CLOCK -> {
					listOf(
						"gamerule advance_time false",
						"gamerule advance_weather false",
						"gamerule spawn_mobs false",
						"gamerule spawn_monsters false",
						"gamerule spawn_patrols false",
						"gamerule spawn_phantoms false",
						"gamerule spawn_wandering_traders false",
						"gamerule spawn_wardens false",
						"gamerule spawner_blocks_work false",
						"gamerule allow_entering_nether_using_portals false",
						"gamerule block_drops false",
						"gamerule block_explosion_drop_decay false",
						"gamerule entity_drops false",
						"gamerule keep_inventory true",
						"gamerule limited_crafting true",
						"gamerule locator_bar false",
						"gamerule mob_drops false",
						"gamerule mob_explosion_drop_decay false",
						"gamerule mob_griefing false",
						"gamerule natural_health_regeneration false",
						"gamerule projectiles_can_break_blocks false",
						"gamerule raids false",
						"gamerule random_tick_speed 0",
						"gamerule spectators_generate_chunks false",
						"gamerule spread_vines false",
						"gamerule tnt_explodes false",
						"gamerule tnt_explosion_drop_decay false",
					).forEach {
						commandManager.dispatchCommand(it)
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