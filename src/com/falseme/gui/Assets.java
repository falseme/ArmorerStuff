package com.falseme.gui;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Assets {

	public static BufferedImage[][] ARMOR = new BufferedImage[6][2];
	public static BufferedImage[][] ITEM_ARMOR = new BufferedImage[6][4];
	public static BufferedImage[] ARMOR_FORM = new BufferedImage[4];

	public static BufferedImage[] LEATHER_OVERLAY = new BufferedImage[2];
	public static Color[][] leatherColorPalette = new Color[17][6];
	public static Color[][] leatherPalette = new Color[2][6];

	public static BufferedImage ITEMBOX;
	public static BufferedImage ITEMLABEL;

	public static BufferedImage[] RUNE = new BufferedImage[11];
	public static BufferedImage RUNE_FORM;
	public static BufferedImage[] MATERIAL = new BufferedImage[10];
	public static BufferedImage MATERIAL_FORM;

	public static BufferedImage[][] ARMOR_TRIM = new BufferedImage[11][2];

	public static BufferedImage[] DYES = new BufferedImage[16];

	public static Color[] trimPalette = new Color[8];
	public static Color[][] trimColor = new Color[10][8];

	public static BufferedImage X;

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

		ARMOR[5][0] = Loader.loadPng("/assets/armor/leather1.png");
		ARMOR[5][1] = Loader.loadPng("/assets/armor/leather2.png");

		LEATHER_OVERLAY[0] = Loader.loadPng("/assets/armor/leatherOV1.png");
		LEATHER_OVERLAY[1] = Loader.loadPng("/assets/armor/leatherOV2.png");

		leatherColorPalette[16] = Loader.loadPalette("/assets/color_palettes/leather/normal.png");
		leatherPalette[0] = Loader.loadPalette("/assets/color_palettes/leather/leather1.png");
		leatherPalette[1] = Loader.loadPalette("/assets/color_palettes/leather/leather2.png");

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

		ITEM_ARMOR[5][0] = Loader.loadPng("/assets/armor/item/leather_helmet.png");
		ITEM_ARMOR[5][1] = Loader.loadPng("/assets/armor/item/leather_chestplate.png");
		ITEM_ARMOR[5][2] = Loader.loadPng("/assets/armor/item/leather_leggings.png");
		ITEM_ARMOR[5][3] = Loader.loadPng("/assets/armor/item/leather_boots.png");

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
		RUNE_FORM = Loader.loadPng("/assets/runes/form.png");

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
		MATERIAL_FORM = Loader.loadPng("/assets/materials/form.png");

		ARMOR_TRIM[0][0] = Loader.loadPng("/assets/trims/sentry.png");
		ARMOR_TRIM[0][1] = Loader.loadPng("/assets/trims/sentry_leggings.png");
		ARMOR_TRIM[1][0] = Loader.loadPng("/assets/trims/dune.png");
		ARMOR_TRIM[1][1] = Loader.loadPng("/assets/trims/dune_leggings.png");
		ARMOR_TRIM[2][0] = Loader.loadPng("/assets/trims/coast.png");
		ARMOR_TRIM[2][1] = Loader.loadPng("/assets/trims/coast_leggings.png");
		ARMOR_TRIM[3][0] = Loader.loadPng("/assets/trims/wild.png");
		ARMOR_TRIM[3][1] = Loader.loadPng("/assets/trims/wild_leggings.png");
		ARMOR_TRIM[4][0] = Loader.loadPng("/assets/trims/tide.png");
		ARMOR_TRIM[4][1] = Loader.loadPng("/assets/trims/tide_leggings.png");
		ARMOR_TRIM[5][0] = Loader.loadPng("/assets/trims/ward.png");
		ARMOR_TRIM[5][1] = Loader.loadPng("/assets/trims/ward_leggings.png");
		ARMOR_TRIM[6][0] = Loader.loadPng("/assets/trims/vex.png");
		ARMOR_TRIM[6][1] = Loader.loadPng("/assets/trims/vex_leggings.png");
		ARMOR_TRIM[7][0] = Loader.loadPng("/assets/trims/rib.png");
		ARMOR_TRIM[7][1] = Loader.loadPng("/assets/trims/rib_leggings.png");
		ARMOR_TRIM[8][0] = Loader.loadPng("/assets/trims/snout.png");
		ARMOR_TRIM[8][1] = Loader.loadPng("/assets/trims/snout_leggings.png");
		ARMOR_TRIM[9][0] = Loader.loadPng("/assets/trims/eye.png");
		ARMOR_TRIM[9][1] = Loader.loadPng("/assets/trims/eye_leggings.png");
		ARMOR_TRIM[10][0] = Loader.loadPng("/assets/trims/spire.png");
		ARMOR_TRIM[10][1] = Loader.loadPng("/assets/trims/spire_leggings.png");

		trimColor[0] = Loader.loadPalette("/assets/color_palettes/iron.png");
		trimColor[1] = Loader.loadPalette("/assets/color_palettes/copper.png");
		trimColor[2] = Loader.loadPalette("/assets/color_palettes/gold.png");
		trimColor[3] = Loader.loadPalette("/assets/color_palettes/lapis.png");
		trimColor[4] = Loader.loadPalette("/assets/color_palettes/emerald.png");
		trimColor[5] = Loader.loadPalette("/assets/color_palettes/diamond.png");
		trimColor[6] = Loader.loadPalette("/assets/color_palettes/netherite.png");
		trimColor[7] = Loader.loadPalette("/assets/color_palettes/redstone.png");
		trimColor[8] = Loader.loadPalette("/assets/color_palettes/amethyst.png");
		trimColor[9] = Loader.loadPalette("/assets/color_palettes/quartz.png");

		trimPalette = Loader.loadPalette("/assets/color_palettes/trim.png");

		DYES[0] = Loader.loadPng("/assets/dyes/white_dye.png");
		DYES[1] = Loader.loadPng("/assets/dyes/orange_dye.png");
		DYES[2] = Loader.loadPng("/assets/dyes/magenta_dye.png");
		DYES[3] = Loader.loadPng("/assets/dyes/light_blue_dye.png");
		DYES[5] = Loader.loadPng("/assets/dyes/yellow_dye.png");
		DYES[4] = Loader.loadPng("/assets/dyes/lime_dye.png");
		DYES[6] = Loader.loadPng("/assets/dyes/pink_dye.png");
		DYES[7] = Loader.loadPng("/assets/dyes/gray_dye.png");
		DYES[8] = Loader.loadPng("/assets/dyes/light_gray_dye.png");
		DYES[9] = Loader.loadPng("/assets/dyes/cyan_dye.png");
		DYES[10] = Loader.loadPng("/assets/dyes/purple_dye.png");
		DYES[11] = Loader.loadPng("/assets/dyes/blue_dye.png");
		DYES[12] = Loader.loadPng("/assets/dyes/brown_dye.png");
		DYES[13] = Loader.loadPng("/assets/dyes/green_dye.png");
		DYES[14] = Loader.loadPng("/assets/dyes/red_dye.png");
		DYES[15] = Loader.loadPng("/assets/dyes/black_dye.png");

		X = Loader.loadPng("/assets/x.png");

	}

}
