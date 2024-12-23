package com.BattleTrident.games.player

import com.BattleTrident.Utils.onlinePlayers
import org.bukkit.entity.Player

class PlayerManager {
	private val players = HashMap<Player, ArrayList<String>>()

	init {
		for (player in onlinePlayers) {
			players[player] = arrayListOf()
		}
	}

	fun setSkills(player: Player, skills: List<String>) {
		players[player]?.addAll(skills)
	}

	fun getSkills(player: Player): List<String>? {
		return players[player]
	}
}