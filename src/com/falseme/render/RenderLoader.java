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
		loadLegs(tris);

		return tris;

	}

	private static void loadHead(List<Triangle> tris) {

		// HEAD (8x8)
		// FRONT
		loadXYSide(tris, 8, 8, 8, 8, 40, 8, 0, -10, 4, 1, 0); // y-offset = 4(head.height/2) + 6(torso.height/2)
		// BACK
		loadXYSide(tris, 8, 8, 24, 8, 56, 8, 0, -10, -4, -1, -1);
		// LEFT
		loadZYSide(tris, 8, 8, 16, 8, 48, 8, 4, -10, 0, -1, -1);
		// RIGHT
		loadZYSide(tris, 8, 8, 0, 8, 32, 8, -4, -10, 0, 1, 0);
		// TOP
		loadXZSide(tris, 8, 8, 8, 0, 40, 0, 0, -4, 0, -10);
		// BOTTOM
		loadXZSide(tris, 8, 8, 16, 0, 48, 0, 0, 4, 0, -10);

	}

	private static void loadTorso(List<Triangle> tris) {

		// TORSO (8x12 x4)
		// FRONT
		loadXYSide(tris, 8, 12, 20, 20, 20, 36, 0, 0, 2, 1, 0);
		// BACK
		loadXYSide(tris, 8, 12, 32, 20, 32, 36, 0, 0, -2, -1, -1);

	}

	private static void loadArms(List<Triangle> tris) {

		// ARMS (4x12 x4-3[?])
		// RIGHT ARM
		// FRONT
		loadXYSide(tris, 4, 12, 44, 20, 44, 36, -6, 0, 2, 1, 0); // x-offset = 4(torso-width/2) + 2(arm-width/2)
		// BACK
		loadXYSide(tris, 4, 12, 52, 20, 52, 36, -6, 0, -2, -1, -1);
		// SIDE
		loadZYSide(tris, 4, 12, 40, 20, 40, 36, -8, 0, 0, 1, 0);
		// TOP
		loadXZSide(tris, 4, 4, 44, 16, 44, 32, -6, -6, 0, 0);
		// BOTTOM
		loadXZSide(tris, 4, 4, 48, 16, 48, 32, -6, 6, 0, 0);

		// LEFT ARM
		// FRONT
		loadXYSide(tris, 4, 12, 36, 52, 52, 52, 6, 0, 2, 1, 0);
		// BACK
		loadXYSide(tris, 4, 12, 44, 52, 60, 52, 6, 0, -2, -1, -1);
		// SIDE
		loadZYSide(tris, 4, 12, 40, 52, 56, 52, 8, 0, 0, -1, -1);
		// TOP
		loadXZSide(tris, 4, 4, 36, 48, 52, 48, 6, -6, 0, 0);
		// BOTTOM
		loadXZSide(tris, 4, 4, 40, 48, 56, 48, 6, 6, 0, 0);

	}

	private static void loadLegs(List<Triangle> tris) {

		// LEGS (4x12 x4)
		// RIGHT LEG
		// FRONT
		loadXYSide(tris, 4, 12, 4, 20, 4, 36, -2, 12, 2, 1, 0);
		// BACK
		loadXYSide(tris, 4, 12, 12, 20, 12, 36, -2, 12, -2, -1, -1);
		// SIDE
		loadZYSide(tris, 4, 12, 0, 20, 0, 36, -4, 12, 0, 1, 0);
		// BOTTOM
		loadXZSide(tris, 4, 4, 8, 16, 8, 32, -2, 18, 0, 0);

		// LEFT LEG
		// FRONT
		loadXYSide(tris, 4, 12, 20, 52, 4, 52, 2, 12, 2, 1, 0);
		// BACK
		loadXYSide(tris, 4, 12, 28, 52, 12, 52, 2, 12, -2, -1, -1);
		// SIDE
		loadZYSide(tris, 4, 12, 24, 52, 8, 52, 4, 12, 0, -1, -1);
		// BOTTOM
		loadXZSide(tris, 4, 4, 24, 48, 8, 48, 2, 18, 0, 0);

	}

	private static void loadXYSide(List<Triangle> tris, int width, int height, int xPng, int yPng, int xPngOverlay,
			int yPngOverlay, int xOffset, int yOffset, int zOffset, int pointing, int pixelOffset) {

		int x = 0, y = 0, z = 0;

		for (int i = 0; i < width * height; i++) {

			for (int s = 0; s < 2; s++) {

				z = triSize[s] * zOffset;

				x = ((i % width) * triSize[s]) - (triSize[s] * (width / 2));
				y = triSize[s] * (i / width) - (triSize[s] * (height / 2));

				Color color;
				if (s > 0) { // overlay
					color = getSkinColor(x * pointing, y, triSize[s], xPngOverlay + pixelOffset, yPngOverlay, width,
							height);
					if (color == null) // transparent
						continue;
				} else { // base
					color = getSkinColor(x * pointing, y, triSize[s], xPng + pixelOffset, yPng, width, height);
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

	private static void loadZYSide(List<Triangle> tris, int width, int height, int xPng, int yPng, int xPngOverlay,
			int yPngOverlay, int xOffset, int yOffset, int zOffset, int pointing, int pixelOffset) {

		int x = 0, y = 0, z = 0;

		for (int i = 0; i < width * height; i++) {

			// base & overlay
			for (int s = 0; s < 2; s++) {

				x = triSize[s] * xOffset;

				z = ((i % width) * triSize[s]) - (triSize[s] * (width / 2));
				y = triSize[s] * (i / width) - (triSize[s] * (height / 2));

				Color color;
				if (s > 0) { // overlay
					color = getSkinColor(z * pointing, y, triSize[s], xPngOverlay + pixelOffset, yPngOverlay, width,
							height);
					if (color == null) // transparent
						continue;
				} else { // base
					color = getSkinColor(z * pointing, y, triSize[s], xPng + pixelOffset, yPng, width, height);
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

	private static void loadXZSide(List<Triangle> tris, int width, int height, int xPng, int yPng, int xPngOverlay,
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

	private static Color getSkinColor(int x, int y, int pixelSize, int xOffset, int yOffset, int width, int height) {

		if (skin == null)
			return Color.WHITE;
		if (skin.getRGB(x / pixelSize + xOffset + (width / 2), y / pixelSize + yOffset + (height / 2)) == 0)// transparent
			return null;
		return new Color(skin.getRGB(x / pixelSize + xOffset + (width / 2), y / pixelSize + yOffset + (height / 2)));

	}

}
