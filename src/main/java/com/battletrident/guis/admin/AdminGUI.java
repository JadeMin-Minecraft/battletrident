package com.battletrident.guis.admin;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class AdminGUI implements InventoryHolder {
	private final AdminButtons gui;

	public AdminGUI() {
		this.gui = new AdminButtons(this);
	}

	@Override
	public Inventory getInventory() {
		return this.gui.build();
	}
}