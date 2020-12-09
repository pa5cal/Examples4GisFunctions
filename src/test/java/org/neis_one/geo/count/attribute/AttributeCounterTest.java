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