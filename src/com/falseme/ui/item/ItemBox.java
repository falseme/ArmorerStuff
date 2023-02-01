package com.falseme.ui.item;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

import com.falseme.event.ItemBoxMouseColoredEvent;
import com.falseme.gui.Assets;
import com.falseme.ui.item.Item.ItemType;

public class ItemBox extends JComponent {
	private static final long serialVersionUID = 1l;

	protected BufferedImage background, placeHolder;
	protected Item item;

	private boolean isStatic = false;

	public ItemBox(Item item, boolean isStatic, BufferedImage placeHolder) {

		this.isStatic = isStatic;
		this.placeHolder = placeHolder;

		background = Assets.ITEMBOX;
		setBackground(new Color(0, 0, 0, 0));

		if (!isStatic) {
			setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			addMouseListener(new ItemBoxMouseColoredEvent(this));
		}

		this.item = item;

	}

	public void empty(ItemType type, int... params) {
		item = new Item(type, null, params);
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public void paintComponent(Graphics g) {

		if (!isStatic)
			g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
		g.setColor(getBackground());
		g.fillRect(0, 0, getWidth(), getHeight());

		// item & placeholder

		int size = (int) (getWidth() * 0.7);
		int pos = (getWidth() - size) / 2;

		if (placeHolder != null)
			g.drawImage(placeHolder, pos, pos, size, size, null);

		if (item != null)
			g.drawImage(item.texture, pos, pos, size, size, null);

	}

}
