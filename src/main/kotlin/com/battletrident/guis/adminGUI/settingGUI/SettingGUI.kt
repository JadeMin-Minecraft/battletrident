package com.battletrident.guis.adminGUI.settingGUI

import com.battletrident.BattleTrident.Companion.plugin
import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder
import org.bukkit.inventory.ItemStack

class SettingGUI : InventoryHolder {
	private val SIZE = 9
	private val TITLE = Component.text("설정")
	private val inv: Inventory = plugin.server.createInventory(
		this,
		SIZE,
		TITLE,
	)

	init {
		inv.setItem(
			0,
			ItemStack(Material.CLOCK).also {
				it.editMeta { meta ->
					meta.displayName(
						Component.text("월드 초기화")
					)
					meta.lore(listOf(
						Component.text("월드 내 게임 규칙을 초기화합니다.")
					))
				}
			}
		)
		inv.setItem(
			1,
			ItemStack(Material.PLAYER_HEAD).also {
				it.editMeta { meta ->
					meta.displayName(
						Component.text("맵 중앙 지정")
					)
					meta.lore(listOf(
						Component.text("맵의 중앙을 현재 위치로 지정합니다.")
					))
				}
			}
		)
	}
	
	override fun getInventory(): Inventory = inv
}