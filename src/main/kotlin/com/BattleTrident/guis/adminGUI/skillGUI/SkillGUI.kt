package com.BattleTrident.guis.adminGUI.skillGUI

import org.bukkit.inventory.InventoryHolder

class SkillGUI : InventoryHolder {
	val gui = SkillButtons(this)

	override fun getInventory() = gui.build()
}