package com.snajder.d.colorlib;

/**
 * HSL values based on RYB values.
 * <p>
 * HSL is primarily used with RGB colors. We are using it with RYB color by
 * passing values as R=R, Y=G, B=B. HSL is handful for calculating color
 * harmonies, because colors in HSL are represented using cylindrical coordinate
 * system.
 * </p>
 */
public class HSLRYB extends HSL {

	public HSLRYB(float h, float s, float l) {
		super(h, s, l);
	}

	/**
	 * Creates HSLRYB from specified RYB.
	 * <p>
	 * HSL is primarily used with RGB colors. We are using it with RYB color by
	 * passing values as R=R, Y=G, B=B. HSL is handful for calculating color
	 * harmonies, because colors in HSL are represented using cylindrical
	 * coordinate system.
	 * </p>
	 * <p>
	 * Using this method, output is HSL where base are RYB values.
	 * 
	 * @param ryb
	 *            - The RYB value
	 * @return the HSLRYB values
	 */
	public static HSLRYB from(RYB ryb) {
		return HSLRYB.from(HSL.from(RGB.from(ryb.getR(), ryb.getY(), ryb.getB())));
	}

	/**
	 * Creates HSLRYB from specified HSL.
	 * 
	 * @param hsl
	 *            - The HSL
	 * @return the HSLRYB
	 */
	private static HSLRYB from(HSL hsl) {
		return new HSLRYB(hsl.getH(), hsl.getS(), hsl.getL());
	}
}
