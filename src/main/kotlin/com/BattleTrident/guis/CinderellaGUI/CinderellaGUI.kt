package com.BattleTrident.guis.CinderellaGUI

import org.bukkit.entity.Player
import org.bukkit.inventory.InventoryHolder

class CinderellaGUI(
	opener: Player
) : InventoryHolder {
	private val gui: CinderellaButtons = CinderellaButtons(this, opener)

	override fun getInventory() = gui.build()
}