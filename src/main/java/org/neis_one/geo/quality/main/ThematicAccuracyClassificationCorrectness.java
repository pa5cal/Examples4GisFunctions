package org.neis_one.geo.quality.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.data.store.ReprojectingFeatureCollection;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.operation.union.UnaryUnionOp;
import org.neis_one.geo.calculation.Transform;
import org.neis_one.geo.file.shp.ReadShapefile;

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

		// Transform Featurecollections
		final ReprojectingFeatureCollection transformedFeaturesExpected = new ReprojectingFeatureCollection(
				lineFeaturesExpected.getFeatures(), Transform.getUTM32());
		final ReprojectingFeatureCollection transformedFeatures = new ReprojectingFeatureCollection(
				lineFeaturesActual.getFeatures(), Transform.getUTM32());

		final Collection<Geometry> geometries = new ArrayList<>();
		final SimpleFeatureIterator iter = transformedFeaturesExpected.features();
		while (iter.hasNext()) {
			geometries.add((Geometry) iter.next().getDefaultGeometry());
		}

		// #TODO pa5cal filter/sort/group ny key
		Geometry union = new UnaryUnionOp(geometries).union();
		// #TODO pa5cal buffer union
		System.out.println(union);
		// #TODO pa5cal intersect?

		// Buffer, intersect and compare attributes
//		while (iter.hasNext()) {
//			SimpleFeature feature = iter.next();
//			Geometry geom = ((Geometry) feature.getDefaultGeometryProperty()).buffer(10);
//		}

		// Output result

	}
}