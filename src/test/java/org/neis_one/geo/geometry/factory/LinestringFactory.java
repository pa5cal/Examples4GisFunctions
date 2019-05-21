package org.neis_one.geo.geometry.factory;

import org.locationtech.jts.geom.LineString;

/**
 * Factory for creating {@link LineString}'s.
 * 
 * @author pa5cal
 */
public class LinestringFactory extends AbstractGeometryFactory {

	/**
	 * Creates linestring with WGS84 coordinates.
	 */
	public static LineString createTestObjWgs84() {
		return createByWkt("LINESTRING(8 50, 9 50)");
	}

	/**
	 * Creates linestring based on WKT definition.
	 */
	public static LineString createByWkt(String inputWkt) {
		return (LineString) readWktGeometry(inputWkt, getGeometryFactory().createLineString());
	}
}