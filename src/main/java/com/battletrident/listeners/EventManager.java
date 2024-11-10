package com.battletrident.listeners;

import com.battletrident.listeners.event.*;
import com.battletrident.listeners.event.skill.*;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

import static com.battletrident.Consts.Plugin;

public class EventManager {
	private static final Listener[] LISTENERS = {
		new OnAdminGUI(),

		new OnGameUpdate(),
		new OnRingUpdate(),
		new OnPlayer(),
		new OnDeath(),

		new OnTrident(),
		new OnCinderella(),
		new OnBangal(),
	};

	public static void register() {
		for (Listener listener : LISTENERS) {
			Bukkit.getPluginManager()
				.registerEvents(listener, Plugin);
		}
	}
}