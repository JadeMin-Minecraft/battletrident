package com.BattleTrident.guis.adminGUI

import org.bukkit.inventory.InventoryHolder

class AdminGUI : InventoryHolder {
	val gui: AdminButtons = AdminButtons(this)

	override fun getInventory() = gui.build()
}