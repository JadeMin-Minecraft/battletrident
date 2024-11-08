package com.battletrident.guis.cinderella;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class CinderellaGUI implements InventoryHolder {
	private final CinderellaButtons gui;

	public CinderellaGUI() {
		this.gui = new CinderellaButtons(this);
	}

	@Override
	public Inventory getInventory() {
		return this.gui.build();
	}
}