package com.battletrident.guis.adminGUI.settingGUI;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class settingGUI implements InventoryHolder {
	private final settingButtons gui;

	public settingGUI() {
		this.gui = new settingButtons(this);
	}

	@Override
	public Inventory getInventory() {
		return this.gui.build();
	}
}
