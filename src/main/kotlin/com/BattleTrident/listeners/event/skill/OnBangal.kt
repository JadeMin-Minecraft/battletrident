package com.BattleTrident.listeners.event.skill

import com.BattleTrident.Managers.gameManager
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.entity.EntityRegainHealthEvent
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

class OnBangal : Listener {
	companion object {
		private const val TARGET_HEALTH = 9.0

		fun doBangal(health: Double, player: Player) {
			if (health <= TARGET_HEALTH) {
				player.addPotionEffect(
					PotionEffect(
						PotionEffectType.SPEED,
						20, 1, false, false
					)
				)
			}
		}
	}

	@EventHandler
	fun onEntityDamage(event: EntityDamageEvent) {
		if (!gameManager.isPlaying) return

		if (event.entityType != EntityType.PLAYER) {
			val player = event.entity as Player
			val finalHealth = player.health - event.finalDamage

			doBangal(finalHealth, player)
		}
	}

	@EventHandler
	fun onPlayerHealt(event: EntityRegainHealthEvent) {
		if (!gameManager.isPlaying) return

		val entity = event.entity

		if (entity.type == EntityType.PLAYER) {
			val player = entity as Player
			val finalHealth = player.health + event.amount

			doBangal(finalHealth, player)
		}
	}
}