package io.papermc.BattleTrident.Games;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;



public final class PlayerManager {
	public static List<Player> players = null;

	public static final void update() {
		players = new ArrayList<Player>(Bukkit.getOnlinePlayers());
	}
}