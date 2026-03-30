package com.battletrident.games.player

import com.battletrident.BattleTrident.Companion.plugin
import org.bukkit.Location
import org.bukkit.entity.Player
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

data class PlayerData(
	var isAlive: Boolean,
	var rank: Int,
)

class PlayerManager {
	val players = HashMap<Player, PlayerData>()

	init {
		for (player in plugin.server.onlinePlayers) {
			players[player] = PlayerData(
				true,
				0
			)
		}
	}

	fun add(player: Player) {
		players[player] = PlayerData(
			true,
			0
		)
	}
	fun get(player: Player): PlayerData? {
		return players[player]
	}
	fun remove(player: Player) {
		players.remove(player)
	}
	fun getAll(): HashMap<Player, PlayerData> {
		return players
	}
	
	fun giveImmune(player: Player) {
		player.addPotionEffects(
			listOf(
				PotionEffect(
					PotionEffectType.WEAKNESS,
					PotionEffect.INFINITE_DURATION,
					Integer.MAX_VALUE,
					true, false
				),
				PotionEffect(
					PotionEffectType.RESISTANCE,
					PotionEffect.INFINITE_DURATION,
					Integer.MAX_VALUE,
					true, false
				),
				PotionEffect(
					PotionEffectType.REGENERATION,
					PotionEffect.INFINITE_DURATION,
					Integer.MAX_VALUE,
					true, false
				)
			)
		)
	}
}