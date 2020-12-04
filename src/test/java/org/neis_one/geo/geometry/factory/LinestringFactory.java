package org.neis_one.geo.geometry.factory;

import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.MultiLineString;

/**
 * Factory for creating {@link LineString}'s.
 * 
 * @author pa5cal
 */
public class LinestringFactory extends AbstractGeometryFactory {

	/**
	 * Creates {@link LineString} with WGS84 coordinates.
	 */
	public static LineString createLinestringWgs84() {
		// LINESTRING(Rechts/horizontale Hoch/vertikale, Rechts/horizontale
		// Hoch/vertikale)
		// LINESTRING(West/East North/South, West/East North/South)
		// LINESTRING(X Y, X Y)
		// LINESTRING(Lon Lat, Lon Lat)
		LineString geometryType = getGeometryFactory().createLineString();
		return (LineString) createByWkt("LINESTRING(8 50, 9 50)", geometryType);
	}

	/**
	 * Creates {@link MultiLineString} with WGS84 coordinates.
	 */
	public static MultiLineString createMultiLinestringWgs84() {
		MultiLineString geometryType = getGeometryFactory().createMultiLineString();
		return (MultiLineString) createByWkt("MULTILINESTRING((8 50, 9 50),(9 50, 9 51))", geometryType);
	}

	/**
	 * Creates {@link Geometry} based on WKT definition.
	 */
	public static Geometry createByWkt(String inputWkt, Geometry geometryType) {
		return readWktGeometry(inputWkt, geometryType);
	}
}