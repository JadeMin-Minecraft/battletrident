package com.battletrident.guis.adminGUI

import com.battletrident.BattleTrident.Companion.plugin
import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder
import org.bukkit.inventory.ItemStack

class AdminGUI : InventoryHolder {
	private val SIZE = 9
	private val TITLE = Component.text("관리자 패널")
	private val inv: Inventory = plugin.server.createInventory(
		this,
		SIZE,
		TITLE
	)

	init {
		inv.setItem(
			0,
			ItemStack(Material.FIREWORK_ROCKET).also {
				it.editMeta { meta ->
					meta.displayName(Component.text("시작"))
				}
			}
		)
		inv.setItem(
			1,
			ItemStack(Material.BARRIER).also {
				it.editMeta { meta ->
					meta.displayName(Component.text("중지"))
				}
			}
		)
		inv.setItem(
			8,
			ItemStack(Material.WRITABLE_BOOK).also {
				it.editMeta { meta ->
					meta.displayName(Component.text("설정"))
				}
			}
		)
	}

	override fun getInventory(): Inventory = inv
}