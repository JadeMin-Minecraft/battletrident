package com.battletrident.listeners

import com.battletrident.BattleTrident.Companion.plugin
import com.battletrident.guis.adminGUI.settingGUI.SettingGUIListener
import com.battletrident.listeners.event.*
import com.battletrident.listeners.event.gui.AdminGUIListener
import com.battletrident.skills.cinderella.gui.CinderellaGUIListener
import com.battletrident.skills.bangal.OnBangal
import com.battletrident.skills.cinderella.OnCinderella
import com.battletrident.skills.trident.OnTrident

class EventManager {
	val LISTENERS = listOf(
		OnPlayerJoinExit(),
		OnGameUpdate(),
		OnRingUpdate(),
		OnPlayerInteract(),
		OnPlayerDeath(),
		
		AdminGUIListener(),
		SettingGUIListener(),

		OnTrident(),
		OnCinderella(),
		OnBangal(),
		
		CinderellaGUIListener(),
	)

	init {
		val pm = plugin.server.pluginManager
		
		for (listener in LISTENERS) {
			pm.registerEvents(listener, plugin)
		}
	}
}