package com.battletrident.schedulers;

import com.battletrident.games.state.*;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import static com.battletrident.Consts.*;

public class ScheduleManager {
	public static void register() {
		new BukkitRunnable() {
			@Override
			public void run() {
				if (GameManager.isPlaying()) {
					for (Player player : getPlayers()) {
						PlayerInventory playerInv = player.getInventory();

						ItemStack itemTrident = new ItemStack(Material.TRIDENT, 1);
						ItemStack itemEnderPearl = new ItemStack(Material.ENDER_PEARL, 1);
						ItemStack itemHeal = new ItemStack(Material.POTION, 1);

						itemHeal.editMeta(PotionMeta.class, meta -> {
							meta.displayName(
								Component.text("체력 회복")
							);
							meta.addCustomEffect(
								new PotionEffect(
									PotionEffectType.INSTANT_HEALTH,
									1,
									0
								),
								true
							);
						});

						playerInv.setItem(0, itemTrident);
						playerInv.setItem(1, itemEnderPearl);
						playerInv.setItem(2, itemHeal);
					}
				} else {
					for (Player player : getPlayers()) {
						if (!player.isOp()) {
							PlayerInventory playerInv = player.getInventory();

							playerInv.clear();
						}
					}
				}
			}
		}.runTaskTimer(Plugin, 0, 0);
	}
}