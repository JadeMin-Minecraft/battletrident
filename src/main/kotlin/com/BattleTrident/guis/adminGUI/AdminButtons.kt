package com.BattleTrident.guis.adminGUI

import net.kyori.adventure.text.Component
import com.BattleTrident.Manager.server
import org.bukkit.Material
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder
import org.bukkit.inventory.ItemStack

class AdminButtons(
	holder: InventoryHolder
) {
	private val SIZE = 9
	private val TITLE = Component.text("관리자 패널")
	private val inv: Inventory = server.createInventory(
		holder,
		SIZE,
		TITLE
	)

	fun build(): Inventory {
		inv.setItem(
			0,
			ItemStack(Material.FIREWORK_ROCKET).also {
				it.editMeta {
					it.displayName(Component.text("시작"))
				}
			}
		)
		inv.setItem(
			1,
			ItemStack(Material.BARRIER).also {
				it.editMeta {
					it.displayName(Component.text("중지"))
				}
			}
		)
		inv.setItem(
			8,
			ItemStack(Material.WRITABLE_BOOK).also {
				it.editMeta {
					it.displayName(Component.text("설정"))
				}
			}
		)

		return inv
	}
}