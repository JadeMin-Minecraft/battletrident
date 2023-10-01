package io.papermc.BattleTrident.Commands;

import org.bukkit.command.*;

import io.papermc.BattleTrident.Games.GameManager;



public final class Commands implements CommandExecutor {
	@Override
	public final boolean onCommand(
		final CommandSender sender,
		final Command command,
		final String label,
		final String[] args
	) {
		//final Player player = (Player)sender;

		switch(command.getName()) {
			case "gstart":
				GameManager.startGame();
				return true;
			case "gstop":
				GameManager.stopGame();
				return true;
			default:
				return false;
		}
	}
}