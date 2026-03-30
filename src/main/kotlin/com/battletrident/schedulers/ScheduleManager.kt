package com.battletrident.schedulers

import com.battletrident.BattleTrident.Companion.gameManager
import com.battletrident.BattleTrident.Companion.playerManager
import com.battletrident.BattleTrident.Companion.plugin
import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.PotionMeta
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import org.bukkit.scheduler.BukkitRunnable

class ScheduleManager {
	val itemTrident = ItemStack(Material.TRIDENT, 1)
	val itemEnderPearl = ItemStack(Material.ENDER_PEARL, 1)
	val itemHeal = ItemStack(Material.POTION, 1)

	init {
		itemHeal.editMeta {
			it.displayName(
				Component.text("체력 회복")
			)
			(it as PotionMeta).addCustomEffect(
				PotionEffect(
					PotionEffectType.INSTANT_HEALTH,
					1,
					0
				),
				true
			)
		}

		object : BukkitRunnable() {
			override fun run() {
				val onlinePlayers = playerManager.getAll().keys
				
				if (gameManager.isPlaying) {
					for (player in onlinePlayers) {
						val playerInv = player.inventory

						playerInv.setItem(0, itemTrident)
						playerInv.setItem(1, itemEnderPearl)
						playerInv.setItem(2, itemHeal)
					}
				} else {
					for (player in onlinePlayers) {
						if (player.isOp) return

						player.inventory.clear()
					}
				}
			}
		}.runTaskTimer(plugin, 0, 1)
	}
}