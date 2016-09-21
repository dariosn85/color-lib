package com.snajder.d.colorlib;

/**
 * Represents the RGB color (R-red, G-green, Y-yellow).
 * <p>
 * All three values should be in between 0 and 255.
 * </p>
 * <p>
 * To create RGB instance use {@link RGB#RGB(int, int, int)} or
 * {@link RGB#from(int, int, int)}.
 * </p>
 * 
 * @see <a href="https://en.wikipedia.org/wiki/RGB_color_model">RGB on Wiki</a>
 */
public class RGB {
	private int r;
	private int g;
	private int b;

	/**
	 * Constructs RGB color.
	 * 
	 * @param r
	 *            - Red value
	 * @param g
	 *            - Green value
	 * @param b
	 *            - Blue value
	 */
	public RGB(int r, int g, int b) {
		this.r = r;
		this.g = g;
		this.b = b;
	}

	/**
	 * Gets red component.
	 * 
	 * @return red component
	 */
	public int getR() {
		return r;
	}

	/**
	 * Sets red component.
	 * 
	 * @param r
	 *            - red component value
	 */
	public void setR(int r) {
		this.r = r;
	}

	/**
	 * Gets green component.
	 * 
	 * @return green component
	 */
	public int getG() {
		return g;
	}

	/**
	 * Sets green component.
	 * 
	 * @param g
	 *            - green component value
	 */
	public void setG(int g) {
		this.g = g;
	}

	/**
	 * Gets blue component.
	 * 
	 * @return blue component
	 */
	public int getB() {
		return b;
	}

	/**
	 * Sets blue component.
	 * 
	 * @param b
	 *            - blue component value
	 */
	public void setB(int b) {
		this.b = b;
	}

	/**
	 * Creates {@link RGB} from specified HSL value.
	 * 
	 * @param hsl
	 *            - The {@link HSL} value
	 * @return the constructed {@link RGB}
	 */
	public static RGB from(HSL hsl) {
		return from(hsl.getH(), hsl.getS(), hsl.getL());
	}

	/**
	 * Creates {@link RGB} from specified HSL values.
	 * 
	 * @param h
	 *            - Hue
	 * @param s
	 *            - Saturation
	 * @param l
	 *            - Lightness
	 * @return the constructed {@link RGB}
	 */
	public static RGB from(float h, float s, float l) {
		float c = (1 - Math.abs(2 * l - 1)) * s;
		float x = c * (1 - Math.abs((h / 60f) % 2 - 1));
		float m = l - c / 2f;

		float r_tmp = 0;
		float g_tmp = 0;
		float b_tmp = 0;

		if (h < 60f) {
			r_tmp = c;
			g_tmp = x;
			b_tmp = 0;
		} else if (h < 120) {
			r_tmp = x;
			g_tmp = c;
			b_tmp = 0;
		} else if (h < 180) {
			r_tmp = 0;
			g_tmp = c;
			b_tmp = x;
		} else if (h < 240) {
			r_tmp = 0;
			g_tmp = x;
			b_tmp = c;
		} else if (h < 300) {
			r_tmp = x;
			g_tmp = 0;
			b_tmp = c;
		} else if (h < 360) {
			r_tmp = c;
			g_tmp = 0;
			b_tmp = x;
		}

		int r = Math.round((r_tmp + m) * 255f);
		int g = Math.round((g_tmp + m) * 255f);
		int b = Math.round((b_tmp + m) * 255f);

		return from(r, g, b);
	}

	/**
	 * Creates {@link RGB} based on specified {@link RYB} value.
	 * <p>
	 * All the conversions are done based on <i>RYB Color Compositing</i>
	 * <a href=
	 * "http://nishitalab.org/user/UEI/publication/Sugita_IWAIT2015.pdf">
	 * article</a> (authors: <i>Junichi Sugita</i>, <i>Tokiichiro Takahashi</i>
	 * ).
	 * </p>
	 * 
	 * @param ryb
	 *            the RYB value
	 *
	 * @return the RGB value
	 * @see {@link RYB}
	 */
	public static RGB from(RYB ryb) {
		int r = ryb.getR();
		int y = ryb.getY();
		int b = ryb.getB();

		// normalize RYB
		float rn = (float) r / 255f;
		float yn = (float) y / 255f;
		float bn = (float) b / 255f;

		// remove whiteness
		float whiteComponent = Math.min(Math.min(rn, yn), bn);

		float rr = (float) rn - whiteComponent;
		float yy = (float) yn - whiteComponent;
		float bb = (float) bn - whiteComponent;

		// calculate R' G' B'
		float rr2 = rr + yy - Math.min(yy, bb);
		float gg2 = yy + 2f * Math.min(yy, bb);
		float bb2 = 2f * (bb - Math.min(yy, bb));

		// normalize R' G' B' values
		float n = Math.max(Math.max(rr2, gg2), bb2) / Math.max(Math.max(rr, yy), bb);

		// check if there is NaN
		n = Float.isNaN(n) ? 0 : n;

		rr2 /= n;
		gg2 /= n;
		bb2 /= n;

		// add black component
		float blackComonent = Math.min(Math.min(1f - rn, 1f - yn), 1f - bn);

		rr2 = Float.isNaN(rr2) ? 0 : rr2;
		gg2 = Float.isNaN(gg2) ? 0 : gg2;
		bb2 = Float.isNaN(bb2) ? 0 : bb2;

		rr2 += blackComonent;
		gg2 += blackComonent;
		bb2 += blackComonent;

		return new RGB(Math.round(rr2 * 255f), Math.round(gg2 * 255f), Math.round(bb2 * 255f));
	}

	/**
	 * Creates instance of {@link RGB} based on specified values.
	 * 
	 * @param r
	 *            - the red component value
	 * @param g
	 *            - the green component value
	 * @param b
	 *            - the blue component value
	 * @return instance of {@link RGB}
	 */
	public static RGB from(int r, int g, int b) {
		return new RGB(r, g, b);
	}

	@Override
	public String toString() {
		return "[r=" + r + ", g=" + g + ", b=" + b + "]";
	}
}
