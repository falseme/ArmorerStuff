package com.falseme.render;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.UIManager;

import com.falseme.event.RenderMouseMovedListener;
import com.falseme.gui.Assets;
import com.falseme.user.Config;

public class PlayerRender extends JPanel {
	private static final long serialVersionUID = 1l;

	private static String SKIN_PATH = null;

	private static JPanel renderPanel;
	private static List<Triangle> skin;
//	List<Triangle> skin = RenderLoader.loadSkin(null); // test white render
	private static List<Triangle> armor = new ArrayList<>();
//	private static List<Triangle> armor = RenderLoader.loadArmor();

	private int xRotation = 0;
	private int yRotation = 0;
	private int YPos = 0;
	private int FOV = 115;

	public PlayerRender() {

		// load skin
		SKIN_PATH = Config.getSkinPath();
		if (!SKIN_PATH.endsWith(".png"))
			SKIN_PATH = null;
		skin = RenderLoader.loadSkin(SKIN_PATH);

		setLayout(new BorderLayout());

		// button to import skin
		JButton btn = new JButton("Import skin");
		btn.setBackground(Assets.BACKGROUND_LIGHT_COLOR.brighter());
		btn.setBorder(null);
		btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn.setFont(new Font("Dialog", Font.BOLD, 10));
		int w = 70, h = 20;
		int gap = 20;
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				btn.setBounds(getWidth() - gap - w, getHeight() - gap - h, w, h);
			}
		});
		add(btn);
		btn.addActionListener(e -> {
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			JFileChooser chooser = new JFileChooser(Config.getSkinPath());
			chooser.showOpenDialog(null);
			if (chooser.getSelectedFile() == null)
				return;
			SKIN_PATH = chooser.getSelectedFile().getAbsolutePath();
			Config.setSkinPath(SKIN_PATH);
			loadRender();
		});

		renderPanel = new JPanel() {
			private static final long serialVersionUID = 1l;

			@Override
			public void paint(Graphics g) {

				Graphics2D g2 = (Graphics2D) g;
				g2.setColor(Assets.BACKGROUND_LIGHT_COLOR);
				g2.fillRect(0, 0, getWidth(), getHeight());

				double heading = Math.toRadians(xRotation);
				Matrix4 headingTransform = new Matrix4(new double[] { Math.cos(heading), 0, -Math.sin(heading), 0, 0, 1,
						0, 0, Math.sin(heading), 0, Math.cos(heading), 0, 0, 0, 0, 1 });
				double pitch = Math.toRadians(yRotation);
				Matrix4 pitchTransform = new Matrix4(new double[] { 1, 0, 0, 0, 0, Math.cos(pitch), Math.sin(pitch), 0,
						0, -Math.sin(pitch), Math.cos(pitch), 0, 0, 0, 0, 1 });

				Matrix4 panOutTransform = new Matrix4(
						new double[] { 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, -600, 1 });

				double viewportWidth = getWidth();
				double viewportHeight = getHeight();
				double fovAngle = Math.toRadians(FOV);
				double fov = Math.tan(fovAngle / 2) * 170;

				Matrix4 transform = headingTransform.multiply(pitchTransform).multiply(panOutTransform);

				BufferedImage img = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);

				double[] zBuffer = new double[img.getWidth() * img.getHeight()];
				// initialize array with extremely far away depths
				for (int q = 0; q < zBuffer.length; q++) {
					zBuffer[q] = Double.NEGATIVE_INFINITY;
				}

				ArrayList<Triangle> tris = new ArrayList<>(skin);
				tris.addAll(armor);

				for (Triangle t : tris) {
					Vertex v1 = transform.transform(t.v1);
					Vertex v2 = transform.transform(t.v2);
					Vertex v3 = transform.transform(t.v3);

					v1.y += YPos;
					v2.y += YPos;
					v3.y += YPos;

					Vertex ab = new Vertex(v2.x - v1.x, v2.y - v1.y, v2.z - v1.z, v2.w - v1.w);
					Vertex ac = new Vertex(v3.x - v1.x, v3.y - v1.y, v3.z - v1.z, v3.w - v1.w);
					Vertex norm = new Vertex(ab.y * ac.z - ab.z * ac.y, ab.z * ac.x - ab.x * ac.z,
							ab.x * ac.y - ab.y * ac.x, 1);
					double normalLength = Math.sqrt(norm.x * norm.x + norm.y * norm.y + norm.z * norm.z);
					norm.x /= normalLength;
					norm.y /= normalLength;
					norm.z /= normalLength;

					double angleCos = Math.abs(norm.z);

					v1.x = v1.x / (-v1.z) * fov;
					v1.y = v1.y / (-v1.z) * fov;
					v2.x = v2.x / (-v2.z) * fov;
					v2.y = v2.y / (-v2.z) * fov;
					v3.x = v3.x / (-v3.z) * fov;
					v3.y = v3.y / (-v3.z) * fov;

					v1.x += viewportWidth / 2;
					v1.y += viewportHeight / 2;
					v2.x += viewportWidth / 2;
					v2.y += viewportHeight / 2;
					v3.x += viewportWidth / 2;
					v3.y += viewportHeight / 2;

					int minX = (int) Math.max(0, Math.ceil(Math.min(v1.x, Math.min(v2.x, v3.x))));
					int maxX = (int) Math.min(img.getWidth() - 1, Math.floor(Math.max(v1.x, Math.max(v2.x, v3.x))));
					int minY = (int) Math.max(0, Math.ceil(Math.min(v1.y, Math.min(v2.y, v3.y))));
					int maxY = (int) Math.min(img.getHeight() - 1, Math.floor(Math.max(v1.y, Math.max(v2.y, v3.y))));

					double triangleArea = (v1.y - v3.y) * (v2.x - v3.x) + (v2.y - v3.y) * (v3.x - v1.x);

					for (int y = minY; y <= maxY; y++) {
						for (int x = minX; x <= maxX; x++) {
							double b1 = ((y - v3.y) * (v2.x - v3.x) + (v2.y - v3.y) * (v3.x - x)) / triangleArea;
							double b2 = ((y - v1.y) * (v3.x - v1.x) + (v3.y - v1.y) * (v1.x - x)) / triangleArea;
							double b3 = ((y - v2.y) * (v1.x - v2.x) + (v1.y - v2.y) * (v2.x - x)) / triangleArea;
							if (b1 >= 0 && b1 <= 1 && b2 >= 0 && b2 <= 1 && b3 >= 0 && b3 <= 1) {
								double depth = b1 * v1.z + b2 * v2.z + b3 * v3.z;
								int zIndex = y * img.getWidth() + x;
								if (zBuffer[zIndex] < depth) {
									img.setRGB(x, y, getShade(t.color, angleCos).getRGB());
									zBuffer[zIndex] = depth;
								}
							}
						}
					}

					v1.y -= YPos;
					v2.y -= YPos;
					v3.y -= YPos;

				}

				g2.drawImage(img, 0, 0, null);

				btn.repaint();

			}

		};
		add(renderPanel, BorderLayout.CENTER);

		RenderMouseMovedListener renderMouseListener = new RenderMouseMovedListener(this);
		renderPanel.addMouseListener(renderMouseListener);
		renderPanel.addMouseMotionListener(renderMouseListener);
		renderPanel.addMouseWheelListener(renderMouseListener);
		
		renderPanel.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));

	}

	private static Color getShade(Color color, double shade) {

		double redLinear = Math.pow(color.getRed(), 2.4) * shade;
		double greenLinear = Math.pow(color.getGreen(), 2.4) * shade;
		double blueLinear = Math.pow(color.getBlue(), 2.4) * shade;

		int red = (int) Math.pow(redLinear, 1 / 2.4);
		int green = (int) Math.pow(greenLinear, 1 / 2.4);
		int blue = (int) Math.pow(blueLinear, 1 / 2.4);

		return new Color(red, green, blue);
	}

	public static void loadRender() {

		skin = RenderLoader.loadSkin(SKIN_PATH);
		armor = RenderLoader.loadArmorAssets();

		renderPanel.repaint();

	}

	public void setRotation(int xRot, int yRot) {

		xRotation = xRot;
		yRotation = yRot;

		renderPanel.repaint();

	}

	public int getXRotation() {
		return xRotation;
	}
	
	public int getYRotation() {
		return yRotation;
	}
	
	public void setPos(int ypos) {
		YPos = ypos;
		renderPanel.repaint();
	}
	
	public int getPos() {
		return YPos;
	}
	
	public void setFOV(int fov) {
		FOV = fov;
		renderPanel.repaint();
	}
	
	public int getFOV() {
		return FOV;
	}
	
}

