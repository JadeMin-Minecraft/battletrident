package com.battletrident.guis.adminGUI;

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

		MCButton settingBtn = new MCButton(Material.WRITABLE_BOOK);
		MCButton playBtn = new MCButton(Material.FIREWORK_ROCKET);
		MCButton stopBtn = new MCButton(Material.BARRIER);

		playBtn.displayName(Component.text("시작"));
		stopBtn.displayName(Component.text("중지"));
		settingBtn.displayName(Component.text("설정"));

		gui.setButton(0, playBtn);
		gui.setButton(1, stopBtn);
		gui.setButton(8, settingBtn);

		return gui.build();
	}
}