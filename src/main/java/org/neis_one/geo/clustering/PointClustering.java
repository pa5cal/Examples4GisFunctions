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
package org.neis_one.geo.clustering;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.geotools.data.simple.SimpleFeatureCollection;
import org.locationtech.jts.geom.Geometry;

/**
 * PointClustering.
 * 
 * @author pa5cal
 */
public class PointClustering {

	public static Collection<Geometry> execute(SimpleFeatureCollection clusterFeatures, double clusterDistance) {
		// Create collection
		var pointFeatures = new HashSet<Geometry>();
		var iter = clusterFeatures.features();
		while (iter.hasNext()) {
			pointFeatures.add((Geometry) iter.next().getDefaultGeometry());
		}
		// Cluster nearest points
		return cluster(pointFeatures, clusterDistance);
	}

	private static Collection<Geometry> cluster(Set<Geometry> features, double clusterDistance) {
		var clusters = new HashSet<Geometry>();
		var foundCluster = false;
		for (var feature : features) {
			// Find cluster with minimum distance
			var minDistanceCluster = clusterDistance;
			Optional<Geometry> pointCluster = Optional.empty();
			for (var cluster : clusters) {
				var distance = cluster.distance(feature);
				if (distance < minDistanceCluster) {
					pointCluster = Optional.of(cluster);
					minDistanceCluster = distance;
				}
			}
			// Cluster points
			if (pointCluster.isPresent()) {
				clusters.remove(pointCluster.get());
				clusters.add(pointCluster.get().union(feature));
				foundCluster = true;
			} else {
				clusters.add(feature);
			}
		}
		return foundCluster ? cluster(clusters, clusterDistance) : clusters;
	}
}