package com.falseme.render;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import com.falseme.gui.Assets;

public class RenderLoader {

	private static boolean slim = false;

	public static int[][] armor = { { -1, -1, -1 }, { -1, -1, -1 }, { -1, -1, -1 }, { -1, -1, -1 } };
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

	/*
	 * @param armorInventorySlot - the slot index (helmet, chestplate, leggings,
	 * boots)
	 * 
	 * @param paramIndex - the param index (armor-material, armor-trim, trim-color)
	 * 
	 * @param param - the param value (if 'paramIndex' = 0 then 'param' represents
	 * the armor-material value)
	 * 
	 * @see Assets
	 * 
	 * @see RenderLoader.armor
	 */
	public static void loadArmor(int armorInventorySlot, int paramIndex, int param) {

		armor[armorInventorySlot][paramIndex] = param;

		PlayerRender.loadRender();

	}

	public static ArrayList<Triangle> loadArmorAssets() {

		ArrayList<Triangle> tris = new ArrayList<>();

		// HELMET
		if (armor[0][0] != -1) {
			if (armor[0][1] == -1 || armor[0][2] == -1) {
				trimImg = null;
				trimColorPalette = null;
			} else {
				trimImg = Assets.ARMOR_TRIM[armor[0][1]][0];
				trimColorPalette = loadTrimColorPalette(armor[0][2], armor[0][0]);
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
				trimColorPalette = loadTrimColorPalette(armor[1][2], armor[1][0]);
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
				trimColorPalette = loadTrimColorPalette(armor[2][2], armor[2][0]);
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
				trimColorPalette = loadTrimColorPalette(armor[3][2], armor[3][0]);
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

		// SLIM SKIN
		// ARMS (4x12 x3)
		if (slim) {
			// RIGHT ARM
			// FRONT
			loadXYSide(tris, 3, 12, 44, 20, 44, 36, -6, 0, 2, 1, 0, false, triSize[0], triSize[1]);
			// BACK
			loadXYSide(tris, 3, 12, 51, 20, 52, 36, -6, 0, -2, -1, -1, false, triSize[0], triSize[1]);
			// SIDE
			loadZYSide(tris, 4, 12, 40, 20, 40, 36, -7, 0, 0, 1, 0, false, triSize[0], triSize[1]);
			// INSIDE
			loadZYSide(tris, 4, 12, 47, 20, 47, 36, -4, 0, 0, -1, -1, false, triSize[0], triSize[1]);
			// TOP
			loadXZSide(tris, 3, 4, 44, 16, 44, 32, -5.5, -6, 0, 0, false, triSize[0], triSize[1]);
			// BOTTOM
			loadXZSide(tris, 3, 4, 47, 16, 47, 32, -5.5, 6, 0, 0, false, triSize[0], triSize[1]);

			// LEFT ARM
			// FRONT
			loadXYSide(tris, 3, 12, 36, 52, 52, 52, 5, 0, 2, 1, 0, false, triSize[0], triSize[1]);
			// BACK
			loadXYSide(tris, 3, 12, 43, 52, 60, 52, 5, 0, -2, -1, -1, false, triSize[0], triSize[1]);
			// SIDE
			loadZYSide(tris, 4, 12, 39, 52, 55, 52, 7, 0, 0, -1, -1, false, triSize[0], triSize[1]);
			// INSIDE
			loadZYSide(tris, 4, 12, 32, 52, 48, 52, 4, 0, 0, 1, 0, false, triSize[0], triSize[1]);
			// TOP
			loadXZSide(tris, 3, 4, 36, 48, 52, 48, 5.5, -6, 0, 0, false, triSize[0], triSize[1]);
			// BOTTOM
			loadXZSide(tris, 3, 4, 39, 48, 55, 48, 5.5, 6, 0, 0, false, triSize[0], triSize[1]);

			return;
		}

		// ARMS (4x12 x4)
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
			int yPngOverlay, double xOffset, int yOffset, int zOffset, int yExOffset, boolean drawTrim,
			int... pixelSize) {

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
			slim = false;
			return;
		}

		try {
			currentImage = ImageIO.read(new File(path));
		} catch (IOException ex) {
			ex.printStackTrace();
			currentImage = null;
		}

		// slim skin??
		slim = true;
		for (int x = 0; x < 2; x++) {
			for (int y = 0; y < 12; y++) {
				// right arm
				if (currentImage.getRGB(54 + x, 20 + y) != 0) { // if not transparent
					slim = false;
					return;
				}
				// left arm
				if (currentImage.getRGB(46 + x, 52 + y) != 0) { // if not transparent
					slim = false;
					return;
				}
			}
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

	/*
	 * @param trimIndex Index in the original trim color palette list. The hashmap
	 * with darker colors uses the same index.
	 * 
	 * @param armorIndex Index in the armor textures list. Used to compare the
	 * materials so as to pick the darker color.
	 */
	private static Color[] loadTrimColorPalette(int trimIndex, int armorIndex) {

		// compare if the material is the same
		boolean sameMaterial = false;
		for (int[] indexes : sameMaterialIndexes) {
			if (armorIndex == indexes[0] && trimIndex == indexes[1])
				sameMaterial = true;
		}
		// return the original color or a darker version if necessary
		if (sameMaterial)
			return Assets.darkTrimColor.get(trimIndex);
		else
			return Assets.trimColor[trimIndex];

	}

	// list of indexes comparisons between the armor material and the trim
	// material in which they are the same.
	// this is the order: (iron, gold, diamond, netherite) - (armor, trim).
	private static int[][] sameMaterialIndexes = { { 1, 0 }, { 2, 2 }, { 3, 5 }, { 4, 6 } };

}
