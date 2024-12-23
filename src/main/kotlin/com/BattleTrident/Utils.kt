package com.BattleTrident

import org.bukkit.Server
import org.bukkit.World
import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitTask
import java.util.logging.Logger

object Utils {
	lateinit var plugin: BattleTrident
	lateinit var logger: Logger
	lateinit var server: Server
	lateinit var world: World
	lateinit var onlinePlayers: Collection<Player>

	fun register(plugin: BattleTrident) {
		this.plugin = plugin
		this.logger = plugin.logger
		this.server = plugin.server
		this.world = server.worlds.first()
		this.onlinePlayers = server.onlinePlayers
	}

	fun runTaskLater(task: Runnable, delay: Long): BukkitTask {
		return server.scheduler.runTaskLater(
			plugin,
			task,
			delay
		)
	}

	fun dispatchCommand(command: String): Boolean {
		return server.dispatchCommand(server.consoleSender, command)
	}
}