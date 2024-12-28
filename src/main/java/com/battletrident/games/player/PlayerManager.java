package com.battletrident.games.player;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public class PlayerManager {
	private Player[] players;

	public void giveImmune(Player player, boolean isPermanent) {
		player.addPotionEffects(
			List.of(
				new PotionEffect(
					PotionEffectType.RESISTANCE,
					isPermanent?
						PotionEffect.INFINITE_DURATION
						:
						20 * 10,
					Integer.MAX_VALUE,
					true, false
				),
				new PotionEffect(
					PotionEffectType.REGENERATION,
					isPermanent?
						PotionEffect.INFINITE_DURATION
						:
						20 * 10,
					Integer.MAX_VALUE,
					true, false
				)
			)
		);
	}
	public void giveImmune(Player player) {
		giveImmune(player, false);
	}
}
