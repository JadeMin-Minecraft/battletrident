package com.battletrident;

import com.battletrident.consts.Managers;
import com.battletrident.consts.Plugin;
import com.battletrident.commands.CommandManager;
import com.battletrident.games.ring.RingManager;
import com.battletrident.listeners.EventManager;
import com.battletrident.schedulers.ScheduleManager;
import org.bukkit.plugin.java.JavaPlugin;

public class BattleTrident extends JavaPlugin {
	@Override
	public void onEnable() {
		Plugin.Plugin = this;
		Plugin.Logger = this.getLogger();

		Managers.CommandManager = new CommandManager();
		Managers.ScheduleManager = new ScheduleManager();
		Managers.EventManager = new EventManager();
		Managers.RingManager = new RingManager();
	}

	@Override
	public void onDisable() {

	}
}