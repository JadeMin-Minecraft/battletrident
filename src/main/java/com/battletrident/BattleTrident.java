package com.battletrident;

import com.battletrident.commands.CommandManager;
import com.battletrident.games.ring.RingManager;
import com.battletrident.listeners.EventManager;
import com.battletrident.schedulers.ScheduleManager;
import org.bukkit.plugin.java.JavaPlugin;

import static com.battletrident.Consts.getWorld;

public class BattleTrident extends JavaPlugin {
	@Override
	public void onEnable() {
		Consts.Plugin = this;
		Consts.Logger = this.getLogger();

		CommandManager.register();
		ScheduleManager.register();
		EventManager.register();
		RingManager.register();
	}

	@Override
	public void onDisable() {

	}
}