package com.falseme.ui.layout;

import java.awt.Container;

public class MainLayout extends LayoutAdapter {

	@Override
	public void layoutContainer(Container c) {

		int W = c.getWidth(), H = c.getHeight();

		int x = 0;
		int y = 0;
		int w = 420;
		x = W - w;
		int h = H;

		c.getComponent(0).setBounds(x, y, w, h);

	}

}
