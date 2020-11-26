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