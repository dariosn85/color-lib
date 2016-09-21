package com.snajder.d.colorlib.util;

/**
 * Utility class to keep matrices used for {@link XYZ} conversions.
 */
public final class XYZMatrices {
	/**
	 * SRGB matrix, reference white point D65.
	 */
	public static final float[][] SRGB = new float[3][3];

	static {
		// reference white point D65
		SRGB[0][0] = 0.4124564f;
		SRGB[0][1] = 0.3575761f;
		SRGB[0][2] = 0.1804375f;

		SRGB[1][0] = 0.2126729f;
		SRGB[1][1] = 0.7151522f;
		SRGB[1][2] = 0.0721750f;

		SRGB[2][0] = 0.0193339f;
		SRGB[2][1] = 0.1191920f;
		SRGB[2][2] = 0.9503041f;
	}
}
