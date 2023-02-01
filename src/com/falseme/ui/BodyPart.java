package com.falseme.ui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;

import com.falseme.gui.Assets;

public class BodyPart extends JComponent {
	private static final long serialVersionUID = 1l;

	private static final Color border = Color.ORANGE.darker();
	private boolean active = true;

	private BodyPart friend;

	public BodyPart(ActionListener event) {

		setBackground(Assets.BACKGROUND_LIGHT_COLOR);
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				event.actionPerformed(null);
				active = !active;
				repaint();
				if (friend != null) {
					friend.active = !friend.active;
					friend.repaint();
				}
			}

		});

	}

	public void setFriend(BodyPart friend) {
		this.friend = friend;
	}

	public void paintComponent(Graphics g) {

		g.setColor(getBackground());
		g.fillRect(0, 0, getWidth(), getHeight());

		if (active)
			g.setColor(border);
		else
			g.setColor(Color.RED.darker());
		g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);

	}

}
