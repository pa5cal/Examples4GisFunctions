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
package org.neis_one.geo.calculation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.geometry.jts.JTS;
import org.geotools.referencing.CRS;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.locationtech.jts.geom.Geometry;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.geometry.MismatchedDimensionException;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.TransformException;

public class Transform {

	public static CoordinateReferenceSystem getWGS84() {
		return DefaultGeographicCRS.WGS84;
	}

	public static CoordinateReferenceSystem getUTM32() {
		CoordinateReferenceSystem resut = getWGS84();
		try {
			resut = CRS.decode("EPSG:25832");
		} catch (FactoryException e) {
			System.out.println("Not found ... use default WGS84");
			e.printStackTrace();
		}
		return resut;
	}

	public static Geometry fromWgs84toUTM32(Geometry geom) {
		try {
			MathTransform transform = CRS.findMathTransform(getWGS84(), getUTM32(), true);
			geom = JTS.transform(geom, transform);
		} catch (FactoryException | MismatchedDimensionException | TransformException e) {
			e.printStackTrace();
		}
		return geom;
	}

	public static Geometry fromUTM32toWgs84(Geometry geom) {
		try {
			MathTransform transform = CRS.findMathTransform(getUTM32(), getWGS84(), true);
			geom = JTS.transform(geom, transform);
		} catch (FactoryException | MismatchedDimensionException | TransformException e) {
			e.printStackTrace();
		}
		return geom;
	}

	/**
	 * Transforms entire {@link SimpleFeatureSource} from WGS84 to UTM32.
	 */
	public static Collection<Geometry> transform(SimpleFeatureSource features) {
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
}