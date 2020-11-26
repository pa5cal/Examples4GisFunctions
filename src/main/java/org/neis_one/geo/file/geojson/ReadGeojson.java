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
	 * Returns {@link FeatureCollection} of the geosjon file.
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