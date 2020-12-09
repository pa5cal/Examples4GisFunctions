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
package org.neis_one.geo.grid;

import java.io.IOException;
import java.util.Map;

import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.factory.CommonFactoryFinder;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.geotools.grid.GridElement;
import org.geotools.grid.GridFeatureBuilder;
import org.geotools.grid.PolygonElement;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.filter.Filter;
import org.opengis.filter.FilterFactory2;

/**
 * Copy of http://docs.geotools.org/latest/userguide/extension/grid.html
 */
public class IntersectionBuilder extends GridFeatureBuilder {
	private final FilterFactory2 ff = CommonFactoryFinder.getFilterFactory2();
	private final GeometryFactory gf = JTSFactoryFinder.getGeometryFactory();

	private final SimpleFeatureSource source;
	private int id = 0;

	public IntersectionBuilder(SimpleFeatureType type, SimpleFeatureSource source) {
		super(type);
		this.source = source;
	}

	@Override
	public void setAttributes(GridElement el, Map<String, Object> attributes) {
		attributes.put("id", ++id);
	}

	@Override
	public boolean getCreateFeature(GridElement el) {
		Coordinate c = ((PolygonElement) el).getCenter();
		Geometry p = gf.createPoint(c);
		Filter filter = ff.intersects(ff.property("the_geom"), ff.literal(p));
		boolean result = false;
		try {
			result = !source.getFeatures(filter).isEmpty();
		} catch (IOException ex) {
			throw new IllegalStateException(ex);
		}
		return result;
	}
}