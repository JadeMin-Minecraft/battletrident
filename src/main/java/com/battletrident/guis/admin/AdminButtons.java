package com.battletrident.guis.admin;

import com.battletrident.utils.MCGUI.MCButton;
import com.battletrident.utils.MCGUI.MCGUI;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class AdminButtons {
	private final int INVENTORY_SIZE = 9;
	private final MCGUI gui;

	AdminButtons(InventoryHolder holder) {
		this.gui = new MCGUI(holder, INVENTORY_SIZE);
	}

	Inventory build() {
		gui.setTitle(Component.text("관리자 패널"));

		MCButton playBtn = new MCButton(Material.FIREWORK_ROCKET);
		MCButton stopBtn = new MCButton(Material.BARRIER);

		playBtn.displayName(Component.text("게임 시작하기"));
		stopBtn.displayName(Component.text("게임 중지하기"));

		gui.setButton(0, playBtn);
		gui.setButton(1, stopBtn);

		return gui.build();
	}
}