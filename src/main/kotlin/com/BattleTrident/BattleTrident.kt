package com.BattleTrident

import com.BattleTrident.schedulers.ScheduleManager
import com.battletrident.games.ring.RingManager
import com.BattleTrident.commands.CommandManager
import com.BattleTrident.games.player.PlayerManager
import com.BattleTrident.games.state.GameManager
import com.BattleTrident.listeners.EventManager
import org.bukkit.plugin.java.JavaPlugin

val commandManager = CommandManager()
val playerManager = PlayerManager()
val scheduleManager = ScheduleManager()
val eventManager = EventManager()
val ringManager = RingManager()
val gameManager = GameManager()

object BattleTrident : JavaPlugin() {
    override fun onEnable() {

    }

    override fun onDisable() {

    }
}