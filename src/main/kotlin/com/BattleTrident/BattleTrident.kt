package com.BattleTrident

import com.BattleTrident.Managers.commandManager
import com.BattleTrident.Managers.eventManager
import com.BattleTrident.Managers.playerManager
import com.BattleTrident.Managers.ringManager
import com.BattleTrident.Managers.scheduleManager
import com.BattleTrident.commands.CommandManager
import com.BattleTrident.games.player.PlayerManager
import com.BattleTrident.games.ring.RingManager
import com.BattleTrident.listeners.EventManager
import com.BattleTrident.schedulers.ScheduleManager
import org.bukkit.plugin.java.JavaPlugin

class BattleTrident : JavaPlugin() {
	companion object {
		lateinit var plugin: BattleTrident
	}

	override fun onEnable() {
		plugin = this
		Utils.register(this)

		commandManager = CommandManager()
		playerManager = PlayerManager()
		scheduleManager = ScheduleManager()
		eventManager = EventManager()
		ringManager = RingManager()
	}

	override fun onDisable() {

	}
}