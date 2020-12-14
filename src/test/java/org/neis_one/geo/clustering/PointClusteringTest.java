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
package org.neis_one.geo.clustering;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;
import org.neis_one.geo.calculation.SphericalDistanceLibrary;
import org.neis_one.geo.file.shp.ReadShapefile;
import org.neis_one.geo.geometry.factory.SimpleFeatureCollectionFactory;

/**
 * Test {@link PointClustering}.
 * 
 * @author pa5cal
 */
public class PointClusteringTest {
	/**
	 * Beschreibung: Prueft ob Cluster erzeugt werden koennen.<br>
	 * Erwartetes Ergebnis: Verschiedene Cluster.
	 */
	@Test
	public void testExecute() {
		// Arrange Testdata
		var coodinates = new Coordinate[] {
				new Coordinate(4, 4),
				new Coordinate(4.01, 4.01),
				new Coordinate(4.1, 4.1),
				new Coordinate(7.9, 7.9),
				new Coordinate(8.1, 8.1),
				new Coordinate(8, 8)
		};
		var clusterFeatures = SimpleFeatureCollectionFactory.create(coodinates);
		// Act/Test
		var result = PointClustering.execute(clusterFeatures, 0.15);
		// Assert
		assertEquals(2, result.size());
	}

	@Test
	public void testExecuteWithSignalanlagen() throws IOException {
		// Arrange Testdata
		var filename = "./src/test/resources/samplepoints/Signalanlagen.shp";
		var clusterFeatures = new ReadShapefile().get(filename).getFeatures();
		var clusterDistance = SphericalDistanceLibrary.metersToDegrees(50);
		// Act/Test
		var result = PointClustering.execute(clusterFeatures, clusterDistance);
		// Assert
		assertEquals(4, result.size());
	}
}