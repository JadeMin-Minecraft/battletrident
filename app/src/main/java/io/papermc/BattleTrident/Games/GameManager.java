package io.papermc.BattleTrident.Games;



public final class GameManager {
	public static enum GameState {
		NOT_STARTED,
		PLAYING,
		ENDED,
	};

	public static GameState gameState = GameState.NOT_STARTED;

	public static final void startGame() {
		GameManager.gameState = GameState.PLAYING;
	}

	public static final void stopGame() {
		GameManager.gameState = GameState.ENDED;
	}
}