package com.battletrident.games.ring;

import org.bukkit.WorldBorder;

import static com.battletrident.Consts.*;

public class RingManager {
	private static WorldBorder worldBorder;
	private static PhaseManager phase;

	public static void register() {
		worldBorder = getServer().getWorld("world").getWorldBorder();
		worldBorder.setWarningDistance(30);
		worldBorder.setWarningTime(0);
		worldBorder.setDamageBuffer(0);

		phase = new PhaseManager();
	}

	private static void setRing(Ring ring) {
		worldBorder.setSize(ring.size(), ring.speed());
		worldBorder.setDamageAmount(ring.damage());
	}
	private static void repeat() {
		Ring current = phase.current();
		setRing(current);

		current.ender = runTaskLater(() -> {
			phase.notifyEnd();

			current.starter = runTaskLater(() -> {
				if (phase.hasMore()) {
					phase.next();
					repeat();
				}
			}, current.delay() * 20);
		}, current.speed() * 20);
	}

	public static void start() {
		phase.clearTasks();
		phase.set(0);

		repeat();
	}

	public static void reset() {
		phase.clearTasks();
		phase.set(0);

		setRing(phase.current());
	}
}