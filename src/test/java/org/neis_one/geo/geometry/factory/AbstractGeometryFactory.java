package org.neis_one.geo.geometry.factory;

import org.geotools.geometry.jts.JTSFactoryFinder;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;

/**
 * Abstract factory for creating {@link Geometry}'s.
 * 
 * @author pa5cal
 */
public abstract class AbstractGeometryFactory {
	protected static GeometryFactory getGeometryFactory() {
		return JTSFactoryFinder.getGeometryFactory();
	}

	protected static Geometry readWktGeometry(String inputWkt, Geometry outputGeometry) {
		try {
			WKTReader reader = new WKTReader(getGeometryFactory());
			outputGeometry = reader.read(inputWkt);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return outputGeometry;
	}
}
