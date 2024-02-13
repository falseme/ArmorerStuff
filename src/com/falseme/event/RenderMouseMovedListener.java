package com.falseme.event;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import com.falseme.render.PlayerRender;

public class RenderMouseMovedListener implements MouseListener, MouseMotionListener, MouseWheelListener {

	private PlayerRender render;

	private int x, y;
	private int auxXRot, auxYRot;
	private int auxYPos;

	public RenderMouseMovedListener(PlayerRender render) {
		this.render = render;
	}

	@Override
	public void mouseDragged(MouseEvent e) {

		if(e.isControlDown()) {
			final int YPOS = y - e.getY();
			render.setPos(auxYPos - YPOS);
			return;
		}
		
		final int X = x - e.getX();
		final int Y = y - e.getY();
		render.setRotation(auxXRot - X, auxYRot + Y);
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

		x = e.getX();
		y = e.getY();

		auxXRot = render.getXRotation();
		auxYRot = render.getYRotation();
		auxYPos = render.getPos();

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		
		render.setFOV(render.getFOV() - e.getWheelRotation());
		
	}

}
