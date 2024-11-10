package com.battletrident.games.state;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import static com.battletrident.Consts.getServer;

public class GameManager {
	private static final GameUpdateEvent gameState = new GameUpdateEvent(GameState.ENDED, null);

	public static void playGame() {
		gameState.setGameState(GameState.PLAYING, null);
		gameState.callEvent();
	}
	public static void playGame(Player executor) {
		gameState.setGameState(GameState.PLAYING, executor);
		gameState.callEvent();
	}
	public static void stopGame() {
		gameState.setGameState(GameState.ENDED, null);
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
