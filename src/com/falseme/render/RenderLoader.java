package com.falseme.render;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import com.falseme.gui.Assets;
import com.falseme.ui.Window;

public class RenderLoader {

	public static int[][] armor = { { 0, 0, 0 }, { 2, 0, 0 }, { 0, 0, 0 }, { 1, 0, 0 } };
	// [n][0] = material / [n][1] = trim / [n][2] = trim-color /
	// n = type(helmet,boots...) /

	private static BufferedImage currentImage = null; // image that contains the player skin or the armor
	final static int[] triSize = { 25, 26, 28, 27 }; // triangle sizes [0-base/ /1-overlay/ /2-armor/ /3-leggings]

	private static boolean[] doRender = { true, true, true, true };

	private static BufferedImage trimImg;
	private static Color[] trimColorPalette;

	public static int leather_dye = 16;

	public static ArrayList<Triangle> loadSkin(String skinPath) {

		loadSkinImage(skinPath);

		ArrayList<Triangle> tris = new ArrayList<>();

		loadHead(tris);
		loadTorso(tris);
		loadArms(tris);
		loadLegs(tris);

		return tris;

	}

	public static ArrayList<Triangle> loadArmor() {

		for (int i = 0; i < 4; i++) {
			int n = i * 4;
			armor[i][0] = Window.userBoxes.get(2 + n).getItem().params[2]; // armor-material
			armor[i][1] = Window.userBoxes.get(1 + n).getItem().params[2]; // trim
			armor[i][2] = Window.userBoxes.get(3 + n).getItem().params[2]; // trim-color
		}

		ArrayList<Triangle> tris = new ArrayList<>();

		// HELMET
		if (armor[0][0] != -1) {
			if (armor[0][1] == -1 || armor[0][2] == -1) {
				trimImg = null;
				trimColorPalette = null;
			} else {
				trimImg = Assets.ARMOR_TRIM[armor[0][1]][0];
				trimColorPalette = Assets.trimColor[armor[0][2]];
			}
			currentImage = Assets.ARMOR[armor[0][0]][0];
			loadHelmet(tris);
		}
		// CHESTPLATE
		if (armor[1][0] != -1) {
			if (armor[1][1] == -1 || armor[1][2] == -1) {
				trimImg = null;
				trimColorPalette = null;
			} else {
				trimImg = Assets.ARMOR_TRIM[armor[1][1]][0];
				trimColorPalette = Assets.trimColor[armor[1][2]];
			}
			currentImage = Assets.ARMOR[armor[1][0]][0];
			loadChestplate(tris);
		}
		// LEGGINGS
		if (armor[2][0] != -1) {
			if (armor[2][1] == -1 || armor[2][2] == -1) {
				trimImg = null;
				trimColorPalette = null;
			} else {
				trimImg = Assets.ARMOR_TRIM[armor[2][1]][1];
				trimColorPalette = Assets.trimColor[armor[2][2]];
			}
			currentImage = Assets.ARMOR[armor[2][0]][1];
			loadLeggings(tris);
		}
		// BOOTS
		if (armor[3][0] != -1) {
			if (armor[3][1] == -1 || armor[3][2] == -1) {
				trimImg = null;
				trimColorPalette = null;
			} else {
				trimImg = Assets.ARMOR_TRIM[armor[3][1]][0];
				trimColorPalette = Assets.trimColor[armor[3][2]];
			}
			currentImage = Assets.ARMOR[armor[3][0]][0];
			loadBoots(tris);
		}

		return tris;

	}

