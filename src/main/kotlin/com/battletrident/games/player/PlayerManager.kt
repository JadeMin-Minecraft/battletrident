package com.battletrident.games.player

import com.battletrident.BattleTrident.Companion.plugin
import org.bukkit.entity.Player
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

class PlayerManager {
	private val players = HashMap<Player, ArrayList<String>>()

	init {
		for (player in plugin.server.onlinePlayers) {
			players[player] = arrayListOf()
		}
	}



	fun add(player: Player) {
		players[player] = arrayListOf()
	}
	fun remove(player: Player) {
		players.remove(player)
	}
	fun get(UUID: String): Player? {
		for (player in players.keys) {
			if (player.uniqueId.toString() == UUID) {
				return player
			}
		}

		return null
	}
	fun getAll(): Set<Player> {
		return players.keys
	}

	
	
	fun setSkills(player: Player, skills: List<String>) {
		players[player]?.addAll(skills)
	}
	fun getSkills(player: Player): List<String>? {
		return players[player]
	}
	
	
	
	fun giveImmune(player: Player) {
		player.addPotionEffects(
			listOf(
				PotionEffect(
					PotionEffectType.WEAKNESS,
					PotionEffect.INFINITE_DURATION,
					Integer.MAX_VALUE,
					true
				),
				PotionEffect(
					PotionEffectType.RESISTANCE,
					PotionEffect.INFINITE_DURATION,
					Integer.MAX_VALUE,
					true
				),
				PotionEffect(
					PotionEffectType.REGENERATION,
					PotionEffect.INFINITE_DURATION,
					Integer.MAX_VALUE,
					true
				)
			)
		)
	}
		
}