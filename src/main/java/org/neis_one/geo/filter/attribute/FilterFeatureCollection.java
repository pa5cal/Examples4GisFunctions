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
package org.neis_one.geo.filter.attribute;

import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.factory.CommonFactoryFinder;
import org.geotools.feature.FeatureCollection;
import org.geotools.util.factory.GeoTools;
import org.opengis.filter.Filter;
import org.opengis.filter.FilterFactory2;

/**
 * Class to filter a {@link FeatureCollection}.
 * 
 * @author pa5cal
 */
public class FilterFeatureCollection {

	/**
	 * Filter {@link SimpleFeatureCollection} by name and value of an attribute.
	 */
	public static SimpleFeatureCollection filter(SimpleFeatureCollection features, String attributeName, String attributvalue) {
		FilterFactory2 filterFactory = CommonFactoryFinder.getFilterFactory2(GeoTools.getDefaultHints());
		Filter filter = filterFactory.equal(filterFactory.property(attributeName), filterFactory.literal(attributvalue), false);
		return features.subCollection(filter);
	}
}