package com.falseme.ui.item;

import java.awt.image.BufferedImage;

public class Item {

	public enum ItemType {

		armor, rune, mineral, none;

	}

	public ItemType type;

	public BufferedImage texture;
	public int[] params;

	public Item(ItemType type, BufferedImage texture, int... param) {
		this.type = type;
		this.texture = texture;
		params = param;
	}

}
