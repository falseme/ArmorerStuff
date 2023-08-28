package com.falseme.ui.item;

import java.awt.GridLayout;

import javax.swing.JComponent;

import com.falseme.gui.Assets;
import com.falseme.ui.item.Item.ItemType;

public class Inventory extends JComponent {
	private static final long serialVersionUID = 1l;

	public Inventory() {

		setLayout(new GridLayout(4, 7));

		Item[] helmets = new Item[] { new Item(ItemType.armor, Assets.ITEM_ARMOR[0][0]),
				new Item(ItemType.armor, Assets.ITEM_ARMOR[1][0]), new Item(ItemType.armor, Assets.ITEM_ARMOR[2][0]),
				new Item(ItemType.armor, Assets.ITEM_ARMOR[3][0]), new Item(ItemType.armor, Assets.ITEM_ARMOR[4][0]),
				new Item(ItemType.armor, Assets.ITEM_ARMOR[5][0]) };
		
		Item[] chestplates = new Item[] { new Item(ItemType.armor, Assets.ITEM_ARMOR[0][1]),
				new Item(ItemType.armor, Assets.ITEM_ARMOR[1][1]), new Item(ItemType.armor, Assets.ITEM_ARMOR[2][1]),
				new Item(ItemType.armor, Assets.ITEM_ARMOR[3][1]), new Item(ItemType.armor, Assets.ITEM_ARMOR[4][1]),
				new Item(ItemType.armor, Assets.ITEM_ARMOR[5][1]) };
		
		Item[] leggings = new Item[] { new Item(ItemType.armor, Assets.ITEM_ARMOR[0][2]),
				new Item(ItemType.armor, Assets.ITEM_ARMOR[1][2]), new Item(ItemType.armor, Assets.ITEM_ARMOR[2][2]),
				new Item(ItemType.armor, Assets.ITEM_ARMOR[3][2]), new Item(ItemType.armor, Assets.ITEM_ARMOR[4][2]),
				new Item(ItemType.armor, Assets.ITEM_ARMOR[5][2]) };
		
		Item[] boots = new Item[] { new Item(ItemType.armor, Assets.ITEM_ARMOR[0][3]),
				new Item(ItemType.armor, Assets.ITEM_ARMOR[1][3]), new Item(ItemType.armor, Assets.ITEM_ARMOR[2][3]),
				new Item(ItemType.armor, Assets.ITEM_ARMOR[3][3]), new Item(ItemType.armor, Assets.ITEM_ARMOR[4][3]),
				new Item(ItemType.armor, Assets.ITEM_ARMOR[5][3]) };

		add(new ItemBox(Assets.ARMOR_FORM[0], helmets));
		add(new ItemBox(Assets.ARMOR_FORM[1], chestplates));
		add(new ItemBox(Assets.ARMOR_FORM[2], leggings));
		add(new ItemBox(Assets.ARMOR_FORM[3], boots));

	}

}
