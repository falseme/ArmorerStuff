package com.falseme.ui.item;

import java.awt.GridLayout;

import javax.swing.JComponent;

import com.falseme.gui.Assets;
import com.falseme.ui.item.Item.ItemType;

public class Inventory extends JComponent {
	private static final long serialVersionUID = 1l;

	public Inventory() {

		setLayout(new GridLayout(4, 7));

		final int HELMET_SLOT = 0;
		final int CHESTPLATE_SLOT = 1;
		final int LEGGINGS_SLOT = 2;
		final int BOOTS_SLOT = 3;
		
		Item[] helmets = new Item[6];
		for(int i=0; i<helmets.length; i++) {
			helmets[i] = new Item(ItemType.armor, Assets.ITEM_ARMOR[i][0], i);
		}
		
		Item[] chestplates = new Item[6];
		for(int i=0; i<chestplates.length; i++) {
			chestplates[i] = new Item(ItemType.armor, Assets.ITEM_ARMOR[i][1], i);
		}
		
		Item[] leggings = new Item[6];
		for(int i=0; i<leggings.length; i++) {
			leggings[i] = new Item(ItemType.armor, Assets.ITEM_ARMOR[i][2], i);
		}
		
		Item[] boots = new Item[6];
		for(int i=0; i<boots.length; i++) {
			boots[i] = new Item(ItemType.armor, Assets.ITEM_ARMOR[i][3], i);
		}
		
//		Item[] chestplates = new Item[] { new Item(ItemType.armor, Assets.ITEM_ARMOR[0][1]),
//				new Item(ItemType.armor, Assets.ITEM_ARMOR[1][1]), new Item(ItemType.armor, Assets.ITEM_ARMOR[2][1]),
//				new Item(ItemType.armor, Assets.ITEM_ARMOR[3][1]), new Item(ItemType.armor, Assets.ITEM_ARMOR[4][1]),
//				new Item(ItemType.armor, Assets.ITEM_ARMOR[5][1]) };
//		
//		Item[] leggings = new Item[] { new Item(ItemType.armor, Assets.ITEM_ARMOR[0][2]),
//				new Item(ItemType.armor, Assets.ITEM_ARMOR[1][2]), new Item(ItemType.armor, Assets.ITEM_ARMOR[2][2]),
//				new Item(ItemType.armor, Assets.ITEM_ARMOR[3][2]), new Item(ItemType.armor, Assets.ITEM_ARMOR[4][2]),
//				new Item(ItemType.armor, Assets.ITEM_ARMOR[5][2]) };
//		
//		Item[] boots = new Item[] { new Item(ItemType.armor, Assets.ITEM_ARMOR[0][3]),
//				new Item(ItemType.armor, Assets.ITEM_ARMOR[1][3]), new Item(ItemType.armor, Assets.ITEM_ARMOR[2][3]),
//				new Item(ItemType.armor, Assets.ITEM_ARMOR[3][3]), new Item(ItemType.armor, Assets.ITEM_ARMOR[4][3]),
//				new Item(ItemType.armor, Assets.ITEM_ARMOR[5][3]) };
		
		add(new ItemBox(HELMET_SLOT, Assets.ARMOR_FORM[0], helmets));
		add(new ItemBox(CHESTPLATE_SLOT, Assets.ARMOR_FORM[1], chestplates));
		add(new ItemBox(LEGGINGS_SLOT, Assets.ARMOR_FORM[2], leggings));
		add(new ItemBox(BOOTS_SLOT, Assets.ARMOR_FORM[3], boots));

	}

}
