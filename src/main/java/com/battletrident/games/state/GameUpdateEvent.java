package com.battletrident.games.state;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class GameUpdateEvent extends Event {
	private static final HandlerList HANDLER_LIST = new HandlerList();
	private GameState gameState;
	private Player executor;

	public GameUpdateEvent(GameState gamestate, Player executor) {
		this.gameState = gamestate;
		this.executor = executor;
	}

	public static HandlerList getHandlerList() {
		return HANDLER_LIST;
	}
	@Override
	public HandlerList getHandlers() {
		return HANDLER_LIST;
	}

	public GameState getGameState() {
		return this.gameState;
	}
	public void setGameState(GameState gameState, Player executor) {
		this.gameState = gameState;
		this.executor = executor;
	}

	public Player getExecutor() {
		return executor;
	}
}
