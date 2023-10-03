package io.papermc.BattleTrident.Events;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Trident;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.projectiles.ProjectileSource;

import net.kyori.adventure.text.Component;



public final class OnTrident implements Listener {
	private final float explosionSize = 10.0f;

	@EventHandler
	public final void onTridentThrow(final ProjectileLaunchEvent event) {
		final Projectile projectile = event.getEntity();
		final ProjectileSource shooter = projectile.getShooter();

		if(
			shooter instanceof Player &&
			projectile.getType() == EntityType.TRIDENT
		) {
			final Player player = (Player)shooter;
			final Trident trident = (Trident)projectile;
			final Material tridentMaterial = trident.getItemStack().getType();

			if(!player.hasCooldown(tridentMaterial)) {
				player.setCooldown(tridentMaterial, 20);
			} else {
				player.sendMessage(
					Component.text(
						"해당 스킬의 쿨타임이 아직 준비 중입니다."
					)
				);
			}
		}
	}

	@EventHandler
	public final void onTridentHit(final ProjectileHitEvent event) {
		final Projectile projectile = event.getEntity();
		final ProjectileSource shooter = projectile.getShooter();

		if(
			shooter instanceof Player &&
			projectile.getType() == EntityType.TRIDENT
		) {
			projectile.getWorld().strikeLightning(projectile.getLocation());
			projectile.getWorld().createExplosion(
				projectile.getLocation(),
				this.explosionSize,
				false,
				false
			);
			projectile.remove();
		}
	}
}