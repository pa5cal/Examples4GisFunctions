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