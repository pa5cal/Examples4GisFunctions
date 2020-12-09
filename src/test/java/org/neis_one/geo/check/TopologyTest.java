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
package org.neis_one.geo.check;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Geometry;
import org.neis_one.geo.geometry.factory.PolygonFactory;

/**
 * Test {@link Topology}.
 * 
 * @author pa5cal
 */
public class TopologyTest {

	/**
	 * Beschreibung: Prueft ob ein Polygon valide ist.<br>
	 * Erwartetes Ergebnis: Polygon ist valide.
	 */
	@Test
	public void testIsValid() {
		// Arrange Testdata
		Geometry geom = PolygonFactory.createTestObjWgs84();
		// Act/Test
		boolean isValid = Topology.isValid(geom);
		// Assert
		assertTrue(isValid);
	}

	/**
	 * Beschreibung: Prueft ob ein Polygon simple ist.<br>
	 * Erwartetes Ergebnis: Polygon ist simple.
	 */
	@Test
	public void testIsSimple() {
		// Arrange Testdata
		Geometry geom = PolygonFactory.createTestObjWgs84();
		// Act/Test
		boolean isValid = Topology.isSimple(geom);
		// Assert
		assertTrue(isValid);
	}

	/**
	 * Beschreibung: Prueft ob ein Polygon invalide ist.<br>
	 * Erwartetes Ergebnis: Polygon ist invalide.
	 */
	@Test
	public void testIsValidFalse() {
		// Arrange Testdata
		Geometry geom = PolygonFactory.createTestObjWgs84Invalid();
		// Act/Test
		boolean isValid = Topology.isValid(geom);
		// Assert
		assertFalse(isValid);
	}

	/**
	 * Beschreibung: Prueft ob ein Polygon nicht simple ist.<br>
	 * Erwartetes Ergebnis: Polygon ist nicht simple.
	 */
	@Test
	public void testIsSimpleFalse() {
		// Arrange Testdata
		Geometry geom = PolygonFactory.createTestObjWgs84Invalid();
		// Act/Test
		boolean isValid = Topology.isSimple(geom);
		// Assert
		assertFalse(isValid);
	}
}