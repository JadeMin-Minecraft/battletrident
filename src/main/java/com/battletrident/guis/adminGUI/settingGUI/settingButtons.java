package com.battletrident.guis.adminGUI.settingGUI;

import com.battletrident.utils.MCGUI.MCButton;
import com.battletrident.utils.MCGUI.MCGUI;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class settingButtons {
	private final int INVENTORY_SIZE = 9;
	private final MCGUI gui;

	settingButtons(InventoryHolder holder) {
		this.gui = new MCGUI(holder, INVENTORY_SIZE);
	}

	Inventory build() {
		gui.setTitle(Component.text("설정"));

		MCButton centeringBtn = new MCButton(Material.PLAYER_HEAD);

		centeringBtn.displayName(
			Component.text("맵 중앙 지정")
		);
		centeringBtn.description(
			Component.text("현재 위치를 맵의 중앙으로 지정합니다.")
		);

		gui.setButton(1, centeringBtn);

		return gui.build();
	}
}
