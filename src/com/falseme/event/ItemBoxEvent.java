package com.falseme.event;

import java.awt.event.MouseAdapter;

import com.falseme.ui.item.ItemBox;

public class ItemBoxEvent extends MouseAdapter {

	protected ItemBox ib;
	
	public ItemBoxEvent(ItemBox ib) {
		this.ib = ib;
	}
	
	protected boolean isMouseInsideBox(int mousex, int mousey) {

		int size = ib.getClickSize();
		int x = (ib.getWidth() - size) / 2;
		int y = (ib.getHeight() - size) / 2;

		if (mousex > x && mousex <= x + size && mousey > y && mousey <= y + size)
			return true;
		return false;

	}
	
}
