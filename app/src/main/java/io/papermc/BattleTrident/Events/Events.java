package io.papermc.BattleTrident.Events;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Trident;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.projectiles.ProjectileSource;

import net.kyori.adventure.text.Component;

import io.papermc.BattleTrident.GUIs.CinderellaGUI;
import io.papermc.BattleTrident.Games.GameManager;
import io.papermc.BattleTrident.Games.PlayerManager;



public final class Events implements Listener {
	private final float explosionSize = 10.0f;

	@EventHandler
	public final void onPlayerJoin(final PlayerJoinEvent event) {
		PlayerManager.update();
	}

	@EventHandler
	public final void onPlayerQuit(final PlayerQuitEvent event) {
		PlayerManager.update();
	}

	@EventHandler
	public final void onPlayerDropItem(final PlayerDropItemEvent event) {
		event.setCancelled(true);
	}

	@EventHandler
	public final void onPlayerInventoryClick(final InventoryClickEvent event) {
		if(event.getClickedInventory() instanceof PlayerInventory) {
			event.setCancelled(true);
		}
	}

	@EventHandler
	public final void onEntityDamage(final EntityDamageEvent event) {
		if(event.getEntityType() == EntityType.PLAYER) {
			final Player player = (Player)event.getEntity();
			final double damagedHealth = player.getHealth() - event.getFinalDamage();

			if(damagedHealth <= 8) {
				player.addPotionEffect(
					new PotionEffect(
						PotionEffectType.SPEED,
						40,
						1,
						true
					)
				);
			}
		}
	}

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

	@EventHandler
	public final void onCinderella(final PlayerInteractEvent event) {
		final Player player = event.getPlayer();
		final ItemStack item = event.getItem();
		final Action action = event.getAction();

		if(item != null && item.getType() == Material.ENDER_PEARL) {
			if(!player.hasCooldown(item.getType())) {
				if(
					action == Action.LEFT_CLICK_AIR ||
					action == Action.LEFT_CLICK_BLOCK
				) {
					event.setCancelled(true);

					player.setCooldown(item.getType(), 100);
					player.openInventory(new CinderellaGUI(player).getInventory());
				}
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
	public final void onCinderellaClick(final InventoryClickEvent event) {
		final ItemStack clickedItem = event.getCurrentItem();

		if(clickedItem != null) {
			if(event.getInventory().getHolder() instanceof CinderellaGUI) {
				event.setCancelled(true);
				
				if(clickedItem.getType() == Material.PLAYER_HEAD) {
					final HumanEntity clicker = event.getWhoClicked();
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