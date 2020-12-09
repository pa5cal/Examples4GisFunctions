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
package org.neis_one.geo.file.csv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.geotools.data.DataUtilities;
import org.geotools.feature.SchemaException;
import org.geotools.feature.simple.SimpleFeatureBuilder;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.locationtech.jts.geom.Coordinate;
import org.opengis.feature.simple.SimpleFeature;

/**
 * Reads data from a csv file with Geotools library.
 * 
 * @author pa5cal
 */
public class ReadCsvWithGeotools {

	/**
	 * Returns content of the csv file.
	 */
	public static List<SimpleFeature> get(String filename) {
		var features = new ArrayList<SimpleFeature>();
		try {
			final var TYPE = DataUtilities.createType("Location", "the_geom:Point:srid=4326,name:String,number:Integer");
			// Read file
			var geometryFactory = JTSFactoryFinder.getGeometryFactory();
			var featureBuilder = new SimpleFeatureBuilder(TYPE);
			try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
				var line = reader.readLine();
				for (line = reader.readLine(); line != null; line = reader.readLine()) {
					if (line.trim().length() > 0) {
						// Parse columns
						String tokens[] = line.split("\\;");
						var latitude = getDoubleValue(tokens[8]);
						var longitude = getDoubleValue(tokens[7]);
						var name = tokens[1].trim();
						var number = Integer.parseInt(tokens[0].trim());
						// Build feature
						featureBuilder.add(geometryFactory.createPoint(new Coordinate(longitude, latitude)));
						featureBuilder.add(name);
						featureBuilder.add(number);
						features.add(featureBuilder.buildFeature(String.valueOf(number)));
					}
				}
			}
		} catch (IOException | SchemaException e) {
			e.printStackTrace();
		}
		return features;
	}

	private static double getDoubleValue(String value) {
		double result = 0;
		try {
			result = NumberFormat.getInstance(Locale.getDefault()).parse(value).doubleValue();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}
}