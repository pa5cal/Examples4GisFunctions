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
package org.neis_one.geo.buffer.main;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import org.geotools.data.DataUtilities;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.feature.DefaultFeatureCollection;
import org.geotools.feature.SchemaException;
import org.geotools.feature.simple.SimpleFeatureBuilder;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.operation.union.UnaryUnionOp;
import org.neis_one.geo.calculation.Transform;
import org.neis_one.geo.file.shp.ReadShapefile;
import org.neis_one.geo.file.shp.WriteShapefile;
import org.opengis.feature.simple.SimpleFeatureType;

/**
 * Example: How to buffer a point shapefile and write the results in a
 * shapefile.
 * 
 * @author pa5cal
 */
public class Point {

	public static void main(String[] args) {
		// Read Shapefiles
		final SimpleFeatureSource pointFeatures = new ReadShapefile()
				.get("./src/test/resources/samplepoints/SamplePoints.shp");

		// Transform
		final Collection<Geometry> pointGeometries = Transform.transform(pointFeatures);
		// Buffer
		final Collection<Geometry> geometriesBuffered = pointGeometries.stream().map(g -> g.buffer(1000))
				.collect(Collectors.toList());
		// Union
		UnaryUnionOp union = new UnaryUnionOp(geometriesBuffered);
		Geometry result = union.union();

		// Create featurecollection
		Optional<SimpleFeatureSource> featureSource = createFeatureSource(Transform.fromUTM32toWgs84(result));

		// Write Shapefile
		featureSource.ifPresent(f -> new WriteShapefile().saveToFile(f, "./src/main/resources/pointbuffer.shp"));
	}

	private static Optional<SimpleFeatureSource> createFeatureSource(Geometry result) {
		SimpleFeatureSource featureSource = null;
		try {
			SimpleFeatureType featureType = DataUtilities.createType("Pointbuffer",
					"geom:MultiPolygon:srid=4326,id:String");
			DefaultFeatureCollection featureCollection = new DefaultFeatureCollection("internal", featureType);
			featureCollection.add(SimpleFeatureBuilder.build(featureType, new Object[] { result, "1" }, "1"));
			featureSource = DataUtilities.source(featureCollection);
		} catch (SchemaException e) {
			e.printStackTrace();
		}
		return Optional.ofNullable(featureSource);
	}
}