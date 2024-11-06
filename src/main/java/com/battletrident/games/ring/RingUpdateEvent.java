package com.battletrident.games.ring;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class RingUpdateEvent extends Event {
	private static final HandlerList HANDLER_LIST = new HandlerList();
	private RingState state;
	private Ring ring;

	public RingUpdateEvent(Ring ring, RingState state) {
		this.ring = ring;
		this.state = state;
	}

	public static HandlerList getHandlerList() {
		return HANDLER_LIST;
	}
	@Override
	public HandlerList getHandlers() {
		return HANDLER_LIST;
	}

	public Ring getRing() {
		return this.ring;
	}
	public void setRing(Ring ring) {
		this.ring = ring;
		this.state = RingState.SHRINKING;
	}
	public RingState getState() {
		return this.state;
	}
	public void setState(RingState state) {
		this.state = state;
	}
}