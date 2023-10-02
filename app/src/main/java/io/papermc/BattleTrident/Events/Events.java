package io.papermc.BattleTrident.Events;

import java.time.Duration;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalUnit;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Trident;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.projectiles.ProjectileSource;

import io.papermc.BattleTrident.GUIs.CinderellaGUI;



public final class Events implements Listener {
	private final float explosionSize = 2.0f;

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

			player.setCooldown(trident.getItemStack().getType(), 40);
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

	@EventHandler
	public final void onCinderella(final PlayerInteractEvent event) {
		final Player player = event.getPlayer();
		final ItemStack item = event.getItem();

		if(item != null) {
			if(
				item.getType() == Material.ENDER_PEARL &&
				!player.hasCooldown(item.getType())
			) {
				event.setCancelled(true);

				player.setCooldown(item.getType(), 1200);
				player.openInventory(new CinderellaGUI().getInventory());
			}
		}
	}

	@EventHandler
	public final void onCinderellaClick(final InventoryClickEvent event) {
		final ItemStack clickedItem = event.getCurrentItem();

		if(clickedItem != null) {
			if(event.getInventory().getHolder() instanceof CinderellaGUI) {
				event.setCancelled(true);
				
				if(clickedItem.getType() == Material.PLAYER_HEAD) {
					final Player clicker = (Player)event.getWhoClicked();
					final SkullMeta clickedItemMeta = (SkullMeta)clickedItem.getItemMeta();
					final Player clickedPlayer = (Player)clickedItemMeta.getOwningPlayer();

					clicker.closeInventory();
					clickedPlayer.addPotionEffect(
						new PotionEffect(
							PotionEffectType.GLOWING,
							40,
							255,
							true
						)
					);
				}
			}
		}
	}
}