package com.falseme.ui.layout;

import java.awt.Container;

public class BodyLayout extends LayoutAdapter {

	@Override
	public void layoutContainer(Container c) {

		int w = c.getWidth() / 5;
		int W = w * 4 / 3;
		int gap = w / 5;

		// head
		c.getComponent(0).setBounds(w, gap, w, w);
		// body
		c.getComponent(1).setBounds(w, gap * 2 + w, w, W);
		// arms
		c.getComponent(2).setBounds(w * 2 + gap, gap * 2 + w, w / 2, W); // left
		c.getComponent(3).setBounds(w - gap - w / 2, gap * 2 + w, w / 2, W); // right
		// legs
		c.getComponent(4).setBounds(w + w / 2 + gap / 2, gap * 3 + w + W, w / 2, W); // left
		c.getComponent(5).setBounds(w - gap / 2, gap * 3 + w + W, w / 2, W); // right

	}

}
