package com.battletrident.games.ring;

public class PhaseManager {
	public final Ring[] RINGS = {
		new Ring(0, 300, 2, 0, 10),
		new Ring(1, 200, 40, 0.001, 30),
		new Ring(2, 100, 40, 0.1, 30),
		new Ring(3, 1, 60, 1, -1),
	};

	private int index = 0;
	private final RingUpdateEvent event = new RingUpdateEvent(RINGS[index], null);

	public void notifyEnd() {
		event.setState(RingState.ENDED);
		event.callEvent();
	}
	public boolean hasMore() {
		return index < RINGS.length + 1;
	}
	public Ring current() {
		return event.getRing();
	}
	public Ring next() {
		if (hasMore()) {
			event.setRing(RINGS[++index]);
			event.callEvent();

			return event.getRing();
		} else throw new IndexOutOfBoundsException();
	}
	public Ring previous() {
		if (index > 0) {
			event.setRing(RINGS[--index]);
			event.callEvent();

			return event.getRing();
		} else throw new IndexOutOfBoundsException();
	}
	public void set(int i) {
		if (i >= 0 && i < RINGS.length) {
			event.setRing(RINGS[index = i]);
			event.callEvent();
		} else throw new IndexOutOfBoundsException();
	}
	public void clearTasks() {
		for (Ring ring : RINGS) {
			if (ring.ender != null)
				ring.ender.cancel();
			if (ring.starter != null)
				ring.starter.cancel();
		}
	}
}