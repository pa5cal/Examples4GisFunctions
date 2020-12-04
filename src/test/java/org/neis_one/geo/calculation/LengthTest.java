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