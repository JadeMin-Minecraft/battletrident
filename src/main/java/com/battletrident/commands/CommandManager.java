package com.battletrident.commands;

import com.battletrident.commands.command.BT;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.plugin.lifecycle.event.LifecycleEventManager;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import org.bukkit.plugin.Plugin;

import static com.battletrident.consts.Plugin.plugin;

public class CommandManager {
	public static void register() {
		final LifecycleEventManager<Plugin> manager = plugin.getLifecycleManager();

		manager.registerEventHandler(LifecycleEvents.COMMANDS, event -> {
			final Commands commands = event.registrar();
			commands.register(
				"bt",
				"BattleTrident 게임 관리자 패널",
				new BT()
			);
		});
	}
}