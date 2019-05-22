package org.neis_one.geo.grid.main;

import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.geometry.jts.ReferencedEnvelope;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.neis_one.geo.file.shp.WriteShapefile;
import org.neis_one.geo.grid.Hexagon;

public class CreateHexGridShpFileByBbox {

	public static void main(String[] args) {
		ReferencedEnvelope gridBounds = new ReferencedEnvelope(8, 9, 49.5, 50.5, DefaultGeographicCRS.WGS84);
		double sideLen = 0.1;
		SimpleFeatureSource grid = Hexagon.create(gridBounds, sideLen);
		new WriteShapefile().put("./src/main/resources/hexgrid.shp", grid);
	}
}