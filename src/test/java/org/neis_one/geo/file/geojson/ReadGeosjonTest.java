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
		String filename = "./src/test/resources/samplegeojson/RandomGeojsonExample.geojson";
		// Act/test
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
		String filename = "./src/test/resources/samplegeojson/OSMAmenityFuelMainz.geojson";
		// Act/test
		FeatureCollection features = ReadGeojson.get(filename);
		// Assert
		assertEquals(30, features.getFeatures().size());
	}
}