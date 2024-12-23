package com.BattleTrident.games.state

import org.bukkit.entity.Player

class GameManager {
	val gameState = GameUpdateEvent(GameState.ENDED, null)

	val isPlaying: Boolean
		get() = gameState.getState() == GameState.PLAYING
	val isEnded: Boolean
		get() = gameState.getState() == GameState.ENDED
	val isMapChanging: Boolean
		get() = gameState.getState() == GameState.MAP_CHANGING

	fun start(executor: Player? = null) {
		gameState.setState(GameState.PLAYING, executor)
		gameState.callEvent()
	}
	fun stop(executor: Player? = null) {
		gameState.setState(GameState.ENDED, executor)
		gameState.callEvent()
	}
}