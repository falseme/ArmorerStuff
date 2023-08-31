package com.falseme.event;

import java.awt.Color;
import java.awt.event.MouseEvent;

import com.falseme.ui.item.ItemBox;

public class ItemBoxMouseColoredEvent extends ItemBoxEvent {

	boolean in = false;
	boolean click = false;

	public ItemBoxMouseColoredEvent(ItemBox ib) {
		super(ib);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (!in)
			return;

		if (isMouseInsideBox(e.getX(), e.getY())) {
			setClickColor();
			click = true;
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (!in)
			return;

		if (isMouseInsideBox(e.getX(), e.getY())) {
			setFocusColor();
			click = false;
		}

	}

	@Override
	public void mouseMoved(MouseEvent e) {

		if (isMouseInsideBox(e.getX(), e.getY()) && !click) {
			setFocusColor();
			in = true;
		} else if(!isMouseInsideBox(e.getX(), e.getY())) {
			setNormalColor();
			in = false;
			click = false;
		}

	}

	@Override
	public void mouseExited(MouseEvent e) {
		in = false;
		setNormalColor();
		click = false;
	}

	

	private void setFocusColor() {
		ib.setBackground(new Color(1f, 1f, 1f, 0.05f));
	}

	private void setClickColor() {
		ib.setBackground(new Color(1f, 1f, 1f, 0.02f));
	}

	private void setNormalColor() {
		ib.setBackground(new Color(0, 0, 0, 0));
	}

}
