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