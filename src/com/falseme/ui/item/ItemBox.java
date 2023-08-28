package com.falseme.ui.item;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

import com.falseme.event.ItemBoxMouseColoredEvent;
import com.falseme.event.ItemBoxPopupEvent;
import com.falseme.gui.Assets;
import com.falseme.ui.item.Item.ItemType;

public class ItemBox extends JComponent {
	private static final long serialVersionUID = 1l;

	protected BufferedImage background, placeHolder;
	protected Item[] items;
	protected Item item;

	private boolean listitem = false;

	public ItemBox() {
		
		background = Assets.ITEMBOX;
		setBackground(new Color(0, 0, 0, 0));
		
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		ItemBoxMouseColoredEvent colorMouseEvent = new ItemBoxMouseColoredEvent(this);
		addMouseListener(colorMouseEvent);
		addMouseMotionListener(colorMouseEvent);
		setBorder(null);
		
		addMouseListener(new ItemBoxPopupEvent(this));
		
	}
	
	public ItemBox(BufferedImage placeHolder, Item... items) {

		this();
		
		this.placeHolder = placeHolder;
		this.items = items;
		item = null;
		
		listitem = false;

	}
	
	public ItemBox(Item item) {
		
		this();
		
		placeHolder = null;
		this.item = item;
		items = null;
		
		listitem = true;
		
	}

	public void empty(ItemType type, int... params) {
		item = new Item(type, null, params);
	}

	public boolean isListItem() {
		return listitem;
	}

	public Item getItem() {
		return item;
	}

	public Item[] getItemList() {
		return items;
	}

	public void setItem(Item item) {
		this.item = item;
		repaint();
	}

	public int getClickSize() {

		double dim;
		double factor = 0.8;
		if (getWidth() <= getHeight())
			dim = getWidth() * factor;
		else
			dim = getHeight() * factor;

		return (int) dim;
	}

	public void paintComponent(Graphics g) {

		int S;
		if (getWidth() <= getHeight())
			S = getWidth();
		else
			S = getHeight();

		// background
		int size = (int) (S * 0.8);
		int x = (getWidth() - size) / 2;
		int y = (getHeight() - size) / 2;

		g.drawImage(background, x, y, size, size, null);
		g.setColor(getBackground());
		g.fillRect(x, y, size, size);

		// item &(or) placeholder
		int itemsize = (int) (size * 0.7);
		int posx = (getWidth() - itemsize) / 2;
		int posy = (getHeight() - itemsize) / 2;

		if (item != null)
			g.drawImage(item.texture, posx, posy, itemsize, itemsize, null);
		else if (placeHolder != null)
			g.drawImage(placeHolder, posx, posy, itemsize, itemsize, null);

	}

}
