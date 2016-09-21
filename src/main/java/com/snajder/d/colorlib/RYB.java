package com.snajder.d.colorlib;

/**
 * Represents the RYB color (R-red, Y-yellow, B-blue). RYB colors are primarily
 * used in art, design and fashion.
 * <p>
 * All three values should be in between 0 and 255.
 * </p>
 * <p>
 * To create RYB instance use {@link RYB#RYB(int, int, int)} or
 * {@link RYB#from(int, int, int)}.
 * </p>
 * <p>
 * All the conversions are done based on <i>RYB Color Compositing</i>
 * <a href="http://nishitalab.org/user/UEI/publication/Sugita_IWAIT2015.pdf">
 * article</a> (authors: <i>Junichi Sugita</i>, <i>Tokiichiro Takahashi</i>).
 * </p>
 * 
 * @see {@link RGB}
 * @see <a href="https://en.wikipedia.org/wiki/RYB_color_model">RYB on Wiki</a>
 */
public class RYB {
	private int r;
	private int y;
	private int b;

	/**
	 * Constructs RYB color.
	 * 
	 * @param r
	 *            - Red value
	 * @param y
	 *            - Yellow value
	 * @param b
	 *            - Blue value
	 */
	public RYB(int r, int y, int b) {
		this.r = r;
		this.y = y;
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
	 * Gets yellow component.
	 * 
	 * @return yellow component
	 */
	public int getY() {
		return y;
	}

	/**
	 * Sets yellow component.
	 * 
	 * @param y
	 *            - yellow component value
	 */
	public void setY(int y) {
		this.y = y;
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
	 * Creates RYB from specified HSLRYB.
	 * 
	 * @param hsl
	 *            - The HSLRYB value
	 * @return the RYB values
	 * @see {@link HSLRYB}
	 */
	public static RYB from(HSLRYB hsl) {
		RGB ryb = RGB.from(hsl);
		return new RYB(ryb.getR(), ryb.getG(), ryb.getB());
	}

	/**
	 * Creates {@link RYB} based on specified {@link RGB} value.
	 * <p>
	 * All the conversions are done based on <i>RYB Color Compositing</i>
	 * <a href=
	 * "http://nishitalab.org/user/UEI/publication/Sugita_IWAIT2015.pdf">
	 * article</a> (authors: <i>Junichi Sugita</i>, <i>Tokiichiro Takahashi</i>
	 * ).
	 * </p>
	 * 
	 * @param rgb
	 *            the RGB value
	 *
	 * @return the RYB value
	 * @see {@link RGB}
	 */
	public static final RYB from(RGB rgb) {
		int r = rgb.getR();
		int g = rgb.getG();
		int b = rgb.getB();

		// normalize RGB
		float rn = (float) r / 255f;
		float gn = (float) g / 255f;
		float bn = (float) b / 255f;

		// remove whiteness
		float whiteComponent = Math.min(Math.min(rn, gn), bn);

		float rr = (float) rn - whiteComponent;
		float gg = (float) gn - whiteComponent;
		float bb = (float) bn - whiteComponent;

		// calculate RYB
		float rr2 = rr - Math.min(rr, gg);
		float yy2 = (gg + Math.min(rr, gg)) / 2f;
		float bb2 = (bb + gg - Math.min(rr, gg)) / 2f;

		// normalize RYB values
		float n = Math.max(Math.max(rr2, yy2), bb2) / Math.max(Math.max(rr, gg), bb);

		// check if there is NaN
		n = Float.isNaN(n) ? 0 : n;

		rr2 /= n;
		yy2 /= n;
		bb2 /= n;

		// add black component
		float blackComonent = Math.min(Math.min(1f - rn, 1f - gn), 1f - bn);

		rr2 = Float.isNaN(rr2) ? 0 : rr2;
		yy2 = Float.isNaN(yy2) ? 0 : yy2;
		bb2 = Float.isNaN(bb2) ? 0 : bb2;

		rr2 += blackComonent;
		yy2 += blackComonent;
		bb2 += blackComonent;

		return new RYB(Math.round(rr2 * 255f), Math.round(yy2 * 255f), Math.round(bb2 * 255f));
	}

	/**
	 * Creates instance of {@link RYB} based on specified values.
	 * 
	 * @param r
	 *            - the red component value
	 * @param y
	 *            - the yellow component value
	 * @param b
	 *            - the blue component value
	 * @return instance of {@link RYB}
	 */
	public static RYB from(int r, int y, int b) {
		return new RYB(r, y, b);
	}

	@Override
	public String toString() {
		return "[r=" + r + ", y=" + y + ", b=" + b + "]";
	}
}
