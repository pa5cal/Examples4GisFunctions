package org.neis_one.geo.file.geojson;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.geojson.FeatureCollection;
import org.junit.jupiter.api.Test;

/**
 * Test {@link ReadGeojson}.
 * 
 * @author pa5cal
 */
public class ReadGeosjonTest {

	/**
	 * Beschreibung: Liest Features aus einem ReadGeojson.<br>
	 * Erwartetes Ergebnis: Features aus dem ReadGeojson.
	 */
	@Test
	public void testGet() throws IOException {
		// Arrange testdata
		// Act/test
		String filename = "./src/test/resources/samplegeojson/RandomGeojsonExample.geojson";
		FeatureCollection featureSource = ReadGeojson.get(filename);
		// Assert
		assertEquals(3, featureSource.getFeatures().size());
	}
}