package com.falseme.ui;

import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;

import com.falseme.gui.Assets;
import com.falseme.render.PlayerRender;
import com.falseme.ui.item.Item;
import com.falseme.ui.item.ItemBox;
import com.falseme.ui.layout.MainLayout;

public class Window extends JFrame {
	private static final long serialVersionUID = 1l;

	public Window() {

		Dimension dim = new Dimension(800, 600);
		setPreferredSize(dim);
		setMinimumSize(dim);
		setLocationRelativeTo(null);

		Container pane = getContentPane();
		pane.setLayout(new MainLayout());

		pane.setBackground(Assets.BACKGROUND_COLOR);

		pane.add(new PlayerRender()); // right pane - render
		pane.add(new Body()); // small body representation to select the parts

		// Item selection
		for (int i = 0; i < 4; i++) {
			pane.add(new ItemBox(new Item(Item.ItemType.none, Assets.ARMOR_FORM[i], 0, -1), true, null));
			pane.add(new ItemBox(new Item(Item.ItemType.rune, null, 0, 0, -1), false, null));
			pane.add(new ItemBox(new Item(Item.ItemType.armor, null, 0, 1, -1, i), false, Assets.ARMOR_FORM[i]));
			pane.add(new ItemBox(new Item(Item.ItemType.mineral, null, 0, 2, -1), false, null));
		}

		// Rune selection
		for (int i = 0; i < 11; i++) {

			pane.add(new ItemBox(new Item(Item.ItemType.rune, Assets.RUNE[i], 1, 0, i), false, null));

		}

		// Armor selection
		for (int i = 0; i < 5; i++) {
			pane.add(new ItemBox(new Item(Item.ItemType.armor, Assets.ITEM_ARMOR[i][0], 1, 1, i), false, null));
		}

		// Material selection
		for (int i = 0; i < 10; i++) {

			pane.add(new ItemBox(new Item(Item.ItemType.mineral, Assets.MATERIAL[i], 1, 2, i), false, null));

		}

		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

}
