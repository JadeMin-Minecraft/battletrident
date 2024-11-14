package com.battletrident.consts;

import com.battletrident.BattleTrident;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

public class Plugin {
	public static BattleTrident Plugin;
	public static Logger Logger;

	public static Server getServer() {
		return Plugin.getServer();
	}

	public static BukkitScheduler getScheduler() {
		return getServer().getScheduler();
	}
	public static BukkitTask runTaskLater(Runnable task, long delay) {
		return getScheduler().runTaskLater(Plugin, task, delay);
	}

	public static World getWorld() {
		return getServer().getWorlds().getFirst();
	}
	public static World getWorld(String name) {
		return getServer().getWorld(name);
	}

	public static Collection<? extends Player> getPlayers() {
		return getServer().getOnlinePlayers();
	}
	public static List<? extends Player> getPlayers(Predicate<Player> predicate) {
		return getPlayers().stream().filter(predicate).toList();
	}

	public static boolean dispatchCommand(String command) {
		return getServer().dispatchCommand(getServer().getConsoleSender(), command);
	}
}