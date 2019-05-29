package org.neis_one.geo.quality.main;

import java.io.IOException;

import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.data.simple.SimpleFeatureSource;
import org.neis_one.geo.file.shp.ReadShapefile;

/**
 * Example: Check Topological Consistency of all shapefile features.<br>
 * "Wertekonsistenz (domain consistency): Einhaltung des Wertebereichs,
 * beispielsweise keine negativen Werte bei einer Bevölkerungskartierung" vgl.
 * https://de.wikipedia.org/wiki/Geodaten#Logische_Konsistenz
 * 
 * @author pa5cal
 */
public class LogicalConsistencyDomainConsistency {

	public static void main(String[] args) {
		// Read Shapefile
		SimpleFeatureSource lineFeatures = new ReadShapefile().get("./src/test/resources/samplelines/SampleLines.shp");

		// Check domain consistency
		int features = 0;
		try {
			SimpleFeatureIterator iter = lineFeatures.getFeatures().features();
			while (iter.hasNext()) {
				features++;
				// SimpleFeature line = iter.next();
				// Check values ...
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Output result
		System.out.println("RESULT");
		System.out.println("Features: " + features);
	}
}