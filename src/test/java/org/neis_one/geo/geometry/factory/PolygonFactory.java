package org.neis_one.geo.geometry.factory;

import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.Polygon;

/**
 * Factory for creating {@link Polygon}'s.
 * 
 * @author pa5cal
 */
public class PolygonFactory extends AbstractGeometryFactory {

	/**
	 * Creates polygon with WGS84 coordinates.
	 */
	public static Geometry createTestObjWgs84() {
		return createByWkt("POLYGON((8 50, 8.001 50, 8.001 49.999, 8.001 49.999, 8 50))");
	}

	/**
	 * Creates polygon based on WKT definition.
	 */
	public static Geometry createByWkt(String inputWkt) {
		return readWktGeometry(inputWkt, getGeometryFactory().createPolygon());
	}
}