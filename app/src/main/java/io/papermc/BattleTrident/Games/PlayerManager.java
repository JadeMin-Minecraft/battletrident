package io.papermc.BattleTrident.Games;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;



public final class PlayerManager {
	public final List<Player> players = List.of();

	public PlayerManager() {
		Bukkit.getOnlinePlayers().forEach(player -> {
			players.add(player);
		});
	}
}