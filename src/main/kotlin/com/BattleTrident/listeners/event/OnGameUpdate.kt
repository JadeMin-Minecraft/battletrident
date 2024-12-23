package com.BattleTrident.listeners.event

import com.BattleTrident.Managers.gameManager
import com.BattleTrident.Managers.ringManager
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import com.BattleTrident.Utils.dispatchCommand
import com.BattleTrident.Utils.onlinePlayers
import com.BattleTrident.Utils.server
import com.BattleTrident.Utils.world
import com.BattleTrident.games.state.GameUpdateEvent
import org.bukkit.GameMode
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

class OnGameUpdate : Listener {
	@EventHandler
	fun onForceGameUpdate(event: GameUpdateEvent) {
		val executor = event.getExecutor() as Player ?: return
		
		if (executor.isOp) {
			if (gameManager.isPlaying) {
				server.broadcast(
					Component.text(
						"관리자에 의해 게임이 시작되었습니다.",
						NamedTextColor.YELLOW
					)
				)
			} else {
				server.broadcast(
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
		for (player in onlinePlayers) {
			player.inventory.clear()
			player.clearActivePotionEffects()

			player.gameMode = GameMode.ADVENTURE
		}

		if (gameManager.isPlaying) {
			ringManager.start()

			val worldSpawn = world.spawnLocation
			val ringRadius = world.worldBorder.size / 2
			dispatchCommand(
				"spreadplayers ${worldSpawn.blockX} ${worldSpawn.blockZ} ${ringRadius / onlinePlayers.size} $ringRadius false @a"
			)
		} else {
			ringManager.reset()

			for (player in onlinePlayers) {
				player.resetCooldown()
				player.teleport(world.spawnLocation)
				player.addPotionEffects(
					listOf(
						PotionEffect(
							PotionEffectType.WEAKNESS,
							PotionEffect.INFINITE_DURATION,
							Integer.MAX_VALUE,
							true
						),
						PotionEffect(
							PotionEffectType.RESISTANCE,
							PotionEffect.INFINITE_DURATION,
							Integer.MAX_VALUE,
							true
						),
						PotionEffect(
							PotionEffectType.REGENERATION,
							PotionEffect.INFINITE_DURATION,
							Integer.MAX_VALUE,
							true
						)
					)
				)
			}
		}
	}
}