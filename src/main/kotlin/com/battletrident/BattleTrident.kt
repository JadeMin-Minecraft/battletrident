package com.battletrident

import com.battletrident.commands.CommandManager
import com.battletrident.games.player.PlayerManager
import com.battletrident.games.ring.RingManager
import com.battletrident.games.state.GameManager
import com.battletrident.listeners.EventManager
import com.battletrident.schedulers.ScheduleManager
import org.bukkit.World
import org.bukkit.plugin.java.JavaPlugin

class BattleTrident : JavaPlugin() {
	companion object {
		lateinit var plugin: BattleTrident
			private set
		lateinit var world: World
		
		lateinit var commandManager: CommandManager
		lateinit var playerManager: PlayerManager
		lateinit var scheduleManager: ScheduleManager
		lateinit var eventManager: EventManager
		lateinit var ringManager: RingManager
		lateinit var gameManager: GameManager
	}
	
	
	override fun onEnable() {
		plugin = this
		world = plugin.server.worlds.first()
		
		commandManager = CommandManager()
		playerManager = PlayerManager()
		gameManager = GameManager()
		eventManager = EventManager()
		ringManager = RingManager()
		scheduleManager = ScheduleManager()
	}

	override fun onDisable() {

	}
}