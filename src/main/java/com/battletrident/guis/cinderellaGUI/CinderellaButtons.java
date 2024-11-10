package com.battletrident.guis.cinderellaGUI;

import com.battletrident.utils.MCGUI.MCButton;
import com.battletrident.utils.MCGUI.MCGUI;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.List;

import static com.battletrident.Consts.getServer;

public class CinderellaButtons {
	private final int INVENTORY_SIZE = 9;
	private final MCGUI gui;

	private final List<? extends Player> players;

	CinderellaButtons(InventoryHolder holder, Player opener) {
		this.gui = new MCGUI(holder, INVENTORY_SIZE);

		this.players = getServer().getOnlinePlayers().stream().filter(p ->
			p.getGameMode() != GameMode.SPECTATOR &&
			p.getUniqueId() != opener.getUniqueId()
		).toList();
	}

	Inventory build() {
		this.gui.setTitle(
			Component.text("비비디 바비디 BOOM💥")
		);

		for (int index = 0; index < gui.getButtons().length; index++) {
			MCButton<SkullMeta> button;

			if (index < this.players.size()) {
				Player player = this.players.get(index);

				button = new MCButton<>(Material.PLAYER_HEAD);
				button.displayName(Component.text(player.getName()));
				button.description(
					Component.text(
						String.format("%s", player.getName()),
						NamedTextColor.YELLOW
					).append(
						Component.text(
							"님의 위치를 밝힙니다.",
							NamedTextColor.WHITE
						)
					)
				);

				button.meta.setOwningPlayer(player);
			} else {
				button = new MCButton<>(Material.BARRIER);
				button.displayName(
					Component.text(
						"X",
						NamedTextColor.RED
					)
				);
				button.description(
					Component.text("아무것도 없어 바보야")
				);
			}
			gui.setButton(index, button);
		}

		return gui.build();
	}
}