	// SKIN
	private static void loadHead(List<Triangle> tris) {

		if (!doRender[0])
			return;

		// HEAD (8x8)
		// FRONT
		loadXYSide(tris, 8, 8, 8, 8, 40, 8, 0, -10, 4, 1, 0, false, triSize[0], triSize[1]); // y-offset =
																								// 4(head.height/2) +
		// 6(torso.height/2)
		// BACK
		loadXYSide(tris, 8, 8, 24, 8, 56, 8, 0, -10, -4, -1, -1, false, triSize[0], triSize[1]);
		// LEFT
		loadZYSide(tris, 8, 8, 16, 8, 48, 8, 4, -10, 0, -1, -1, false, triSize[0], triSize[1]);
		// RIGHT
		loadZYSide(tris, 8, 8, 0, 8, 32, 8, -4, -10, 0, 1, 0, false, triSize[0], triSize[1]);
		// TOP
		loadXZSide(tris, 8, 8, 8, 0, 40, 0, 0, -4, 0, -10, false, triSize[0], triSize[1]);
		// BOTTOM
		loadXZSide(tris, 8, 8, 16, 0, 48, 0, 0, 4, 0, -10, false, triSize[0], triSize[1]);

	}

	private static void loadTorso(List<Triangle> tris) {

		if (!doRender[1])
			return;

		// TORSO (8x12 x4)
		// FRONT
		loadXYSide(tris, 8, 12, 20, 20, 20, 36, 0, 0, 2, 1, 0, false, triSize[0], triSize[1]);
		// BACK
		loadXYSide(tris, 8, 12, 32, 20, 32, 36, 0, 0, -2, -1, -1, false, triSize[0], triSize[1]);
		// LEFT
		loadZYSide(tris, 4, 12, 28, 20, 28, 36, 4, 0, 0, -1, -1, false, triSize[0], triSize[1]);
		// RIGHT
		loadZYSide(tris, 4, 12, 16, 20, 16, 36, -4, 0, 0, 1, 0, false, triSize[0], triSize[1]);
		// TOP
		loadXZSide(tris, 8, 4, 20, 16, 20, 32, 0, -6, 0, 0, false, triSize[0], triSize[1]);
		// BOTTOM
		loadXZSide(tris, 8, 4, 28, 16, 28, 32, 0, 6, 0, 0, false, triSize[0], triSize[1]);

	}

	private static void loadArms(List<Triangle> tris) {

		if (!doRender[2])
			return;

		// ARMS (4x12 x4-3[?])
		// RIGHT ARM
		// FRONT
		loadXYSide(tris, 4, 12, 44, 20, 44, 36, -6, 0, 2, 1, 0, false, triSize[0], triSize[1]); // x-offset =
																								// 4(torso-width/2)
																								// + 2(arm-width/2)
		// BACK
		loadXYSide(tris, 4, 12, 52, 20, 52, 36, -6, 0, -2, -1, -1, false, triSize[0], triSize[1]);
		// SIDE
		loadZYSide(tris, 4, 12, 40, 20, 40, 36, -8, 0, 0, 1, 0, false, triSize[0], triSize[1]);
		// INSIDE
		loadZYSide(tris, 4, 12, 48, 20, 48, 36, -4, 0, 0, -1, -1, false, triSize[0], triSize[1]);
		// TOP
		loadXZSide(tris, 4, 4, 44, 16, 44, 32, -6, -6, 0, 0, false, triSize[0], triSize[1]);
		// BOTTOM
		loadXZSide(tris, 4, 4, 48, 16, 48, 32, -6, 6, 0, 0, false, triSize[0], triSize[1]);

		// LEFT ARM
		// FRONT
		loadXYSide(tris, 4, 12, 36, 52, 52, 52, 6, 0, 2, 1, 0, false, triSize[0], triSize[1]);
		// BACK
		loadXYSide(tris, 4, 12, 44, 52, 60, 52, 6, 0, -2, -1, -1, false, triSize[0], triSize[1]);
		// SIDE
		loadZYSide(tris, 4, 12, 40, 52, 56, 52, 8, 0, 0, -1, -1, false, triSize[0], triSize[1]);
		// INSIDE
		loadZYSide(tris, 4, 12, 32, 52, 48, 52, 4, 0, 0, 1, 0, false, triSize[0], triSize[1]);
		// TOP
		loadXZSide(tris, 4, 4, 36, 48, 52, 48, 6, -6, 0, 0, false, triSize[0], triSize[1]);
		// BOTTOM
		loadXZSide(tris, 4, 4, 40, 48, 56, 48, 6, 6, 0, 0, false, triSize[0], triSize[1]);

	}

