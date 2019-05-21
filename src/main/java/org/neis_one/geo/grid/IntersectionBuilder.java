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