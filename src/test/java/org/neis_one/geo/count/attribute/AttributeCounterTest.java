package org.neis_one.geo.count.attribute;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.neis_one.geo.file.shp.ReadShapefile;

/**
 * Test {@link AttributeCounter}.
 * 
 * @author pa5cal
 */
public class AttributeCounterTest {

	/**
	 * Beschreibung: Count attribut values of a feature collection.<br>
	 * Erwartetes Ergebnis: ---.
	 */
	@Test
	public void testCountValuesHighway() throws IOException {
		// Arrange Testdata
		var filename = "./src/test/resources/samplelines/SampleLines.shp";
		var featureIter = new ReadShapefile().get(filename).getFeatures().features();
		String attributeName = "highway";
		// Act/Test
		var counts = AttributeCounter.countValues(featureIter, attributeName);
		// Assert
		assertEquals(1, counts.size());
	}

	/**
	 * Beschreibung: Count attribut values of a feature collection.<br>
	 * Erwartetes Ergebnis: ---.
	 */
	@Test
	public void testCountValuesUser() throws IOException {
		// Arrange Testdata
		var filename = "./src/test/resources/samplelines/SampleLines.shp";
		var featureIter = new ReadShapefile().get(filename).getFeatures().features();
		String attributeName = "user";
		// Act/Test
		var counts = AttributeCounter.countValues(featureIter, attributeName);
		// Assert
		assertEquals(4, counts.size());
	}

	/**
	 * Beschreibung: Count attributes a feature collection.<br>
	 * Erwartetes Ergebnis: ---.
	 */
	@Test
	public void testGetAttributeNames() throws IOException {
		// Arrange Testdata
		var filename = "./src/test/resources/samplelines/SampleLines.shp";
		var featureType = new ReadShapefile().get(filename).getFeatures().getSchema();
		// Act/Test
		var attributeNames = AttributeCounter.getAttributeNames(featureType);
		// Assert
		assertEquals(5, attributeNames.size());
	}
}