	private static void loadLegs(List<Triangle> tris) {

		if (!doRender[3])
			return;

		// LEGS (4x12 x4)
		// RIGHT LEG
		// FRONT
		loadXYSide(tris, 4, 12, 4, 20, 4, 36, -2, 12, 2, 1, 0, false, triSize[0], triSize[1]);
		// BACK
		loadXYSide(tris, 4, 12, 12, 20, 12, 36, -2, 12, -2, -1, -1, false, triSize[0], triSize[1]);
		// SIDE
		loadZYSide(tris, 4, 12, 0, 20, 0, 36, -4, 12, 0, 1, 0, false, triSize[0], triSize[1]);
		// TOP
		loadXZSide(tris, 4, 4, 4, 16, 4, 32, -2, 6, 0, 0, false, triSize[0], triSize[1]);
		// BOTTOM
		loadXZSide(tris, 4, 4, 8, 16, 8, 32, -2, 18, 0, 0, false, triSize[0], triSize[1]);

		// LEFT LEG
		// FRONT
		loadXYSide(tris, 4, 12, 20, 52, 4, 52, 2, 12, 2, 1, 0, false, triSize[0], triSize[1]);
		// BACK
		loadXYSide(tris, 4, 12, 28, 52, 12, 52, 2, 12, -2, -1, -1, false, triSize[0], triSize[1]);
		// SIDE
		loadZYSide(tris, 4, 12, 24, 52, 8, 52, 4, 12, 0, -1, -1, false, triSize[0], triSize[1]);
		// TOP
		loadXZSide(tris, 4, 4, 20, 48, 4, 48, 2, 6, 0, 0, false, triSize[0], triSize[1]);
		// BOTTOM
		loadXZSide(tris, 4, 4, 24, 48, 8, 48, 2, 18, 0, 0, false, triSize[0], triSize[1]);

	}

	// ARMOR
	private static void loadHelmet(List<Triangle> tris) {

		// HELMET (8x8)
		// FRONT
		loadXYSide(tris, 8, 8, 8, 8, 0, 0, 0, -9, 4, 1, 0, true, triSize[2]);
		// BACK
		loadXYSide(tris, 8, 8, 24, 8, 0, 0, 0, -9, -4, -1, -1, true, triSize[2]);
		// LEFT
		loadZYSide(tris, 8, 8, 16, 8, 0, 0, 4, -9, 0, -1, -1, true, triSize[2]);
		// RIGHT
		loadZYSide(tris, 8, 8, 0, 8, 0, 0, -4, -9, 0, 1, 0, true, triSize[2]);
		// TOP
		loadXZSide(tris, 8, 8, 8, 0, 0, 0, 0, -4, 0, -9, true, triSize[2]);

	}

