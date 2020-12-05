package org.neis_one.geo.quality.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.geotools.data.DataUtilities;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.feature.SchemaException;
import org.geotools.feature.simple.SimpleFeatureBuilder;
import org.locationtech.jts.geom.Geometry;
import org.neis_one.geo.calculation.SphericalDistanceLibrary;
import org.neis_one.geo.file.shp.ReadShapefile;
import org.opengis.feature.simple.SimpleFeature;

/**
 * Example: Calculate PositionalAccuracy.<br>
 * "Positionsgenauigkeit (positional accuracy): Genauigkeit der Lage von
 * Objekten" vgl. https://de.wikipedia.org/wiki/Geodaten#Positionsgenauigkeit
 * 
 * @author pa5cal
 */
public class PositionalAccuracyPoint {

	public static void main(String[] args) {
		// Read Shapefile
		final var pointsA = new ReadShapefile()
				.get("./src/test/resources/samplepoints/SamplePoints.shp");

		// Begin buffering feature collection
		var pointsAbuffered = new ArrayList<SimpleFeature>();
		// Note distance is in same units as geometry
		var bufferInDeegrees = SphericalDistanceLibrary.metersToDegrees(15);
		try (SimpleFeatureIterator iterator = pointsA.getFeatures().features();) {
			final var TYPE = DataUtilities.createType("Buffered", "the_geom:Polygon:srid=4326,id:Integer");
			var featureBuilder = new SimpleFeatureBuilder(TYPE);
			while (iterator.hasNext()) {
				var feature = iterator.next();
				// Buffer geometry
				var geom = ((Geometry) feature.getDefaultGeometry()).buffer(bufferInDeegrees);
				// Build new feature
				featureBuilder.add(geom);
				featureBuilder.add(feature.getAttribute("id"));
				pointsAbuffered.add(featureBuilder.buildFeature(null));
			}
		} catch (IOException | SchemaException e) {
			e.printStackTrace();
		}

		// Intersect
		var featureSize = 0;
		var interesctionCount = 0;
		try {
			featureSize = pointsA.getFeatures().size();
			interesctionCount = countIntersections(pointsA.getFeatures().features(), pointsAbuffered);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Output result
		System.out.println("Read feature : " + featureSize);
		System.out.println("Intersections: " + interesctionCount);
	}

	private static int countIntersections(final SimpleFeatureIterator iterator, final Collection<SimpleFeature> buffers) {
		int count = 0;
		while (iterator.hasNext()) {
			var feature = iterator.next();
			Geometry geomFeature = (Geometry) feature.getDefaultGeometry();
			for (SimpleFeature simpleFeature : buffers) {
				Geometry geomBuffer = (Geometry) simpleFeature.getDefaultGeometry();
				if (geomFeature.within(geomBuffer)) {
					count++;
					break;
				}
			}
		}
		return count;
	}
}