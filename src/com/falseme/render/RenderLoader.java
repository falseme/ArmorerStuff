package com.falseme.render;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class RenderLoader {

	private static BufferedImage skin = null;
	final static int[] triSize = { 25, 26, 27 }; // triangle sizes [0-base/ /1-overlay/ /2-armor]

	public static ArrayList<Triangle> loadSkin(String skinPath) {

		if (skinPath != null) {
			try {
				skin = ImageIO.read(new File(skinPath));
			} catch (IOException e) {
				e.printStackTrace();
				skin = null;
			}
		}

		ArrayList<Triangle> tris = new ArrayList<>();

		loadHead(tris);
		loadTorso(tris);
		loadArms(tris);

		return tris;

	}

	private static void loadHead(List<Triangle> tris) {

		// HEAD (8x8)
		loadFrontSide(tris, 8, 8, 8, 8, 40, 8, 0, -10, 4); // y-offset = 4(head.height/2) + 6(torso.height/2)
		loadBackSide(tris, 8, 8, 24, 8, 56, 8, 0, -10, -4);
		loadLeftSide(tris, 8, 8, 16, 8, 48, 8, 4, -10, 0);
		loadRightSide(tris, 8, 8, 0, 8, 32, 8, -4, -10, 0);
		loadTopSide(tris, 8, 8, 8, 0, 40, 0, 0, -4, 0, -10);
		loadBottomSide(tris, 8, 8, 16, 0, 48, 0, 0, 4, 0, -10);

	}

	private static void loadTorso(List<Triangle> tris) {

		int x = 0, y = 0, z = 0;

		// FRONT
		loadFrontSide(tris, 8, 12, 20, 20, 20, 36, 0, 0, 2);

		// BACK
		for (int i = 0; i < 96; i++) {

			// base & overlay
			for (int s = 0; s < 2; s++) {

				z = -triSize[s] * 2;

				x = ((i % 8) * triSize[s]) - (triSize[s] * 4);
				y = triSize[s] * (i / 8) - (triSize[s] * 6);

				Color color = Color.WHITE;
				if (s > 0) { // overlay (y+16)
					if (skin.getRGB(x / triSize[s] + 36, y / triSize[s] + 42) == 0) // transparent
						continue;
					color = new Color(skin.getRGB(x / triSize[s] + 36, y / triSize[s] + 42));
				} else { // base
					color = new Color(skin.getRGB(x / triSize[s] + 36, y / triSize[s] + 26));
				}

				tris.add(new Triangle(new Vertex(x, y, z, 1), new Vertex(x + triSize[s], y, z, 1),
						new Vertex(x, y + triSize[s], z, 1), color));
				tris.add(new Triangle(new Vertex(x + triSize[s], y, z, 1),
						new Vertex(x + triSize[s], y + triSize[s], z, 1), new Vertex(x, y + triSize[s], z, 1), color));

			}

		}

	}

	private static void loadArms(List<Triangle> tris) {

		// LEFT ARM
		// FRONT
		loadFrontSide(tris, 4, 12, 40, 20, 40, 36, 6, 0, 2); // x-offset = 4(torso-width/2) + 2(arm-width/2)

	}

	private static void loadFrontSide(List<Triangle> tris, int width, int height, int xPng, int yPng, int xPngOverlay,
			int yPngOverlay, int xOffset, int yOffset, int zOffset) {

		int x = 0, y = 0, z = 0;

		for (int i = 0; i < width * height; i++) {

			// base & overlay
			for (int s = 0; s < 2; s++) {

				z = triSize[s] * zOffset;

				x = ((i % width) * triSize[s]) - (triSize[s] * (width / 2));
				y = triSize[s] * (i / width) - (triSize[s] * (height / 2));

				Color color;
				if (s > 0) { // overlay
					color = getSkinColor(x, y, triSize[s], xPngOverlay, yPngOverlay, width, height);
					if (color == null) // transparent
						continue;
				} else { // base
					color = getSkinColor(x, y, triSize[s], xPng, yPng, width, height);
					if (color == null) // transparent -> black
						color = Color.BLACK;
				}

				x += xOffset * triSize[0];
				y += yOffset * triSize[0];
				tris.add(new Triangle(new Vertex(x, y, z, 1), new Vertex(x + triSize[s], y, z, 1),
						new Vertex(x, y + triSize[s], z, 1), color));
				tris.add(new Triangle(new Vertex(x + triSize[s], y, z, 1),
						new Vertex(x + triSize[s], y + triSize[s], z, 1), new Vertex(x, y + triSize[s], z, 1), color));

			}

		}

	}

	private static void loadLeftSide(List<Triangle> tris, int width, int height, int xPng, int yPng, int xPngOverlay,
			int yPngOverlay, int xOffset, int yOffset, int zOffset) {

		// !![left side -> FROM RIGHT TO LEFT]!!

		int x = 0, y = 0, z = 0;

		for (int i = 0; i < width * height; i++) {

			// base & overlay
			for (int s = 0; s < 2; s++) {

				x = triSize[s] * xOffset;

				z = ((i % width) * triSize[s]) - (triSize[s] * (width / 2));
				y = triSize[s] * (i / width) - (triSize[s] * (height / 2));

				Color color;
				if (s > 0) { // overlay
					color = getSkinColor(z, y, triSize[s], xPngOverlay, yPngOverlay, width, height);
					if (color == null) // transparent
						continue;
				} else { // base
					color = getSkinColor(z, y, triSize[s], xPng, yPng, width, height);
					if (color == null) // transparent -> black
						color = Color.BLACK;
				}

				z += triSize[0] * zOffset;
				y += triSize[0] * yOffset;
				tris.add(new Triangle(new Vertex(x, y, z, 1), new Vertex(x, y, z + triSize[s], 1),
						new Vertex(x, y + triSize[s], z, 1), color));
				tris.add(new Triangle(new Vertex(x, y, z + triSize[s], 1),
						new Vertex(x, y + triSize[s], z + triSize[s], 1), new Vertex(x, y + triSize[s], z, 1), color));

			}

		}

	}

	private static void loadRightSide(List<Triangle> tris, int width, int height, int xPng, int yPng, int xPngOverlay,
			int yPngOverlay, int xOffset, int yOffset, int zOffset) {

		int x = 0, y = 0, z = 0;

		for (int i = 0; i < width * height; i++) {

			// base & overlay
			for (int s = 0; s < 2; s++) {

				x = triSize[s] * xOffset;

				z = ((i % width) * triSize[s]) - (triSize[s] * (width / 2));
				y = triSize[s] * (i / width) - (triSize[s] * (height / 2));

				Color color;
				if (s > 0) { // overlay
					color = getSkinColor(z, y, triSize[s], xPngOverlay, yPngOverlay, width, height);
					if (color == null) // transparent
						continue;
				} else { // base
					color = getSkinColor(z, y, triSize[s], xPng, yPng, width, height);
					if (color == null) // transparent -> black
						color = Color.BLACK;
				}

				z += triSize[0] * zOffset;
				y += triSize[0] * yOffset;
				tris.add(new Triangle(new Vertex(x, y, z, 1), new Vertex(x, y, z + triSize[s], 1),
						new Vertex(x, y + triSize[s], z, 1), color));
				tris.add(new Triangle(new Vertex(x, y, z + triSize[s], 1),
						new Vertex(x, y + triSize[s], z + triSize[s], 1), new Vertex(x, y + triSize[s], z, 1), color));

			}

		}

	}

	private static void loadBackSide(List<Triangle> tris, int width, int height, int xPng, int yPng, int xPngOverlay,
			int yPngOverlay, int xOffset, int yOffset, int zOffset) {

		// !![from right to left]!!

		int x = 0, y = 0, z = 0;

		for (int i = 0; i < width * height; i++) {

			// base & overlay
			for (int s = 0; s < 2; s++) {

				z = triSize[s] * zOffset;

				x = ((i % width) * triSize[s]) - (triSize[s] * (width / 2));
				y = triSize[s] * (i / width) - (triSize[s] * (height / 2));

				Color color;
				if (s > 0) { // overlay
					color = getSkinColor(x, y, triSize[s], xPngOverlay, yPngOverlay, width, height);
					if (color == null) // transparent
						continue;
				} else { // base
					color = getSkinColor(x, y, triSize[s], xPng, yPng, width, height);
					if (color == null) // transparent -> black
						color = Color.BLACK;
				}

				x += triSize[0] * xOffset;
				y += triSize[0] * yOffset;
				tris.add(new Triangle(new Vertex(x, y, z, 1), new Vertex(x + triSize[s], y, z, 1),
						new Vertex(x, y + triSize[s], z, 1), color));
				tris.add(new Triangle(new Vertex(x + triSize[s], y, z, 1),
						new Vertex(x + triSize[s], y + triSize[s], z, 1), new Vertex(x, y + triSize[s], z, 1), color));

			}

		}

	}

	private static void loadTopSide(List<Triangle> tris, int width, int height, int xPng, int yPng, int xPngOverlay,
			int yPngOverlay, int xOffset, int yOffset, int zOffset, int yExOffset) {

		int x = 0, y = 0, z = 0;

		for (int i = 0; i < width * height; i++) {

			for (int s = 0; s < 2; s++) {

				y = triSize[s] * yOffset;

				x = ((i % width) * triSize[s]) - (triSize[s] * width / 2);
				z = triSize[s] * (i / width) - (triSize[s] * height / 2);

				Color color;
				if (s > 0) { // overlay
					color = getSkinColor(x, z, triSize[s], xPngOverlay, yPngOverlay, width, height);
					if (color == null) // transparent
						continue;
				} else { // base
					color = getSkinColor(x, z, triSize[s], xPng, yPng, width, height);
					if (color == null) // transparent -> black
						color = Color.BLACK;
				}

				x += triSize[0] * xOffset;
				z += triSize[0] * zOffset;
				y += triSize[0] * yExOffset;
				tris.add(new Triangle(new Vertex(x, y, z, 1), new Vertex(x + triSize[s], y, z, 1),
						new Vertex(x, y, z + triSize[s], 1), color));
				tris.add(new Triangle(new Vertex(x + triSize[s], y, z, 1),
						new Vertex(x + triSize[s], y, z + triSize[s], 1), new Vertex(x, y, z + triSize[s], 1), color));

			}

		}

	}

	private static void loadBottomSide(List<Triangle> tris, int width, int height, int xPng, int yPng, int xPngOverlay,
			int yPngOverlay, int xOffset, int yOffset, int zOffset, int yExOffset) {

		int x = 0, y = 0, z = 0;

		for (int i = 0; i < width * height; i++) {

			// base & overlay
			for (int s = 0; s < 2; s++) {

				y = triSize[s] * yOffset;

				x = ((i % width) * triSize[s]) - (triSize[s] * width / 2);
				z = triSize[s] * (i / width) - (triSize[s] * height / 2);

				Color color;
				if (s > 0) { // overlay
					color = getSkinColor(x, z, triSize[s], xPngOverlay, yPngOverlay, width, height);
					if (color == null) // transparent
						continue;
				} else { // base
					color = getSkinColor(x, z, triSize[s], xPng, yPng, width, height);
					if (color == null) // transparent -> black
						color = Color.BLACK;
				}

				x += triSize[0] * xOffset;
				z += triSize[0] * zOffset;
				y += triSize[0] * yExOffset;
				tris.add(new Triangle(new Vertex(x, y, z, 1), new Vertex(x + triSize[s], y, z, 1),
						new Vertex(x, y, z + triSize[s], 1), color));
				tris.add(new Triangle(new Vertex(x + triSize[s], y, z, 1),
						new Vertex(x + triSize[s], y, z + triSize[s], 1), new Vertex(x, y, z + triSize[s], 1), color));

			}

		}

	}

	private static Color getSkinColor(int x, int y, int pixelSize, int xOffset, int yOffset, int width, int height) {

		if (skin == null)
			return Color.WHITE;
		if (skin.getRGB(x / pixelSize + xOffset + (width / 2), y / pixelSize + yOffset + (height / 2)) == 0)// transparent
			return null;
		return new Color(skin.getRGB(x / pixelSize + xOffset + (width / 2), y / pixelSize + yOffset + (height / 2)));

	}

}