	private static void loadChestplate(List<Triangle> tris) {

		// CHESTPLATE
		// PRINCIPAL (8x12 x4)
		// FRONT
		loadXYSide(tris, 8, 12, 20, 20, 0, 0, 0, 0, 2, 1, 0, true, triSize[2]);
		// BACK
		loadXYSide(tris, 8, 12, 32, 20, 0, 0, 0, 0, -2, -1, -1, true, triSize[2]);
		// LEFT
		loadZYSide(tris, 4, 12, 28, 20, 0, 0, 4, 0, 0, -1, -1, true, triSize[2]); // dont know which one is correct
		// RIGHT
		loadZYSide(tris, 4, 12, 16, 20, 0, 0, -4, 0, 0, 1, 0, true, triSize[2]);

		// ARMS (4x12 x4)
		// FRONT
		loadXYSide(tris, 4, 12, 44, 20, 0, 0, 6, 0, 2, -1, -1, true, triSize[2]); // LEFT
		loadXYSide(tris, 4, 12, 44, 20, 0, 0, -6, 0, 2, 1, 0, true, triSize[2]); // RIGHT
		// BACK
		loadXYSide(tris, 4, 12, 52, 20, 0, 0, 6, 0, -2, 1, 0, true, triSize[2]); // LEFT
		loadXYSide(tris, 4, 12, 52, 20, 0, 0, -6, 0, -2, -1, -1, true, triSize[2]); // RIGHT
		// SIDE
		loadZYSide(tris, 4, 12, 40, 20, 0, 0, 8, 0, 0, 1, 0, true, triSize[2]); // LEFT
		loadZYSide(tris, 4, 12, 40, 20, 0, 0, -8, 0, 0, 1, 0, true, triSize[2]); // RIGHT
		// TOP
		loadXZSide(tris, 4, 4, 48, 16, 0, 0, 6, -6, 0, 0, true, triSize[2]); // LEFT
		loadXZSide(tris, 4, 4, 44, 16, 0, 0, -6, -6, 0, 0, true, triSize[2]); // RIGHT

	}

	private static void loadLeggings(List<Triangle> tris) {

		// LEGGINGS (4x12 x4) [x2] (top->8x5)[pos=chestplate (8x12) y-offset=0]
		// FRONT
		loadXYSide(tris, 8, 12, 20, 20, 0, 0, 0, 0, 2, 1, 0, true, triSize[3]); // TOP
		loadXYSide(tris, 4, 12, 4, 20, 0, 0, 2, 12, 2, -1, -1, true, triSize[3]); // LEFT
		loadXYSide(tris, 4, 12, 4, 20, 0, 0, -2, 12, 2, 1, 0, true, triSize[3]); // RIGHT
		// BACK
		loadXYSide(tris, 8, 12, 32, 20, 0, 0, 0, 0, -2, 1, 0, true, triSize[3]); // TOP
		loadXYSide(tris, 4, 12, 12, 20, 0, 0, 2, 12, -2, 1, 0, true, triSize[3]); // LEFT
		loadXYSide(tris, 4, 12, 12, 20, 0, 0, -2, 12, -2, -1, -1, true, triSize[3]); // RIGHT
		// LEFT
		loadZYSide(tris, 4, 12, 28, 20, 0, 0, 4, 0, 0, -1, -1, true, triSize[3]); // TOP
		loadZYSide(tris, 4, 12, 0, 20, 0, 0, 4, 12, 0, 1, 0, true, triSize[3]); // LEFT
		// RIGHT
		loadZYSide(tris, 4, 12, 16, 20, 0, 0, -4, 0, 0, 1, 0, true, triSize[3]); // TOP
		loadZYSide(tris, 4, 12, 0, 20, 0, 0, -4, 12, 0, 1, 0, true, triSize[3]); // RIGHT

	}

	private static void loadBoots(List<Triangle> tris) {

		// BOOTS (4x12 x4)
		// FRONT
		loadXYSide(tris, 4, 12, 4, 20, 0, 0, 2, 12, 2, -1, -1, true, triSize[2]); // LEFT
		loadXYSide(tris, 4, 12, 4, 20, 0, 0, -2, 12, 2, 1, 0, true, triSize[2]); // RIGHT
		// BACK
		loadXYSide(tris, 4, 12, 12, 20, 0, 0, 2, 12, -2, 1, 0, true, triSize[2]); // LEFT
		loadXYSide(tris, 4, 12, 12, 20, 0, 0, -2, 12, -2, -1, -1, true, triSize[2]); // RIGHT
		// LEFT
		loadZYSide(tris, 4, 12, 0, 20, 0, 0, 4, 12, 0, 1, 0, true, triSize[2]);
		// RIGHT
		loadZYSide(tris, 4, 12, 0, 20, 0, 0, -4, 12, 0, 1, 0, true, triSize[2]);
		// BOTTOM
		loadXZSide(tris, 4, 4, 8, 16, 0, 0, -2, 2, 0, 16, true, triSize[2]); // LEFT
		loadXZSide(tris, 4, 4, 8, 16, 0, 0, 2, 2, 0, 16, true, triSize[2]); // RIGHT

	}

