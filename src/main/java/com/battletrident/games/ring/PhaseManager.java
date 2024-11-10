package com.battletrident.games.ring;

public class PhaseManager {
	public final Ring[] RINGS = {
		new Ring(0, 400, 2, 0, 10),
		new Ring(1, 300, 60, 0.0001, 30),
		new Ring(2, 200, 60, 0.0001, 30),
		new Ring(3, 100, 60, 0.1, 30),
		new Ring(4, 1, 60, 1, -1),
	};
	private final RingUpdateEvent event = new RingUpdateEvent(RINGS[0], null);

	private int index = 0;

	public void notifyEnd() {
		event.setState(RingState.ENDED);
		event.callEvent();
	}
	public boolean hasMore() {
		return index + 1 < RINGS.length;
	}
	public Ring current() {
		return event.getRing();
	}
	public Ring next() {
		event.setRing(RINGS[++index]);
		event.callEvent();

		return event.getRing();
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