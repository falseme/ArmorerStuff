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
				System.out.println("[LOADER] Does not exist: " + path);
			if (location == null)
				return null;
			return ImageIO.read(location);
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}

	}

	public static Color[] loadPalette(String path) {

		BufferedImage img = loadPng(path);
		if (img == null)
			return null;
		Color[] palette = new Color[img.getWidth()];

		for (int i = 0; i < palette.length; i++) {
			palette[i] = new Color(img.getRGB(i, 0));
		}

		return palette;

	}

}
