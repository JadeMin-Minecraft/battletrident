package com.BattleTrident.listeners.event

import com.BattleTrident.Managers.gameManager
import com.BattleTrident.games.ring.RingUpdateEvent
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.TextComponent
import net.kyori.adventure.text.format.NamedTextColor
import com.BattleTrident.Utils.onlinePlayers
import com.BattleTrident.Utils.server
import com.BattleTrident.games.ring.Ring
import com.BattleTrident.games.ring.RingState
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

class OnRingUpdate : Listener {
	companion object {
		fun notifyStart(ring: Ring) {
			if (ring.no == 0) return

			server.broadcast(
				Component.text(
					"${ring.no}단계 자기장 축소가 시작됩니다.",
					NamedTextColor.RED
				)
			)
		}
		
		fun notifyEnd(ring: Ring) {
			var message: TextComponent
			
			if (ring.no == 0) {
				message = Component.text(
					"다음 자기장 축소까지 ${ring.delay}초 남았습니다.",
					NamedTextColor.RED
				)
			} else {
				message = Component.text(
					"${ring.no}단계 자기장 축소가 완료되었습니다.",
					NamedTextColor.RED
				)
				if (ring.delay != -1L) {
					message = message.append(
						Component.text(
							" 다음 자기장 축소까지 ${ring.delay}초 남았습니다.",
							NamedTextColor.RED
						)
					)
				}
			}

			server.broadcast(message)
		}
	}

	@EventHandler
	fun onRingUpdate(event: RingUpdateEvent) {
		if (!gameManager.isPlaying) return

		val ring = event.getRing()

		if (event.getState() == RingState.SHRINKING) {
			notifyStart(ring)
		} else {
			notifyEnd(ring)

			if (ring.delay < 0) {
				for (player in onlinePlayers) {
					player.addPotionEffect(
						PotionEffect(
							PotionEffectType.WITHER,
							PotionEffect.INFINITE_DURATION,
							1
						)
					)
				}
			}
		}
	}
}