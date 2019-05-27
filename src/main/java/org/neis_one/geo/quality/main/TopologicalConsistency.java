package org.neis_one.geo.quality.main;

import java.io.IOException;

import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.data.simple.SimpleFeatureSource;
import org.locationtech.jts.geom.Geometry;
import org.neis_one.geo.check.Topology;
import org.neis_one.geo.file.shp.ReadShapefile;
import org.opengis.feature.simple.SimpleFeature;

/**
 * 
 * Example: Check Topological Consistency of all shapefile features. (Logical
 * consistency)
 * 
 * @author pa5cal
 *
 */
public class TopologicalConsistency {

	public static void main(String[] args) {
		// Read Shapefile
		SimpleFeatureSource lineFeatures = new ReadShapefile().get("./src/test/resources/samplelines/SampleLines.shp");

		// Check topology
		int validFeatures = 0;
		int features = 0;
		try {
			SimpleFeatureIterator iter = lineFeatures.getFeatures().features();
			while (iter.hasNext()) {
				features++;
				SimpleFeature line = iter.next();
				Geometry geometry = (Geometry) line.getDefaultGeometry();
				validFeatures += Topology.isValid(geometry) && Topology.isSimple(geometry) ? 1 : 0;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Output result
		System.out.println("RESULT");
		System.out.println("Features: " + features);
		System.out.println("Valid: " + validFeatures);
	}
}