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
	public static final Material SKILL_ITEM = Material.ENDER_PEARL;
	public static final EntityType SKILL_ENTITY = EntityType.ENDER_PEARL;
	public static final int SKILL_COOLDOWN = 120;
	public static final int SKILL_DURATION = 2;

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
}