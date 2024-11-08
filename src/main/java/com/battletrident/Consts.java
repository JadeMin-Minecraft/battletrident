package com.battletrident;

import org.bukkit.Server;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;

import java.util.logging.Logger;

public class Consts {
	public static BattleTrident Plugin;
	public static Logger logger;

	public static Server getServer() {
		return Plugin.getServer();
	}

	public static BukkitScheduler getScheduler() {
		return getServer().getScheduler();
	}
	public static BukkitTask runTaskLater(Runnable task, long delay) {
		return getScheduler().runTaskLater(Plugin, task, delay);
	}
}
