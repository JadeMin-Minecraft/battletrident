package com.BattleTrident.listeners.event.gui

import com.BattleTrident.Managers.gameManager
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import com.BattleTrident.Utils.server
import com.BattleTrident.guis.CinderellaGUI.CinderellaGUI
import com.BattleTrident.listeners.event.skill.OnCinderella
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.meta.SkullMeta
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

class OnCinderellaGUI : Listener {
	@EventHandler
	fun onCinderellaPick(event: InventoryClickEvent) {
		if (!gameManager.isPlaying) return

		val clickedItem = event.currentItem ?: return
		val player = event.whoClicked as Player

		if (event.inventory.holder is CinderellaGUI) {
			event.isCancelled = true

			if (clickedItem.type == Material.PLAYER_HEAD) {
				val clickedItemMeta = clickedItem.itemMeta as SkullMeta
				val clickedPlayer = clickedItemMeta.owningPlayer as Player

				player.closeInventory()
				player.setCooldown(OnCinderella.SKILL_ITEM, OnCinderella.SKILL_COOLDOWN)
				clickedPlayer.addPotionEffect(
					PotionEffect(
						PotionEffectType.GLOWING,
						OnCinderella.SKILL_DURATION * 20,
						255,
						false, false
					)
				)
				server.broadcast(
					Component.text(
						player.name,
						NamedTextColor.BLUE
					).append(
						Component.text(
							"님이 ",
							NamedTextColor.AQUA
						)
					).append(
						Component.text(
							clickedPlayer.name,
							NamedTextColor.RED
						)
					).append(
						Component.text(
							"님의 위치를 ${OnCinderella.SKILL_DURATION}초간 밝힙니다!",
							NamedTextColor.AQUA
						)
					)
				)
			}
		}
	}
}