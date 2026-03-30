package com.battletrident.listeners.event

import com.battletrident.BattleTrident.Companion.gameManager
import com.battletrident.BattleTrident.Companion.playerManager
import com.battletrident.BattleTrident.Companion.plugin
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.title.Title
import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.Sound
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.PlayerRespawnEvent
import org.bukkit.scheduler.BukkitRunnable

class OnPlayerDeath : Listener {
	fun doWhenGameEnds() {
		val deads = playerManager.getAll().filter {
			it.value.isAlive == false
		}.keys
		val alives = playerManager.getAll().filter {
			it.value.isAlive == true
		}.keys
		
		if (alives.isEmpty()) return
		
		val winner = alives.first()
		
		winner.showTitle(
			Title.title(
				Component.text(
					"YOU WIN!",
					NamedTextColor.YELLOW
				),
				Component.text(
					"이겼닭! 오늘 저녁은 치킨이닭!"
				)
			)
		)
		winner.playSound(
			winner,
			Sound.UI_TOAST_CHALLENGE_COMPLETE,
			1.0f, 1.0f
		)
		
		for (player in deads) {
			player.showTitle(
				Title.title(
					Component.text(
						"GAME OVER!",
						NamedTextColor.RED
					),
					Component.text(
						"${winner.name}님이 승리하셨습니다!",
						NamedTextColor.YELLOW
					)
				)
			)
		}
		
		Bukkit.broadcast(
			Component.text(
				"10초 뒤에 게임이 종료됩니다.",
				NamedTextColor.YELLOW
			)
		)
		
		object : BukkitRunnable() {
			override fun run() = gameManager.stop()
		}.runTaskLater(
			plugin,
			10 * 20L
		)
	}
	
	@EventHandler
	fun onDeath(event: PlayerDeathEvent) {
		if (!gameManager.isPlaying) return
		
		val player = event.player
		
		player.gameMode = GameMode.SPECTATOR
		playerManager.get(player)?.isAlive = false
		
		doWhenGameEnds()
	}
	
	@EventHandler
	fun onRespawn(event: PlayerRespawnEvent) {
		if (!gameManager.isPlaying) return
		
		val player = event.player
		
		if (playerManager.get(player)?.isAlive == false) {
			val loc = player.lastDeathLocation ?: return
			
			event.respawnLocation = loc
		}
	}
}