	// TRIMS

	// UTIL
	private static void loadXYSide(List<Triangle> tris, int width, int height, int xPng, int yPng, int xPngOverlay,
			int yPngOverlay, int xOffset, int yOffset, int zOffset, int pointing, int pixelOffset, boolean drawTrim,
			int... pixelSize) {

		int x = 0, y = 0, z = 0;

		for (int i = 0; i < width * height; i++) {

			for (int s = 0; s < pixelSize.length; s++) {

				z = pixelSize[s] * zOffset;

				x = ((i % width) * pixelSize[s]) - (pixelSize[s] * (width / 2));
				y = pixelSize[s] * (i / width) - (pixelSize[s] * (height / 2));

				Color color = null;
				if (drawTrim) { // armor trim
					color = getSkinColor(x * pointing, y, pixelSize[s], xPng + pixelOffset, yPng, width, height,
							trimImg);
				}
				if (!drawTrim || color == null) { // then draw armor
					if (s > 0) { // skin overlay
						color = getSkinColor(x * pointing, y, pixelSize[s], xPngOverlay + pixelOffset, yPngOverlay,
								width, height, null);
					} else { // skin base or armor
						color = getSkinColor(x * pointing, y, pixelSize[s], xPng + pixelOffset, yPng, width, height,
								null);
					}
				}
				if (color == null) // transparent
					continue;

				x += pixelSize[0] * xOffset;
				y += pixelSize[0] * yOffset;
				tris.add(new Triangle(new Vertex(x, y, z, 1), new Vertex(x + pixelSize[s], y, z, 1),
						new Vertex(x, y + pixelSize[s], z, 1), color));
				tris.add(new Triangle(new Vertex(x + pixelSize[s], y, z, 1),
						new Vertex(x + pixelSize[s], y + pixelSize[s], z, 1), new Vertex(x, y + pixelSize[s], z, 1),
						color));

			}

		}

	}

	private static void loadZYSide(List<Triangle> tris, int width, int height, int xPng, int yPng, int xPngOverlay,
			int yPngOverlay, int xOffset, int yOffset, int zOffset, int pointing, int pixelOffset, boolean drawTrim,
			int... pixelSize) {

		int x = 0, y = 0, z = 0;

		for (int i = 0; i < width * height; i++) {

			// base & overlay
			for (int s = 0; s < pixelSize.length; s++) {

				x = pixelSize[s] * xOffset;

				z = ((i % width) * pixelSize[s]) - (pixelSize[s] * (width / 2));
				y = pixelSize[s] * (i / width) - (pixelSize[s] * (height / 2));

				Color color = null;
				if (drawTrim) { // armor trim
					color = getSkinColor(z * pointing, y, pixelSize[s], xPng + pixelOffset, yPng, width, height,
							trimImg);
				}
				if (!drawTrim || color == null) { // then draw armor
					if (s > 0) { // overlay
						color = getSkinColor(z * pointing, y, pixelSize[s], xPngOverlay + pixelOffset, yPngOverlay,
								width, height, null);
					} else { // skin or armor
						color = getSkinColor(z * pointing, y, pixelSize[s], xPng + pixelOffset, yPng, width, height,
								null);
					}
				}
				if (color == null) // transparent
					continue;

				z += pixelSize[0] * zOffset;
				y += pixelSize[0] * yOffset;
				tris.add(new Triangle(new Vertex(x, y, z, 1), new Vertex(x, y, z + pixelSize[s], 1),
						new Vertex(x, y + pixelSize[s], z, 1), color));
				tris.add(new Triangle(new Vertex(x, y, z + pixelSize[s], 1),
						new Vertex(x, y + pixelSize[s], z + pixelSize[s], 1), new Vertex(x, y + pixelSize[s], z, 1),
						color));

			}

		}

	}

