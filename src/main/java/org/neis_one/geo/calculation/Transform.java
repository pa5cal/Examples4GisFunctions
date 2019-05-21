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

public class Transform {

	public static Geometry fromWgs84toUTM32(Geometry geom) {
		try {
			CoordinateReferenceSystem equalAreaCRS = CRS.decode("EPSG:25832");
			MathTransform transform = CRS.findMathTransform(DefaultGeographicCRS.WGS84, equalAreaCRS, true);
			geom = JTS.transform(geom, transform);
		} catch (FactoryException | MismatchedDimensionException | TransformException e) {
			e.printStackTrace();
		}
		return geom;
	}
}