package com.falseme.gui;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Assets {

	public static BufferedImage[][] ARMOR = new BufferedImage[5][2];
	public static BufferedImage[][] ITEM_ARMOR = new BufferedImage[5][4];
	public static BufferedImage[] ARMOR_FORM = new BufferedImage[4];

	public static BufferedImage ITEMBOX;
	public static BufferedImage ITEMLABEL;

	public static BufferedImage[] RUNE = new BufferedImage[11];
	public static BufferedImage[] MATERIAL = new BufferedImage[10];

	// colors
	public static Color BACKGROUND_COLOR = new Color(69, 69, 69);
	public static Color BACKGROUND_LIGHT_COLOR = new Color(80, 80, 80);

	public static void load() {

		ARMOR[0][0] = Loader.loadPng("/assets/armor/chainmail1.png");
		ARMOR[0][1] = Loader.loadPng("/assets/armor/chainmail2.png");

		ARMOR[1][0] = Loader.loadPng("/assets/armor/iron1.png");
		ARMOR[1][1] = Loader.loadPng("/assets/armor/iron2.png");

		ARMOR[2][0] = Loader.loadPng("/assets/armor/gold1.png");
		ARMOR[2][1] = Loader.loadPng("/assets/armor/gold2.png");

		ARMOR[3][0] = Loader.loadPng("/assets/armor/diamond1.png");
		ARMOR[3][1] = Loader.loadPng("/assets/armor/diamond2.png");

		ARMOR[4][0] = Loader.loadPng("/assets/armor/netherite1.png");
		ARMOR[4][1] = Loader.loadPng("/assets/armor/netherite2.png");

		ITEM_ARMOR[0][0] = Loader.loadPng("/assets/armor/item/chainmail_helmet.png");
		ITEM_ARMOR[0][1] = Loader.loadPng("/assets/armor/item/chainmail_chestplate.png");
		ITEM_ARMOR[0][2] = Loader.loadPng("/assets/armor/item/chainmail_leggings.png");
		ITEM_ARMOR[0][3] = Loader.loadPng("/assets/armor/item/chainmail_boots.png");

		ITEM_ARMOR[1][0] = Loader.loadPng("/assets/armor/item/iron_helmet.png");
		ITEM_ARMOR[1][1] = Loader.loadPng("/assets/armor/item/iron_chestplate.png");
		ITEM_ARMOR[1][2] = Loader.loadPng("/assets/armor/item/iron_leggings.png");
		ITEM_ARMOR[1][3] = Loader.loadPng("/assets/armor/item/iron_boots.png");

		ITEM_ARMOR[2][0] = Loader.loadPng("/assets/armor/item/golden_helmet.png");
		ITEM_ARMOR[2][1] = Loader.loadPng("/assets/armor/item/golden_chestplate.png");
		ITEM_ARMOR[2][2] = Loader.loadPng("/assets/armor/item/golden_leggings.png");
		ITEM_ARMOR[2][3] = Loader.loadPng("/assets/armor/item/golden_boots.png");

		ITEM_ARMOR[3][0] = Loader.loadPng("/assets/armor/item/diamond_helmet.png");
		ITEM_ARMOR[3][1] = Loader.loadPng("/assets/armor/item/diamond_chestplate.png");
		ITEM_ARMOR[3][2] = Loader.loadPng("/assets/armor/item/diamond_leggings.png");
		ITEM_ARMOR[3][3] = Loader.loadPng("/assets/armor/item/diamond_boots.png");

		ITEM_ARMOR[4][0] = Loader.loadPng("/assets/armor/item/netherite_helmet.png");
		ITEM_ARMOR[4][1] = Loader.loadPng("/assets/armor/item/netherite_chestplate.png");
		ITEM_ARMOR[4][2] = Loader.loadPng("/assets/armor/item/netherite_leggings.png");
		ITEM_ARMOR[4][3] = Loader.loadPng("/assets/armor/item/netherite_boots.png");

		ARMOR_FORM[0] = Loader.loadPng("/assets/armor/form/helmet.png");
		ARMOR_FORM[1] = Loader.loadPng("/assets/armor/form/chestplate.png");
		ARMOR_FORM[2] = Loader.loadPng("/assets/armor/form/leggings.png");
		ARMOR_FORM[3] = Loader.loadPng("/assets/armor/form/boots.png");

		ITEMBOX = Loader.loadPng("/assets/gui/box.png");
		ITEMLABEL = Loader.loadPng("/assets/gui/label.png");

		RUNE[0] = Loader.loadPng("/assets/runes/sentry.png");
		RUNE[1] = Loader.loadPng("/assets/runes/dune.png");
		RUNE[2] = Loader.loadPng("/assets/runes/coast.png");
		RUNE[3] = Loader.loadPng("/assets/runes/wild.png");
		RUNE[4] = Loader.loadPng("/assets/runes/tide.png");
		RUNE[5] = Loader.loadPng("/assets/runes/ward.png");
		RUNE[6] = Loader.loadPng("/assets/runes/vex.png");
		RUNE[7] = Loader.loadPng("/assets/runes/rib.png");
		RUNE[8] = Loader.loadPng("/assets/runes/snout.png");
		RUNE[9] = Loader.loadPng("/assets/runes/eye.png");
		RUNE[10] = Loader.loadPng("/assets/runes/spire.png");

		MATERIAL[0] = Loader.loadPng("/assets/materials/iron.png");
		MATERIAL[1] = Loader.loadPng("/assets/materials/copper.png");
		MATERIAL[2] = Loader.loadPng("/assets/materials/gold.png");
		MATERIAL[3] = Loader.loadPng("/assets/materials/lapis.png");
		MATERIAL[4] = Loader.loadPng("/assets/materials/emerald.png");
		MATERIAL[5] = Loader.loadPng("/assets/materials/diamond.png");
		MATERIAL[6] = Loader.loadPng("/assets/materials/netherite.png");
		MATERIAL[7] = Loader.loadPng("/assets/materials/redstone.png");
		MATERIAL[8] = Loader.loadPng("/assets/materials/amethyst.png");
		MATERIAL[9] = Loader.loadPng("/assets/materials/quartz.png");

	}

}
