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