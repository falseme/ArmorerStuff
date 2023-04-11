package com.falseme.ui.item;

import java.awt.image.BufferedImage;

public class Item {

	public enum ItemType {

		armor, rune, mineral, dye, none;

	}

	public ItemType type;

	public BufferedImage texture;
	public int[] params;

	/*
	 * @param param Item params in order: pickable(0,1), ItemType(asOrderedInt),
	 * AssetsListIndex(i or -1)))
	 */
	public Item(ItemType type, BufferedImage texture, int... param) {
		this.type = type;
		this.texture = texture;
		params = param;
	}

}
