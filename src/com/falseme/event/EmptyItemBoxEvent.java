package com.falseme.event;

import java.awt.event.MouseEvent;

import com.falseme.render.RenderLoader;
import com.falseme.ui.item.ItemBox;

public class EmptyItemBoxEvent extends ItemBoxEvent {

	private ItemBox[] ibs;

	public EmptyItemBoxEvent(ItemBox ib, ItemBox... ibs) {
		super(ib);
		this.ibs = ibs;
	}

	@Override
	public void mouseReleased(MouseEvent e) {

		ItemBoxPopupEvent.hidePopup();

		if (e.getButton() != MouseEvent.BUTTON1)
			return;

		if (!isMouseInsideBox(e.getX(), e.getY()))
			return;

		for (int i = 0; i < ibs.length; i++) {

			if (ibs[i].getItem() == null)
				continue;

			RenderLoader.loadArmor(ibs[i].getInventorySlot(), ibs[i].getItem().type.getIndexValue(), -1);
			ibs[i].setItem(null);

		}

	}

}
