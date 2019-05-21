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