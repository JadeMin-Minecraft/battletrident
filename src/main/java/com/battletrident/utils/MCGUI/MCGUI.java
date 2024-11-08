package com.battletrident.utils.MCGUI;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import static com.battletrident.Consts.getServer;

public class MCGUI {
	private final InventoryHolder HOLDER;
	private final int INVENTORY_SIZE;
	private int maxStackSize;
	private TextComponent title;
	private MCButton placeholder;
	private MCButton[] buttons;

	public MCGUI(InventoryHolder holder, int size) {
		this.INVENTORY_SIZE = size;
		this.HOLDER = holder;

		this.title = Component.text("제목 없음");
		this.placeholder = new MCButton(Material.AIR);
		this.buttons = new MCButton[INVENTORY_SIZE];
		this.maxStackSize = 1;
	}

	public MCGUI setTitle(TextComponent title) {
		this.title = title;

		return this;
	}

	public MCGUI setPlaceholder(MCButton item) {
		this.placeholder = item;

		return this;
	}

	public MCButton getButton(int pos) {
		return this.buttons[pos];
	}
	public MCGUI setButton(int pos, MCButton button) {
		this.buttons[pos] = button;

		return this;
	}

	public MCButton[] getButtons() {
		return this.buttons;
	}
	public MCGUI setButtons(MCButton[] buttons) {
		this.buttons = buttons;

		return this;
	}

	public MCGUI setMaxStackSize(int size) {
		this.maxStackSize = size;

		return this;
	}

	public Inventory build() {
		Inventory gui = getServer().createInventory(
			this.HOLDER,
			this.INVENTORY_SIZE,
			this.title
		);
		gui.setMaxStackSize(this.maxStackSize);

		for (int i = 0; i < this.INVENTORY_SIZE; i++) {
			gui.setItem(i, this.placeholder.build());
		}
		for (int i = 0; i < this.buttons.length; i++) {
			if (this.buttons[i] != null) {
				gui.setItem(i, this.buttons[i].build());
			}
		}

		return gui;
	}
}
