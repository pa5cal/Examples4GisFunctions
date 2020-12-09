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