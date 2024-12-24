package com.battletrident.listeners.event.gui;

import com.battletrident.games.state.GameManager;
import com.battletrident.guis.cinderellaGUI.CinderellaGUI;
import com.battletrident.listeners.event.skill.OnCinderella;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class OnCinderellaGUI implements Listener {
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
					player.setCooldown(OnCinderella.SKILL_ITEM, OnCinderella.SKILL_COOLDOWN * 20);
					clickedPlayer.addPotionEffect(
						new PotionEffect(
							PotionEffectType.GLOWING,
							OnCinderella.SKILL_DURATION * 20, 255, true
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
								"님의 위치를 " + OnCinderella.SKILL_DURATION + "초 동안 밝힙니다!",
								NamedTextColor.AQUA
							)
						)
					);
				}
			}
		}
	}
}
