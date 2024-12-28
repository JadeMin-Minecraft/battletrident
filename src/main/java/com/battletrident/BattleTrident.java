package com.battletrident;

import com.battletrident.consts.Managers;
import com.battletrident.consts.Plugin;
import com.battletrident.commands.CommandManager;
import com.battletrident.games.player.PlayerManager;
import com.battletrident.games.ring.RingManager;
import com.battletrident.listeners.EventManager;
import com.battletrident.schedulers.ScheduleManager;
import org.bukkit.plugin.java.JavaPlugin;

public class BattleTrident extends JavaPlugin {
	@Override
	public void onEnable() {
		Plugin.Plugin = this;
		Plugin.Logger = this.getLogger();

		Managers.commandManager = new CommandManager();
		Managers.ringManager = new RingManager();
		Managers.scheduleManager = new ScheduleManager();
		Managers.eventManager = new EventManager();
		Managers.playerManager = new PlayerManager();
	}

	@Override
	public void onDisable() {

	}
}