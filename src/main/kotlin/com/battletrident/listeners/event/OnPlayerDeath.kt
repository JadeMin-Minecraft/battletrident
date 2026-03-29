package com.battletrident.listeners.event

import com.battletrident.BattleTrident.Companion.gameManager
import com.battletrident.BattleTrident.Companion.playerManager
import com.battletrident.BattleTrident.Companion.plugin
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.title.Title
import org.bukkit.GameMode
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.scheduler.BukkitRunnable

class OnPlayerDeath : Listener {
	@EventHandler(priority = EventPriority.HIGHEST)
	fun onDeath(event: PlayerDeathEvent) {
		if (!gameManager.isPlaying) return
		
		val onlinePlayers = playerManager.getAll()

		event.player.gameMode = GameMode.SPECTATOR

		val winner = onlinePlayers.firstOrNull {
			it.gameMode != GameMode.SPECTATOR
		} ?: return

		winner.showTitle(
			Title.title(
				Component.text(
					"YOU ARE THE WINNER!",
					NamedTextColor.YELLOW
				),
				Component.text(
					"이겼닭! 오늘 저녁은 치킨이닭!"
				)
			)
		)
		winner.playSound(
			winner,
			"minecraft:ui.toast.challenge_complete",
			1.0f, 1.0f
		)

		for (player in onlinePlayers) {
			if (player.gameMode != GameMode.SPECTATOR) return

			player.showTitle(
				Title.title(
					Component.text(
						"GAME OVER",
						NamedTextColor.GRAY
					),
					Component.text(
						"${winner.name}님이 승리하셨습니다",
						NamedTextColor.YELLOW
					)
				)
			)
		}

		plugin.server.broadcast(
			Component.text(
				"10초 뒤에 게임이 종료됩니다.",
				NamedTextColor.YELLOW
			)
		)
		
		object : BukkitRunnable() {
			override fun run() {
				gameManager.stop()
			}
		}.runTaskLater(
			plugin,
			10 * 20
		)
	}
}