package com.BattleTrident.games.ring

import org.bukkit.scheduler.BukkitTask

data class Ring(
	val no: Int,
	val size: Double,
	val speed: Long,
	val damage: Double,
	val delay: Long,
	var starter: BukkitTask? = null,
	var ender: BukkitTask? = null,
) {
}