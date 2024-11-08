package com.battletrident.listeners.event.skill;

import com.battletrident.games.state.GameManager;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.projectiles.ProjectileSource;

public class OnTrident implements Listener {
	private final Material SKILL_ITEM = Material.TRIDENT;
	private final EntityType SKILL_ENTITY = EntityType.TRIDENT;
	private final int SKILL_COOLDOWN = 1;
	private final float EXPLOSION_SIZE = 10.0f;

	@EventHandler
	public void onTridentThrow(ProjectileLaunchEvent event) {
		if (!GameManager.isPlaying()) return;

		Projectile projectile = event.getEntity();
		ProjectileSource shooter = projectile.getShooter();

		if (
			shooter instanceof Player &&
			projectile.getType() == this.SKILL_ENTITY
		) {
			Player player = (Player)shooter;

			if (!player.hasCooldown(this.SKILL_ITEM)) {
				player.setCooldown(this.SKILL_ITEM, SKILL_COOLDOWN * 20);
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
	public void onTridentHit(ProjectileHitEvent event) {
		if (!GameManager.isPlaying()) return;

		Projectile projectile = event.getEntity();
		ProjectileSource shooter = projectile.getShooter();
		World world = projectile.getWorld();

		if (
			shooter instanceof Player &&
			projectile.getType() == this.SKILL_ENTITY
		) {
			projectile.remove();

			world.strikeLightning(
				projectile.getLocation()
			);
			world.createExplosion(
				projectile.getLocation(),
				this.EXPLOSION_SIZE,
				false, false
			);
		}
	}
}
