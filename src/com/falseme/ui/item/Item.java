package com.falseme.ui.item;

import java.awt.image.BufferedImage;

public class Item {

	public enum ItemType {

		armor(0), rune(1), mineral(2), dye(3), none(-1);

		private int indexvalue;

		private ItemType(int value) {
			this.indexvalue = value;
		}

		public int getIndexValue() {
			return indexvalue;
		}

	}

	public ItemType type;

	public BufferedImage texture;
	public int param;

	/*
	 * @param param Item param determined by the following conditions:
	 * 	if (armor):
	 * 		param = armor-material
	 * 	if (rune/trim):
	 * 		param = trim-item
	 * 	if (mineral):
	 * 		param = mineral-item
	 * 	if (dye):
	 * 		param dye-item
	 */
	public Item(ItemType type, BufferedImage texture, int param) {
		this.type = type;
		this.texture = texture;
		this.param = param;
	}

}
