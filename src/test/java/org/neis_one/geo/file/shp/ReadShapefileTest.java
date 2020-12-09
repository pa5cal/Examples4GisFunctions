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
package org.neis_one.geo.file.shp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.geotools.data.simple.SimpleFeatureSource;
import org.junit.jupiter.api.Test;

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
		assertEquals(6, featureSource.getFeatures().size());
	}
}