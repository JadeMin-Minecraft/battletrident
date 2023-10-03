package io.papermc.BattleTrident.GUIs;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import io.papermc.BattleTrident.Games.PlayerManager;
import net.kyori.adventure.text.Component;
import net.md_5.bungee.api.ChatColor;



public final class CinderellaGUI implements InventoryHolder {
	public final int INVENTORY_SIZE = 9;
	private final Inventory inventory;
	private final List<Player> players;

	public CinderellaGUI(final Player opener) {
		this.players = PlayerManager.players.stream().filter(player -> {
			return player.getUniqueId() != opener.getUniqueId();
		}).toList();

		this.inventory = Bukkit.createInventory(
			this,
			this.INVENTORY_SIZE,
			Component.text("비비디 바비디 BOOM💥")
		);
		this.inventory.setMaxStackSize(1);
	}

	@Override
	public final Inventory getInventory() {
		// 리스트에서 for문을 돌며 모든 플레이어 헤드를 추가한다.
		// 반복 중 더 이상 플레이어가 없으면 베리어를 대신 추가한다.
		for(int index=0; index<this.INVENTORY_SIZE; index++) {
			final ItemStack item;
			
			if(index < this.players.size()) {
				item = new ItemStack(Material.PLAYER_HEAD, 1);
				final Player player = this.players.get(index);
				final SkullMeta meta = (SkullMeta)item.getItemMeta();

				meta.displayName(Component.text(player.getName()));
				meta.lore(
					List.of(
						Component.text(
							String.format(
								"%s%s%s님의 위치로 즉시 텔레포트합니다.",
								ChatColor.YELLOW, player.getName(), ChatColor.RESET
							)
						)
					)
				);
				meta.setOwningPlayer(player);

				item.setItemMeta(meta);
			} else {
				item = new ItemStack(Material.BARRIER, 1);
				final ItemMeta meta = item.getItemMeta();
				
				meta.displayName(
					Component.text(
						String.format(
							"%sX",
							ChatColor.RED
						)
					)
				);
				meta.lore(
					List.of(
						Component.text("아무것도 없어 ㄱㅅㄲ야")
					)
				);

				item.setItemMeta(meta);
			}
			
			this.inventory.setItem(index, item);
		}
		
		return this.inventory;
	}
}