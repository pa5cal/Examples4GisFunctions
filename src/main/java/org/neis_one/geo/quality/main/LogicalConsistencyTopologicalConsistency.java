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
package org.neis_one.geo.quality.main;

import java.io.IOException;

import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.data.simple.SimpleFeatureSource;
import org.locationtech.jts.geom.Geometry;
import org.neis_one.geo.check.Topology;
import org.neis_one.geo.file.shp.ReadShapefile;
import org.opengis.feature.simple.SimpleFeature;

/**
 * Example: Check Topological Consistency of all shapefile features. <br>
 * "Topologische Konsistenz (topological consistency): Richtigkeit der kodierten
 * topologischen Charakteristika, z. B. Nachbarschaftsbeziehungen müssen
 * erhalten bleiben." vgl.
 * https://de.wikipedia.org/wiki/Geodaten#Logische_Konsistenz
 * 
 * @author pa5cal
 */
public class LogicalConsistencyTopologicalConsistency {

	public static void main(String[] args) {
		// Read Shapefile
		SimpleFeatureSource lineFeatures = new ReadShapefile().get("./src/test/resources/samplelines/SampleLines.shp");

		// Check topology
		int validFeatures = 0;
		int features = 0;
		try {
			SimpleFeatureIterator iter = lineFeatures.getFeatures().features();
			while (iter.hasNext()) {
				features++;
				SimpleFeature line = iter.next();
				Geometry geometry = (Geometry) line.getDefaultGeometry();
				validFeatures += Topology.isValid(geometry) && Topology.isSimple(geometry) ? 1 : 0;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Output result
		System.out.println("RESULT");
		System.out.println("Features: " + features);
		System.out.println("Valid: " + validFeatures);
	}
}