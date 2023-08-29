package com.falseme.ui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.JFrame;

import com.falseme.gui.Assets;
import com.falseme.render.PlayerRender;
import com.falseme.ui.item.Inventory;
import com.falseme.ui.layout.MainLayout;

public class Window extends JFrame {
	private static final long serialVersionUID = 1l;

	public Window() {

		Dimension dim = new Dimension(800, 600);
		setPreferredSize(dim);
		setMinimumSize(dim);
		setLocationRelativeTo(null);

		Image icon = Assets.ICON;
		setIconImage(icon);
		setTitle("Armorer's stuff");

		Container pane = getContentPane();
		pane.setLayout(new MainLayout());

		pane.setBackground(Assets.BACKGROUND_COLOR);

		pane.add(new PlayerRender()); // right pane - render
		pane.add(new Body()); // small body representation to select the parts
		Inventory inv = new Inventory();
		pane.add(inv);

		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

}
