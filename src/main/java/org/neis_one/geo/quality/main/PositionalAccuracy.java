package org.neis_one.geo.quality.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.data.simple.SimpleFeatureSource;
import org.locationtech.jts.geom.Geometry;
import org.neis_one.geo.calculation.Transform;
import org.neis_one.geo.file.shp.ReadShapefile;
import org.opengis.feature.simple.SimpleFeature;

/**
 * Example: Calculate PositionalAccuracy.<br>
 * "Positionsgenauigkeit (positional accuracy): Genauigkeit der Lage von
 * Objekten" vgl. https://de.wikipedia.org/wiki/Geodaten#Positionsgenauigkeit
 * 
 * @author pa5cal
 */
public class PositionalAccuracy {

	public static void main(String[] args) {
		// Read Shapefiles
		final SimpleFeatureSource lineFeaturesExpected = new ReadShapefile()
				.get("./src/test/resources/samplelines/SampleLines.shp");
		final SimpleFeatureSource lineFeaturesActual = new ReadShapefile()
				.get("./src/test/resources/samplelines/SampleLinesModifiedCopy.shp");

		// Transform
		final Collection<Geometry> geometriesExpected = transform(lineFeaturesExpected);
		final Collection<Geometry> geometriesActual = transform(lineFeaturesActual);
		// Buffer (10m)
		final Collection<Geometry> geometriesBuffered = geometriesExpected.stream().map(g -> g.buffer(10))
				.collect(Collectors.toList());
		// Intersect
		final Collection<Geometry> geometriesIntersections = intersect(geometriesActual, geometriesBuffered);

		// Calculate length
		double expectedLength = calculateLength(geometriesExpected);
		double actualLength = calculateLength(geometriesActual);
		double intersectionLength = calculateLength(geometriesIntersections);

		// Output result
		System.out.println("RESULT");
		System.out.println("Expected length: " + expectedLength);
		System.out.println("Actual length: " + actualLength);
		System.out.println("PositionalAccuracy length[m]: " + intersectionLength);
	}

	private static Collection<Geometry> intersect(final Collection<Geometry> geometriesActual,
			final Collection<Geometry> geometriesBuffered) {
		Collection<Geometry> geometriesIntersections = new ArrayList<>();
		for (Geometry geom : geometriesActual) {
			for (Geometry geomBuffer : geometriesBuffered) {
				Geometry geomIntersection = geom.intersection(geomBuffer);
				if (!geomIntersection.isEmpty()) {
					geometriesIntersections.add(geomIntersection);
				}
			}
		}
		return geometriesIntersections;
	}

	private static Collection<Geometry> transform(SimpleFeatureSource features) {
		Collection<Geometry> transformed = new ArrayList<>();
		try {
			SimpleFeatureIterator iter = features.getFeatures().features();
			while (iter.hasNext()) {
				SimpleFeature line = iter.next();
				transformed.add(Transform.fromWgs84toUTM32((Geometry) line.getDefaultGeometry()));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return transformed;
	}

	private static double calculateLength(final Collection<Geometry> geometries) {
		return geometries.stream().map(g -> g.getLength()).reduce(0.0, Double::sum);
	}
}