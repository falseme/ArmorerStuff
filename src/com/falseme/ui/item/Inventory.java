package com.falseme.ui.item;

import java.awt.GridLayout;
import java.util.LinkedList;

import javax.swing.JComponent;

import com.falseme.gui.Assets;
import com.falseme.ui.item.Item.ItemType;

public class Inventory extends JComponent {
	private static final long serialVersionUID = 1l;

	public Inventory() {

		setLayout(new GridLayout(4, 5));

		// ARMOR

		Item[] helmets = new Item[6];
		for (int i = 0; i < helmets.length; i++) {
			helmets[i] = new Item(ItemType.armor, Assets.ITEM_ARMOR[i][0], i);
		}

		Item[] chestplates = new Item[6];
		for (int i = 0; i < chestplates.length; i++) {
			chestplates[i] = new Item(ItemType.armor, Assets.ITEM_ARMOR[i][1], i);
		}

		Item[] leggings = new Item[6];
		for (int i = 0; i < leggings.length; i++) {
			leggings[i] = new Item(ItemType.armor, Assets.ITEM_ARMOR[i][2], i);
		}

		Item[] boots = new Item[6];
		for (int i = 0; i < boots.length; i++) {
			boots[i] = new Item(ItemType.armor, Assets.ITEM_ARMOR[i][3], i);
		}

		// TRIMS

		Item[] trims = new Item[Assets.RUNE.length];
		for (int i = 0; i < trims.length; i++) {
			trims[i] = new Item(ItemType.rune, Assets.RUNE[i], i);
		}

		// MINERAL (trim-color)

		Item[] minerals = new Item[Assets.MATERIAL.length];
		for (int i = 0; i < minerals.length; i++) {
			minerals[i] = new Item(ItemType.mineral, Assets.MATERIAL[i], i);
		}

		// DYES

		Item[] dyes = new Item[Assets.DYES.length + 1];
		for (int i = 0; i < dyes.length - 1; i++) {
			dyes[i] = new Item(ItemType.dye, Assets.DYES[i], i);
		}
		dyes[dyes.length - 1] = new Item(ItemType.dye, Assets.DYE_FORM, dyes.length - 1);

		// --- INVENTORY LAYOUT --- //

		LinkedList<ItemBox> itemboxes = new LinkedList<ItemBox>();

		// LOAD ITEM BOXES

//		SLOT: HELMET_SLOT = 0;
//		SLOT: CHESTPLATE_SLOT = 1;
//		SLOT: LEGGINGS_SLOT = 2;
//		SLOT: BOOTS_SLOT = 3;

		int SLOT = 0;
		final int MAX_SLOT = 3;

		for (int i = 0; i <= MAX_SLOT; i++) {

			LinkedList<ItemBox> rowemptyboxes = new LinkedList<ItemBox>();

			itemboxes.add(new ItemBox(SLOT, Assets.ARMOR_FORM[0], helmets));
			rowemptyboxes.add(itemboxes.peekLast());
			itemboxes.add(new ItemBox(SLOT, Assets.RUNE_FORM, trims));
			rowemptyboxes.add(itemboxes.peekLast());
			itemboxes.add(new ItemBox(SLOT, Assets.MATERIAL_FORM, minerals));
			rowemptyboxes.add(itemboxes.peekLast());
			itemboxes.add(new ItemBox(SLOT, Assets.DYE_FORM, dyes));
			rowemptyboxes.add(itemboxes.peekLast());

			ItemBox[] rowboxes = new ItemBox[4];
			itemboxes.add(new ItemBox(Assets.EMPTY, rowemptyboxes.toArray(rowboxes)));

			SLOT++;

		}

		for (int i = 0; i < itemboxes.size(); i++) {
			add(itemboxes.get(i));
		}

	}

}
