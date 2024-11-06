package com.battletrident.listeners.event;

import com.battletrident.games.ring.Ring;
import com.battletrident.games.ring.RingState;
import com.battletrident.games.ring.RingUpdateEvent;
import com.battletrident.games.state.GameManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class OnRingUpdate implements Listener {
	private static void notifyStart(Ring ring) {
		if (ring.no() == 0) return;

		Bukkit.broadcast(
			Component.text(
				ring.no() + "단계 자기장 축소가 시작됩니다.",
				NamedTextColor.RED
			)
		);
	}
	private static void notifyEnd(Ring ring) {
		TextComponent message;

		if (ring.no() == 0) {
			message = Component.text(
				"다음 자기장 축소까지 " + ring.delay() + "초 남았습니다.",
				NamedTextColor.RED
			);
		} else {
			message = Component.text(
				ring.no() + "단계 자기장 축소가 완료되었습니다.",
				NamedTextColor.GREEN
			);
			if (ring.delay() != -1) {
				message = message.append(
					Component.text(
						" 다음 자기장 축소까지 " + ring.delay() + "초 남았습니다.",
						NamedTextColor.RED
					)
				);
			}
		}

		Bukkit.broadcast(message);
	}

	@EventHandler
	public void onRingUpdate(RingUpdateEvent event) {
		if (!GameManager.isPlaying()) return;

		Ring ring = event.getRing();

		if (event.getState() == RingState.SHRINKING) {
			notifyStart(ring);
		} else if (event.getState() == RingState.ENDED) {
			notifyEnd(ring);
		}
	}
}
