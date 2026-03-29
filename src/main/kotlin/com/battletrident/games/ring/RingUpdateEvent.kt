package com.battletrident.games.ring

import org.bukkit.event.Event
import org.bukkit.event.HandlerList

class RingUpdateEvent(
	private var ring: Ring,
	private var state: RingState?,
) : Event() {
	companion object {
		val HANDLER_LIST: HandlerList = HandlerList()
		@JvmStatic fun getHandlerList() = HANDLER_LIST
	}
	override fun getHandlers() = HANDLER_LIST

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