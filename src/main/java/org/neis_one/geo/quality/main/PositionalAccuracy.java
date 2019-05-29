package org.neis_one.geo.quality.main;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import org.geotools.data.simple.SimpleFeatureSource;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.operation.union.UnaryUnionOp;
import org.neis_one.geo.calculation.Transform;
import org.neis_one.geo.file.shp.ReadShapefile;

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
		final Collection<Geometry> geometriesExpected = Transform.transform(lineFeaturesExpected);
		final Collection<Geometry> geometriesActual = Transform.transform(lineFeaturesActual);
		// Buffer (10m)
		final Collection<Geometry> geometriesBuffered = geometriesExpected.stream().map(g -> g.buffer(10))
				.collect(Collectors.toList());

		// Union
		UnaryUnionOp union = new UnaryUnionOp(geometriesBuffered);
		Geometry result = union.union();

		// Intersect
		final Collection<Geometry> geometriesIntersections = intersect(geometriesActual, result);

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
			final Geometry geometriesBuffered) {
		Collection<Geometry> geometriesIntersections = new ArrayList<>();
		for (Geometry geom : geometriesActual) {
			Geometry geomIntersection = geom.intersection(geometriesBuffered);
			if (!geomIntersection.isEmpty()) {
				geometriesIntersections.add(geomIntersection);
			}
		}
		return geometriesIntersections;
	}

	private static double calculateLength(final Collection<Geometry> geometries) {
		return geometries.stream().map(g -> g.getLength()).reduce(0.0, Double::sum);
	}
}