package com.BattleTrident.guis.adminGUI.settingGUI

import org.bukkit.inventory.InventoryHolder

class SettingGUI : InventoryHolder {
	private val gui = SettingButtons(this)

	override fun getInventory() = gui.build()
}