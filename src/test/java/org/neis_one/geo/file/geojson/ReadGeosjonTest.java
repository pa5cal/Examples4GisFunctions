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
	 * Beschreibung: Liest Features aus einem Geojson.<br>
	 * Erwartetes Ergebnis: Features aus dem Geojson.
	 */
	@Test
	public void testGetGeojson() throws IOException {
		// Arrange testdata
		// Act/test
		String filename = "./src/test/resources/samplegeojson/RandomGeojsonExample.geojson";
		FeatureCollection features = ReadGeojson.get(filename);
		// Assert
		assertEquals(3, features.getFeatures().size());
	}

	/**
	 * Beschreibung: Liest Features aus einem Geojson mit OSM Informationen.<br>
	 * Erwartetes Ergebnis: OSM Features aus dem Geojson.
	 */
	@Test
	public void testGetOsm() throws IOException {
		// Arrange testdata
		// Act/test
		String filename = "./src/test/resources/samplegeojson/OSMAmenityFuelMainz.geojson";
		FeatureCollection features = ReadGeojson.get(filename);
		// Assert
		assertEquals(30, features.getFeatures().size());
	}
}