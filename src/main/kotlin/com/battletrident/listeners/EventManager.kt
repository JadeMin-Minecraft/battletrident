package com.battletrident.listeners

import com.battletrident.BattleTrident.Companion.plugin
import com.battletrident.guis.adminGUI.settingGUI.SettingGUIListener
import com.battletrident.listeners.event.*
import com.battletrident.listeners.event.gui.AdminGUIListener
import com.battletrident.listeners.event.gui.CinderellaGUIListener
import com.battletrident.listeners.event.skill.OnBangal
import com.battletrident.listeners.event.skill.OnCinderella
import com.battletrident.listeners.event.skill.OnTrident

class EventManager {
	val LISTENERS = listOf(
		AdminGUIListener(),
		SettingGUIListener(),
		CinderellaGUIListener(),

		OnPlayerJoinExit(),
		OnGameUpdate(),
		OnRingUpdate(),
		OnPlayerInteract(),
		OnPlayerDeath(),

		OnTrident(),
		OnCinderella(),
		OnBangal(),
	)

	init {
		val pm = plugin.server.pluginManager
		
		for (listener in LISTENERS) {
			pm.registerEvents(listener, plugin)
		}
	}
}