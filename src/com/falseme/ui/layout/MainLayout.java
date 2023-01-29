package com.falseme.ui.layout;

import java.awt.Container;

public class MainLayout extends LayoutAdapter {

	@Override
	public void layoutContainer(Container c) {

		int W = c.getWidth(), H = c.getHeight();

		int x = 0;
		int y = 0;
		int w = (int) (W * 0.42);
		x = W - w;
		int h = H;

		c.getComponent(0).setBounds(x, y, w, h);
		c.getComponent(1).setBounds(0, 0, (int) (W * 0.2), (int) (w * 0.4));

	}

}
