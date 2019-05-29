package org.neis_one.geo.quality.main;

import java.io.IOException;
import java.util.Set;

import org.apache.commons.compress.utils.Sets;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.data.simple.SimpleFeatureSource;
import org.neis_one.geo.file.shp.ReadShapefile;
import org.opengis.feature.simple.SimpleFeature;

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
		SimpleFeatureSource lineFeatures = new ReadShapefile()
				.get("./src/test/resources/samplelines/SampleLinesModifiedCopy.shp");

		// Check domain consistency
		int readFeatures = 0;
		int validFeatures = 0;
		try {
			String key = "highway";
			Set<String> allowedValues = Sets.newHashSet("motorway", "path");
			SimpleFeatureIterator iter = lineFeatures.getFeatures().features();
			while (iter.hasNext()) {
				SimpleFeature line = iter.next();
				// Check values
				String value = line.getAttribute(key).toString();
				if (allowedValues.contains(value)) {
					validFeatures++;
				}
				readFeatures++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Output result
		System.out.println("RESULT");
		System.out.println("Read Features: " + readFeatures);
		System.out.println("Valid Features: " + validFeatures);
	}
}