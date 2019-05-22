package org.neis_one.geo.file.shp;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.geotools.data.simple.SimpleFeatureSource;
import org.junit.Test;

/**
 * Test {@link ReadShapefile}.
 * 
 * @author pa5cal
 */
public class ReadShapefileTest {

	/**
	 * Beschreibung: Liest Features aus einem Shapefile.<br>
	 * Erwartetes Ergebnis: Features aus dem Shapefile.
	 */
	@Test
	public void testGet() throws IOException {
		// Arrange Testdata
		ReadShapefile reader = new ReadShapefile();
		// Act/Test
		String filename = "./src/test/resources/samplepoints/SamplePoints.shp";
		SimpleFeatureSource featureSource = reader.get(filename);
		// Assert
		assertEquals(5, featureSource.getFeatures().size());
	}
}