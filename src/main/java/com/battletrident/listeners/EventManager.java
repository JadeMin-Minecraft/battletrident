package com.battletrident.listeners;

import com.battletrident.listeners.event.*;
import com.battletrident.listeners.event.gui.OnAdminGUI;
import com.battletrident.listeners.event.skill.*;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

import static com.battletrident.consts.Plugin.Plugin;

public class EventManager {
	private final Listener[] LISTENERS = {
		new OnAdminGUI(),

		new OnGameUpdate(),
		new OnRingUpdate(),
		new OnPlayerInteract(),
		new OnPlayerDeath(),

		new OnTrident(),
		new OnCinderella(),
		new OnBangal(),
	};

	public EventManager() {
		for (Listener listener : LISTENERS) {
			Bukkit.getPluginManager()
				.registerEvents(listener, Plugin);
		}
	}
}