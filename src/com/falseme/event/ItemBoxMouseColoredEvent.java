package com.falseme.event;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import com.falseme.gui.Assets;
import com.falseme.render.PlayerRender;
import com.falseme.ui.item.Item;
import com.falseme.ui.item.ItemBox;

public class ItemBoxMouseColoredEvent extends MouseAdapter {

	boolean in = false;

	private ItemBox ib;
	private static ItemBox BOX_SELECTED = null;

	public ItemBoxMouseColoredEvent(ItemBox ib) {

		this.ib = ib;

	}

	@Override
	public void mousePressed(MouseEvent e) {

		ib.setBackground(new Color(1f, 1f, 1f, 0.05f));

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (!in)
			return;

		if (BOX_SELECTED == null) {

			ib.setBackground(new Color(1f, 1f, 1f, 0.08f));
			BOX_SELECTED = ib;

//		} else if (ib.getClass() == BOX_SELECTED.getClass()) {
		} else if (ib.getItem().params[0] == BOX_SELECTED.getItem().params[0]) {
			// ARE THE IN THE SAME LINE

			BOX_SELECTED.setBackground(new Color(0, 0, 0, 0));
			ib.setBackground(new Color(1f, 1f, 1f, 0.1f));
			BOX_SELECTED = ib;

		} else if (ib.getItem().params[1] == BOX_SELECTED.getItem().params[1]) {
			// ARE IN DIFFERENT LINES & THE SAME CATEGORY
			// WILL MOVE THE ITEM

			ib.setBackground(new Color(0, 0, 0, 0));
			BOX_SELECTED.setBackground(new Color(0, 0, 0, 0));

			ItemBox userBox, selectBox;
			if (ib.getItem().params[0] == 0) {
				userBox = ib;
				selectBox = BOX_SELECTED;
			} else {
				userBox = BOX_SELECTED;
				selectBox = ib;
			}

			int[] params = userBox.getItem().params;
			params[2] = selectBox.getItem().params[2];

			BufferedImage texture = selectBox.getItem().texture;
			if (params.length > 3) { // IS AN ARMOR ITEMBOX
				texture = Assets.ITEM_ARMOR[params[2]][params[3]];
			}
			userBox.setItem(new Item(userBox.getItem().type, texture, params));

			BOX_SELECTED = null;

			PlayerRender.loadRender();

		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		in = true;
	}

	@Override
	public void mouseExited(MouseEvent e) {
		in = false;
		if (ib != BOX_SELECTED)
			ib.setBackground(new Color(0, 0, 0, 0));
	}

}
