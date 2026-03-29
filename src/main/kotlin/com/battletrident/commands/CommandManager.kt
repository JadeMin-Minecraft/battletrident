package com.battletrident.commands

import com.battletrident.BattleTrident.Companion.plugin
import com.battletrident.commands.command.BT
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents

class CommandManager {
	//private val commands: List<BasicCommand>

	init {
		val lm = plugin.lifecycleManager

		lm.registerEventHandler(LifecycleEvents.COMMANDS) {
			it.registrar().register(
				"bt",
				"BattleTrident 게임 관리자 패널",
				BT()
			)
		}
	}
	
	fun dispatchCommand(command: String) {
		plugin.server.dispatchCommand(
			plugin.server.consoleSender,
			command
		)
	}
}