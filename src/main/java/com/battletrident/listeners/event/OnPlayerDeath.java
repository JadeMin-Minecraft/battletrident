package com.battletrident.listeners.event;

import com.battletrident.games.state.GameManager;
import net.kyori.adventure.identity.Identity;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.title.Title;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.battletrident.consts.Plugin.*;

public class OnPlayerDeath implements Listener {
	private final Map<Identity, Location> respawnLoc = new HashMap<>();

	private void doWhenGameEnds() {
		Collection<? extends Player> deadPlayers = getPlayers(
			p -> p.getGameMode() == GameMode.SPECTATOR
		);
		List<? extends Player> survivors = getPlayers(
			p -> p.getGameMode() != GameMode.SPECTATOR
		);

		if (survivors.size() == 1) {
			Player winner = survivors.getFirst();

			winner.showTitle(
				Title.title(
					Component.text(
						"YOU'RE THE WINNER!",
						NamedTextColor.YELLOW
					),
					Component.text(
						"이겼닭! 오늘 저녁은 치킨이닭!"
					)
				)
			);
			winner.playSound(
				winner,
				Sound.UI_TOAST_CHALLENGE_COMPLETE,
				1.0f, 1.0f
			);

			for (Player p : deadPlayers) {
				p.showTitle(
					Title.title(
						Component.text(
							"GAME OVER",
							NamedTextColor.GRAY
						),
						Component.text(
							survivors.getFirst().getName() + "님이 승리하셨습니다.",
							NamedTextColor.YELLOW
						)
					)
				);
			}

			Bukkit.broadcast(
				Component.text(
					"10초 뒤에 게임이 종료됩니다.",
					NamedTextColor.YELLOW
				)
			);
			runTaskLater(GameManager::stopGame, 10 * 20);
		}
	}
	/*private void doWhenDeath(Player deadPlayer) {
		deadPlayer.setGameMode(GameMode.SPECTATOR);
		deadPlayer.showTitle(
			Title.title(
				Component.text(
					"사망했습니다!",
					NamedTextColor.RED
				),
				Component.text(
					""
				)
			)
		);

		doWhenGameEnds();
	}*/


	@EventHandler
	public void onDeath(PlayerDeathEvent event) {
		if (!GameManager.isPlaying()) return;

		Player player = event.getPlayer();

		player.setGameMode(GameMode.SPECTATOR);
		respawnLoc.put(player.identity(), player.getLocation());
		doWhenGameEnds();
	}

	@EventHandler
	public void onRespawn(PlayerRespawnEvent event) {
		if (!GameManager.isPlaying()) return;

		Player player = event.getPlayer();

		if (player.getGameMode() == GameMode.SPECTATOR) {
			Location loc = respawnLoc.get(player.identity());

			if (loc != null) {
				event.setRespawnLocation(loc);
				respawnLoc.remove(player.identity());
			}
		}
	}
}
