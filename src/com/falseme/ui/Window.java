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

		pane.add(new PlayerRender());

		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

}
