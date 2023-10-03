package io.papermc.BattleTrident.Events;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

import io.papermc.BattleTrident.BattleTrident;



public final class EventManager {
	private final BattleTrident plugin;
	private final Listener[] listeners = {
		new OnPlayer(),
		new OnTrident(),
		new OnCinderella(),
		new OnBangal(),
	};

	public EventManager(final BattleTrident plugin) {
		this.plugin = plugin;
	}

	public final void register() {
		for(final Listener listener : this.listeners) {
			Bukkit.getPluginManager().registerEvents(listener, this.plugin);
		}
	}
}