package com.falseme.render;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
		loadTorso(tris);

		return tris;

	}

	public static void loadHead(List<Triangle> tris) {

		int x = 0, y = 0, z = 0;
		int[] torsoHeight = { triSize[0] * 10, triSize[1] * 10 }; // no se porque 10 pero bue

		// FROM LEFT TO RIGHT, FROM TOP TO BOTTOM
		// HEAD (8x8)
		// FRONT
		for (int i = 0; i < 64; i++) {

			// base & overlay (s = Size[triangle])
			for (int s = 0; s < 2; s++) {

				z = triSize[s] * 4;

				x = ((i % 8) * triSize[s]) - (triSize[s] * 4);
				y = triSize[s] * (i / 8) - (triSize[s] * 4);

				Color color = Color.WHITE;
				if (s > 0) {
					if (skin.getRGB(x / triSize[s] + 44, y / triSize[s] + 12) == 0) // transparent pixel
						continue;
					color = new Color(skin.getRGB(x / triSize[s] + 44, y / triSize[s] + 12));
				} else { // base color
					color = new Color(skin.getRGB(x / triSize[s] + 12, y / triSize[s] + 12));
				}

				y -= torsoHeight[s];
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

				Color color = Color.WHITE;
				if (s > 0) { // overlay (x+32)
					if (skin.getRGB(-z / triSize[s] + 51, y / triSize[s] + 12) == 0)
						continue;
					color = new Color(skin.getRGB(-z / triSize[s] + 51, y / triSize[s] + 12));
				} else { // base
					color = new Color(skin.getRGB(-z / triSize[s] + 19, y / triSize[s] + 12));
				}

				y -= torsoHeight[s];
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

				Color color = Color.WHITE;
				if (s > 0) { // overlay
					if (skin.getRGB(z / triSize[s] + 36, y / triSize[s] + 12) == 0)// transparent pixel
						continue;
					color = new Color(skin.getRGB(z / triSize[s] + 36, y / triSize[s] + 12));
				} else { // base
					color = new Color(skin.getRGB(z / triSize[s] + 4, y / triSize[s] + 12));
				}

				y -= torsoHeight[s];
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

				Color color = Color.WHITE;
				if (s > 0) {// overlay
					if (skin.getRGB(-x / triSize[s] + 59, y / triSize[s] + 12) == 0) // transparent
						continue;
					color = new Color(skin.getRGB(-x / triSize[s] + 59, y / triSize[s] + 12));
				} else {// base
					color = new Color(skin.getRGB(-x / triSize[s] + 27, y / triSize[s] + 12));
				}

				y -= torsoHeight[s];
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

				Color color = Color.WHITE;
				if (s > 0) { // overlay
					if (skin.getRGB(x / triSize[s] + 44, z / triSize[s] + 4) == 0) // transparent
						continue;
					color = new Color(skin.getRGB(x / triSize[s] + 44, z / triSize[s] + 4));
				} else {// base
					color = new Color(skin.getRGB(x / triSize[s] + 12, z / triSize[s] + 4));
				}

				y -= torsoHeight[s];
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

				Color color = Color.WHITE;
				if (s > 0) { // overlay
					if (skin.getRGB(x / triSize[s] + 52, z / triSize[s] + 4) == 0)// transparent
						continue;
					color = new Color(skin.getRGB(x / triSize[s] + 52, z / triSize[s] + 4));
				} else { // base
					color = new Color(skin.getRGB(x / triSize[s] + 20, z / triSize[s] + 4));
				}

				y -= torsoHeight[s];
				tris.add(new Triangle(new Vertex(x, y, z, 1), new Vertex(x + triSize[s], y, z, 1),
						new Vertex(x, y, z + triSize[s], 1), color));
				tris.add(new Triangle(new Vertex(x + triSize[s], y, z, 1),
						new Vertex(x + triSize[s], y, z + triSize[s], 1), new Vertex(x, y, z + triSize[s], 1), color));

			}

		}

	}

	public static void loadTorso(List<Triangle> tris) {

		int x = 0, y = 0, z = 0;

		// FRONT
		for (int i = 0; i < 96; i++) {

			// base & overlay
			for (int s = 0; s < 2; s++) {

				z = triSize[s] * 2;

				x = ((i % 8) * triSize[s]) - (triSize[s] * 4);
				y = triSize[s] * (i / 8) - (triSize[s] * 6);

				Color color = Color.WHITE;
				if (s > 0) { // overlay (y+16)
					if (skin.getRGB(x / triSize[s] + 24, y / triSize[s] + 42) == 0) // transparent
						continue;
					color = new Color(skin.getRGB(x / triSize[s] + 24, y / triSize[s] + 42));
				} else { // base
					color = new Color(skin.getRGB(x / triSize[s] + 24, y / triSize[s] + 26));
				}

				tris.add(new Triangle(new Vertex(x, y, z, 1), new Vertex(x + triSize[s], y, z, 1),
						new Vertex(x, y + triSize[s], z, 1), color));
				tris.add(new Triangle(new Vertex(x + triSize[s], y, z, 1),
						new Vertex(x + triSize[s], y + triSize[s], z, 1), new Vertex(x, y + triSize[s], z, 1), color));

			}

		}

		// LEFT !![from right to left]!!
		for (int i = 0; i < 48; i++) {

			// base & overlay
			for (int s = 0; s < 2; s++) {

				x = triSize[s] * 4;

				y = triSize[s] * (i / 4) - (triSize[s] * 6);
				z = ((i % 4) * triSize[s]) - (triSize[s] * 2);

				Color color = Color.WHITE;
				if (s > 0) { // overlay (y+16)
					if (skin.getRGB(-z / triSize[s] + 29, y / triSize[s] + 42) == 0)
						continue;
					color = new Color(skin.getRGB(-z / triSize[s] + 29, y / triSize[s] + 42));
				} else { // base
					color = new Color(skin.getRGB(-z / triSize[s] + 29, y / triSize[s] + 26));
				}

				tris.add(new Triangle(new Vertex(x, y, z, 1), new Vertex(x, y, z + triSize[s], 1),
						new Vertex(x, y + triSize[s], z, 1), color));
				tris.add(new Triangle(new Vertex(x, y, z + triSize[s], 1),
						new Vertex(x, y + triSize[s], z + triSize[s], 1), new Vertex(x, y + triSize[s], z, 1), color));

			}

		}

		// RIGHT
		for (int i = 0; i < 48; i++) {

			// base & overlay
			for (int s = 0; s < 2; s++) {

				x = -triSize[s] * 4;

				y = triSize[s] * (i / 4) - (triSize[s] * 6);
				z = ((i % 4) * triSize[s]) - (triSize[s] * 2);

				Color color = Color.WHITE;
				if (s > 0) { // overlay (y+16)
					if (skin.getRGB(z / triSize[s] + 18, y / triSize[s] + 42) == 0)
						continue;
					color = new Color(skin.getRGB(z / triSize[s] + 18, y / triSize[s] + 42));
				} else { // base
					color = new Color(skin.getRGB(z / triSize[s] + 18, y / triSize[s] + 26));
				}

				tris.add(new Triangle(new Vertex(x, y, z, 1), new Vertex(x, y, z + triSize[s], 1),
						new Vertex(x, y + triSize[s], z, 1), color));
				tris.add(new Triangle(new Vertex(x, y, z + triSize[s], 1),
						new Vertex(x, y + triSize[s], z + triSize[s], 1), new Vertex(x, y + triSize[s], z, 1), color));

			}

		}

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

		// TOP
		for (int i = 0; i < 32; i++) {

			// base & overlay
			for (int s = 0; s < 2; s++) {

				y = -triSize[s] * 6;

				x = ((i % 8) * triSize[s]) - (triSize[s] * 4);
				z = triSize[s] * (i / 8) - (triSize[s] * 2);

				Color color = Color.WHITE;
				if (s > 0) { // overlay (y+16)
					if (skin.getRGB(x / triSize[s] + 24, z / triSize[s] + 34) == 0) // transparent
						continue;
					color = new Color(skin.getRGB(x / triSize[s] + 24, z / triSize[s] + 34));
				} else { // base
					color = new Color(skin.getRGB(x / triSize[s] + 24, z / triSize[s] + 18));
				}

				tris.add(new Triangle(new Vertex(x, y, z, 1), new Vertex(x + triSize[s], y, z, 1),
						new Vertex(x, y, z + triSize[s], 1), color));
				tris.add(new Triangle(new Vertex(x + triSize[s], y, z, 1),
						new Vertex(x + triSize[s], y, z + triSize[s], 1), new Vertex(x, y, z + triSize[s], 1), color));

			}

		}

		// BOTTOM
		for (int i = 0; i < 32; i++) {

			// base & overlay
			for (int s = 0; s < 2; s++) {

				y = triSize[s] * 6;

				x = ((i % 8) * triSize[s]) - (triSize[s] * 4);
				z = triSize[s] * (i / 8) - (triSize[s] * 2);

				Color color = Color.WHITE;
				if (s > 0) { // overlay (y+16)
					if (skin.getRGB(x / triSize[s] + 32, z / triSize[s] + 34) == 0) // transparent
						continue;
					color = new Color(skin.getRGB(x / triSize[s] + 32, z / triSize[s] + 34));
				} else { // base
					color = new Color(skin.getRGB(x / triSize[s] + 32, z / triSize[s] + 18));
				}

				tris.add(new Triangle(new Vertex(x, y, z, 1), new Vertex(x + triSize[s], y, z, 1),
						new Vertex(x, y, z + triSize[s], 1), color));
				tris.add(new Triangle(new Vertex(x + triSize[s], y, z, 1),
						new Vertex(x + triSize[s], y, z + triSize[s], 1), new Vertex(x, y, z + triSize[s], 1), color));

			}

		}

	}

}
