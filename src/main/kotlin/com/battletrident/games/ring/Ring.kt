package com.battletrident.games.ring

import org.bukkit.scheduler.BukkitRunnable

data class Ring(
	val no: Int,
	val size: Double,
	val speed: Long,
	val damage: Double,
	val delay: Long,
	var ender: BukkitRunnable? = null,
	var starter: BukkitRunnable? = null,
) {
}