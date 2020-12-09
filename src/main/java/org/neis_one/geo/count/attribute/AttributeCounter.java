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
package org.neis_one.geo.count.attribute;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.feature.FeatureCollection;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.feature.type.AttributeDescriptor;

/**
 * Class to count attribute values of a {@link FeatureCollection}.
 * 
 * @author pa5cal
 */
public class AttributeCounter {

	/**
	 * Count attribute values.
	 */
	public static Map<String, Integer> countValues(SimpleFeatureIterator featureIter, String attributeName) {
		var counter = new HashMap<String, Integer>();
		while (featureIter.hasNext()) {
			var feature = featureIter.next();
			var value = String.valueOf(feature.getAttribute(attributeName));
			counter.merge(value, 1, Integer::sum);
		}
		return counter;
	}

	public static List<String> getAttributeNames(SimpleFeatureType featureType) {
		var attributeNames = new ArrayList<String>();
		for (AttributeDescriptor attDesc : featureType.getAttributeDescriptors()) {
			attributeNames.add(attDesc.getName().getLocalPart());
		}
		return attributeNames;
	}
}