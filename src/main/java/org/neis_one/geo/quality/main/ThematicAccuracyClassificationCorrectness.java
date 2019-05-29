package org.neis_one.geo.quality.main;

import java.io.IOException;

import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.data.simple.SimpleFeatureSource;
import org.locationtech.jts.geom.Geometry;
import org.neis_one.geo.calculation.Transform;
import org.neis_one.geo.file.shp.ReadShapefile;
import org.opengis.feature.simple.SimpleFeature;

/**
 * Example: Calculate ThematicAccuracy. <br>
 * "Richtigkeit der Klassifikation (classification correctness): Stimmen
 * Objekte, oder ihre Attribute mit den zugewiesenen Klassen überein, z. B.
 * Zuordnung zu Fluss, statt zu Weg" vgl.
 * https://de.wikipedia.org/wiki/Geodaten#Thematische_Genauigkeit
 * 
 * @author pa5cal
 */
public class ThematicAccuracyClassificationCorrectness {

	public static void main(String[] args) throws IOException {
		// Read Shapefiles
		final SimpleFeatureSource lineFeaturesExpected = new ReadShapefile()
				.get("./src/test/resources/samplelines/SampleLines.shp");
		final SimpleFeatureSource lineFeaturesActual = new ReadShapefile()
				.get("./src/test/resources/samplelines/SampleLinesModifiedCopy.shp");

		// Buffer, intersect and compare attributes
		SimpleFeatureIterator iter = lineFeaturesExpected.getFeatures().features();
		while (iter.hasNext()) {
			SimpleFeature feature = iter.next();
			Geometry geom = (Geometry) feature.getDefaultGeometryProperty();
			// Transform & buffer
			Geometry bufferedGeom = Transform.fromWgs84toUTM32(geom).buffer(10);
		}

		// Output result

	}
}