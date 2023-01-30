package com.falseme.ui;

import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;

import com.falseme.render.PlayerRender;
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

		pane.add(new PlayerRender()); // right pane - render
		pane.add(new Body()); // small body representation to select the parts

		// Item selection
		for (int i = 0; i < 16; i++) {
			pane.add(new ItemBox());
		}

		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

}
