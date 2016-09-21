package com.snajder.d.colorlib;

import java.util.Arrays;
import java.util.List;

/**
 * Utility class for creating color schemes. Harmonies are calculated based on
 * RYB color wheel, where specified RGB color is implicitly concerted to RYB,
 * harmonies are searched using HSL and at the end RYB is converted back to RGB.
 */
public class ColorSchemeRYB {

	/**
	 * Gets the Triad colors, where pivot is specified RGB color.
	 * 
	 * @param rgb
	 *            - the pivot color
	 * @return the List of three triad colors
	 */
	public static List<RGB> getTriads(RGB rgb) {
		HSLRYB hsl = HSLRYB.from(RYB.from(rgb));

		hsl.addH(120f);

		RGB rgb1 = RGB.from(RYB.from(hsl));

		hsl.addH(120f);

		RGB rgb2 = RGB.from(RYB.from(hsl));

		return Arrays.asList(rgb1, rgb, rgb2);
	}

	/**
	 * Gets the complementary color of specified RGB color.
	 * 
	 * @param rgb
	 *            - the pivot color
	 * @return the complementary color
	 */
	public static RGB getComplementary(RGB rgb) {
		HSLRYB hsl = HSLRYB.from(RYB.from(rgb));
		hsl.addH(180f);

		return RGB.from(RYB.from(hsl));
	}

	/**
	 * Gets the tetradic colors, where pivot is specified RGB color.
	 * 
	 * @param rgb
	 *            - the pivot color
	 * @return the List of three tetradic colors
	 */
	public static List<RGB> getTetradic(RGB rgb) {
		HSLRYB hsl = HSLRYB.from(RYB.from(rgb));

		hsl.addH(45f);

		RGB rgb1 = RGB.from(RYB.from(hsl));

		hsl.addH(135f);

		RGB rgb2 = RGB.from(RYB.from(hsl));

		hsl.addH(45f);

		RGB rgb3 = RGB.from(RYB.from(hsl));

		return Arrays.asList(rgb1, rgb2, rgb3);
	}

	/**
	 * Gets the analogous colors, where pivot is specified RGB color.
	 * 
	 * @param rgb
	 *            - the pivot color
	 * @return the List of three analogous colors
	 */
	public static List<RGB> getAnalogous(RGB rgb) {
		HSLRYB hsl = HSLRYB.from(RYB.from(rgb));

		hsl.addH(-30f); // base - 30

		RGB rgb1 = RGB.from(RYB.from(hsl));

		hsl.addH(60f); // base + 30

		RGB rgb2 = RGB.from(RYB.from(hsl));

		return Arrays.asList(rgb1, rgb, rgb2);
	}

	/**
	 * Gets the monochromatic colors, where pivot is specified RGB color.
	 * 
	 * @param rgb
	 *            - the pivot color
	 * @return the List of three monochromatic colors
	 */
	public static List<RGB> getMonochromatic3(RGB rgb) {
		HSL hsl = HSL.from(rgb);
		hsl.setS(1.0f);

		hsl.setL(0.7f);
		RGB rgb1 = RGB.from(hsl);

		hsl.setL(0.5f);
		RGB rgb2 = RGB.from(hsl);

		hsl.setL(0.3f);
		RGB rgb3 = RGB.from(hsl);

		return Arrays.asList(rgb1, rgb2, rgb3);
	}
}
