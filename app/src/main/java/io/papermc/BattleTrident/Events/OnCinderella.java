package io.papermc.BattleTrident.Events;

import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.kyori.adventure.text.Component;

import io.papermc.BattleTrident.GUIs.CinderellaGUI;



public final class OnCinderella implements Listener {
	@EventHandler
	public final void onCinderellaOpen(final PlayerInteractEvent event) {
		final ItemStack item = event.getItem();
		final Action action = event.getAction();
		final Player player = event.getPlayer();

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
		final HumanEntity player = event.getWhoClicked();

		if(clickedItem != null) {
			if(event.getInventory().getHolder() instanceof CinderellaGUI) {
				event.setCancelled(true);
				
				if(clickedItem.getType() == Material.PLAYER_HEAD) {
					final SkullMeta clickedItemMeta = (SkullMeta)clickedItem.getItemMeta();
					final Player clickedPlayer = (Player)clickedItemMeta.getOwningPlayer();

					player.closeInventory();
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