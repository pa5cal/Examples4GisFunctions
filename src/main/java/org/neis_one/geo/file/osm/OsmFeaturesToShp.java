package org.neis_one.geo.file.osm;

import java.util.Collection;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;

/**
 * Writes {@link DroneMapElement}'s to a file.
 * 
 * @author pa5cal
 */
public class OsmFeaturesToShp {

	public OsmFeaturesToShp(Collection<OsmFeature> osmFeatures) {
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

			// TODO pa5cal create feature for featurecollection
		}
	}

	public void save(String shpFilepath) {
		// TODO pa5cal save as shapefile
	}
}