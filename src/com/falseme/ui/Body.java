package com.falseme.ui;

import javax.swing.JComponent;

import com.falseme.render.RenderLoader;
import com.falseme.ui.layout.BodyLayout;

public class Body extends JComponent {
	private static final long serialVersionUID = 1l;

	public Body() {

		setLayout(new BodyLayout());

		BodyPart head = new BodyPart(e -> RenderLoader.changeRender(0));
		BodyPart torso = new BodyPart(e -> RenderLoader.changeRender(1));
		BodyPart leftArm = new BodyPart(e -> RenderLoader.changeRender(2));
		BodyPart rightArm = new BodyPart(e -> RenderLoader.changeRender(2));
		BodyPart leftLeg = new BodyPart(e -> RenderLoader.changeRender(3));
		BodyPart rightLeg = new BodyPart(e -> RenderLoader.changeRender(3));

		leftArm.setFriend(rightArm);
		rightArm.setFriend(leftArm);

		leftLeg.setFriend(rightLeg);
		rightLeg.setFriend(leftLeg);

		add(head);
		add(torso);
		add(leftArm);
		add(rightArm);
		add(leftLeg);
		add(rightLeg);

	}

}
