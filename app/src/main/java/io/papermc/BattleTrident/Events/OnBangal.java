package io.papermc.BattleTrident.Events;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;



public final class OnBangal implements Listener {
	private final void bangal(final double health, final Player player) {
		if(health <= 9.0d) {
			player.addPotionEffect(
				new PotionEffect(
					PotionEffectType.SPEED,
					20,
					1,
					true
				)
			);
		}
	}

	@EventHandler
	public final void onEntityDamage(final EntityDamageEvent event) {
		if(event.getEntityType() == EntityType.PLAYER) {
			final Player player = (Player)event.getEntity();
			final double damagedHealth = player.getHealth() - event.getFinalDamage();

			this.bangal(damagedHealth, player);
		}
	}

	@EventHandler
	public final void onPlayerHealt(final EntityRegainHealthEvent event) {
		final Entity entity = event.getEntity();

		if(entity.getType() == EntityType.PLAYER) {
			final Player player = (Player)entity;
			final double health = player.getHealth() + event.getAmount();

			this.bangal(health, player);
		}
	}
}