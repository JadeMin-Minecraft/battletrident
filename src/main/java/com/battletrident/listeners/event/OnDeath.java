package com.battletrident.listeners.event;

import com.battletrident.games.state.GameManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.title.Title;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.List;

import static com.battletrident.Consts.*;

public class OnDeath implements Listener {
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onDeath(PlayerDeathEvent event) {
		if (GameManager.isPlaying()) {
			event.getPlayer().setGameMode(GameMode.SPECTATOR);

			List<? extends Player> winner = getPlayers(p ->
				p.getGameMode() != GameMode.SPECTATOR
			);

			if (winner.size() == 1) {
				for (Player player : getPlayers()) {
					if (player.getGameMode() == GameMode.SPECTATOR) {
						player.showTitle(
							Title.title(
								Component.text(
									"GAME OVER",
									NamedTextColor.GRAY
								),
								Component.text(
									winner.getFirst().getName() + "님이 승리하셨습니다",
									NamedTextColor.YELLOW
								)
							)
						);
					}
				}

				winner.getFirst().showTitle(
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
				winner.getFirst().playSound(
					winner.getFirst(),
					"minecraft:ui.toast.challenge_complete",
					1.0f, 1.0f
				);

				Bukkit.broadcast(
					Component.text(
						"10초 뒤에 게임이 종료됩니다.",
						NamedTextColor.YELLOW
					)
				);
				runTaskLater(GameManager::stopGame, 10 * 20);
			}
		}
	}
}
