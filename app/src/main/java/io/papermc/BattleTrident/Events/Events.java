package io.papermc.BattleTrident.Events;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Trident;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import net.kyori.adventure.text.Component;
import net.md_5.bungee.api.ChatColor;

import io.papermc.BattleTrident.GUIs.CinderellaGUI;



public final class Events implements Listener {
	private final float explosionSize = 2.0f;

	@EventHandler
	public final void onTridentHit(final ProjectileHitEvent event) {
		final Projectile projectile = event.getEntity();
		
		if(projectile instanceof Trident) {
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
	public final void onEnderPearl(final PlayerInteractEvent event) {
		final Player player = event.getPlayer();
		final ItemStack item = event.getItem();
		
		if(item.getType() == Material.ENDER_PEARL) {
			event.setCancelled(true);

			if(player.hasCooldown(Material.ENDER_PEARL) == false) {
				player.openInventory(new CinderellaGUI().getInventory());
				if(player.getGameMode() != GameMode.CREATIVE) {
					item.setAmount(item.getAmount() - 1);
				}
			} else {
				player.sendMessage(
					Component.text(
						String.format(
							"%s해당 스킬은 쿨다운 중입니다.\n%s초 남았습니다.",
							ChatColor.RED, player.getCooldown(Material.ENDER_PEARL) / 20
						)
					)
				);
			}
		}
	}

	@EventHandler
	public final void onCinderellaClick(final InventoryClickEvent event) {
		if(event.getCurrentItem() != null) {
			if(event.getInventory().getHolder() instanceof CinderellaGUI) {
				event.setCancelled(true);
				
				if(event.getCurrentItem().getType() == Material.PLAYER_HEAD) {
					final Player clicker = (Player)event.getWhoClicked();
					final SkullMeta clickedItemMeta = (SkullMeta)event.getCurrentItem().getItemMeta();
					final Player targetPlayer = (Player)clickedItemMeta.getOwningPlayer();

					clicker.closeInventory();
					clicker.teleport(targetPlayer.getLocation());
				}
			}
		}
	}
}