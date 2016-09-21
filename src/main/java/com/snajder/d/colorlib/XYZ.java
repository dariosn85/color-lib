package com.snajder.d.colorlib;

import com.snajder.d.colorlib.util.XYZMatrices;

/**
 * Represents the XYZ color. XYZ was introduced by International Commission on
 * Illumination.
 * <p>
 * All three values should be between 0 and 1.
 * </p>
 * 
 * @see <a href="https://en.wikipedia.org/wiki/CIE_1931_color_space">XYZ on
 *      Wiki</a>
 */
public class XYZ {
	private float x;
	private float y;
	private float z;

	/**
	 * Constructs XYZ color.
	 * 
	 * @param x
	 *            - X value
	 * @param y
	 *            - Y value
	 * @param z
	 *            - Z value
	 */
	public XYZ(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * Gets X component.
	 * 
	 * @return X component
	 */
	public float getX() {
		return x;
	}

	/**
	 * Sets X component.
	 * 
	 * @param x
	 *            - X component value
	 */
	public void setX(float x) {
		this.x = x;
	}

	/**
	 * Gets Y component.
	 * 
	 * @return Y component
	 */
	public float getY() {
		return y;
	}

	/**
	 * Sets Y component.
	 * 
	 * @param y
	 *            - Y component value
	 */
	public void setY(float y) {
		this.y = y;
	}

	/**
	 * Gets Z component.
	 * 
	 * @return Z component
	 */
	public float getZ() {
		return z;
	}

	/**
	 * Sets Z component.
	 * 
	 * @param z
	 *            - Z component value
	 */
	public void setZ(float z) {
		this.z = z;
	}

	/**
	 * Creates {@link XYZ} based on specified {@link RGB} value.
	 * 
	 * @param rgb
	 *            the RGB value
	 *
	 * @return the XYZ value
	 * @see {@link RGB}
	 * @see <a href=
	 *      "http://www.brucelindbloom.com/index.html?Eqn_RGB_to_XYZ.html">RGB
	 *      to XYZ conversion</a>
	 */
	public static XYZ from(RGB rgb) {
		float r = inverseCompanding(rgb.getR() / 255f);
		float g = inverseCompanding(rgb.getG() / 255f);
		float b = inverseCompanding(rgb.getB() / 255f);

		float[][] M = XYZMatrices.SRGB;

		float x = M[0][0] * r + M[0][1] * g + M[0][2] * b;
		float y = M[1][0] * r + M[1][1] * g + M[1][2] * b;
		float z = M[2][0] * r + M[2][1] * g + M[2][2] * b;
		return new XYZ(x, y, z);
	}

	/**
	 * Calculates inverse companding.
	 * 
	 * @param v
	 *            - the value
	 * @return the value
	 * @see <a href=
	 *      "http://www.brucelindbloom.com/index.html?Eqn_RGB_to_XYZ.html">RGB
	 *      to XYZ conversion</a>
	 */
	private static float inverseCompanding(float v) {
		if (v > 0.04045) {
			return (float) Math.pow((v + 0.055f) / 1.055f, 2.4f);
		} else {
			return v / 12.92f;
		}
	}

	@Override
	public String toString() {
		return "[x=" + x + ", y=" + y + ", z=" + z + "]";
	}
}
