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

		// --- INVENTORY LAYOUT ---//

		// HELMET

		add(new ItemBox(HELMET_SLOT, Assets.ARMOR_FORM[0], helmets));
		add(new ItemBox(HELMET_SLOT, Assets.RUNE_FORM, trims));
		add(new ItemBox(HELMET_SLOT, Assets.MATERIAL_FORM, minerals));

		// CHESTPLATE

		add(new ItemBox(CHESTPLATE_SLOT, Assets.ARMOR_FORM[1], chestplates));
		add(new ItemBox(CHESTPLATE_SLOT, Assets.RUNE_FORM, trims));
		add(new ItemBox(CHESTPLATE_SLOT, Assets.MATERIAL_FORM, minerals));

		// LEGGINGS

		add(new ItemBox(LEGGINGS_SLOT, Assets.ARMOR_FORM[2], leggings));
		add(new ItemBox(LEGGINGS_SLOT, Assets.RUNE_FORM, trims));
		add(new ItemBox(LEGGINGS_SLOT, Assets.MATERIAL_FORM, minerals));

		// BOOTS

		add(new ItemBox(BOOTS_SLOT, Assets.ARMOR_FORM[3], boots));
		add(new ItemBox(BOOTS_SLOT, Assets.RUNE_FORM, trims));
		add(new ItemBox(BOOTS_SLOT, Assets.MATERIAL_FORM, minerals));

	}

}
