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
public class ReadShp {

	/**
	 * Liefert ein {@link SimpleFeatureSource} von dem Shapefile, welches über den
	 * angegeben Pfad gelesen wurde.
	 */
	public SimpleFeatureSource get(String filename) {
		SimpleFeatureSource features = null;
		try {
			// File
			File file = new File(filename);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("url", file.toURI().toURL());

			// Datastore
			DataStore dataStore = DataStoreFinder.getDataStore(map);
			String typeName = dataStore.getTypeNames()[0];

			// Read feature
			features = dataStore.getFeatureSource(typeName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return features;
	}
}