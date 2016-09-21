package com.snajder.d.colorlib;

/**
 * Represents the CIELab color. CIELab could be used for determining distance
 * between two colors (using deltaE calculation).
 * <p>
 * All three values should be between 0 and 1.
 * </p>
 * 
 * @see <a href="https://en.wikipedia.org/wiki/Lab_color_space">CIELab on
 *      Wiki</a>
 */
public class CIELab {
	public static final float X_N = 95.047f;
	public static final float Y_N = 100.0f;
	public static final float Z_N = 108.883f;

	private static final float C1 = (float) Math.pow(6.0 / 29.0, 3d);
	private static final float C2 = 1f / 3f;
	private static final float C3 = (float) ((1f / 3f) * Math.pow(29f / 6f, 2));
	private static final float C4 = 4f / 29f;

	private float l;
	private float a;
	private float b;

	/**
	 * Constructs CIELab color.
	 * 
	 * @param l
	 *            - L value
	 * @param a
	 *            - A value
	 * @param b
	 *            - B value
	 */
	public CIELab(float l, float a, float b) {
		this.l = l;
		this.a = a;
		this.b = b;
	}

	/**
	 * Gets L component.
	 * 
	 * @return L component
	 */
	public float getL() {
		return l;
	}

	/**
	 * Sets L component.
	 * 
	 * @param l
	 *            - L component value
	 */
	public void setL(float l) {
		this.l = l;
	}

	/**
	 * Gets A component.
	 * 
	 * @return A component
	 */
	public float getA() {
		return a;
	}

	/**
	 * Sets A component.
	 * 
	 * @param a
	 *            - A component value
	 */
	public void setA(float a) {
		this.a = a;
	}

	/**
	 * Gets B component.
	 * 
	 * @return B component
	 */
	public float getB() {
		return b;
	}

	/**
	 * Sets B component.
	 * 
	 * @param b
	 *            - B component value
	 */
	public void setB(float b) {
		this.b = b;
	}

	/**
	 * Calculates deltaE (color difference, color distance) between this and
	 * specified CIELab color.
	 * 
	 * @param lab
	 *            - The CIELab color to which calculate distance
	 * @return the distance
	 * @see <a href="https://en.wikipedia.org/wiki/Color_difference#CIE76">Color
	 *      difference on Wiki</a>
	 */
	public float distance(CIELab lab) {
		float ll = getL() - lab.getL();
		float aa = getA() - lab.getA();
		float bb = getB() - lab.getB();

		float delta_e = (float) Math.sqrt(ll * ll + aa * aa + bb * bb);

		return delta_e;
	}

	/**
	 * Creates CIELab based on specified RGB value.
	 * 
	 * @param rgb
	 *            - the RGB value
	 * @return the CIELab value
	 */
	public static CIELab from(RGB rgb) {
		XYZ xyz = XYZ.from(rgb);

		return from(xyz);
	}

	/**
	 * Creates CIELab based on specified XYZ value.
	 * 
	 * @param xyz
	 *            - the XYZ value
	 * @return the CIELab value
	 */
	public static CIELab from(XYZ xyz) {
		float ll = 116f * f(xyz.getY() / Y_N) - 16f;
		float aa = 500 * (f(xyz.getX() / X_N) - f(xyz.getY() / Y_N));
		float bb = 200 * (f(xyz.getY() / Y_N) - f(xyz.getZ() / Z_N));

		return new CIELab(ll, aa, bb);
	}

	/**
	 * Conversion function used in XYZ to CIELab conversion.
	 * 
	 * @param t
	 *            - the value
	 * @return the value
	 */
	private static float f(float t) {
		if (t > C1) {
			return (float) Math.pow(t, C2);
		} else {
			return C3 * t + C4;
		}
	}

	@Override
	public String toString() {
		return "[l=" + l + ", a=" + a + ", b=" + b + "]";
	}
}
