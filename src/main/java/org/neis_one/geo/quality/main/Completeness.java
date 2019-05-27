package org.neis_one.geo.quality.main;

import java.io.IOException;

import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.geometry.jts.Geometries;
import org.locationtech.jts.geom.Geometry;
import org.neis_one.geo.calculation.Length;
import org.neis_one.geo.file.shp.ReadShapefile;
import org.opengis.feature.simple.SimpleFeature;

/**
 * Example: Calculate the total length of all shapefile features.
 * 
 * @author pa5cal
 */
public class Completeness {

	public static void main(String[] args) {
		// Read Shapefile
		SimpleFeatureSource lineFeatures = new ReadShapefile().get("./src/test/resources/samplelines/SampleLines.shp");

		// Calculate total length
		double totalLength = 0;
		int countedFeatures = 0;
		try {
			SimpleFeatureIterator iter = lineFeatures.getFeatures().features();
			while (iter.hasNext()) {
				SimpleFeature line = iter.next();
				Geometry geom = (Geometry) line.getDefaultGeometry();
				switch (Geometries.get(geom)) {
				case LINESTRING:
					totalLength = Length.calculate(geom);
					countedFeatures++;
					break;
				case MULTILINESTRING:
					for (int idx = 0; idx < geom.getNumGeometries(); idx++) {
						totalLength = Length.calculate(geom.getGeometryN(idx));
						countedFeatures++;
					}
					break;
				default:
					System.out.println("Not supported Geometry! " + line.getType());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Output result
		System.out.println("RESULT");
		System.out.println("Features: " + countedFeatures);
		System.out.println("Total length [m]: " + totalLength);
	}
}