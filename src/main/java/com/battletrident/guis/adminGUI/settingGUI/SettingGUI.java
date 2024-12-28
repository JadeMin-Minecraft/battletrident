package com.battletrident.guis.adminGUI.settingGUI;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class SettingGUI implements InventoryHolder {
	private final SettingButtons gui;

	public SettingGUI() {
		this.gui = new SettingButtons(this);
	}

	@Override
	public Inventory getInventory() {
		return this.gui.build();
	}
}
