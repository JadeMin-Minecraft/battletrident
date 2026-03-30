package com.battletrident.listeners.event.skill

import com.battletrident.BattleTrident.Companion.gameManager
import com.battletrident.BattleTrident.Companion.plugin
import org.bukkit.Material
import org.bukkit.Particle
import org.bukkit.Sound
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.ProjectileHitEvent
import org.bukkit.event.entity.ProjectileLaunchEvent
import org.bukkit.scheduler.BukkitRunnable

class OnTrident : Listener {
	private val SKILL_ITEM = Material.TRIDENT
	private val SKILL_ENTITY = EntityType.TRIDENT
	private val SKILL_COOLDOWN = 2
	private val EXPLOSION_SIZE = 0.0f
	

	@EventHandler
	fun onTridentThrow(event: ProjectileLaunchEvent) {
		if (!gameManager.isPlaying) return

		val projectile = event.entity
		val shooter = projectile.shooter as? Player ?: return

		if (projectile.type == SKILL_ENTITY) {
			shooter.setCooldown(SKILL_ITEM, SKILL_COOLDOWN * 20)

			object : BukkitRunnable() {
				override fun run() {
					if (projectile.isDead) {
						this.cancel()
					} else {
						shooter.world.spawnParticle(
							Particle.SONIC_BOOM,
							projectile.location,
							1
						)
					}
				}
			}.runTaskTimer(plugin, 0, 1)
		}
	}

	@EventHandler
	fun onTridentHit(event: ProjectileHitEvent) {
		if (!gameManager.isPlaying) return

		val projectile = event.entity
		val shooter = projectile.shooter as? Player ?: return

		if (projectile.type == SKILL_ENTITY) {
			if (event.hitEntity != null) {
				val world = projectile.world
				
				shooter.playSound(
					shooter.location,
					Sound.ENTITY_ARROW_HIT_PLAYER,
					100f, 1.0f
				)
				world.createExplosion(
					projectile.location,
					EXPLOSION_SIZE,
					false, false
				)
				world.strikeLightning(
					projectile.location
				)
			}

			projectile.remove()
		}
	}
}