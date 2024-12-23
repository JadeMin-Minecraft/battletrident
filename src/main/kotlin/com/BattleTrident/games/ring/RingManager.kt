package com.BattleTrident.games.ring

import com.BattleTrident.Utils.runTaskLater
import com.BattleTrident.Utils.world
import org.bukkit.WorldBorder

class RingManager {
	private val worldBorder: WorldBorder = world.getWorldBorder()
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

		current.ender = runTaskLater({
			phase.notifyEnd()
			if (phase.hasMore()) {
				current.starter = runTaskLater({
					phase.next()
					repeat()
				}, current.delay * 20)
			}
		}, current.speed * 20)
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