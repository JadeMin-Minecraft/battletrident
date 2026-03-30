package com.battletrident.listeners.event

import com.battletrident.BattleTrident.Companion.playerManager
import org.bukkit.attribute.Attribute
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent

class OnPlayerJoinExit : Listener {
	@EventHandler
	fun onPlayerQuit(event: PlayerJoinEvent) {
		playerManager.add(event.player)
	}

	@EventHandler
	fun onPlayerQuit(event: PlayerQuitEvent) {
		playerManager.remove(event.player)
	}
	
	
	@EventHandler
	fun makePlayers2LineHealth(event: PlayerJoinEvent) {
		val player = event.player
		
		val maxHealth = player.getAttribute(Attribute.MAX_HEALTH)
		if (maxHealth != null) {
			maxHealth.baseValue = 40.0
		}
	}
}