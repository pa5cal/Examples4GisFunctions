package org.neis_one.geo.filter.attribute;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.neis_one.geo.file.shp.ReadShapefile;

/**
 * Test {@link FilterFeatureCollection}.
 * 
 * @author pa5cal
 */
public class FilterFeatureCollectionTest {

	/**
	 * Beschreibung: Filtert features aus einer FeatureCollection.<br>
	 * Erwartetes Ergebnis: Gefilterte Features.
	 */
	@Test
	public void testFilterMotorway() throws IOException {
		// Arrange Testdata
		var filename = "./src/test/resources/samplelines/SampleLines.shp";
		var features = new ReadShapefile().get(filename).getFeatures();
		String attributeFilterName = "highway";
		String attributeFilterValue = "motorway";
		// Act/Test
		var subCollection = FilterFeatureCollection.filter(features, attributeFilterName, attributeFilterValue);
		// Assert
		assertEquals(7, subCollection.size());
	}

	/**
	 * Beschreibung: Filtert features aus einer FeatureCollection.<br>
	 * Erwartetes Ergebnis: Gefilterte Features.
	 */
	@Test
	public void testFilterMotorwayCaseInsensitive() throws IOException {
		// Arrange Testdata
		var filename = "./src/test/resources/samplelines/SampleLines.shp";
		var features = new ReadShapefile().get(filename).getFeatures();
		String attributeFilterName = "highway";
		String attributeFilterValue = "Motorway";
		// Act/Test
		var subCollection = FilterFeatureCollection.filter(features, attributeFilterName, attributeFilterValue);
		// Assert
		assertEquals(7, subCollection.size());
	}

	/**
	 * Beschreibung: Filtert features aus einer FeatureCollection.<br>
	 * Erwartetes Ergebnis: Sobald auf die Collection zugegriffen wird ein NP.
	 */
	@Test
	public void testFilterNll() throws IOException {
		// Arrange Testdata
		var filename = "./src/test/resources/samplelines/SampleLines.shp";
		var features = new ReadShapefile().get(filename).getFeatures();
		String attributeFilterName = "test";
		String attributeFilterValue = "test";
		// Act/Test
		var subCollection = FilterFeatureCollection.filter(features, attributeFilterName, attributeFilterValue);
		// Assert
		assertThrows(NullPointerException.class,
				() -> {
					// Expect: java.lang.NullPointerException:
					// PropertyDescriptor is null - did you request a property
					// that does not exist?
					assertEquals(0, subCollection.size());
				});
	}
}