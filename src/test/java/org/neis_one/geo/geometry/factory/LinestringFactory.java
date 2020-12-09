/*
Copyright 2020 Pascal Neis <neis-one.org>.

This file is part of Examples4GisFunctions (https://github.com/pa5cal)

Examples4GisFunctions is free software: you can redistribute it and/or modify it under 
the terms of the GNU General Public License as published by the Free Software Foundation, 
either version 3 of the License, or (at your option) any later version.

Examples4GisFunctions is distributed in the hope that it will be useful, but WITHOUT 
ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A 
PARTICULAR PURPOSE. See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with this program.
If not, see <http://www.gnu.org/licenses/>.
*/
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