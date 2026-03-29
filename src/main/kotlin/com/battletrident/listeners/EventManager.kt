package com.battletrident.listeners

import com.battletrident.BattleTrident.Companion.plugin
import com.battletrident.listeners.event.*
import com.battletrident.listeners.event.gui.AdminGUIListener
import com.battletrident.listeners.event.gui.CinderellaGUIListener
import com.battletrident.listeners.event.skill.OnCinderella
import com.battletrident.listeners.event.skill.OnOctane
import com.battletrident.listeners.event.skill.OnTrident

class EventManager {
	val LISTENERS = listOf(
		AdminGUIListener(),
		CinderellaGUIListener(),

		OnPlayerJoinExit(),
		OnGameUpdate(),
		OnRingUpdate(),
		OnPlayerInteract(),
		OnPlayerDeath(),

		OnTrident(),
		OnCinderella(),
		OnOctane(),
	)

	init {
		val pm = plugin.server.pluginManager
		
		for (listener in LISTENERS) {
			pm.registerEvents(listener, plugin)
		}
	}
}