package com.battletrident;

import com.battletrident.commands.CommandManager;
import com.battletrident.games.ring.RingManager;
import com.battletrident.consts.Plugin;
import com.battletrident.listeners.EventManager;
import com.battletrident.schedulers.ScheduleManager;
import org.bukkit.plugin.java.JavaPlugin;

public class BattleTrident extends JavaPlugin {
	@Override
	public void onEnable() {
		Plugin.plugin = this;

		CommandManager.register();
		ScheduleManager.register();
		EventManager.register();
		RingManager.register();
	}

	@Override
	public void onDisable() {

	}
}