package org.neis_one.geo.count.attribute;

import java.util.HashMap;
import java.util.Map;

import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.feature.FeatureCollection;

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
}