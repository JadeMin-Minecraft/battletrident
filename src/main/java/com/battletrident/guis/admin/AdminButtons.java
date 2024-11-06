package com.battletrident.guis.admin;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class AdminButtons {
	public final ItemStack[] items = new ItemStack[2];

	public AdminButtons(Player opener) {
		items[0] = new ItemStack(Material.FIREWORK_ROCKET, 1);
		items[1] = new ItemStack(Material.BARRIER, 1);

		ItemMeta tempMeta;

		tempMeta = items[0].getItemMeta();
		tempMeta.displayName(
			Component.text("게임 시작하기")
		);
		items[0].setItemMeta(tempMeta);

		tempMeta = items[1].getItemMeta();
		tempMeta.displayName(
			Component.text("게임 중지하기")
		);
		items[1].setItemMeta(tempMeta);
	}

	public void apply(Inventory inventory) {
		for (ItemStack item : items) {
			inventory.addItem(item);
		}
	}
}