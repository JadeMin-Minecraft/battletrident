package com.BattleTrident

import org.bukkit.scheduler.BukkitTask

object Manager {
	val Logger get() = BattleTrident.logger
	val server get() = BattleTrident.server
	val onlinePlayers get() = server.onlinePlayers
	val world get() = server.worlds.first()

	fun runTaskLater(task: Runnable, delay: Long): BukkitTask {
		return server.scheduler.runTaskLater(
			BattleTrident,
			task,
			delay
		)
	}
	fun runTaskTimer(task: Runnable, delay: Long, period: Long): BukkitTask {
		return server.scheduler.runTaskTimer(
			BattleTrident,
			task,
			delay,
			period
		)
	}

	fun dispatchCommand(command: String): Boolean {
		return server.dispatchCommand(server.consoleSender, command)
	}
}