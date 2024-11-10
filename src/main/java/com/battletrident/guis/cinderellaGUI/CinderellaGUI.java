package com.battletrident.guis.cinderellaGUI;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class CinderellaGUI implements InventoryHolder {
	private final CinderellaButtons gui;

	public CinderellaGUI(final Player opener) {
		this.gui = new CinderellaButtons(this, opener);
	}

	@Override
	public Inventory getInventory() {
		return this.gui.build();
	}
}