package com.BattleTrident.listeners.event

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.title.Title
import com.BattleTrident.Manager.onlinePlayers
import com.BattleTrident.Manager.runTaskLater
import com.BattleTrident.Manager.server
import com.BattleTrident.gameManager
import org.bukkit.GameMode
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent

class OnPlayerDeath : Listener {
	@EventHandler(priority = EventPriority.HIGHEST)
	fun onDeath(event: PlayerDeathEvent) {
		if (!gameManager.isPlaying) return

		event.player.gameMode = GameMode.SPECTATOR

		val winner = onlinePlayers.firstOrNull {
			it.gameMode != GameMode.SPECTATOR
		} ?: return

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

		server.broadcast(
			Component.text(
				"10초 뒤에 게임이 종료됩니다.",
				NamedTextColor.YELLOW
			)
		)
		runTaskLater(Runnable {
			gameManager.stop()
		}, 10 * 20)
	}
}