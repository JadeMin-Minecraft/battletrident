package com.BattleTrident.listeners.event

import com.BattleTrident.gameManager
import org.bukkit.GameMode
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.player.PlayerDropItemEvent
import org.bukkit.event.player.PlayerJoinEvent

class OnPlayerInteract : Listener {
	@EventHandler
	fun onPlayerJoin(evnet: PlayerJoinEvent) {
		val player = evnet.player

		if (gameManager.isPlaying) {
			player.gameMode = GameMode.SPECTATOR
			player.sendMessage("이미 진행 중인 게임이 있습니다. 관전자로 전환되었습니다.")
		}
	}

	/*@EventHandler
	fun onPlayerQuit(event: PlayerQuitEvent) {
		val player = event.player

		if (GameManager.isPlaying) {
			player.gameMode = GameMode.SPECTATOR
		}
	}*/

	@EventHandler
	fun onPlayerDropItem(event: PlayerDropItemEvent) {
		if (!gameManager.isPlaying) return

		event.isCancelled = true
	}

	@EventHandler
	fun onPlayerAttacksPlayer(event: EntityDamageByEntityEvent) {
		if (!gameManager.isPlaying) return

		val damager = event.damager
		val entity = event.entity

		if (damager is Player && entity is Player) {
			event.isCancelled = true
		}
	}
}