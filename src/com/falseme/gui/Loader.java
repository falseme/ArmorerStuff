package com.falseme.gui;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class Loader {

	public static BufferedImage loadPng(String path) {

		try {
			URL location = Loader.class.getResource(path);
			if (location == null)
				return null;
			return ImageIO.read(location);
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}

	}

}
