package com.falseme.ui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

public class ItemBox extends JComponent {
	private static final long serialVersionUID = 1l;

	public ItemBox() {

	}

	public void paintComponent(Graphics g) {

		g.setColor(Color.BLACK);
		g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);

	}

}
