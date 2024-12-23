package com.BattleTrident.commands.command

import io.papermc.paper.command.brigadier.BasicCommand
import io.papermc.paper.command.brigadier.CommandSourceStack
import com.BattleTrident.guis.adminGUI.AdminGUI
import org.bukkit.entity.Player

class BT : BasicCommand {
	override fun execute(stack: CommandSourceStack, args: Array<String>) {
		val sender = stack.sender

		if (
			sender is Player &&
			sender.isOp
		) {
			sender.openInventory(AdminGUI().inventory)
		}
	}
}