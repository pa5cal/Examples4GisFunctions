package org.neis_one.geo.calculation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.LineString;
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
		LineString geom = LinestringFactory.createTestObjWgs84();
		// Act/Test
		double length = Length.calculate(geom);
		// Assert
		assertEquals(71695, length, 1);
	}
}