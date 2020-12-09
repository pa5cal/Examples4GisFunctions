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
package org.neis_one.geo.file.shp;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.geotools.data.DataStore;
import org.geotools.data.DataStoreFinder;
import org.geotools.data.simple.SimpleFeatureSource;

/**
 * Reads data from Shapefile.
 * 
 * @author pa5cal
 */
public class ReadShapefile {

	/**
	 * Liefert ein {@link SimpleFeatureSource} von dem Shapefile, welches über
	 * den angegeben Pfad gelesen wurde.
	 */
	public SimpleFeatureSource get(String filename) {
		SimpleFeatureSource features = null;
		try {
			// File
			File file = new File(filename);
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("url", file.toURI().toURL());

			// Datastore
			DataStore dataStore = DataStoreFinder.getDataStore(params);
			String typeName = dataStore.getTypeNames()[0];

			// Read feature
			features = dataStore.getFeatureSource(typeName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return features;
	}
}