package com.battletrident.guis.cinderella;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.List;

import static com.battletrident.consts.Plugin.getServer;

public class CinderellaButtons {
	private final List<? extends Player> players;

	public CinderellaButtons(Player opener) {
		this.players = getServer().getOnlinePlayers().stream().filter(p ->
			p.getGameMode() != GameMode.SPECTATOR/* && !p.equals(opener)*/
		).toList();
	}

	public void apply(Inventory inventory) {
		for (int index = 0; index < inventory.getSize(); index++) {
			ItemStack item;

			if (index < this.players.size()) {
				item = new ItemStack(Material.PLAYER_HEAD, 1);
				Player player = this.players.get(index);
				SkullMeta meta = (SkullMeta)item.getItemMeta();

				meta.displayName(Component.text(player.getName()));
				meta.lore(
					List.of(
						Component.text(
							String.format("%s", player.getName()),
							NamedTextColor.YELLOW
						).append(
							Component.text(
								"님의 위치를 밝힙니다.",
								NamedTextColor.WHITE
							)
						)
					)
				);
				meta.setOwningPlayer(player);

				item.setItemMeta(meta);
			} else {
				item = new ItemStack(Material.BARRIER, 1);
				ItemMeta meta = item.getItemMeta();

				meta.displayName(
					Component.text(
						"X",
						NamedTextColor.RED
					)
				);
				meta.lore(
					List.of(
						Component.text("아무것도 없어 바보야")
					)
				);

				item.setItemMeta(meta);
			}
			inventory.setItem(index, item);
		}
	}
}
