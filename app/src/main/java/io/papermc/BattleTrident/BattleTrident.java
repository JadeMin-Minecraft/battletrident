package io.papermc.BattleTrident;

import org.bukkit.plugin.java.JavaPlugin;

import io.papermc.BattleTrident.Commands.CommandManager;
import io.papermc.BattleTrident.Events.EventManager;
import io.papermc.BattleTrident.Schedules.ScheduleManager;



public final class BattleTrident extends JavaPlugin {
	@Override
	public final void onEnable() {
		final CommandManager commands = new CommandManager(this);
		final ScheduleManager schedules = new ScheduleManager(this);
		final EventManager events = new EventManager(this);
		
		commands.register();
		schedules.register();
		events.register();
	}

	@Override
	public final void onDisable() {}
}