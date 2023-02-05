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
		c.getComponent(0).setBounds(x, y, w, h); // player render panel

		// DIVIDE LEFT SIDE IN TWO PARTS
		W -= w; // left side width
		w = (int) (W * 0.2);
		H = w * 67 / 45; // Top side height // calculated based on the BodyLayout components

		// BODYPARTS SELECTION
		c.getComponent(1).setBounds(0, 0, w, H);

		// INVENTORY
		w = W / 14; // ItemBox width
		h = w; // ItemBox height
		int gap = (int) (w * 0.2); // gap between 'selection' ItemBoxes
		x = (int) (W * 0.2); // x -> next to the 'body parts' section
		int W2 = (W - x) / 2; // divide top section in two
		gap = (W2 - w * 4) / 11;
		x += gap;
		y = H / 2 - (h + gap);

		// dispose the 4 armor inventory sections
		int tempX = x;
		int N = 2;
		for (int i = 0; i < 4; i++) {

			if (i == 2) {
				y += h + gap * 2;
				x = tempX;
			}

			for (int n = 0; n < 4; n++) {

				c.getComponent(N + n).setBounds(x, y, w, h);
				x += w + gap;

			}
			N += 4;
			x += w;

		}

		// BOTTOM SECTION - ITEM SELECT SECTION
		// RUNE SELECTION
		H = c.getHeight() - H; // Bottom section height
		int ygap = (H - h * 5) / 6;
		gap = (W - w * 11) / 12;

		x = gap;
		y = c.getHeight() - H + ygap;
		N = 18;
		for (int i = 0; i < 11; i++) {
			c.getComponent(N + i).setBounds(x, y, w, h);
			x += w + gap + 1; // trunc int cause bigger errors, just a few pixels.
		}

		// ARMOR SELECTION
		gap = (W - w * 6) / 7;
		x = gap;
		y += h + ygap;

		N = 29;
		for (int i = 0; i < 6; i++) {
			c.getComponent(N + i).setBounds(x, y, w, h);
			x += w + gap;
		}

		// MATERIAL SELECTION
		gap = (W - w * 10) / 11;
		x = gap;
		y += h + ygap;

		N = 35;
		for (int i = 0; i < 10; i++) {
			c.getComponent(N + i).setBounds(x, y, w, h);
			x += w + gap;
		}

		// DYE SELECTION
		gap = (W - w * 8) / 9;
		y += h + ygap;

		N = 45;
		for (int i = 0; i < 2; i++) {
			x = gap;
			for (int j = 0; j < 8; j++) {

				c.getComponent(N + j).setBounds(x, y, w, h);
				x += w + gap;

			}
			N += 8;
			y += h + ygap;
			gap = (W - (w * 9)) / 10;
		}
		c.getComponent(N).setBounds(x, y - (h + ygap), w, h);

	}

}
