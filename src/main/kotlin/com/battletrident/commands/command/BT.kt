package com.battletrident.commands.command

import com.battletrident.guis.adminGUI.AdminGUI
import io.papermc.paper.command.brigadier.BasicCommand
import io.papermc.paper.command.brigadier.CommandSourceStack
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