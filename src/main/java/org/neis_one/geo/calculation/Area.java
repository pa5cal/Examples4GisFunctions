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

import org.geotools.geometry.jts.JTS;
import org.geotools.referencing.CRS;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.locationtech.jts.geom.Geometry;
import org.opengis.geometry.MismatchedDimensionException;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.TransformException;

public class Area {

	/**
	 * Calculate area of polygon (WGS84) in [square meters].
	 */
	public static double calculate(Geometry poly) {
		double area = 0;
		try {
			CoordinateReferenceSystem equalAreaCRS = CRS.decode("EPSG:25832");
			MathTransform transform = CRS.findMathTransform(DefaultGeographicCRS.WGS84, equalAreaCRS, true);
			Geometry transformedPolygon = JTS.transform(poly, transform);
			area = transformedPolygon.getArea();
		} catch (FactoryException | MismatchedDimensionException | TransformException e) {
			e.printStackTrace();
		}

		return area;
	}
}