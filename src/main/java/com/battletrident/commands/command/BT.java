package com.battletrident.commands.command;

import com.battletrident.guis.adminGUI.AdminGUI;
import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.entity.Player;

public class BT implements BasicCommand {
	@Override
	public void execute(CommandSourceStack stack, String[] args) {
		Player player = (Player)stack.getSender();
		if (player.isOp() == false) return;

		player.openInventory(new AdminGUI().getInventory());
	}
}