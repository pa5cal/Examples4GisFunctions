package org.neis_one.geo.file.geojson;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.geotools.feature.FeatureCollection;
import org.junit.jupiter.api.Test;
import org.opengis.feature.Feature;
import org.opengis.feature.type.FeatureType;

/**
 * Test {@link ReadGeosjonWithGeotoolsTest}.
 * 
 * @author pa5cal
 */
public class ReadGeosjonWithGeotoolsTest {

	/**
	 * Beschreibung: Liest Features aus einem Geojson.<br>
	 * Erwartetes Ergebnis: Features aus dem Geojson.
	 */
	@Test
	public void testGet() throws IOException {
		// Arrange testdata
		// Act/test
		String filename = "./src/test/resources/samplegeojson/CleanFeatureExample.geojson";
		FeatureCollection<FeatureType, Feature> features = ReadGeojsonWithGeotools.get(filename);
		// Assert
		assertEquals(4, features.size());
	}
}