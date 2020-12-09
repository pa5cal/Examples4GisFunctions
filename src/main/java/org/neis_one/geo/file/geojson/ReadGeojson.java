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

import org.geojson.FeatureCollection;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Reads data from a Geojson file.
 * 
 * @author pa5cal
 */
public class ReadGeojson {

	/**
	 * Returns {@link FeatureCollection} of the geojson file.
	 */
	public static FeatureCollection get(String filename) {
		FeatureCollection featureCollection = null;
		try {
			// File
			File file = new File(filename);
			featureCollection = new ObjectMapper().readValue(new FileInputStream(file), FeatureCollection.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return featureCollection;
	}
}