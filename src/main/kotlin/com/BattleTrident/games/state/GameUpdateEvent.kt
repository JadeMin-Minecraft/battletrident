package com.BattleTrident.games.state

import org.bukkit.entity.Player
import org.bukkit.event.Event
import org.bukkit.event.HandlerList

class GameUpdateEvent(
	private var state: GameState,
	private var executor: Player?,
) : Event() {
	companion object {
		private val HANDLER_LIST = HandlerList()
	}
	override fun getHandlers() = HANDLER_LIST

	fun setState(gameState: GameState, executor: Player?) {
		this.state = gameState
		this.executor = executor
	}
	fun getState() = state

	fun getExecutor() = executor
}