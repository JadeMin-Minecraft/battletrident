package io.papermc.BattleTrident.Commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import io.papermc.BattleTrident.Games.GameManager;



public final class Commands implements CommandExecutor {
	@Override
	public final boolean onCommand(
		final CommandSender sender,
		final Command command,
		final String label,
		final String[] args
	) {
		//final Player player = (Player)sender;

		switch(command.getName()) {
			case "gstart":
				GameManager.startGame();
				return true;
			case "gstop":
				GameManager.stopGame();
				return true;
			default:
				return false;
		}
	}
}