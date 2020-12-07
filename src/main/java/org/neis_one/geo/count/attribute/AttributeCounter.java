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