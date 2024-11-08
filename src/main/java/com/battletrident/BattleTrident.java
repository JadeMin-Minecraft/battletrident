package com.battletrident;

import com.battletrident.commands.CommandManager;
import com.battletrident.games.ring.RingManager;
import com.battletrident.listeners.EventManager;
import com.battletrident.schedulers.ScheduleManager;
import org.bukkit.plugin.java.JavaPlugin;

public class BattleTrident extends JavaPlugin {
	@Override
	public void onEnable() {
		Consts.Plugin = this;
		Consts.logger = this.getLogger();

		CommandManager.register();
		ScheduleManager.register();
		EventManager.register();
		RingManager.register();
	}

	@Override
	public void onDisable() {

	}
}