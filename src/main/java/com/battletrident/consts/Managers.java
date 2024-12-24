package com.battletrident.consts;

import com.battletrident.commands.CommandManager;
import com.battletrident.games.ring.RingManager;
import com.battletrident.listeners.EventManager;
import com.battletrident.schedulers.ScheduleManager;

public class Managers {
	private static Managers managers;
	public static CommandManager CommandManager;
	public static ScheduleManager ScheduleManager;
	public static EventManager EventManager;
	public static RingManager RingManager;

	private Managers(
		CommandManager c,
		ScheduleManager s,
		EventManager e,
		RingManager r
	) {
		CommandManager = c;
		ScheduleManager = s;
		EventManager = e;
		RingManager = r;
	}

	public static Managers getManagers() {
		if (managers == null) {
			synchronized (Managers.class) {
				if (managers == null) {
					managers = new Managers(
						new CommandManager(),
						new ScheduleManager(),
						new EventManager(),
						new RingManager()
					);
				}
			}
		}

		return managers;
	}
}
