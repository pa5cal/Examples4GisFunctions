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
package org.neis_one.geo.calculation;

import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.geometry.jts.Geometries;
import org.geotools.referencing.GeodeticCalculator;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.opengis.feature.simple.SimpleFeature;

/**
 * Calculate length of {@link Geometry}.
 * 
 * @author pa5cal
 */
public class Length {

	/**
	 * Calculate length of geometry (WGS84) in [meter].
	 */
	public static double calculate(Geometry geometry) {
		double length = 0;
		GeodeticCalculator calculator = new GeodeticCalculator(DefaultGeographicCRS.WGS84);
		Coordinate[] coordinates = geometry.getCoordinates();
		for (int c = 0; c < coordinates.length - 1; c++) {
			calculator.setStartingGeographicPoint(coordinates[c].x, coordinates[c].y);
			calculator.setDestinationGeographicPoint(coordinates[c + 1].x, coordinates[c + 1].y);
			length += calculator.getOrthodromicDistance();
		}
		return length;
		// Alternative
		// JTS.orthodromicDistance(start, end, crs);
	}

	/**
	 * Calculate length of multigeometry (WGS84) in [meter].
	 */
	public static double calculateByMultiGeometry(Geometry geometry) {
		double totalLength = 0;
		for (int idx = 0; idx < geometry.getNumGeometries(); idx++) {
			totalLength += Length.calculate(geometry.getGeometryN(idx));
		}
		return totalLength;
	}

	/**
	 * Calculate length of SimpleFeatureIterator (WGS84) in [meter].
	 */
	public static double calculate(SimpleFeatureIterator iter) {
		double totalLength = 0;
		while (iter.hasNext()) {
			SimpleFeature line = iter.next();
			Geometry geom = (Geometry) line.getDefaultGeometry();
			switch (Geometries.get(geom)) {
			case LINESTRING:
				totalLength = Length.calculate(geom);
				break;
			case MULTILINESTRING:
				for (int idx = 0; idx < geom.getNumGeometries(); idx++) {
					totalLength = Length.calculate(geom.getGeometryN(idx));
				}
				break;
			default:
				throw new RuntimeException("Not supported Geometry! " + line.getType());
			}
		}
		return totalLength;
	}
}