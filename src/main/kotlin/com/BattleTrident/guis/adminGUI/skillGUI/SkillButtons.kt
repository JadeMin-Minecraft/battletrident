package com.BattleTrident.guis.adminGUI.skillGUI

import net.kyori.adventure.text.Component
import com.BattleTrident.Manager.server
import org.bukkit.Material
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder
import org.bukkit.inventory.ItemStack

class SkillButtons(
	holder: InventoryHolder
) {
	private val SIZE = 54
	private val TITLE = Component.text("스킬 설정")
	private val inv: Inventory = server.createInventory(
		holder,
		SIZE,
		TITLE
	)

	fun build(): Inventory {
		inv.setItem(
			0,
			ItemStack(Material.TRIDENT)
		)
		inv.setItem(
			1,
			ItemStack(Material.ENDER_PEARL)
		)
		inv.setItem(
			2,
			ItemStack(Material.POTION)
		)

		return inv
	}
}