	private static void loadXZSide(List<Triangle> tris, int width, int height, int xPng, int yPng, int xPngOverlay,
			int yPngOverlay, int xOffset, int yOffset, int zOffset, int yExOffset, boolean drawTrim, int... pixelSize) {

		int x = 0, y = 0, z = 0;

		for (int i = 0; i < width * height; i++) {

			for (int s = 0; s < pixelSize.length; s++) {

				y = pixelSize[s] * yOffset;

				x = ((i % width) * pixelSize[s]) - (pixelSize[s] * width / 2);
				z = pixelSize[s] * (i / width) - (pixelSize[s] * height / 2);

				Color color = null;
				if (drawTrim) { // armor trim
					color = getSkinColor(x, z, pixelSize[s], xPng, yPng, width, height, trimImg);
				}
				if (!drawTrim || color == null) { // then draw armor
					if (s > 0) { // overlay
						color = getSkinColor(x, z, pixelSize[s], xPngOverlay, yPngOverlay, width, height, null);
					} else { // skin base or armor
						color = getSkinColor(x, z, pixelSize[s], xPng, yPng, width, height, null);
					}
				}
				if (color == null) // transparent
					continue;

				x += pixelSize[0] * xOffset;
				z += pixelSize[0] * zOffset;
				y += pixelSize[0] * yExOffset;
				tris.add(new Triangle(new Vertex(x, y, z, 1), new Vertex(x + pixelSize[s], y, z, 1),
						new Vertex(x, y, z + pixelSize[s], 1), color));
				tris.add(new Triangle(new Vertex(x + pixelSize[s], y, z, 1),
						new Vertex(x + pixelSize[s], y, z + pixelSize[s], 1), new Vertex(x, y, z + pixelSize[s], 1),
						color));

			}

		}

	}

	private static void loadSkinImage(String path) {

		if (path == null || path.isEmpty()) {
			currentImage = null;
			return;
		}

		try {
			currentImage = ImageIO.read(new File(path));
		} catch (IOException ex) {
			ex.printStackTrace();
			currentImage = null;
		}

	}

	private static Color getSkinColor(int x, int y, int pixelSize, int xOffset, int yOffset, int width, int height,
			BufferedImage trim) {

		int cx = x / pixelSize + xOffset + (width / 2);
		int cy = y / pixelSize + yOffset + (height / 2);

		if (trim != null) {

			if (trim.getRGB(cx, cy) == 0)// transparent
				return null;
			Color color = null;

			for (int i = 0; i < 8; i++) {
				color = new Color(trim.getRGB(cx, cy));
				if (compareColors(Assets.trimPalette[i], color)) {
					color = trimColorPalette[i];
					break;
				}
			}

			return color;

		}

		if (currentImage == null)
			return Color.WHITE;
		if (currentImage.getRGB(cx, cy) == 0)// transparent
			return null;
		Color color = new Color(currentImage.getRGB(cx, cy));
		if (currentImage == Assets.ARMOR[5][0] || currentImage == Assets.ARMOR[5][1]) { // leather armor

			int index = (currentImage == Assets.ARMOR[5][0]) ? 0 : 1;

			if (Assets.LEATHER_OVERLAY[index].getRGB(cx, cy) == 0) { // no leather overlay

				for (int i = 0; i < 6; i++) {
					if (compareColors(Assets.leatherPalette[index][i], color)) {
						color = Assets.leatherColorPalette[leather_dye][i];
						break;
					}
				}

			} else { // leather overlay

				color = new Color(Assets.LEATHER_OVERLAY[index].getRGB(cx, cy));

			}

		}
		return color;

	}

	public static void changeRender(int part) {

		doRender[part] = !doRender[part];
		PlayerRender.loadRender();

	}

	private static boolean compareColors(Color c1, Color c2) {

		if (c1.getRed() == c2.getRed() && c1.getGreen() == c2.getGreen() && c1.getBlue() == c2.getBlue())
			return true;
		return false;

	}

}
