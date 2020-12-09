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