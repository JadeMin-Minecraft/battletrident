package com.battletrident.guis.admin;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class AdminGUI implements InventoryHolder {
	private final int INVENTORY_SIZE = 9;
	private final Inventory inventory;
	private final AdminButtons buttons;

	public AdminGUI(Player opener) {
		this.inventory = Bukkit.createInventory(
			this,
			this.INVENTORY_SIZE,
			Component.text("관리자 패널")
		);
		this.inventory.setMaxStackSize(1);

		this.buttons = new AdminButtons(opener);
	}

	@Override
	public Inventory getInventory() {
		buttons.apply(this.inventory);

		return this.inventory;
	}
}