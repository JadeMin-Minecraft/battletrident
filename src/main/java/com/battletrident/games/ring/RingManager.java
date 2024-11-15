package com.battletrident.games.ring;

import org.bukkit.WorldBorder;

import static com.battletrident.consts.Plugin.*;

public class RingManager {
	private final WorldBorder worldBorder;
	private final PhaseManager phase;

	public RingManager() {
		worldBorder = getWorld().getWorldBorder();
		worldBorder.setWarningDistance(30);
		worldBorder.setWarningTime(0);
		worldBorder.setDamageBuffer(0);

		phase = new PhaseManager();
	}

	private void setRing(Ring ring) {
		worldBorder.setSize(ring.size(), ring.speed());
		worldBorder.setDamageAmount(ring.damage());
	}
	private void repeat() {
		Ring current = phase.current();
		setRing(current);

		current.ender = runTaskLater(() -> {
			phase.notifyEnd();

			if (phase.hasMore()) {
				current.starter = runTaskLater(() -> {
					phase.next();
					repeat();
				}, current.delay() * 20);
			}
		}, current.speed() * 20);
	}

	public void reset() {
		worldBorder.setCenter(getWorld().getSpawnLocation());

		phase.clearTasks();
		phase.set(0);
		setRing(phase.current());
	}

	public void start() {
		reset();

		repeat();
	}
}