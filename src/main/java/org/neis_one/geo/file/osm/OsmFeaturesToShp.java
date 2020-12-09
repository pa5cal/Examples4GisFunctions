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
package org.neis_one.geo.file.osm;

import java.util.Collection;
import java.util.Optional;

import org.geotools.data.DataUtilities;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.feature.DefaultFeatureCollection;
import org.geotools.feature.SchemaException;
import org.geotools.feature.simple.SimpleFeatureBuilder;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.neis_one.geo.file.shp.WriteShapefile;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;

/**
 * Writes {@link DroneMapElement}'s to a file.
 * 
 * @author pa5cal
 */
public class OsmFeaturesToShp {

	private Optional<SimpleFeatureSource> featureSource;

	public void init(Collection<OsmFeature> osmFeatures) {

		SimpleFeatureType featureType = createFeatureType();
		DefaultFeatureCollection featureCollection = createDefaultFeatureCollection(featureType);

		for (OsmFeature osmFeature : osmFeatures) {
			if (osmFeature.osmNodes.size() < 2) {
				System.out.println("Invalid number of points in LineString (found 1 - must be 0 or >= 2) - skip");
				continue;
			}

			// Create geometry
			Coordinate[] coordinates = osmFeature.osmNodes.stream()
					.map(n -> new Coordinate(n.getLongitude(), n.getLatitude())).toArray(Coordinate[]::new);
			Geometry elementGeometry;
			if (coordinates[0].equals(coordinates[coordinates.length - 1])) {
				elementGeometry = new GeometryFactory().createPolygon(coordinates);
			} else {
				elementGeometry = new GeometryFactory().createLineString(coordinates);
			}

			// Create feature
			featureCollection.add(createFeature(featureType, osmFeature, elementGeometry));
		}

		featureSource = Optional.of(DataUtilities.source(featureCollection));
	}

	public void save(String shpFilepath) {
		featureSource.ifPresent(f -> new WriteShapefile().saveToFile(f, shpFilepath));
	}

	private SimpleFeatureType createFeatureType() {
		SimpleFeatureType featureType = null;
		try {
			featureType = DataUtilities.createType("OSMExport",
					"geom:Polygon:srid=4326,id:String,key:String,value:String");
		} catch (SchemaException e) {
			e.printStackTrace();
		}
		return featureType;
	}

	private DefaultFeatureCollection createDefaultFeatureCollection(SimpleFeatureType featureType) {
		return new DefaultFeatureCollection("internal", featureType);
	}

	private SimpleFeature createFeature(SimpleFeatureType featureType, OsmFeature osmFeature, Geometry result) {
		String id = Long.toString(osmFeature.osmElement.getId());
		return SimpleFeatureBuilder.build(featureType, new Object[] { result, id }, id);
	}
}