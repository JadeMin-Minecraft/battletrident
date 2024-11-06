package com.battletrident.listeners.event;

import com.battletrident.games.ring.RingManager;
import com.battletrident.games.state.GameManager;
import com.battletrident.games.state.GameUpdateEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class OnGameUpdate implements Listener {
	@EventHandler
	public void onGameUpdate(GameUpdateEvent event) {
		if (GameManager.isPlaying()) {
			if (event.getExecutor().isOp()) {
				Bukkit.broadcast(
					Component.text(
						"관리자에 의해 게임이 시작되었습니다.",
						NamedTextColor.YELLOW
					)
				);
			}

			RingManager.start();
		} else {
			if (event.getExecutor().isOp()) {
				Bukkit.broadcast(
					Component.text(
						"관리자에 의해 게임이 종료되었습니다.",
						NamedTextColor.YELLOW
					)
				);
			}

			RingManager.reset();
		}
	}
}