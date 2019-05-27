package org.neis_one.geo.check;

import org.locationtech.jts.geom.Geometry;

public class Topology {

	/**
	 * Checks if geometry is valid.
	 */
	public static boolean isValid(Geometry geom) {
		return geom.isValid();
	}

	/**
	 * See {@link Geometry#isSimple()}
	 */
	public static boolean isSimple(Geometry geom) {
		return geom.isSimple();
	}
}