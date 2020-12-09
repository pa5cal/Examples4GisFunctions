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
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.data.simple.SimpleFeatureSource;
import org.neis_one.geo.file.shp.ReadShapefile;
import org.opengis.feature.simple.SimpleFeature;

/**
 * Example: Determine temporal accuracy of all shapefile features.<br>
 * "Zeitliche Genauigkeit (temporal accuracy): Genauigkeit der Zeitangaben und
 * der zeitlichen Beziehungen von Objekten" vgl.
 * https://de.wikipedia.org/wiki/Geodaten#Zeitliche_Genauigkeit
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