package com.battletrident.games.ring

import com.BattleTrident.games.ring.Ring
import com.BattleTrident.games.ring.RingState
import org.bukkit.event.Event
import org.bukkit.event.HandlerList

class RingUpdateEvent(
	private var ring: Ring,
	private var state: RingState?,
) : Event() {
	companion object {
		val handlerList: HandlerList = HandlerList()
	}
	override fun getHandlers(): HandlerList {
		return handlerList
	}

	fun getRing() = ring
	fun setRing(ring: Ring) {
		this.ring = ring
		this.state = RingState.SHRINKING
	}

	fun getState() = state
	fun setState(state: RingState) {
		this.state = state
	}
}