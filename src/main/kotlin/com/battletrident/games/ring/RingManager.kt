package com.battletrident.games.ring

import com.battletrident.BattleTrident.Companion.plugin
import com.battletrident.BattleTrident.Companion.world
import org.bukkit.WorldBorder
import org.bukkit.scheduler.BukkitRunnable

class RingManager {
	private val worldBorder: WorldBorder = world.worldBorder
	private val phase: PhaseManager

	init {
		worldBorder.warningDistance = 30
		worldBorder.warningTime = 0
		worldBorder.damageBuffer = 0.0

		phase = PhaseManager()
	}

	private fun setRing(ring: Ring) {
		worldBorder.setSize(ring.size, ring.speed)
		worldBorder.damageAmount = ring.damage
	}

	private fun repeat() {
		val current: Ring = phase.current()
		setRing(current)

		current.ender = object : BukkitRunnable() {
			override fun run() {
				phase.notifyEnd()
				
				if (phase.hasMore()) {
					current.starter = object : BukkitRunnable() {
						override fun run() {
							phase.next()
							repeat()
						}
					}
					
					current.starter?.runTaskLater(plugin, current.delay * 20)
				}
			}
		}
		current.ender?.runTaskLater(
			plugin,
			current.speed * 20
		)
		
		/*
		current.ender = runTaskLater({
			phase.notifyEnd()
			if (phase.hasMore()) {
				current.starter = runTaskLater({
					phase.next()
					repeat()
				}, current.delay * 20)
			}
		}, current.speed * 20)
		 */
	}

	fun reset() {
		worldBorder.center = world.getSpawnLocation()

		phase.clearTasks()
		phase.set(0)
		setRing(phase.current())
	}

	fun start() {
		reset()

		repeat()
	}
}