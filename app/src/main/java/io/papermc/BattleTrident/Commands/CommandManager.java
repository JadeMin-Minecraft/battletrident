package io.papermc.BattleTrident.Commands;

import org.bukkit.command.*;

import io.papermc.BattleTrident.BattleTrident;



public final class CommandManager {
	private final BattleTrident plugin;
	private final String[] commandList = {
		"gstart",
		"gstop",
	};

	public CommandManager(final BattleTrident plugin) {
		this.plugin = plugin;
	}

	public final void register() {
		final CommandExecutor executor = new Commands();

		for(final String command : this.commandList) {
			this.plugin.getCommand(command).setExecutor(executor);
		}
	}
}