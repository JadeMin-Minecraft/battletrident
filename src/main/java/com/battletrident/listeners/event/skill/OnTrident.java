package com.battletrident.listeners.event.skill;

import com.battletrident.games.state.GameManager;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.scheduler.BukkitRunnable;

import static com.battletrident.Consts.*;

public class OnTrident implements Listener {
	private final Material SKILL_ITEM = Material.TRIDENT;
	private final EntityType SKILL_ENTITY = EntityType.TRIDENT;
	private final int SKILL_COOLDOWN = 1;
	private final float EXPLOSION_SIZE = 0.1f;

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

			player.setCooldown(this.SKILL_ITEM, SKILL_COOLDOWN * 20);

			new BukkitRunnable()  {
				@Override
				public void run() {
					if (projectile.isDead()) {
						this.cancel();
					} else {
						getWorld().spawnParticle(
							Particle.SONIC_BOOM,
							projectile.getLocation(),
							1
						);
					}
				}
			}.runTaskTimer(Plugin, 0, 1);
		}
	}

	@EventHandler
	public void onTridentHit(ProjectileHitEvent event) {
		if (!GameManager.isPlaying()) return;

		Projectile projectile = event.getEntity();
		World world = projectile.getWorld();

		if (
			projectile.getShooter() instanceof Player &&
			projectile.getType() == this.SKILL_ENTITY
		) {
			Player shooter = (Player)projectile.getShooter();

			/*world.strikeLightningEffect(
				projectile.getLocation()
			);
			world.createExplosion(
				projectile.getLocation(),
				this.EXPLOSION_SIZE,
				false, false
			);*/
			if (event.getHitEntity() != null) {
				shooter.playSound(
					shooter.getLocation(),
					"minecraft:entity.arrow.hit_player",
					100, 1.0f
				);
				world.strikeLightningEffect(
					projectile.getLocation()
				);
			}

			projectile.remove();
		}
	}
}
