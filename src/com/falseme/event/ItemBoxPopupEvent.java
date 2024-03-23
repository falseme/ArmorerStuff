package com.falseme.event;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;

import javax.swing.JPopupMenu;
import javax.swing.border.LineBorder;

import com.falseme.gui.Assets;
import com.falseme.render.RenderLoader;
import com.falseme.ui.item.Item;
import com.falseme.ui.item.ItemBox;

public class ItemBoxPopupEvent extends ItemBoxEvent {

	private static JPopupMenu popup;

	private static ItemBox SELECTED_BOX;

	public static boolean MOUSE_INSIDE_POP = false;

	public ItemBoxPopupEvent(ItemBox ib) {
		super(ib);

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		// HIDE POPUP
		if (!isMouseInsideBox(e.getX(), e.getY())) {
			if (popup != null)
				popup.setVisible(false);
			return;
		}

		// DETECT LEFT MOUSE BUTTON
		if(e.getButton() != MouseEvent.BUTTON1)
			return;
		
		// IF IS AN ITEM INSIDE THE POPUP MENU
		if (ib.isListItem()) {

			popup.setVisible(false);

			SELECTED_BOX.setItem(ib.getItem());

			RenderLoader.loadArmor(SELECTED_BOX.getInventorySlot(), ib.getItem().type.getIndexValue(),
					ib.getItem().param);

			SELECTED_BOX = null;

			return;

		} else { // IS AN INVENTORY BOX

			SELECTED_BOX = ib;
			MOUSE_INSIDE_POP = true;

			if (popup != null)
				popup.setVisible(false);

			popup = new JPopupMenu();
			popup.setBackground(Assets.BACKGROUND_COLOR.darker().darker());
			popup.setBorder(new LineBorder(Color.BLACK, 2, true));

			int gridx = 1; // columns
			int gridy = ib.getItemList().length; // rows

			int w = ib.getClickSize() * gridx;
			int h = ib.getClickSize() * gridy;
			while (h > ib.getParent().getParent().getHeight()) {
				gridx *= 2;
				gridy = (int) Math.ceil(gridy / 2.0);
				w = ib.getClickSize() * gridx;
				h = ib.getClickSize() * gridy;
			}

			popup.setLayout(new GridLayout(gridy, gridx));
			for (Item i : ib.getItemList()) {
				popup.add(new ItemBox(i));
			}

			popup.setPopupSize(w, h);
			popup.setLocation(e.getXOnScreen() - e.getX(), e.getYOnScreen() - e.getY());
			popup.setVisible(true);

		}

	}
	
	public static void hidePopup() {
		if(popup == null)
			return;
		popup.setVisible(false);
	}

}
