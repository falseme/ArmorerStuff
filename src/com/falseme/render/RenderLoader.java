package com.falseme.render;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class RenderLoader {

	private static BufferedImage skin;
	final static int[] triSize = { 25, 26, 27 }; // triangle sizes [0-base/ /1-overlay/ /2-armor]

	public static ArrayList<Triangle> loadSkin(String skinPath) {

		try {
			skin = ImageIO.read(new File(skinPath));
		} catch (IOException e) {
			e.printStackTrace();
			skin = null;
		}

		ArrayList<Triangle> tris = new ArrayList<>();

		loadHead(tris);

		return tris;

	}

	public static void loadHead(ArrayList<Triangle> tris) {

		int x = 0;
		int y = 0;
		int z = 0;

		// FROM LEFT TO RIGHT, FROM TOP TO BOTTOM
		// HEAD (8x8)
		// FRONT
		for (int i = 0; i < 64; i++) {

			// base & overlay (s = Size[triangle])
			for (int s = 0; s < 2; s++) {

				z = triSize[s] * 4;

				x = ((i % 8) * triSize[s]) - (triSize[s] * 4);
				y = triSize[s] * (i / 8) - (triSize[s] * 4);

				Color color;
				if (s > 0) {
					if (skin.getRGB(x / triSize[s] + 44, y / triSize[s] + 12) == 0) // transparent pixel
						continue;
					color = new Color(skin.getRGB(x / triSize[s] + 44, y / triSize[s] + 12));
				} else { // base color
					color = new Color(skin.getRGB(x / triSize[s] + 12, y / triSize[s] + 12));
				}

				tris.add(new Triangle(new Vertex(x, y, z, 1), new Vertex(x + triSize[s], y, z, 1),
						new Vertex(x, y + triSize[s], z, 1), color));
				tris.add(new Triangle(new Vertex(x + triSize[s], y, z, 1),
						new Vertex(x + triSize[s], y + triSize[s], z, 1), new Vertex(x, y + triSize[s], z, 1), color));

			}

		}
		// LEFT (front.x -> left.z) !![from right to left]!!
		for (int i = 0; i < 64; i++) {

			// base & overlay
			for (int s = 0; s < 2; s++) {

				x = triSize[s] * 4;

				y = triSize[s] * (i / 8) - (triSize[s] * 4);
				z = ((i % 8) * triSize[s]) - (triSize[s] * 4);

				Color color;
				if (s > 0) { // overlay (x+32)
					if (skin.getRGB(-z / triSize[s] + 51, y / triSize[s] + 12) == 0)
						continue;
					color = new Color(skin.getRGB(-z / triSize[s] + 51, y / triSize[s] + 12));
				} else { // base
					color = new Color(skin.getRGB(-z / triSize[s] + 19, y / triSize[s] + 12));
				}

				tris.add(new Triangle(new Vertex(x, y, z, 1), new Vertex(x, y, z + triSize[s], 1),
						new Vertex(x, y + triSize[s], z, 1), color));
				tris.add(new Triangle(new Vertex(x, y, z + triSize[s], 1),
						new Vertex(x, y + triSize[s], z + triSize[s], 1), new Vertex(x, y + triSize[s], z, 1), color));

			}

		}
		// RIGHT (-left.x)
		for (int i = 0; i < 64; i++) {

			// base & overlay
			for (int s = 0; s < 2; s++) {

				x = -triSize[s] * 4;

				y = triSize[s] * (i / 8) - (triSize[s] * 4);
				z = ((i % 8) * triSize[s]) - (triSize[s] * 4);

				Color color;
				if (s > 0) { // overlay
					if (skin.getRGB(z / triSize[s] + 36, y / triSize[s] + 12) == 0)// transparent pixel
						continue;
					color = new Color(skin.getRGB(z / triSize[s] + 36, y / triSize[s] + 12));
				} else { // base
					color = new Color(skin.getRGB(z / triSize[s] + 4, y / triSize[s] + 12));
				}

				tris.add(new Triangle(new Vertex(x, y, z, 1), new Vertex(x, y, z + triSize[s], 1),
						new Vertex(x, y + triSize[s], z, 1), color));
				tris.add(new Triangle(new Vertex(x, y, z + triSize[s], 1),
						new Vertex(x, y + triSize[s], z + triSize[s], 1), new Vertex(x, y + triSize[s], z, 1), color));

			}

		}
		// BACK (-top.z) !![from right to left]!!
		for (int i = 0; i < 64; i++) {

			// base & overlay
			for (int s = 0; s < 2; s++) {

				z = -triSize[s] * 4;

				x = ((i % 8) * triSize[s]) - (triSize[s] * 4);
				y = triSize[s] * (i / 8) - (triSize[s] * 4);

				Color color;
				if (s > 0) {// overlay
					if (skin.getRGB(-x / triSize[s] + 59, y / triSize[s] + 12) == 0) // transparent
						continue;
					color = new Color(skin.getRGB(-x / triSize[s] + 59, y / triSize[s] + 12));
				} else {// base
					color = new Color(skin.getRGB(-x / triSize[s] + 27, y / triSize[s] + 12));
				}

				tris.add(new Triangle(new Vertex(x, y, z, 1), new Vertex(x + triSize[s], y, z, 1),
						new Vertex(x, y + triSize[s], z, 1), color));
				tris.add(new Triangle(new Vertex(x + triSize[s], y, z, 1),
						new Vertex(x + triSize[s], y + triSize[s], z, 1), new Vertex(x, y + triSize[s], z, 1), color));

			}

		}
		// TOP (front.y -> -z)
		for (int i = 0; i < 64; i++) {

			for (int s = 0; s < 2; s++) {

				y = -triSize[s] * 4;

				x = ((i % 8) * triSize[s]) - (triSize[s] * 4);
				z = triSize[s] * (i / 8) - (triSize[s] * 4);

				Color color;
				if (s > 0) { // overlay
					if (skin.getRGB(x / triSize[s] + 44, z / triSize[s] + 4) == 0) // transparent
						continue;
					color = new Color(skin.getRGB(x / triSize[s] + 44, z / triSize[s] + 4));
				} else {// base
					color = new Color(skin.getRGB(x / triSize[s] + 12, z / triSize[s] + 4));
				}

				tris.add(new Triangle(new Vertex(x, y, z, 1), new Vertex(x + triSize[s], y, z, 1),
						new Vertex(x, y, z + triSize[s], 1), color));
				tris.add(new Triangle(new Vertex(x + triSize[s], y, z, 1),
						new Vertex(x + triSize[s], y, z + triSize[s], 1), new Vertex(x, y, z + triSize[s], 1), color));

			}

		}
		// BOTTOM (-top.y)
		for (int i = 0; i < 64; i++) {

			// base & overlay
			for (int s = 0; s < 2; s++) {

				y = triSize[s] * 4;

				x = ((i % 8) * triSize[s]) - (triSize[s] * 4);
				z = triSize[s] * (i / 8) - (triSize[s] * 4);

				Color color;
				if (s > 0) { // overlay
					if (skin.getRGB(x / triSize[s] + 52, z / triSize[s] + 4) == 0)// transparent
						continue;
					color = new Color(skin.getRGB(x / triSize[s] + 52, z / triSize[s] + 4));
				} else { // base
					color = new Color(skin.getRGB(x / triSize[s] + 20, z / triSize[s] + 4));
				}

				tris.add(new Triangle(new Vertex(x, y, z, 1), new Vertex(x + triSize[s], y, z, 1),
						new Vertex(x, y, z + triSize[s], 1), color));
				tris.add(new Triangle(new Vertex(x + triSize[s], y, z, 1),
						new Vertex(x + triSize[s], y, z + triSize[s], 1), new Vertex(x, y, z + triSize[s], 1), color));

			}

		}

	}

}
