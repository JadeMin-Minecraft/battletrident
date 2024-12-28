package com.battletrident.listeners.event;

import com.battletrident.games.state.GameManager;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class OnPlayerInteract implements Listener {
	@EventHandler
	public void onPlayerJoins(PlayerJoinEvent event) {
		Player player = event.getPlayer();

		if (GameManager.isPlaying()) {
			player.setGameMode(GameMode.SPECTATOR);
			player.sendMessage("게임이 이미 진행 중이므로 관전자로 전환됩니다.");
		}

		AttributeInstance maxHealth = player.getAttribute(Attribute.GENERIC_MAX_HEALTH);
		if (maxHealth != null) {
			maxHealth.setBaseValue(40);
		}
	}

	/*@EventHandler
	public void onPlayerLeaves(PlayerQuitEvent event) {
		Player player = event.getPlayer();

		if (GameManager.isPlaying()) {
			player.setGameMode(GameMode.SPECTATOR);
		}
	}*/

	@EventHandler
	public void onPlayerDropItem(PlayerDropItemEvent event) {
		if (!GameManager.isPlaying()) return;

		event.setCancelled(true);
	}

	@EventHandler
	public void onPlayerInventoryClick(InventoryClickEvent event) {
		if (!GameManager.isPlaying()) return;

		if (event.getClickedInventory() instanceof PlayerInventory) {
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onPlayerAttacksPlayer(EntityDamageByEntityEvent event) {
		if (
			event.getDamager() instanceof Player &&
			event.getEntity() instanceof Player
		) {
			Player damager = (Player)event.getDamager();
			Player entity = (Player)event.getEntity();

			if (damager.getInventory().getItemInMainHand().getType() == Material.TRIDENT) {
				event.setCancelled(true);
			}
		}
	}
}