package com.falseme.gui;

import java.awt.image.BufferedImage;

public class Assets {

	public static BufferedImage[][] ARMOR = new BufferedImage[1][2];

	public static void load() {

		ARMOR[0][0] = Loader.loadPng("/assets/armor/netherite1.png");
		ARMOR[0][1] = Loader.loadPng("/assets/armor/netherite2.png");

	}

}
