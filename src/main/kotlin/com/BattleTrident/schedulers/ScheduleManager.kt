package com.BattleTrident.schedulers

import net.kyori.adventure.text.Component
import com.BattleTrident.BattleTrident
import com.BattleTrident.Manager.onlinePlayers
import com.BattleTrident.gameManager
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
		}.runTaskTimer(BattleTrident, 0, 1)
	}
}