class Vertex {
	double x;
	double y;
	double z;
	double w;

	Vertex(double x, double y, double z, double w) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}
}

class Triangle {
	Vertex v1;
	Vertex v2;
	Vertex v3;
	Color color;

	Triangle(Vertex v1, Vertex v2, Vertex v3, Color color) {
		this.v1 = v1;
		this.v2 = v2;
		this.v3 = v3;
		this.color = color;
	}
}

class Matrix4 {
	double[] values;

	Matrix4(double[] values) {
		this.values = values;
	}

	Matrix4 multiply(Matrix4 other) {
		double[] result = new double[16];
		for (int row = 0; row < 4; row++) {
			for (int col = 0; col < 4; col++) {
				for (int i = 0; i < 4; i++) {
					result[row * 4 + col] += this.values[row * 4 + i] * other.values[i * 4 + col];
				}
			}
		}
		return new Matrix4(result);
	}

	Vertex transform(Vertex in) {
		return new Vertex(in.x * values[0] + in.y * values[4] + in.z * values[8] + in.w * values[12],
				in.x * values[1] + in.y * values[5] + in.z * values[9] + in.w * values[13],
				in.x * values[2] + in.y * values[6] + in.z * values[10] + in.w * values[14],
				in.x * values[3] + in.y * values[7] + in.z * values[11] + in.w * values[15]);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int row = 0; row < 4; row++) {
			for (int col = 0; col < 4; col++) {
				sb.append(values[row * 4 + col]);
				if (col != 3) {
					sb.append(",");
				}
			}
			if (row != 3) {
				sb.append(";\n ");
			}
		}
		sb.append("]");
		return sb.toString();
	}
}
