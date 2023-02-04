package com.falseme.gui;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class Loader {

	public static BufferedImage loadPng(String path) {

		try {
			URL location = Loader.class.getResource(path);
			if (location == null)
				System.out.println(path);
			if (location == null)
				return null;
			return ImageIO.read(location);
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}

	}

	public static Color[] loadPalette(String path) {

		Color[] palette = new Color[8];
		BufferedImage img = loadPng(path);
		if (img == null)
			return null;

		for (int i = 0; i < palette.length; i++) {
			palette[i] = new Color(img.getRGB(i, 0));
		}

		return palette;

	}

}
