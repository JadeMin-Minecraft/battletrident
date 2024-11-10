package com.battletrident.listeners.event;

import com.battletrident.games.ring.RingManager;
import com.battletrident.games.state.GameManager;
import com.battletrident.games.state.GameUpdateEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

import static com.battletrident.Consts.*;

public class OnGameUpdate implements Listener {
	@EventHandler
	public void onForceGameUpdate(GameUpdateEvent event) {
		Player executor = event.getExecutor();

		if (executor != null && executor.isOp()) {
			if (GameManager.isPlaying()) {
				Bukkit.broadcast(
					Component.text(
						"관리자에 의해 게임이 시작되었습니다.",
						NamedTextColor.YELLOW
					)
				);
			} else {
				Bukkit.broadcast(
					Component.text(
						"관리자에 의해 게임이 중지되었습니다.",
						NamedTextColor.YELLOW
					)
				);
			}
		}
	}

	@EventHandler
	public void onGameUpdate(GameUpdateEvent event) {
		for (Player player : getPlayers()) {
			player.getInventory().clear();
			player.clearActivePotionEffects();

			player.setGameMode(GameMode.ADVENTURE);
		}

		if (GameManager.isPlaying()) {
			RingManager.start();

			Location worldSpawn = getWorld().getSpawnLocation();
			int ringRadius = (int)getWorld().getWorldBorder().getSize() / 2;
			dispatchCommand(
				String.format(
					"spreadplayers %d %d %d %d false @a",
					worldSpawn.getBlockX(), worldSpawn.getBlockZ(), ringRadius / getPlayers().size(), ringRadius
				)
			);
		} else {
			RingManager.reset();

			for (Player player : getPlayers()) {
				player.resetCooldown();
				player.teleport(player.getWorld().getSpawnLocation());
				player.addPotionEffects(
					List.of(
						new PotionEffect(
							PotionEffectType.WEAKNESS,
							PotionEffect.INFINITE_DURATION,
							Integer.MAX_VALUE,
							true
						),
						new PotionEffect(
							PotionEffectType.RESISTANCE,
							PotionEffect.INFINITE_DURATION,
							Integer.MAX_VALUE,
							true
						),
						new PotionEffect(
							PotionEffectType.SATURATION,
							PotionEffect.INFINITE_DURATION,
							Integer.MAX_VALUE,
							true
						)
					)
				);
			}
		}
	}
}