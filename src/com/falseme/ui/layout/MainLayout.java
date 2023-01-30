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

		w = (int) (W * 0.2);
		h = (int) (W * 0.2);
		c.getComponent(1).setBounds(0, y, w, h);

		// Item boxes
		W = x - w / 2;
		W /= 2;
		x = w * 3 / 5;

		w = W / 4;
		int gap = (int) (w * 0.2);
		w -= gap * 2;

		x += gap;
		y = gap * 2;
		h = w;

		int X = x; // temp
		int N = 2;
		for (int i = 0; i < 4; i++) {

			if (i % 2 == 0 && i != 0) {
				y += w * 2;
				x = X;
			}

			for (int n = 0; n < 4; n++) {

				c.getComponent(N + n).setBounds(x, y, w, h);
				x += w + gap;

			}
			N += 4;
			x += w;

		}

	}

}
