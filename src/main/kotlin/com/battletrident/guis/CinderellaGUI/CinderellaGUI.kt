package com.battletrident.guis.CinderellaGUI

import com.battletrident.BattleTrident.Companion.playerManager
import com.battletrident.BattleTrident.Companion.plugin
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.GameMode
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.SkullMeta

class CinderellaGUI(opener: Player) : InventoryHolder {
	private val SIZE = 9
	private val TITLE = Component.text("비비디 바비디 BOOM💥")
	private val inv = plugin.server.createInventory(
		this,
		SIZE,
		TITLE
	)

	private var players =
		playerManager.getAll().keys.filter {
			it.gameMode != GameMode.SPECTATOR &&
				it.uniqueId != opener.uniqueId
		}

	init {
		for (i in 0..<SIZE) {
			var item: ItemStack

			if (i < players.size) {
				val player = this.players[i]

				item = ItemStack(Material.PLAYER_HEAD)
				item.editMeta(SkullMeta::class.java) {
					it.displayName(Component.text(player.name))
					it.lore(listOf(
						Component.text(
							player.name,
							NamedTextColor.YELLOW
						).append(
							Component.text(
								"님의 위치를 밝힙니다.",
								NamedTextColor.WHITE
							)
						)
					))
					it.owningPlayer = player
				}
			} else {
				item = ItemStack(Material.BARRIER)
				item.editMeta {
					it.displayName(
						Component.text(
							"X",
							NamedTextColor.RED
						)
					)
					it.lore(listOf(
						Component.text(
							"아무것도 없다 이 멍청아"
						)
					))
				}
			}

			inv.setItem(i, item)
		}
	}


	override fun getInventory(): Inventory = inv
}