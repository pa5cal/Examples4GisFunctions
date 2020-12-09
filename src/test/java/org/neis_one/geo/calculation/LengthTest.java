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
package org.neis_one.geo.calculation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.MultiLineString;
import org.neis_one.geo.geometry.factory.LinestringFactory;

/**
 * Test {@link Length}.
 * 
 * @author pa5cal
 */
public class LengthTest {

	/**
	 * Beschreibung: Berechnet Laenge eines Linestrings.<br>
	 * Erwartetes Ergebnis: Laenge des Linestring in Meter.
	 */
	@Test
	public void testCalculate() {
		// Arrange Testdata
		LineString geom = LinestringFactory.createLinestringWgs84();
		// Act/Test
		double length = Length.calculate(geom);
		// Assert
		assertEquals(71695.22, length, 0.001);
	}

	/**
	 * Beschreibung: Berechnet Laenge eines MultiLinestrings.<br>
	 * Erwartetes Ergebnis: Laenge des MultiLinestrings in Meter.
	 */
	@Test
	public void testCalculateByMultiGeometry() {
		// Arrange Testdata
		MultiLineString geom = LinestringFactory.createMultiLinestringWgs84();
		// Act/Test
		double length = Length.calculateByMultiGeometry(geom);
		// Assert
		assertEquals(182933.900, length, 0.001);
	}

	/**
	 * Beschreibung: Berechnet Laenge eines MultiLinestrings.<br>
	 * Erwartetes Ergebnis: Laenge des MultiLinestrings in Meter.
	 */
	@Test
	public void testCalculateMultiGeometry() {
		// Arrange Testdata
		MultiLineString geom = LinestringFactory.createMultiLinestringWgs84();
		// Act/Test
		double length = Length.calculate(geom);
		// Assert
		assertEquals(182933.900, length, 0.001);
	}
}