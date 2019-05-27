package org.neis_one.geo.quality.main;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.data.simple.SimpleFeatureSource;
import org.neis_one.geo.file.shp.ReadShapefile;
import org.opengis.feature.simple.SimpleFeature;

/**
 * 
 * Example: Determine temporal accuracy of all shapefile features.
 * 
 * @author pa5cal
 */
public class TemporalAccuracy {

	public static void main(String[] args) {
		// Read Shapefile
		SimpleFeatureSource lineFeatures = new ReadShapefile().get("./src/test/resources/samplelines/SampleLines.shp");

		// Determine temporal accuracy
		Map<Integer, Integer> temporalAccuracyMap = new HashMap<>();
		try {
			SimpleFeatureIterator iter = lineFeatures.getFeatures().features();
			while (iter.hasNext()) {
				SimpleFeature line = iter.next();
				Object obj = line.getAttribute("tmstmp");
				if (obj instanceof Date) {
					Calendar calendar = Calendar.getInstance();
					calendar.setTime((Date) obj);
					int year = calendar.get(Calendar.YEAR);
					temporalAccuracyMap.put(year, temporalAccuracyMap.getOrDefault(year, 0) + 1);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Output result
		System.out.println("RESULT");
		System.out.println(temporalAccuracyMap);
	}
}