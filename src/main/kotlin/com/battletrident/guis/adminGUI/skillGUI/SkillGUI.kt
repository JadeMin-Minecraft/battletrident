package com.battletrident.guis.adminGUI.skillGUI

import com.battletrident.BattleTrident.Companion.plugin
import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder
import org.bukkit.inventory.ItemStack

class SkillGUI : InventoryHolder {
	private val SIZE = 54
	private val TITLE = Component.text("스킬 설정")
	private val inv: Inventory = plugin.server.createInventory(
		this,
		SIZE,
		TITLE
	)

	init {
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
	}

	override fun getInventory(): Inventory = inv
}