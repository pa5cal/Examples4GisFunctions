package org.neis_one.geo.file.geojson;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.geotools.feature.FeatureCollection;
import org.geotools.geojson.feature.FeatureJSON;
import org.opengis.feature.Feature;
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
			featureCollection = new FeatureJSON().readFeatureCollection(new FileInputStream(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return featureCollection;
	}
}