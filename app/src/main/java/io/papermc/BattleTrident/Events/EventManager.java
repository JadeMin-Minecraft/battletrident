package io.papermc.BattleTrident.Events;

import org.bukkit.Bukkit;

import io.papermc.BattleTrident.BattleTrident;



public final class EventManager {
	private final BattleTrident plugin;

	public EventManager(final BattleTrident plugin) {
		this.plugin = plugin;
	}

	public final void register() {
		Bukkit.getPluginManager().registerEvents(new Events(), this.plugin);
	}
}