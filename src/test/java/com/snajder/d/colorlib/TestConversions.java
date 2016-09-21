package com.snajder.d.colorlib;

public class TestConversions {
	public static void main(String[] args) {
		RGB rgb = RGB.from(255, 0, 0); // red
		System.out.println(rgb);

		HSL hsl = HSL.from(rgb);
		System.out.println(hsl);

		RYB ryb = RYB.from(rgb);
		System.out.println(ryb);

		XYZ xyz = XYZ.from(rgb);
		System.out.println(xyz);

		CIELab cieLab = CIELab.from(xyz);
		System.out.println(cieLab);

		RGB complementary = ColorSchemeRYB.getComplementary(rgb);
		System.out.println(complementary); // should be green
	}
}
