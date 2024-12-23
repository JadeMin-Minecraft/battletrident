package com.BattleTrident.commands

import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents
import com.BattleTrident.BattleTrident
import com.BattleTrident.commands.command.BT

class CommandManager {
	//private val commands: List<BasicCommand>

	init {
		val lm = BattleTrident.lifecycleManager

		lm.registerEventHandler(LifecycleEvents.COMMANDS) {
			it.registrar().register(
				"bt",
				"BattleTrident 게임 관리자 패널",
				BT()
			)
		}
	}
}