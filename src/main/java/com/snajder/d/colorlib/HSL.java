package com.snajder.d.colorlib;

/**
 * Represents the HSL color (H-hue, S-saturation, L-lightness).
 * <p>
 * <b>Hue</b> is in degrees and should be between 0 and 360. <b>Saturation</b>
 * and <b>Lightness</b> are in percents and should be between 0 and 1.
 * </p>
 * <p>
 * To create HSL instance use {@link HSL#HSL(float, float, float)} or
 * {@link HSL#from(float, float, float)}.
 * </p>
 * 
 * @see <a href="https://en.wikipedia.org/wiki/HSL_and_HSV">HSL on Wiki</a>
 */
public class HSL {
	private float h;
	private float s;
	private float l;

	/**
	 * Constructs HSL color.
	 * 
	 * @param h
	 *            - Hue value
	 * @param s
	 *            - Saturation value
	 * @param l
	 *            - Lightness value
	 */
	public HSL(float h, float s, float l) {
		this.h = h;
		this.s = s;
		this.l = l;
	}

	/**
	 * Gets the Hue.
	 * <p>
	 * Hue is between 0 and 360 degrees.
	 * </p>
	 * 
	 * @return the hue
	 */
	public float getH() {
		return h;
	}

	/**
	 * Sets the specified hue value.
	 * <p>
	 * NOTE: Hue is automatically converted into range between 0 and 360
	 * degrees.
	 * </p>
	 * 
	 * @param h
	 *            - the hue
	 */
	public void setH(float h) {
		this.h = h;
		addH(0f);
	}

	/**
	 * Gets the saturation value.
	 * 
	 * @return the saturation between 0 and 1
	 */
	public float getS() {
		return s;
	}

	/**
	 * Sets the saturation value.
	 * 
	 * @param s
	 *            - saturation between 0 and 1
	 */
	public void setS(float s) {
		this.s = s;
	}

	/**
	 * Gets the lightness value.
	 * 
	 * @return the lightness value between 0 and 1
	 */
	public float getL() {
		return l;
	}

	/**
	 * Sets the lightness value.
	 * 
	 * @param l
	 *            - lightness between 0 and 1
	 */
	public void setL(float l) {
		this.l = l;
	}

	/**
	 * Adds specified hue in degrees to current hue.
	 * 
	 * @param h
	 *            - the hue value to add
	 */
	public void addH(float h) {
		this.h = this.h + h;
		this.h = this.h % 360;
		this.h = this.h < 0 ? this.h + 360f : this.h;
	}

	/**
	 * Creates {@link HSL} from specified RGB value.
	 * 
	 * @param rgb
	 *            - The {@link RGB} value
	 * @return the constructed {@link HSL}
	 */
	public static HSL from(RGB rgb) {
		return from(rgb.getR(), rgb.getG(), rgb.getB());
	}

	/**
	 * Creates {@link HSL} from specified RGB values.
	 * 
	 * @param r
	 *            - The Red component
	 * @param g
	 *            - The Green component
	 * @param b
	 *            - The Blue component
	 * @return the constructed {@link HSL}
	 */
	public static HSL from(int r, int g, int b) {
		float h = 0;
		float s = 0;
		float l = 0;
		float r_norm = (float) r / 255f;
		float g_norm = (float) g / 255f;
		float b_norm = (float) b / 255f;

		// max/min values
		float max = Math.max(Math.max(r_norm, g_norm), b_norm);
		float min = Math.min(Math.min(r_norm, g_norm), b_norm);

		float delta = max - min;

		float h_temp = 0;

		if (delta == 0f) {
			h_temp = 0; // undefined
		} else if (max == r_norm) {
			h_temp = ((g_norm - b_norm) / delta) % 6;
		} else if (max == g_norm) {
			h_temp = ((b_norm - r_norm) / delta) + 2;
		} else if (max == b_norm) {
			h_temp = ((r_norm - g_norm) / delta) + 4;
		}

		h = h_temp * 60f;

		h = h < 0 ? h + 360 : h;

		l = 0.5f * (min + max);

		if (delta == 0f) {
			s = 0;
		} else {
			s = delta / (1 - Math.abs(2 * l - 1));
		}

		return new HSL(h, s, l);
	}

	/**
	 * Creates instance of {@link HSL} based on specified values.
	 * 
	 * @param h
	 *            - the Hue value
	 * @param s
	 *            - the Saturation value
	 * @param l
	 *            - the Lightness value
	 * @return instance of {@link RGB}
	 */
	public static HSL from(float h, float s, float l) {
		return new HSL(h, s, l);
	}

	@Override
	public String toString() {
		return "[h=" + h + ", s=" + s + ", l=" + l + "]";
	}
}
