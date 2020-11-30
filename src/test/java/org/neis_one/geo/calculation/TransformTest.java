package org.neis_one.geo.calculation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.geotools.geometry.jts.JTSFactoryFinder;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;

/**
 * Test {@link Transform}.
 * 
 * @author pa5cal
 */
public class TransformTest {

	/**
	 * Beschreibung: Koordinatentransofrmation von WGS84 nach UTM32.<br>
	 * Erwartetes Ergebnis: Die Koordainten in UTM32.
	 */
	@Test
	public void testFromWgs84toUTM32() {
		// Tesdata
		var geometryFactory = JTSFactoryFinder.getGeometryFactory();
		var coord = new Coordinate(8.227330, 49.988380);
		var point = geometryFactory.createPoint(coord);
		// Test
		var result = Transform.fromWgs84toUTM32(point);
		// Assert
		assertEquals(444611.94, result.getCoordinate().x, 0.01);
		assertEquals(5537624.791, result.getCoordinate().y, 0.01);
	}

	/**
	 * Beschreibung: Koordinatentransofrmation von UTM32 nach WGS84.<br>
	 * Erwartetes Ergebnis: Die Koordainten in WGS84.
	 */
	@Test
	public void testFromUTM32toWgs84() {
		// Tesdata
		var geometryFactory = JTSFactoryFinder.getGeometryFactory();
		var coord = new Coordinate(444611.94, 5537624.791);
		var point = geometryFactory.createPoint(coord);
		// Test
		var result = Transform.fromUTM32toWgs84(point);
		// Assert
		assertEquals(8.227330, result.getCoordinate().x, 0.0000001);
		assertEquals(49.988380, result.getCoordinate().y, 0.0000001);
	}
}
