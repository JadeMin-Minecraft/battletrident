package com.battletrident.games.ring;

import org.bukkit.scheduler.BukkitTask;

public class Ring {
	private final int no;
	private final double size;
	private final long speed;
	private final double damage;
	private final long delay;
	public BukkitTask starter;
	public BukkitTask ender;

	public Ring(
		int no,
		double size,
		long speed,
		double damage,
		long delay
	) {
		this.no = no;
		this.size = size;
		this.speed = speed;
		this.damage = damage;
		this.delay = delay;
	}

	public int no() { return no; }
	public double size() { return size; }
	public long speed() { return speed; }
	public double damage() { return damage; }
	public long delay() { return delay; }
}