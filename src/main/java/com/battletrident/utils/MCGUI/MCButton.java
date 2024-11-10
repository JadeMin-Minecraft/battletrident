package com.battletrident.utils.MCGUI;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class MCButton<T extends ItemMeta> {
	private final ItemStack item;
	private TextComponent name;
	private List<? extends Component> description;

	public T meta;

	public MCButton(Material item) {
		this.item = new ItemStack(item, 1);
		this.meta = (T) this.item.getItemMeta();
	}
	public MCButton(Material item, int amount) {
		this.item = new ItemStack(item, amount);
		this.meta = (T) this.item.getItemMeta();
	}

	public MCButton<T> displayName(TextComponent name) {
		this.name = name;

		return this;
	}

	public MCButton<T> description(Component ...description) {
		this.description = List.of(description);

		return this;
	}

	public ItemStack build() {
		if (item.getType() != Material.AIR) {
			// 주의: 아이템이 AIR일 경우 메타데이터가 null임
			meta.displayName(name);
			meta.lore(description);

			item.setItemMeta(meta);
		}

		return item;
	}
}
