package com.BattleTrident

import com.BattleTrident.commands.CommandManager
import com.BattleTrident.games.player.PlayerManager
import com.BattleTrident.games.ring.RingManager
import com.BattleTrident.games.state.GameManager
import com.BattleTrident.listeners.EventManager
import com.BattleTrident.schedulers.ScheduleManager

object Managers {
	lateinit var commandManager: CommandManager
	lateinit var playerManager: PlayerManager
	lateinit var scheduleManager: ScheduleManager
	lateinit var eventManager: EventManager
	lateinit var ringManager: RingManager
	lateinit var gameManager: GameManager
}