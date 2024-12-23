package com.BattleTrident.listeners

import com.BattleTrident.BattleTrident.Companion.plugin
import com.BattleTrident.Utils.server
import com.BattleTrident.listeners.event.OnGameUpdate
import com.BattleTrident.listeners.event.OnPlayerDeath
import com.BattleTrident.listeners.event.OnPlayerInteract
import com.BattleTrident.listeners.event.OnRingUpdate
import com.BattleTrident.listeners.event.gui.OnAdminGUI
import com.BattleTrident.listeners.event.gui.OnCinderellaGUI
import com.BattleTrident.listeners.event.skill.OnBangal
import com.BattleTrident.listeners.event.skill.OnCinderella
import com.BattleTrident.listeners.event.skill.OnTrident

class EventManager {
	val LISTENERS = listOf(
		OnAdminGUI(),
		OnCinderellaGUI(),

		OnGameUpdate(),
		OnRingUpdate(),
		OnPlayerInteract(),
		OnPlayerDeath(),

		OnTrident(),
		OnCinderella(),
		OnBangal(),
	)

	init {
		for (listener in LISTENERS) {
			server.pluginManager.registerEvents(
				listener,
				plugin
			)
		}
	}
}