package com.falseme.event;

import java.awt.event.MouseEvent;

import javax.swing.JPopupMenu;
import javax.swing.border.LineBorder;

import com.falseme.gui.Assets;
import com.falseme.ui.item.Item;
import com.falseme.ui.item.ItemBox;

public class ItemBoxPopupEvent extends ItemBoxEvent {

	private static JPopupMenu popup;

	private static ItemBox SELECTED_BOX;

	public ItemBoxPopupEvent(ItemBox ib) {
		super(ib);

	}

	@Override
	public void mouseReleased(MouseEvent e) {

		if (!isMouseInsideBox(e.getX(), e.getY()))
			return;

		// IF IS AN ITEM INSIDE THE POPUP MENU
		if (ib.isListItem()) {

			popup.setVisible(false);

			SELECTED_BOX.setItem(ib.getItem());
			
			SELECTED_BOX = null;

			return;

		} else { // IS AN INVENTORY BOX

			SELECTED_BOX = ib;

			if (popup != null)
				popup.setVisible(false);

			popup = new JPopupMenu();
			popup.setBackground(Assets.BACKGROUND_COLOR.darker());
			popup.setBorder(new LineBorder(Assets.BACKGROUND_COLOR.darker().darker(), 2, false));
			for (Item i : ib.getItemList()) {
				popup.add(new ItemBox(i));
			}

			popup.setPopupSize(ib.getClickSize(), ib.getClickSize() * ib.getItemList().length);
			popup.setLocation(e.getXOnScreen(), e.getYOnScreen());
			popup.setVisible(true);

		}

	}

}
