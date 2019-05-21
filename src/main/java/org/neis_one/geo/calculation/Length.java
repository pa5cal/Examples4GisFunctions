package org.neis_one.geo.calculation;

import org.geotools.referencing.GeodeticCalculator;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.LineString;

public class Length {

	/**
	 * Calculate length of linestring (WGS84) in [meter].
	 */
	public static double calculate(LineString line) {
		double length = 0;
		GeodeticCalculator calculator = new GeodeticCalculator(DefaultGeographicCRS.WGS84);
		Coordinate[] coordinates = line.getCoordinates();
		for (int c = 0; c < coordinates.length - 1; c++) {
			calculator.setStartingGeographicPoint(coordinates[c].x, coordinates[c].y);
			calculator.setDestinationGeographicPoint(coordinates[c + 1].x, coordinates[c + 1].y);
			length += calculator.getOrthodromicDistance();
		}
		return length;
	}
}