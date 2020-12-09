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
	 * Creates invalid polygon with WGS84 coordinates.
	 */
	public static Geometry createTestObjWgs84Invalid() {
		return createByWkt("POLYGON((8 50, 8.001 50, 7.001 49.999, 8.001 49.999, 8 50))");
	}

	/**
	 * Creates polygon based on WKT definition.
	 */
	public static Geometry createByWkt(String inputWkt) {
		return readWktGeometry(inputWkt, getGeometryFactory().createPolygon());
	}
}