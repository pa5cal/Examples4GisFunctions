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
package org.neis_one.geo.file.geojson;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.geotools.feature.FeatureCollection;
import org.geotools.geojson.feature.FeatureJSON;
import org.opengis.feature.Feature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.feature.type.FeatureType;

/**
 * Reads data from a Geojson file.
 * 
 * @author pa5cal
 */
public class ReadGeojsonWithGeotools {

	/**
	 * Returns {@link FeatureCollection} of the geojson file.
	 */
	@SuppressWarnings("unchecked")
	public static FeatureCollection<FeatureType, Feature> get(String filename) {
		FeatureCollection<FeatureType, Feature> featureCollection = null;
		try {
			File file = new File(filename);
			FeatureJSON featureJSON = new FeatureJSON();
			// Read full feature schema
			SimpleFeatureType featureschema = featureJSON.readFeatureCollectionSchema(new FileInputStream(file), false);
			featureJSON.setFeatureType(featureschema);
			// Read feature collection of json file
			featureCollection = featureJSON.readFeatureCollection(new FileInputStream(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return featureCollection;
	}
}