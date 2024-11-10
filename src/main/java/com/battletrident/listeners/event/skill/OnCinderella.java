package com.battletrident.listeners.event.skill;

import com.battletrident.games.state.GameManager;
import com.battletrident.guis.cinderellaGUI.CinderellaGUI;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class OnCinderella implements Listener {
	private final Material SKILL_ITEM = Material.ENDER_PEARL;
	private final EntityType SKILL_ENTITY = EntityType.ENDER_PEARL;
	private final int SKILL_COOLDOWN = 120;
	private final int SKILL_DURATION = 2;

	@EventHandler
	public void onCinderellaOpen(PlayerInteractEvent event) {
		if (!GameManager.isPlaying()) return;

		ItemStack item = event.getItem();
		Action action = event.getAction();
		Player player = event.getPlayer();

		if (
			item != null &&
			item.getType() == this.SKILL_ITEM
		) {
			event.setCancelled(true);

			if (player.hasCooldown(this.SKILL_ITEM)) return;

			if (action.isLeftClick()) {
				InventoryHolder holder = new CinderellaGUI(player);
				player.openInventory(holder.getInventory());
			}
		}
	}

	@EventHandler
	public void onCinderellaPick(InventoryClickEvent event) {
		if (!GameManager.isPlaying()) return;

		ItemStack clickedItem = event.getCurrentItem();
		HumanEntity player = event.getWhoClicked();

		if (clickedItem != null) {
			if (event.getInventory().getHolder() instanceof CinderellaGUI) {
				event.setCancelled(true);

				if (clickedItem.getType() == Material.PLAYER_HEAD) {
					SkullMeta clickedItemMeta = (SkullMeta)clickedItem.getItemMeta();
					Player clickedPlayer = (Player)clickedItemMeta.getOwningPlayer();

					player.closeInventory();
					player.setCooldown(this.SKILL_ITEM, SKILL_COOLDOWN * 20);
					clickedPlayer.addPotionEffect(
						new PotionEffect(
							PotionEffectType.GLOWING,
							SKILL_DURATION * 20, 255, true
						)
					);
					Bukkit.broadcast(
						Component.text(
							player.getName(),
							NamedTextColor.BLUE
						).append(
							Component.text(
								"님이 ",
								NamedTextColor.AQUA
							)
						).append(
							Component.text(
								clickedPlayer.getName(),
								NamedTextColor.RED
							)
						).append(
							Component.text(
								"님의 위치를 " + SKILL_DURATION + "초 동안 밝힙니다!",
								NamedTextColor.AQUA
							)
						)
					);
				}
			}
		}
	}
}