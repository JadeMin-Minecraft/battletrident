package com.battletrident.schedulers;

import com.battletrident.games.state.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import static com.battletrident.consts.Plugin.*;

public class ScheduleManager {
	public static void register() {
		getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
			if (GameManager.isPlaying()) {
				getServer().getOnlinePlayers().forEach(player -> {
					ItemStack itemTrident = new ItemStack(Material.TRIDENT, 1);
					ItemStack itemEnderPearl = new ItemStack(Material.ENDER_PEARL, 1);

					player.getInventory().setItem(0, itemTrident);
					player.getInventory().setItem(1, itemEnderPearl);
				});
			}
		}, 0, 0);
	}
}
