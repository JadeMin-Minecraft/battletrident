package com.battletrident.games.state;

import org.bukkit.entity.Player;

public class GameManager {
	private static final GameUpdateEvent gameState = new GameUpdateEvent(GameState.ENDED, null);

	public static void playGame(Player executor) {
		gameState.setGameState(GameState.PLAYING, executor);
		gameState.callEvent();
	}
	public static void stopGame(Player executor) {
		gameState.setGameState(GameState.ENDED, executor);
		gameState.callEvent();
	}

	public static boolean isPlaying() {
		return gameState.getGameState() == GameState.PLAYING;
	}
	public static boolean isEnded() {
		return gameState.getGameState() == GameState.ENDED;
	}
	public static boolean isMapChanging() {
		return gameState.getGameState() == GameState.MAP_CHANGING;
	}
}
