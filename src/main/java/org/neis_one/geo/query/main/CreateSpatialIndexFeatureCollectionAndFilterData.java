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
package org.neis_one.geo.query.main;

import java.io.IOException;

import org.geotools.data.DataUtilities;
import org.geotools.data.collection.SpatialIndexFeatureCollection;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.factory.CommonFactoryFinder;
import org.locationtech.jts.geom.Geometry;
import org.neis_one.geo.file.shp.ReadShapefile;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.filter.Filter;
import org.opengis.filter.FilterFactory2;

public class CreateSpatialIndexFeatureCollectionAndFilterData {

	public static void main(String[] args) throws IOException {
		// Read Shapefiles
		SimpleFeatureSource pointFeatures = new ReadShapefile()
				.get("./src/test/resources/samplepoints/SamplePoints.shp");
		SimpleFeatureSource polygonFeatures = new ReadShapefile()
				.get("./src/test/resources/samplepolygons/SamplePolygon.shp");

		// Create SpatialIndexFeatureCollection
		SimpleFeatureCollection indexedPolygonCollection = new SpatialIndexFeatureCollection(
				polygonFeatures.getFeatures());

		// Fast spatial Access
		SimpleFeatureSource source = DataUtilities.source(indexedPolygonCollection);

		// Create Filter
		FilterFactory2 ff = CommonFactoryFinder.getFilterFactory2();
		SimpleFeatureIterator iter = pointFeatures.getFeatures().features();

		// Filter features
		while (iter.hasNext()) {
			SimpleFeature point = iter.next();
			Geometry pointGeometry = (Geometry) point.getDefaultGeometry();
			Filter filter = ff.intersects(ff.property("the_geom"), ff.literal(pointGeometry));
			SimpleFeatureCollection results = source.getFeatures(filter);
			System.out.println(results.size());
		}
	}
}