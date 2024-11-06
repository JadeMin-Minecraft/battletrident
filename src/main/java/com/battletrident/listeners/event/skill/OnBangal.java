package com.battletrident.listeners.event.skill;

import com.battletrident.games.state.GameManager;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class OnBangal implements Listener {
	private static final double TARGET_HEALTH = 9.0d;

	private void bangal(double health, Player player) {
		if (health <= TARGET_HEALTH) {
			player.addPotionEffect(
				new PotionEffect(
					PotionEffectType.SPEED,
					20, 1, false, false
				)
			);
		}
	}

	@EventHandler
	public void OnEntityDamage(EntityDamageEvent event) {
		if (!GameManager.isPlaying()) return;

		if (event.getEntityType() == EntityType.PLAYER) {
			Player player = (Player)event.getEntity();
			double damagedHealth = player.getHealth() - event.getFinalDamage();

			this.bangal(damagedHealth, player);
		}
	}

	@EventHandler
	public void onPlayerHealt(EntityRegainHealthEvent event) {
		if (!GameManager.isPlaying()) return;

		Entity entity = event.getEntity();

		if (entity.getType() == EntityType.PLAYER) {
			Player healtHealth = (Player)entity;
			double health = healtHealth.getHealth() + event.getAmount();

			this.bangal(health, healtHealth);
		}
	}
}
