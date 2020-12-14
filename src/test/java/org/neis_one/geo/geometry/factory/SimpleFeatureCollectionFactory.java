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

import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.feature.DefaultFeatureCollection;
import org.geotools.feature.simple.SimpleFeatureBuilder;
import org.geotools.feature.simple.SimpleFeatureTypeBuilder;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.MultiPoint;
import org.locationtech.jts.geom.impl.PackedCoordinateSequenceFactory;

/**
 * Factory for creating {@link SimpleFeatureCollection}.
 * 
 * @author pa5cal
 */
public class SimpleFeatureCollectionFactory {

	public static SimpleFeatureCollection create(Coordinate[] pts) {
		// FeatureTypeBuilder
		var tb = new SimpleFeatureTypeBuilder();
		tb.setName("data");
		tb.add("shape", MultiPoint.class);
		tb.add("value", Double.class);

		// FeatureColelction
		var fc = new DefaultFeatureCollection();
		var fb = new SimpleFeatureBuilder(tb.buildFeatureType());
		var factory = new GeometryFactory(new PackedCoordinateSequenceFactory());
		for (var p : pts) {
			fb.add(factory.createPoint(p));
			fc.add(fb.buildFeature(null));
		}
		return fc;
	}
}