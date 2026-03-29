package com.battletrident.listeners.event

import com.battletrident.BattleTrident.Companion.commandManager
import com.battletrident.BattleTrident.Companion.gameManager
import com.battletrident.BattleTrident.Companion.playerManager
import com.battletrident.BattleTrident.Companion.plugin
import com.battletrident.BattleTrident.Companion.ringManager
import com.battletrident.BattleTrident.Companion.world
import com.battletrident.games.state.GameUpdateEvent
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.GameMode
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

class OnGameUpdate : Listener {
	@EventHandler
	fun onForceGameUpdate(event: GameUpdateEvent) {
		val executor = event.getExecutor() ?: return 
		
		if (executor.isOp) {
			if (gameManager.isPlaying) {
				plugin.server.broadcast(
					Component.text(
						"관리자에 의해 게임이 시작되었습니다.",
						NamedTextColor.YELLOW
					)
				)
			} else {
				plugin.server.broadcast(
					Component.text(
						"관리자에 의해 게임이 중단되었습니다.",
						NamedTextColor.YELLOW
					)
				)
			}
		}
	}

	@EventHandler
	fun onGameUpdate(event: GameUpdateEvent) {
		val onlinePlayers = playerManager.getAll()
		
		for (player in onlinePlayers) {
			player.inventory.clear()
			player.clearActivePotionEffects()

			player.gameMode = GameMode.ADVENTURE
		}

		if (gameManager.isPlaying) {
			ringManager.start()

			val worldSpawn = world.spawnLocation
			val ringRadius = world.worldBorder.size / 2
			
			commandManager.dispatchCommand(
				"spreadplayers ${worldSpawn.blockX} ${worldSpawn.blockZ} ${ringRadius / onlinePlayers.size} $ringRadius false @a"
			)
		} else {
			ringManager.reset()

			for (player in onlinePlayers) {
				player.resetCooldown()
				player.teleport(world.spawnLocation)
				playerManager.giveImmune(player)
			}
		}
	}
}