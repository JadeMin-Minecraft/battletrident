package com.battletrident.games.ring

import com.BattleTrident.games.ring.Ring
import com.BattleTrident.games.ring.RingState

class PhaseManager {
	private val RINGS = arrayOf(
		Ring(0, 300.0, 2, 0.0, 10),
		Ring(1, 200.0, 30, 0.0001, 30),
		Ring(2, 100.0, 20, 0.1, 30),
		Ring(3, 1.0, 60, 0.5, -1),
	)
	private val event = RingUpdateEvent(RINGS[0], null)

	private var index = 0

	fun notifyEnd() {
		event.setState(RingState.ENDED)
		event.callEvent()
	}

	fun hasMore(): Boolean {
		return index + 1 < RINGS.size
	}

	fun current(): Ring {
		return event.getRing()
	}

	fun next(): Ring {
		event.setRing(RINGS[++index])
		event.callEvent()

		return event.getRing()
	}

	fun set(i: Int) {
		if (i >= 0 && i < RINGS.size) {
			index = i
			event.setRing(RINGS[i])
			event.callEvent()
		} else throw IndexOutOfBoundsException()
	}

	fun clearTasks() {
		for (ring in RINGS) {
			ring.ender?.cancel()
			ring.starter?.cancel()
		}
	}
}