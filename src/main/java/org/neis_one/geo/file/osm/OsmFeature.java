package org.neis_one.geo.file.osm;

import java.util.Collection;

import org.openstreetmap.osmosis.core.domain.v0_6.Entity;
import org.openstreetmap.osmosis.core.domain.v0_6.Node;

/**
 * MapElement.
 * 
 * @author pa5cal
 */
public class OsmFeature {
	public final Entity osmElement;
	public final Collection<Node> osmNodes;

	public OsmFeature(Entity osmElement, Collection<Node> osmNodes) {
		this.osmElement = osmElement;
		this.osmNodes = osmNodes;
	}
}