package com.falseme.ui.layout;

import java.awt.Container;

public class MainLayout extends LayoutAdapter {

	@Override
	public void layoutContainer(Container c) {

		int W = c.getWidth(), H = c.getHeight();

		int x = 0;
		int y = 0;
		int w = (int) (W * 0.42); // right side width
		x = W - w;
		int h = H;
		c.getComponent(0).setBounds(x, y, w, h); // player render panel

		// DIVIDE LEFT SIDE IN TWO PARTS

		// BODYPARTS SELECTION [TOP]
		W -= w; // left side width
		w = (int) (W * 0.2);
		float bodyparts_aspectratio = 67.f / 47.f; // calculated based on the BodyLayout components
		h = (int) (w * bodyparts_aspectratio);
		x = (W - w) / 2;
		y = 0;

		c.getComponent(1).setBounds(x, y, w, h);

		// INVENTORY [BOTTOM]
		H -= h;
		x = 0;
		y = h;
		w = W;
		h = H;

		c.getComponent(2).setBounds(x, y, w, h);

	}

}
