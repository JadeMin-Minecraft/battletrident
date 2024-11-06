package com.battletrident.guis.cinderella;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class CinderellaGUI implements InventoryHolder {
	private final int INVENTORY_SIZE = 9;
	private final Inventory inventory;
	private final CinderellaButtons buttons;

	public CinderellaGUI(Player opener) {
		this.inventory = Bukkit.createInventory(
			this,
			this.INVENTORY_SIZE,
			Component.text("비비디 바비디 BOOM💥")
		);
		this.inventory.setMaxStackSize(1);

		this.buttons = new CinderellaButtons(opener);
	}

	@Override
	public Inventory getInventory() {
		buttons.apply(this.inventory);

		return this.inventory;
	}
}
