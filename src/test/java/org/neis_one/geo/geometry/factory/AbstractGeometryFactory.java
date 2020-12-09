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
package org.neis_one.geo.geometry.factory;

import org.geotools.geometry.jts.JTSFactoryFinder;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;

/**
 * Abstract factory for creating {@link Geometry}'s.
 * 
 * @author pa5cal
 */
public abstract class AbstractGeometryFactory {
	protected static GeometryFactory getGeometryFactory() {
		return JTSFactoryFinder.getGeometryFactory();
	}

	protected static Geometry readWktGeometry(String inputWkt, Geometry outputGeometry) {
		try {
			WKTReader reader = new WKTReader(getGeometryFactory());
			outputGeometry = reader.read(inputWkt);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return outputGeometry;
	}
}
