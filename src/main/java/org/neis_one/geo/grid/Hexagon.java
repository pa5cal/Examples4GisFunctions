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
