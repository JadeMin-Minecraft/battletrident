package com.battletrident.commands.command;

import com.battletrident.guis.admin.AdminGUI;
import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.entity.Player;

public class BT implements BasicCommand {
	@Override
	public void execute(CommandSourceStack stack, String[] args) {
		Player player = (Player)stack.getSender();
		AdminGUI gui = new AdminGUI();

		player.openInventory(gui.getInventory());
	}
}