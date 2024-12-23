package com.BattleTrident.listeners.event.skill

import com.BattleTrident.gameManager
import com.BattleTrident.guis.CinderellaGUI.CinderellaGUI
import org.bukkit.Material
import org.bukkit.entity.EntityType
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent

class OnCinderella : Listener {
	companion object {
		val SKILL_ITEM = Material.ENDER_PEARL
		val SKILL_ENTITY = EntityType.ENDER_PEARL
		val SKILL_COOLDOWN = 120
		val SKILL_DURATION = 2
	}

	@EventHandler
	fun onCinderellaOpen(event: PlayerInteractEvent) {
		if (!gameManager.isPlaying) return

		val item = event.item ?: return
		val action = event.action
		val player = event.player

		if (item.type == SKILL_ITEM) {
			event.isCancelled = true

			if (player.hasCooldown(SKILL_ITEM)) return

			if (action.isLeftClick) {
				val holder = CinderellaGUI(player)
				player.openInventory(holder.getInventory())
			}
		}
	}
}