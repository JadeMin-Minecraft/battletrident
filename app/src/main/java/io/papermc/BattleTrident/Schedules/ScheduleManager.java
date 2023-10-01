package io.papermc.BattleTrident.Schedules;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import io.papermc.BattleTrident.BattleTrident;



public final class ScheduleManager {
	private final BattleTrident plugin;

	public ScheduleManager(final BattleTrident plugin) {
		this.plugin = plugin;
	}

	public final void register() {
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this.plugin, () -> {
			if(GameManager.getGameState() == GameManager.GameState.PLAYING) {
				Bukkit.getOnlinePlayers().forEach(player -> {
					player.getInventory().setItem(0, new ItemStack(Material.TRIDENT, 1));
				});
			}
		}, 0, 0);
	}
}