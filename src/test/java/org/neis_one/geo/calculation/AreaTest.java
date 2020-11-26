package org.neis_one.geo.calculation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Geometry;
import org.neis_one.geo.geometry.factory.PolygonFactory;

/**
 * Test {@link Area}.
 * 
 * @author pa5cal
 */
public class AreaTest {

	/**
	 * Beschreibung: Berechnet Flaeche eines Polygons.<br>
	 * Erwartetes Ergebnis: Flaeche des Polygons in Quadratmeter.
	 */
	@Test
	public void testCalculate() {
		// Arrange Testdata
		Geometry geom = PolygonFactory.createTestObjWgs84();
		// Act/Test
		double area = Area.calculate(geom);
		// Assert
		assertEquals(3984, area, 1);
	}
}