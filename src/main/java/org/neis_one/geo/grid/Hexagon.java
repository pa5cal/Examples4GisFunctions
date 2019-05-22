package org.neis_one.geo.grid;

import java.awt.Polygon;
import java.io.IOException;

import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.feature.simple.SimpleFeatureTypeBuilder;
import org.geotools.geometry.jts.ReferencedEnvelope;
import org.geotools.grid.Envelopes;
import org.geotools.grid.GridFeatureBuilder;
import org.geotools.grid.Grids;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.opengis.feature.simple.SimpleFeatureType;

/**
 * Creates hexagon grid. More grid examples, see
 * http://docs.geotools.org/latest/userguide/extension/grid.html
 * 
 * @author pa5cal
 */
public class Hexagon {

	public static SimpleFeatureSource create(ReferencedEnvelope gridBounds, double sideLen) {
		return Grids.createHexagonalGrid(gridBounds, sideLen);
	}

	public static SimpleFeatureSource create(SimpleFeatureSource boundary) {
		double sideLen = 1.0;
		ReferencedEnvelope gridBounds = new ReferencedEnvelope(0, 0, 0, 0, DefaultGeographicCRS.WGS84);
		try {
			gridBounds = Envelopes.expandToInclude(boundary.getBounds(), sideLen);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Default Envelope is used: 0 0 0 0");
		}

		// Create a feature type
		SimpleFeatureTypeBuilder tb = new SimpleFeatureTypeBuilder();
		tb.setName("grid");
		tb.add(GridFeatureBuilder.DEFAULT_GEOMETRY_ATTRIBUTE_NAME, Polygon.class,
				gridBounds.getCoordinateReferenceSystem());
		tb.add("id", Integer.class);
		SimpleFeatureType TYPE = tb.buildFeatureType();

		// Build the grid the custom feature builder class
		GridFeatureBuilder builder = new IntersectionBuilder(TYPE, boundary);
		return Grids.createHexagonalGrid(gridBounds, sideLen, -1, builder);
	}
}
