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