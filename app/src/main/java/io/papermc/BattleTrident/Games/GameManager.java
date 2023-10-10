package io.papermc.BattleTrident.Games;

//import java.util.List;



public final class GameManager {
	/*private static final List<Listener> startListeners = List.of();
	private static final List<Listener> endListeners = List.of();*/
	private static GameState gameState = GameState.ENDED;
	
	public static interface Listener {
		public void update(final GameState state);
	};
	public static enum GameState {
		PLAYING,
		ENDED,
	};

	public static final void startGame() {
		gameState = GameState.PLAYING;
	}
	public static final void stopGame() {
		gameState = GameState.ENDED;
	}
	public static final GameState getGameState() {
		return gameState;
	}

	/*public final void subscribe(final GameState type, final Listener listener) {
		switch(type) {
			case PLAYING:
				startListeners.add(listener);
				break;
			case ENDED:
				endListeners.add(listener);
				break;
			default:
				throw new IllegalArgumentException("Invalid event type: " + type);
		}
	}
	public final void unsubscribe(final GameState type, final Listener listener) {
		switch(type) {
			case PLAYING:
				startListeners.remove(listener);
				break;
			case ENDED:
				endListeners.remove(listener);
				break;
			default:
				throw new IllegalArgumentException("Invalid event type: " + type);
		}
	}
	public final void notify(final GameState type, final GameState state) {
		switch(type) {
			case PLAYING:
				startListeners.forEach(listener -> listener.update(state));
				break;
			case ENDED:
				endListeners.forEach(listener -> listener.update(state));
				break;
			default:
				throw new IllegalArgumentException("Invalid event type: " + type);
		}
	}*/
}