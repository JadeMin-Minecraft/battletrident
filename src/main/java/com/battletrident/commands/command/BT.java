package com.battletrident.commands.command;

import com.battletrident.guis.admin.AdminGUI;
import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.entity.Player;

public class BT implements BasicCommand {
	@Override
	public void execute(CommandSourceStack stack, String[] args) {
		/*if (args.length != 1) return;

		if (args[0].equalsIgnoreCase("start")) {
			GameManager.playGame();
		}
		if (args[0].equalsIgnoreCase("stop")) {
			GameManager.stopGame();
		}*/
		Player player = (Player)stack.getSender();
		AdminGUI gui = new AdminGUI(player);

		player.openInventory(gui.getInventory());
	}
}