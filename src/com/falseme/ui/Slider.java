package com.falseme.ui;

import java.awt.Graphics;

import javax.swing.JSlider;

import com.falseme.gui.Assets;

public class Slider extends JSlider {
	private static final long serialVersionUID = 1l;

	public Slider(int min, int max, int value) {
		super(min, max, value);
		addChangeListener(e -> repaint());
	}

	public Slider(int orientation, int min, int max, int value) {
		super(orientation, min, max, value);
		addChangeListener(e -> repaint());
	}

	public void paintComponent(Graphics g) {

		// background
		g.setColor(Assets.BACKGROUND_COLOR.darker());
		g.fillRect(0, 0, getWidth(), getHeight());

		// scroll bar
		int x, y, w, h;
		int X, Y, W, H;
		int total = Math.abs(getMinimum()) + Math.abs(getMaximum());
		if (getOrientation() == HORIZONTAL) {
			// bar
			h = getHeight() / 5;
			y = (getHeight() - h) / 2;
			w = getWidth() - y * 2;
			x = (getWidth() - w) / 2;
			// scroll
			int xpercent = (getValue() + Math.abs(getMinimum())) * 100 / total;
			X = xpercent * w / 100 + x;
			Y = getHeight() / 2;
			H = (int) (getHeight() * 0.6);
			W = H * 3;
		} else { // VERTICAL
			// bar
			w = getWidth() / 5;
			x = (getWidth() - w) / 2;
			h = getHeight() - x * 2;
			y = (getHeight() - h) / 2;
			// scroll
			int ypercent = (-getValue() + Math.abs(getMinimum())) * 100 / total;
			Y = ypercent * h / 100 + y;
			X = getWidth() / 2;
			W = (int) (getWidth() * 0.6);
			H = W * 3;
		}
		// bar
		g.setColor(Assets.BACKGROUND_LIGHT_COLOR);
		g.fillRect(x, y, w, h);
		// scroll
		g.setColor(Assets.BACKGROUND_LIGHT_COLOR.brighter());
		g.fillRoundRect(X - W / 2, Y - H / 2, W, H, 10, 10);
		g.setColor(Assets.BACKGROUND_COLOR);
		g.drawRoundRect(X - W / 2, Y - H / 2, W, H, 10, 10);

	}

}

class SliderListener {

}
