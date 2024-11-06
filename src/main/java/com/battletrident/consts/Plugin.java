package com.battletrident.consts;

import com.battletrident.BattleTrident;
import org.bukkit.Server;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;

public class Plugin {
	public static BattleTrident plugin;

	public static Server getServer() {
		return plugin.getServer();
	}

	public static BukkitScheduler getScheduler() {
		return getServer().getScheduler();
	}
	public static BukkitTask runTaskLater(Runnable r, long delay) {
		return getScheduler().runTaskLater(plugin, r, delay);
	}
}
